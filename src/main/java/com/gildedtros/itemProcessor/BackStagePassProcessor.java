package com.gildedtros.itemProcessor;

import com.gildedtros.Item;
import com.gildedtros.ItemType;

public class BackStagePassProcessor extends ItemProcessor {

    private static final int BACKSTAGE_PHASE_1_THRESHOLD_DAYS = 10;
    private static final int BACKSTAGE_PHASE_2_THRESHOLD_DAYS = 5;
    private static final int BACKSTAGE_PHASE_1_QUALITY_INCREMENT = 2;
    private static final int BACKSTAGE_PHASE_2_QUALITY_INCREMENT = 3;

    @Override
    public void updateSellIn(Item item) {
        item.sellIn -= 1;
    }

    @Override
    public void updateQuality(Item item) {
        if(isPastSellByDate(item)) {
            item.quality = 0;
        } else if (!isMaxQuality(item)) {
            if (item.sellIn <= BACKSTAGE_PHASE_2_THRESHOLD_DAYS) {
                item.quality += BACKSTAGE_PHASE_2_QUALITY_INCREMENT;
            } else if (item.sellIn <= BACKSTAGE_PHASE_1_THRESHOLD_DAYS) {
                item.quality += BACKSTAGE_PHASE_1_QUALITY_INCREMENT;
            } else {
                item.quality += 1;
            }

            if(isAboveMaxQuality(item)) {
                item.quality = MAX_QUALITY_VALUE;
            }
        }
    }

    @Override
    public ItemType getItemType() {
        return ItemType.BACKSTAGE_PASS;
    }
}
