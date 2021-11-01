package com.gildedtros.itemProcessor;

import com.gildedtros.entity.Item;
import com.gildedtros.entity.ItemType;

public abstract class ItemProcessor {
    protected static final int MAX_QUALITY_VALUE = 50;
    protected static final int MIN_QUALITY_VALUE = 0;

    public void updateSellIn(Item item) {
        item.sellIn -= 1;
    }

    public abstract void updateQuality(Item item);
    public abstract ItemType getItemType();

    protected final void increaseQuality(Item item, int amount) {
        if(!isMaxQuality(item)) {
            item.quality += amount;
        }

        if(isAboveMaxQuality(item)) {
            item.quality = MAX_QUALITY_VALUE;
        }
    }

    protected final void decreaseQuality(Item item, int amount) {
        if(!isMinQuality(item)) {
            item.quality -= amount;
        }

        if(isBelowMinQuality(item)) {
            item.quality = MIN_QUALITY_VALUE;
        }
    }

    protected final boolean isMaxQuality(Item item) {
        return item.quality == MAX_QUALITY_VALUE;
    }

    protected final boolean isMinQuality(Item item) {
        return item.quality == MIN_QUALITY_VALUE;
    }

    protected final boolean isAboveMaxQuality(Item item) {
        return item.quality > MAX_QUALITY_VALUE;
    }

    protected final boolean isBelowMinQuality(Item item) {
        return item.quality < MIN_QUALITY_VALUE;
    }

    protected final boolean isPastSellByDate(Item item) {
        return item.sellIn < 0;
    }
}
