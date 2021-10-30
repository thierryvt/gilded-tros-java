package com.gildedtros;

public abstract class ItemProcessor {
    protected static final int MAX_QUALITY_VALUE = 50;
    protected static final int MIN_QUALITY_VALUE = 0;

    public abstract void processSelIn(Item item);
    public abstract void processQuality(Item item);
    public abstract ItemType getItemType();

    protected boolean hasReachedMaxQuality(Item item) {
        return item.quality >= MAX_QUALITY_VALUE;
    }

    protected boolean hasReachedMinQuality(Item item) {
        return item.quality <= MIN_QUALITY_VALUE;
    }

    protected boolean HasPassedSellByDate(Item item) {
        return item.sellIn < 0;
    }
}
