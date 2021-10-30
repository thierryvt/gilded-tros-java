package com.gildedtros;

public class GoodWineProcessor extends ItemProcessor {

    @Override
    public void processSelIn(Item item) {
        item.sellIn -= 1;
    }

    @Override
    public void processQuality(Item item) {
        
    }

    @Override
    public ItemType getItemType() {
        return ItemType.GOOD_WINE;
    }
}
