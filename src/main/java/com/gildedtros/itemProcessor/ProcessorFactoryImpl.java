package com.gildedtros.itemProcessor;

import com.gildedtros.entity.Item;
import com.gildedtros.configuration.ConfigurationFactory;
import com.gildedtros.configuration.ItemProcessorConfiguration;
import com.gildedtros.entity.ItemType;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProcessorFactoryImpl implements ProcessorFactory {
    private static final int DEFAULT_MAX_QUALITY_VALUE = 50;
    private static final int DEFAULT_MIN_QUALITY_VALUE = 0;
    private static final ItemProcessor DEFAULT_PROCESSOR = new NormalItemProcessor(DEFAULT_MAX_QUALITY_VALUE, DEFAULT_MIN_QUALITY_VALUE);

    private static ProcessorFactoryImpl instance;

    private Map<ItemType, ItemProcessor> processors;
    private Map<String, ItemType> itemNameTypeMap;
    //TODO: fix oversight.
    //TODO: If 2 items have the same item type but with different configuration (min and max) values then only the first one encountered will effectively be created and applied to all.
    //TODO: This basically makes the entire concept useless in the current implementation
    //TODO: options:
    //TODO: 1. roll back and always use the default min and max values (least nr of potential processor instances, but lose the configurability)
    //TODO: 2. change the processor map so that its key is build from a combination of the ItemType and the config parameters. This would make it harder (or at least more error prone) to expand the configurations. (medium amount of processor instances)
    //TODO: 3. Change the way processor instances are created so instead of having 1 processor instance per itemType you get 1 instance per ITEM. (potentially very large amount of processor instances)

    private final ConfigurationFactory configurationFactory;

    private ProcessorFactoryImpl(ConfigurationFactory configurationFactory) {
        this.configurationFactory = configurationFactory;
    }

    public static ProcessorFactoryImpl getInstance(ConfigurationFactory configurationFactory){
        if(instance == null) {
            instance = new ProcessorFactoryImpl(configurationFactory);
        }

        return instance;
    }

    @Override
    public void init() {
        if(processors != null) {
            System.out.println("WARN: Processor Factory already initialized.");
            return;
        }

        processors = new HashMap<>();
        itemNameTypeMap = new HashMap<>();

        Collection<ItemProcessorConfiguration> configurations = configurationFactory.getConfigurations();

        for (ItemProcessorConfiguration configuration : configurations) {
            try {
                if (!itemNameTypeMap.containsKey(configuration.getItemName())) {
                    int maxQuality = configuration.getOverrideMaxQualityValue() == null ? DEFAULT_MAX_QUALITY_VALUE : configuration.getOverrideMaxQualityValue();
                    int minQuality = configuration.getOverrideMinQualityValue() == null ? DEFAULT_MIN_QUALITY_VALUE : configuration.getOverrideMinQualityValue();

                    Class<?> clazz = Class.forName(configuration.getProcessorClassName());
                    ItemProcessor itemProcessor = (ItemProcessor) clazz.getConstructor(int.class, int.class).newInstance(maxQuality, minQuality);

                    ItemType type = itemProcessor.getItemType();
                    itemNameTypeMap.put(configuration.getItemName(), type);
                    processors.putIfAbsent(type, itemProcessor);
                }
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException ex) {
                //Should ideally be handled by a logging framework
                System.out.println("ERROR: Failed to initialize item processor for configuration: " + configuration);
                ex.printStackTrace();
            }
        }
    }

    @Override
    public ItemProcessor getProcessorForItem(Item item) {
        if(processors == null) {
            throw new IllegalStateException("Processor Factory was not initialized properly. Please call init before proceeding.");
        }

        ItemType type = itemNameTypeMap.getOrDefault(item.name, null);
        return type == null ? DEFAULT_PROCESSOR : processors.getOrDefault(type, DEFAULT_PROCESSOR);
    }
}
