package com.gildedtros.configuration;

import java.util.ArrayList;
import java.util.Collection;

public class ConfigurationFactoryImpl implements ConfigurationFactory {

    private static final String GOOD_WINE_NAME = "Good Wine";
    private static final String KEYCHAIN_ITEM_NAME = "B-DAWG Keychain";
    private static final String REFACTOR_PASS_ITEM_NAME = "Backstage passes for Re:Factor";
    private static final String HAXX_PASS_ITEM_NAME = "Backstage passes for HAXX";

    public Collection<ItemProcessorConfiguration> getConfigurations() {
        Collection<ItemProcessorConfiguration> configurations = new ArrayList<>();

        //ideally these would be loaded in from a config file or database
        configurations.add(new ItemProcessorConfiguration(GOOD_WINE_NAME, "com.gildedtros.itemProcessor.GoodWineProcessor"));
        configurations.add(new ItemProcessorConfiguration(KEYCHAIN_ITEM_NAME, "com.gildedtros.itemProcessor.KeyChainProcessor"));
        configurations.add(new ItemProcessorConfiguration(REFACTOR_PASS_ITEM_NAME, "com.gildedtros.itemProcessor.BackStagePassProcessor"));
        configurations.add(new ItemProcessorConfiguration(HAXX_PASS_ITEM_NAME, "com.gildedtros.itemProcessor.BackStagePassProcessor"));

        return configurations;
    }
}
