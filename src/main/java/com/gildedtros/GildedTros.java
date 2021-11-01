package com.gildedtros;

import com.gildedtros.itemProcessor.ItemProcessor;
import com.gildedtros.itemProcessor.ProcessorFactory;

class GildedTros {
    
    protected final Item[] items;

    private final ProcessorFactory processorFactory;

    public GildedTros(Item[] items, ProcessorFactory processorFactory) {
        this.items = items;
        this.processorFactory = processorFactory;
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemProcessor processor = processorFactory.getProcessorForItem(item);

            if(processor != null) {
                processor.updateSellIn(item);
                processor.updateQuality(item);
            }
        }
    }
}