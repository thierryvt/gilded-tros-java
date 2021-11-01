package com.gildedtros.itemProcessor;

import com.gildedtros.entity.Item;
import com.gildedtros.entity.ItemType;

public abstract class ItemProcessor {

    private int maxQuality;
    private int minQuality;

    public ItemProcessor(int maxQuality, int minQuality) {
        this.maxQuality = maxQuality;
        this.minQuality = minQuality;
    }

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
            item.quality = maxQuality;
        }
    }

    protected final void decreaseQuality(Item item, int amount) {
        if(!isMinQuality(item)) {
            item.quality -= amount;
        }

        if(isBelowMinQuality(item)) {
            item.quality = minQuality;
        }
    }

    protected final boolean isMaxQuality(Item item) {
        return item.quality == maxQuality;
    }

    protected final boolean isMinQuality(Item item) {
        return item.quality == minQuality;
    }

    protected final boolean isAboveMaxQuality(Item item) {
        return item.quality > maxQuality;
    }

    protected final boolean isBelowMinQuality(Item item) {
        return item.quality < minQuality;
    }

    protected final boolean isPastSellByDate(Item item) {
        return item.sellIn < 0;
    }

    protected final void setMaxQuality(Item item) {
        item.quality = maxQuality;
    }
}
