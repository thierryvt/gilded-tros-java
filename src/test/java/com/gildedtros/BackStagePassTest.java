package com.gildedtros;

import org.junit.jupiter.api.Test;

import static com.gildedtros.TestHelper.*;

public class BackStagePassTest {

    @Test
    public void testBackStagePassMoreThan10DaysFromSellBy() {
        GildedTros gildedTros = createSituation(REFACTOR_PASS_ITEM_NAME, 15, 20);
        Item item = gildedTros.items[0];

        simulateDays(gildedTros, 5);
        verifyState(10, 25, item);
    }

    @Test
    public void testBackStagePass6To10DaysFromSellBy() {
        GildedTros gildedTros = createSituation(REFACTOR_PASS_ITEM_NAME, 15, 20);
        Item item = gildedTros.items[0];

        simulateDays(gildedTros, 5);
        verifyState(10, 25, item);
        simulateDays(gildedTros, 5);
        verifyState(5, 35, item);
    }

    @Test
    public void testBackStagePass0To5DaysFromSellBy() {
        GildedTros gildedTros = createSituation(REFACTOR_PASS_ITEM_NAME, 15, 20);
        Item item = gildedTros.items[0];

        simulateDays(gildedTros, 10);
        verifyState(5, 35, item);
        simulateDays(gildedTros, 5);
        verifyState(0, 50, item);
    }

    @Test
    public void testBackStagePassWorthlessAfterSellBy() {
        GildedTros gildedTros = createSituation(REFACTOR_PASS_ITEM_NAME, 15, 20);
        Item item = gildedTros.items[0];

        simulateDays(gildedTros, 15);
        verifyState(0, 50, item);
        simulateDays(gildedTros, 1);
        verifyState(-1, 0, item);
        simulateDays(gildedTros, 9);
        verifyState(-10, 0, item);
    }

    @Test
    public void testBackStagePassMaxQuality() {
        GildedTros gildedTros = createSituation(REFACTOR_PASS_ITEM_NAME, 16, 45);
        Item item = gildedTros.items[0];

        simulateDays(gildedTros, 6);
        verifyState(10, 50, item);
        simulateDays(gildedTros, 10);
        verifyState(0, 50, item);
        simulateDays(gildedTros, 10);
        verifyState(-10, 0, item);
    }
}
