package com.gildedtros.itemProcessor;

import com.gildedtros.entity.Item;
import com.gildedtros.entity.ItemType;

public class KeyChainProcessor extends ItemProcessor {

    public KeyChainProcessor(int maxQuality, int minQuality) {
        super(maxQuality, minQuality);
    }

    @Override
    public void updateSellIn(Item item) {
        //do nothing
    }

    @Override
    public void updateQuality(Item item) {
        if(!isMaxQuality(item)) {
            setMaxQuality(item);
        }
    }

    @Override
    public ItemType getItemType() {
        return ItemType.KEYCHAIN;
    }
}
