package com.gildedtros.configuration;

public class ItemProcessorConfiguration {

    private Integer overrideMaxQualityValue;
    private Integer overrideMinQualityValue;

    private String itemName;
    private String processorClassName;

    public ItemProcessorConfiguration(String itemName, String processorClassName, Integer overrideMaxQualityValue, Integer overrideMinQualityValue) {
        this.overrideMaxQualityValue = overrideMaxQualityValue;
        this.overrideMinQualityValue = overrideMinQualityValue;
        this.itemName = itemName;
        this.processorClassName = processorClassName;
    }

    public Integer getOverrideMaxQualityValue() {
        return overrideMaxQualityValue;
    }

    public void setOverrideMaxQualityValue(Integer overrideMaxQualityValue) {
        this.overrideMaxQualityValue = overrideMaxQualityValue;
    }

    public Integer getOverrideMinQualityValue() {
        return overrideMinQualityValue;
    }

    public void setOverrideMinQualityValue(Integer overrideMinQualityValue) {
        this.overrideMinQualityValue = overrideMinQualityValue;
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
