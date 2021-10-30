package com.gildedtros;

public class NormalItemProcessor extends ItemProcessor {

    @Override
    public void processSelIn(Item item) {
        item.sellIn -= 1;
    }

    @Override
    public void processQuality(Item item) {
        if(!hasReachedMinQuality(item)) {
            item.quality -= 1;
        }
    }

    @Override
    public ItemType getItemType() {
        return ItemType.NORMAL;
    }
}
