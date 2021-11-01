package com.gildedtros.itemProcessor;

import com.gildedtros.Item;
import com.gildedtros.ItemType;

public class NormalItemProcessor extends ItemProcessor {

    @Override
    public void updateSellIn(Item item) {
        item.sellIn -= 1;
    }

    @Override
    public void updateQuality(Item item) {
        if(!isMinQuality(item) && ! isBelowMinQuality(item)) {
            item.quality -= 1;
        }

        if(isBelowMinQuality(item)) {
            item.quality = MIN_QUALITY_VALUE;
        }
    }

    @Override
    public ItemType getItemType() {
        return ItemType.NORMAL;
    }
}
