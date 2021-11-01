package com.gildedtros.itemProcessor;

import com.gildedtros.Item;
import com.gildedtros.configuration.ItemProcessorConfiguration;

import java.util.Collection;

public interface ProcessorFactory {

    void init();
    ItemProcessor getProcessorForItem(Item item);

}
