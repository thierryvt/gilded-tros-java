package com.gildedtros;

import org.junit.jupiter.api.Test;

import static com.gildedtros.TestHelper.*;

public class KeyChainTest {

    @Test
    public void testKeyChainNeverChanges() {
        GildedTros gildedTros = createSituation(KEYCHAIN_ITEM_NAME, 0, 80);
        Item item = gildedTros.items[0];

        verifyState(0, 80, item);
        simulateDays(gildedTros, 20);
        verifyState(0, 80, item);

        item.sellIn = 10;
        verifyState(10, 80, item);
        simulateDays(gildedTros, 20);
        verifyState(10, 80, item);

        item.sellIn = -10;
        verifyState(-10, 80, item);
        simulateDays(gildedTros, 20);
        verifyState(-10, 80, item);
    }

}
