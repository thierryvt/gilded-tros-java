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

    private Map<ItemType, ItemProcessor> processors;
    private Map<String, ItemType> itemNameTypeMap;

    private final ConfigurationFactory configurationFactory;

    private static final ItemProcessor DEFAULT_PROCESSOR = new NormalItemProcessor(DEFAULT_MAX_QUALITY_VALUE, DEFAULT_MIN_QUALITY_VALUE);

    public ProcessorFactoryImpl(ConfigurationFactory configurationFactory) {
        this.configurationFactory = configurationFactory;
    }

    @Override
    public void init() {
        if(processors == null) {
            processors = new HashMap<>();
        }
        if(itemNameTypeMap == null) {
            itemNameTypeMap = new HashMap<>();
        }

        Collection<ItemProcessorConfiguration> configurations = configurationFactory.getConfigurations();

        for (ItemProcessorConfiguration configuration : configurations) {
            try {
                if (!processors.containsKey(configuration.getItemName())) {
                    int maxQuality = configuration.getOverrideMaxQualityValue() == null ? DEFAULT_MAX_QUALITY_VALUE : configuration.getOverrideMaxQualityValue();
                    int minQuality = configuration.getOverrideMinQualityValue() == null ? DEFAULT_MIN_QUALITY_VALUE : configuration.getOverrideMinQualityValue();

                    Class<?> clazz = Class.forName(configuration.getProcessorClassName());
                    ItemProcessor itemProcessor = (ItemProcessor) clazz.getConstructor(int.class, int.class).newInstance(maxQuality, minQuality);

                    ItemType type = itemProcessor.getItemType();
                    itemNameTypeMap.putIfAbsent(configuration.getItemName(), type);
                    processors.put(type, itemProcessor);
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
