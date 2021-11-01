package com.gildedtros.itemProcessor;

import com.gildedtros.entity.Item;

public interface ProcessorFactory {

    void init();

    /***
     * Returns the appropriate processor for the given item
     * Note: current implementation returns the processor for 'normal' items if none could be found.
     * @param item The item to get the processor for
     * @return The appropriate processor.
     */
    ItemProcessor getProcessorForItem(Item item);

}
