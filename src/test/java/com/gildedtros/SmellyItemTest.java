package com.gildedtros;

import com.gildedtros.entity.Item;
import org.junit.jupiter.api.Test;

import static com.gildedtros.TestHelper.*;

public class SmellyItemTest {

    @Test
    public void testSmellyItem(){
        GildedTros gildedTros = createSituation(DUPLICATE_CODE_ITEM_NAME, 10, 20);
        Item item = gildedTros.items[0];

        verifyState(10, 20, item);
        simulateDays(gildedTros, 1);
        verifyState(9, 18, item);
        simulateDays(gildedTros, 9);
        verifyState(0, 0, item);
    }

    @Test
    public void testSmellyItemQualityReductionDoublesPastSellDate() {
        GildedTros gildedTros = createSituation(DUPLICATE_CODE_ITEM_NAME, 10, 20);
        Item item = gildedTros.items[0];

        simulateDays(gildedTros, 10);
        verifyState(0, 0, item);
        simulateDays(gildedTros, 5);
        verifyState(-5, 0, item);
    }

    @Test
    public void testSmellyItemQualityNeverNegative() {
        GildedTros gildedTros = createSituation(DUPLICATE_CODE_ITEM_NAME, 10, 20);
        Item item = gildedTros.items[0];

        simulateDays(gildedTros, 10);
        verifyState(0, 0, item);
        simulateDays(gildedTros, 5);
        verifyState(-5, 0, item);
        simulateDays(gildedTros, 1);
        verifyState(-6, 0, item);
    }
}
