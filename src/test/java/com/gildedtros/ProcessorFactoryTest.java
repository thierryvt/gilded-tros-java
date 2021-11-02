package com.gildedtros;

import com.gildedtros.configuration.ConfigurationFactory;
import com.gildedtros.configuration.ItemProcessorConfiguration;
import com.gildedtros.entity.Item;
import com.gildedtros.entity.ItemType;
import com.gildedtros.itemProcessor.ItemProcessor;
import com.gildedtros.itemProcessor.ProcessorFactory;
import com.gildedtros.itemProcessor.ProcessorFactoryImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

public class ProcessorFactoryTest {

    @Test
    public void testAllClassesKnown(){
        ProcessorFactory processorFactory = ProcessorFactoryImpl.getInstance(new DummyNormalConfigurationFactory());
        runScenario(processorFactory);
    }

    @Test
    public void testInvalidConfig(){
        ProcessorFactory processorFactory = ProcessorFactoryImpl.getInstance(new DummyInvalidConfigurationFactory());
        runScenario(processorFactory);
    }

    @Test
    public void testDuplicateConfig(){
        ProcessorFactory processorFactory = ProcessorFactoryImpl.getInstance(new DummyDuplicateConfigurationFactory());
        runScenario(processorFactory);
    }

    private void runScenario(ProcessorFactory processorFactory){
        Item item = new Item(TestHelper.GOOD_WINE_NAME, 20, 20);
        ItemProcessor wineProcessor = processorFactory.getProcessorForItem(item);

        item = new Item(TestHelper.REFACTOR_PASS_ITEM_NAME, 20, 20);
        ItemProcessor passProcessor = processorFactory.getProcessorForItem(item);

        item = new Item(TestHelper.KEYCHAIN_ITEM_NAME, 20, 20);
        ItemProcessor chainProcessor = processorFactory.getProcessorForItem(item);

        item = new Item(TestHelper.LONG_METHODS_ITEM_NAME, 20, 20);
        ItemProcessor smellyProcessor = processorFactory.getProcessorForItem(item);

        item = new Item("Some unknown item", 20, 20);
        ItemProcessor normalProcessor = processorFactory.getProcessorForItem(item);

        assertEquals(ItemType.GOOD_WINE, wineProcessor.getItemType());
        assertEquals(ItemType.BACKSTAGE_PASS, passProcessor.getItemType());
        assertEquals(ItemType.KEYCHAIN, chainProcessor.getItemType());
        assertEquals(ItemType.SMELLY, smellyProcessor.getItemType());
        assertEquals(ItemType.NORMAL, normalProcessor.getItemType());
    }



    private class DummyNormalConfigurationFactory implements ConfigurationFactory {
        @Override
        public Collection<ItemProcessorConfiguration> getConfigurations() {
            Collection<ItemProcessorConfiguration> configurations = new ArrayList<>();

            configurations.add(new ItemProcessorConfiguration(TestHelper.GOOD_WINE_NAME, "com.gildedtros.itemProcessor.GoodWineProcessor"));
            configurations.add(new ItemProcessorConfiguration(TestHelper.KEYCHAIN_ITEM_NAME, "com.gildedtros.itemProcessor.KeyChainProcessor"));
            configurations.add(new ItemProcessorConfiguration(TestHelper.REFACTOR_PASS_ITEM_NAME, "com.gildedtros.itemProcessor.BackStagePassProcessor"));
            configurations.add(new ItemProcessorConfiguration(TestHelper.HAXX_PASS_ITEM_NAME, "com.gildedtros.itemProcessor.BackStagePassProcessor"));
            configurations.add(new ItemProcessorConfiguration(TestHelper.DUPLICATE_CODE_ITEM_NAME, "com.gildedtros.itemProcessor.SmellyItemProcessor"));
            configurations.add(new ItemProcessorConfiguration(TestHelper.LONG_METHODS_ITEM_NAME, "com.gildedtros.itemProcessor.SmellyItemProcessor"));
            configurations.add(new ItemProcessorConfiguration(TestHelper.UGLY_VARIABLE_NAMES_ITEM_NAME, "com.gildedtros.itemProcessor.SmellyItemProcessor"));

            return configurations;
        }
    }

    private class DummyInvalidConfigurationFactory implements ConfigurationFactory {
        @Override
        public Collection<ItemProcessorConfiguration> getConfigurations() {
            Collection<ItemProcessorConfiguration> configurations = new ArrayList<>();

            configurations.add(new ItemProcessorConfiguration(TestHelper.GOOD_WINE_NAME, "com.invalidPackage.ThisClassWontBeFound"));
            configurations.add(new ItemProcessorConfiguration(TestHelper.GOOD_WINE_NAME, "com.gildedtros.itemProcessor.GoodWineProcessor"));
            configurations.add(new ItemProcessorConfiguration(TestHelper.KEYCHAIN_ITEM_NAME, "com.gildedtros.itemProcessor.KeyChainProcessor"));
            configurations.add(new ItemProcessorConfiguration(TestHelper.REFACTOR_PASS_ITEM_NAME, "com.gildedtros.itemProcessor.BackStagePassProcessor"));
            configurations.add(new ItemProcessorConfiguration(TestHelper.HAXX_PASS_ITEM_NAME, "com.gildedtros.itemProcessor.BackStagePassProcessor"));
            configurations.add(new ItemProcessorConfiguration(TestHelper.DUPLICATE_CODE_ITEM_NAME, "com.gildedtros.itemProcessor.SmellyItemProcessor"));
            configurations.add(new ItemProcessorConfiguration(TestHelper.LONG_METHODS_ITEM_NAME, "com.gildedtros.itemProcessor.SmellyItemProcessor"));
            configurations.add(new ItemProcessorConfiguration(TestHelper.UGLY_VARIABLE_NAMES_ITEM_NAME, "com.gildedtros.itemProcessor.SmellyItemProcessor"));

            return configurations;
        }
    }

    private class DummyDuplicateConfigurationFactory implements ConfigurationFactory {
        @Override
        public Collection<ItemProcessorConfiguration> getConfigurations() {
            Collection<ItemProcessorConfiguration> configurations = new ArrayList<>();

            configurations.add(new ItemProcessorConfiguration(TestHelper.GOOD_WINE_NAME, "com.gildedtros.itemProcessor.GoodWineProcessor"));
            configurations.add(new ItemProcessorConfiguration(TestHelper.GOOD_WINE_NAME, "com.gildedtros.itemProcessor.KeyChainProcessor"));
            configurations.add(new ItemProcessorConfiguration(TestHelper.KEYCHAIN_ITEM_NAME, "com.gildedtros.itemProcessor.KeyChainProcessor"));
            configurations.add(new ItemProcessorConfiguration(TestHelper.REFACTOR_PASS_ITEM_NAME, "com.gildedtros.itemProcessor.BackStagePassProcessor"));
            configurations.add(new ItemProcessorConfiguration(TestHelper.HAXX_PASS_ITEM_NAME, "com.gildedtros.itemProcessor.BackStagePassProcessor"));
            configurations.add(new ItemProcessorConfiguration(TestHelper.DUPLICATE_CODE_ITEM_NAME, "com.gildedtros.itemProcessor.SmellyItemProcessor"));
            configurations.add(new ItemProcessorConfiguration(TestHelper.LONG_METHODS_ITEM_NAME, "com.gildedtros.itemProcessor.SmellyItemProcessor"));
            configurations.add(new ItemProcessorConfiguration(TestHelper.UGLY_VARIABLE_NAMES_ITEM_NAME, "com.gildedtros.itemProcessor.SmellyItemProcessor"));

            return configurations;
        }
    }

}
