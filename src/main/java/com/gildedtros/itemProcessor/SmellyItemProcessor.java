package com.gildedtros.itemProcessor;

import com.gildedtros.entity.Item;
import com.gildedtros.entity.ItemType;

public class SmellyItemProcessor extends ItemProcessor {

    private static final int NORMAL_QUALITY_INCREMENT = 2;
    private static final int PAST_SELL_BY_QUALITY_INCREMENT = 4;

    @Override
    public void updateQuality(Item item) {
        if(isPastSellByDate(item)) {
            decreaseQuality(item, PAST_SELL_BY_QUALITY_INCREMENT);
        } else {
            decreaseQuality(item, NORMAL_QUALITY_INCREMENT);
        }
    }

    @Override
    public ItemType getItemType() {
        return ItemType.SMELLY;
    }
}
