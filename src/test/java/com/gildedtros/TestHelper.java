package com.gildedtros;

import com.gildedtros.configuration.ConfigurationFactory;
import com.gildedtros.configuration.ConfigurationFactoryImpl;
import com.gildedtros.entity.Item;
import com.gildedtros.itemProcessor.ProcessorFactory;
import com.gildedtros.itemProcessor.ProcessorFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TestHelper {

    public static final String NORMAL_ITEM_NAME = "Normal Item";
    public static final String GOOD_WINE_NAME = "Good Wine";
    public static final String KEYCHAIN_ITEM_NAME = "B-DAWG Keychain";
    public static final String REFACTOR_PASS_ITEM_NAME = "Backstage passes for Re:Factor";
    public static final String HAXX_PASS_ITEM_NAME = "Backstage passes for HAXX";
    public static final String DUPLICATE_CODE_ITEM_NAME = "Duplicate Code";
    public static final String LONG_METHODS_ITEM_NAME = "Long Methods";
    public static final String UGLY_VARIABLE_NAMES_ITEM_NAME = "Ugly Variable Names";

    public static void verifyState(int expectedSellIn, int expectedQuality, Item item) {
        assertEquals(expectedSellIn, item.sellIn);
        assertEquals(expectedQuality, item.quality);
    }

    public static void simulateDays(GildedTros gildedTros, int nrDays) {
        for (int i = 0; i < nrDays; i++) {
            gildedTros.updateQuality();
        }
    }

    public static GildedTros createSituation(String itemName, int sellIn, int quality) {
        Item item = new Item(itemName, sellIn, quality);
        Item[] items = new Item[]{
                item
        };

        return createSituation(items);
    }

    public static GildedTros createSituation(Item[] items) {
        ConfigurationFactory configurationFactory = new ConfigurationFactoryImpl();
        ProcessorFactory processorFactory = ProcessorFactoryImpl.getInstance(configurationFactory);

        return new GildedTros(items, processorFactory);
    }
}
