package com.gildedtros.configuration;

import java.util.Collection;

public interface ConfigurationFactory {

    /***
     * Load all the configurations for the different items
     * @return The configurations
     */
    Collection<ItemProcessorConfiguration> getConfigurations();

}
