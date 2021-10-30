package com.gildedtros;

import org.junit.jupiter.api.Test;

import static com.gildedtros.TestHelper.*;

public class GoodWineTest {

    @Test
    public void testGoodWineQualityIncreases() {
        GildedTros gildedTros = createSituation(GOOD_WINE_NAME, 10, 20);
        Item item = gildedTros.items[0];

        verifyState(10, 20, item);
        simulateDays(gildedTros, 1);
        verifyState(9, 21, item);
        simulateDays(gildedTros, 9);
        verifyState(0, 30, item);
    }

    @Test
    public void testGoodWineQualityIncreaseDoublesPastSellByDate() {
        GildedTros gildedTros = createSituation(GOOD_WINE_NAME, 10, 20);
        Item item = gildedTros.items[0];

        verifyState(10, 20, item);
        simulateDays(gildedTros, 10);
        verifyState(0, 30, item);
        simulateDays(gildedTros, 1);
        verifyState(-1, 32, item);
        simulateDays(gildedTros, 9);
        verifyState(-10, 50, item);
        simulateDays(gildedTros, 1);
        verifyState(-11, 50, item);
    }

    @Test
    public void testGoodWineMaxQuality() {
        GildedTros gildedTros = createSituation(GOOD_WINE_NAME, 10, 20);
        Item item = gildedTros.items[0];

        verifyState(10, 20, item);
        simulateDays(gildedTros, 20);
        verifyState(-10, 50, item);
        simulateDays(gildedTros, 1);
        verifyState(-11, 50, item);
    }

}
