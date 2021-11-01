package com.gildedtros.itemProcessor;

import com.gildedtros.Item;
import com.gildedtros.ItemType;

public class GoodWineProcessor extends ItemProcessor {

    @Override
    public void updateSellIn(Item item) {
        item.sellIn -= 1;
    }

    @Override
    public void updateQuality(Item item) {
        //todo centralize quality changing and checks
        if (isPastSellByDate(item)) {
            item.quality += 2;
        } else {
            item.quality += 1;
        }

        if(isAboveMaxQuality(item)) {
            item.quality = MAX_QUALITY_VALUE;
        }
    }

    @Override
    public ItemType getItemType() {
        return ItemType.GOOD_WINE;
    }
}
