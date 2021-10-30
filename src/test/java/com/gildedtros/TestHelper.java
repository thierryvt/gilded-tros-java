package com.gildedtros;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TestHelper {

    public static final String NORMAL_ITEM_NAME = "Normal Item";
    public static final String GOOD_WINE_NAME = "Good Wine";
    public static final String KEYCHAIN_ITEM_NAME = "B-DAWG Keychain";
    public static final String REFACTOR_PASS_ITEM_NAME = "Backstage passes for Re:Factor";
    public static final String HAXX_PASS_ITEM_NAME = "Backstage passes for HAXX";

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

        return new GildedTros(items);
    }
}
