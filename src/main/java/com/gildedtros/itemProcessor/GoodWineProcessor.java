package com.gildedtros.itemProcessor;

import com.gildedtros.entity.Item;
import com.gildedtros.entity.ItemType;

public class GoodWineProcessor extends ItemProcessor {

    private static final int NORMAL_QUALITY_INCREMENT = 1;
    private static final int PAST_SELL_BY_QUALITY_INCREMENT = 2;

    @Override
    public void updateQuality(Item item) {
        if (isPastSellByDate(item)) {
            increaseQuality(item, PAST_SELL_BY_QUALITY_INCREMENT);
        } else {
            increaseQuality(item, NORMAL_QUALITY_INCREMENT);
        }
    }

    @Override
    public ItemType getItemType() {
        return ItemType.GOOD_WINE;
    }
}
