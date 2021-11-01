package com.gildedtros.itemProcessor;

import com.gildedtros.entity.Item;

public interface ProcessorFactory {

    void init();
    ItemProcessor getProcessorForItem(Item item);

}
