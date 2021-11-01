package com.gildedtros.itemProcessor;

import com.gildedtros.entity.Item;
import com.gildedtros.entity.ItemType;

public abstract class ItemProcessor {

    private final int maxQuality;
    private final int minQuality;

    public ItemProcessor(int maxQuality, int minQuality) {
        this.maxQuality = maxQuality;
        this.minQuality = minQuality;
    }

    /***
     * Default implementation that always reduced the sell-in value by 1
     * @param item The item to reduce the sell-in valu eof
     */
    public void updateSellIn(Item item) {
        item.sellIn -= 1;
    }

    /***
     * Function to update the quality of the item
     * @param item The item to update the quality of
     */
    public abstract void updateQuality(Item item);

    /***
     * Returns the type of item this processor can be applied to
     * @return The item type
     */
    public abstract ItemType getItemType();

    /***
     * Increases the quality of the item by the given amount.
     * If the resulting quality exceeds the set maximum it will be set to that maximum.
     * @param item The item to increase the quality of
     * @param amount The amount to increase the quality with
     */
    protected final void increaseQuality(Item item, int amount) {
        if(!isMaxQuality(item)) {
            item.quality += amount;
        }

        if(isAboveMaxQuality(item)) {
            item.quality = maxQuality;
        }
    }

    /***
     * Decreases the quality of the given item by the given amount.
     * If the resulting quality falls below the set minimum then it will be set to that minimum.
     * @param item The item to decrease the quality of
     * @param amount The amount to decrease the quality with. (Note: value must be a positive integer)
     */
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
