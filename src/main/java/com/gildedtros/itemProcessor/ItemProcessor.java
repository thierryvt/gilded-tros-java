package com.gildedtros.itemProcessor;

import com.gildedtros.Item;
import com.gildedtros.ItemType;

public abstract class ItemProcessor {
    protected static final int MAX_QUALITY_VALUE = 50;
    protected static final int MIN_QUALITY_VALUE = 0;

    public abstract void updateSellIn(Item item);
    public abstract void updateQuality(Item item);
    public abstract ItemType getItemType();

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
