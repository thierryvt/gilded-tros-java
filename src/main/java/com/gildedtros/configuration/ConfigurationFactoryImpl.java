package com.gildedtros.configuration;

import java.util.ArrayList;
import java.util.Collection;

public class ConfigurationFactoryImpl implements ConfigurationFactory {

    private static final String GOOD_WINE_NAME = "Good Wine";
    private static final String KEYCHAIN_ITEM_NAME = "B-DAWG Keychain";
    private static final String REFACTOR_PASS_ITEM_NAME = "Backstage passes for Re:Factor";
    private static final String HAXX_PASS_ITEM_NAME = "Backstage passes for HAXX";
    private static final String DUPLICATE_CODE_ITEM_NAME = "Duplicate Code";
    private static final String LONG_METHODS_ITEM_NAME = "Long Methods";
    private static final String UGLY_VARIABLE_NAMES_ITEM_NAME = "Ugly Variable Names";

    public Collection<ItemProcessorConfiguration> getConfigurations() {
        Collection<ItemProcessorConfiguration> configurations = new ArrayList<>();

        //ideally these would be loaded in from a config file or database
        configurations.add(new ItemProcessorConfiguration(GOOD_WINE_NAME, "com.gildedtros.itemProcessor.GoodWineProcessor", null, null));
        configurations.add(new ItemProcessorConfiguration(KEYCHAIN_ITEM_NAME, "com.gildedtros.itemProcessor.KeyChainProcessor", 80, null));
        configurations.add(new ItemProcessorConfiguration(REFACTOR_PASS_ITEM_NAME, "com.gildedtros.itemProcessor.BackStagePassProcessor", null, null));
        configurations.add(new ItemProcessorConfiguration(HAXX_PASS_ITEM_NAME, "com.gildedtros.itemProcessor.BackStagePassProcessor", null, null));
        configurations.add(new ItemProcessorConfiguration(DUPLICATE_CODE_ITEM_NAME, "com.gildedtros.itemProcessor.SmellyItemProcessor", null, null));
        configurations.add(new ItemProcessorConfiguration(LONG_METHODS_ITEM_NAME, "com.gildedtros.itemProcessor.SmellyItemProcessor", null, null));
        configurations.add(new ItemProcessorConfiguration(UGLY_VARIABLE_NAMES_ITEM_NAME, "com.gildedtros.itemProcessor.SmellyItemProcessor", null, null));

        return configurations;
    }
}
