package com.gildedtros;

public class ItemProcessorConfiguration {

    private String itemName;
    private String processorClassName;

    public ItemProcessorConfiguration(String itemName, String processorClassName) {
        this.itemName = itemName;
        this.processorClassName = processorClassName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getProcessorClassName() {
        return processorClassName;
    }

    public void setProcessorClassName(String processorClassName) {
        this.processorClassName = processorClassName;
    }

    @Override
    public String toString() {
        return "[" + itemName + " -- " + processorClassName + "]";
    }
}
