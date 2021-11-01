package com.gildedtros;

import com.gildedtros.entity.Item;
import org.junit.jupiter.api.Test;

import static com.gildedtros.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NormalItemTest {

    @Test
    public void testFoo() {
        GildedTros gildedTros = createSituation("foo", 0, 0);
        Item item = gildedTros.items[0];

        gildedTros.updateQuality();
        assertEquals("foo", item.name);
    }

    @Test
    public void testNormalItem(){
        GildedTros gildedTros = createSituation(NORMAL_ITEM_NAME, 10, 20);
        Item item = gildedTros.items[0];

        verifyState(10, 20, item);
        simulateDays(gildedTros, 1);
        verifyState(9, 19, item);
        simulateDays(gildedTros, 9);
        verifyState(0, 10, item);
    }

    @Test
    public void testNormalItemQualityReductionDoublesPastSellDate() {
        GildedTros gildedTros = createSituation(NORMAL_ITEM_NAME, 10, 20);
        Item item = gildedTros.items[0];

        simulateDays(gildedTros, 10);
        verifyState(0, 10, item);
        simulateDays(gildedTros, 5);
        verifyState(-5, 0, item);
    }

    @Test
    public void testNormalItemQualityNeverNegative() {
        GildedTros gildedTros = createSituation(NORMAL_ITEM_NAME, 10, 20);
        Item item = gildedTros.items[0];

        simulateDays(gildedTros, 10);
        verifyState(0, 10, item);
        simulateDays(gildedTros, 5);
        verifyState(-5, 0, item);
        simulateDays(gildedTros, 1);
        verifyState(-6, 0, item);
    }
}
