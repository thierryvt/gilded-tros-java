package com.gildedtros;

class GildedTros {

    public static final String GOOD_WINE_NAME = "Good Wine";
    public static final String KEYCHAIN_ITEM_NAME = "B-DAWG Keychain";
    public static final String REFACTOR_PASS_ITEM_NAME = "Backstage passes for Re:Factor";
    public static final String HAXX_PASS_ITEM_NAME = "Backstage passes for HAXX";

    private final int MAX_QUALITY_VALUE = 50;
    private final int MIN_QUALITY_VALUE = 0;

    private final int DEFAULT_QUALITY_INCREMENT = 1;
    private final int DEFAULT_SELL_IN_INCREMENT = 1;

    private final int BACKSTAGE_PHASE_1_THRESHOLD_DAYS = 10;
    private final int BACKSTAGE_PHASE_2_THRESHOLD_DAYS = 5;
    private final int BACKSTAGE_PHASE_1_QUALITY_INCREMENT = 2;
    private final int BACKSTAGE_PHASE_2_QUALITY_INCREMENT = 3;
    
    protected final Item[] items;

    public GildedTros(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {

            processQuality(item);

            reduceSellIn(item, DEFAULT_SELL_IN_INCREMENT);

            checkPastSellByDate(item);
        }
    }

    private void processQuality(Item item) {
        if (item.name.equals(GOOD_WINE_NAME)
                || item.name.equals(REFACTOR_PASS_ITEM_NAME)
                || item.name.equals(HAXX_PASS_ITEM_NAME)) {
            if (item.quality < MAX_QUALITY_VALUE) {
                if (item.name.equals(REFACTOR_PASS_ITEM_NAME) || item.name.equals(HAXX_PASS_ITEM_NAME)) {
                    if (item.sellIn <= BACKSTAGE_PHASE_2_THRESHOLD_DAYS) {
                        //sell by is <= 5 days, value increases by 3 per day
                        item.quality += BACKSTAGE_PHASE_2_QUALITY_INCREMENT;
                    } else if (item.sellIn <= BACKSTAGE_PHASE_1_THRESHOLD_DAYS) {
                        //sell by date is <= 10 days, value increases by 2 per day
                        item.quality += BACKSTAGE_PHASE_1_QUALITY_INCREMENT;
                    } else {
                        item.quality += DEFAULT_QUALITY_INCREMENT;
                    }
                } else {
                    item.quality += DEFAULT_QUALITY_INCREMENT;
                }
            }

            if (item.quality > MAX_QUALITY_VALUE) {
                item.quality = MAX_QUALITY_VALUE;
            }
        } else if (!item.name.equals(KEYCHAIN_ITEM_NAME) && item.quality > MIN_QUALITY_VALUE) {
            item.quality -= DEFAULT_QUALITY_INCREMENT;
        }
    }

    private void reduceSellIn(Item item, int amount) {
        //keychains dont have a sell-by date
        if (!item.name.equals(KEYCHAIN_ITEM_NAME)) {
            item.sellIn -= amount;
        }
    }

    private void checkPastSellByDate(Item item) {
        if (isPastSellBy(item)) {
            if (item.name.equals(GOOD_WINE_NAME)) {
                //good wine past sell by date increases in quality twice as fast
                if (item.quality < MAX_QUALITY_VALUE) {
                    item.quality += DEFAULT_QUALITY_INCREMENT;
                }
            } else {
                if (item.name.equals(REFACTOR_PASS_ITEM_NAME) || item.name.equals(HAXX_PASS_ITEM_NAME)) {
                    //backstage pass
                    item.quality = 0;
                } else if (!item.name.equals(KEYCHAIN_ITEM_NAME) && item.quality > MIN_QUALITY_VALUE) {
                    //normal item
                    //overdue items lose quality twice as fast (quality has already been reduced once on line 27 so only reduce by 1 here)
                    item.quality -= DEFAULT_QUALITY_INCREMENT;
                }
            }
        }
    }

    private boolean isPastSellBy(Item item) {
        return item.sellIn < 0;
    }
}