package com.gildedtros;

class GildedTros {
    Item[] items;

    public GildedTros(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        //ew no getters or setters being used anywhere
        //todo: solve all the magic strings
        //todo: improve readability
        for (int i = 0; i < items.length; i++) {

            //good wine increases in value
            //backstage passes have special logic
            if (!items[i].name.equals("Good Wine")
                    && !items[i].name.equals("Backstage passes for Re:Factor")
                    && !items[i].name.equals("Backstage passes for HAXX"))
            {
                //normal item or a keychain, reduce quality
                //quality can not be negative
                if (items[i].quality > 0) {
                    //keychains never decrease in quality.
                    if (!items[i].name.equals("B-DAWG Keychain")) {
                        items[i].quality = items[i].quality - 1;
                    }
                }
            } else {
                //item is good wine or a backstage pass
                //good wine is ignored here though?
                //quality can never exceed 50
                if (items[i].quality < 50) {
                    //hard to read code: how much quality is added in any scenario is not easy to deduce
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals("Backstage passes for Re:Factor") || items[i].name.equals("Backstage passes for HAXX") ) {
                        //confusing if-statement structures, both get hit if sell by is <= 5
                        //sell by date is <= 10 days, value increases by 2 per day
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        //sell by is <= 5 days, value increases by 3 per day
                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            //keychains dont have a sell-by date
            if (!items[i].name.equals("B-DAWG Keychain")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Good Wine")) {
                    if (!items[i].name.equals("Backstage passes for Re:Factor") && !items[i].name.equals("Backstage passes for HAXX")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("B-DAWG Keychain")) {
                                //normal item
                                //overdue items lose quality twice as fast (quality has already been reduced once on line 27 so only reduce by 1 here)
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        //backstage pass
                        //overly complicated way to set this to 0?
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    //good wine
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }
}