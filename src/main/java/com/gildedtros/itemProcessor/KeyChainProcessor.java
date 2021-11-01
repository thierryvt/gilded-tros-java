package com.gildedtros.itemProcessor;

import com.gildedtros.entity.Item;
import com.gildedtros.entity.ItemType;

public class KeyChainProcessor extends ItemProcessor {

    @Override
    public void updateSellIn(Item item) {
        //do nothing
    }

    @Override
    public void updateQuality(Item item) {
        //todo magic number
        if(item.quality != 80) {
            //todo log
            item.quality = 80;
        }
    }

    @Override
    public ItemType getItemType() {
        return ItemType.KEYCHAIN;
    }
}
