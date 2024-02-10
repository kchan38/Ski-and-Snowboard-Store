package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {
    private Item testItem;


    @BeforeEach
    void setUp() {
        testItem = new Item("Snowboard", 599.99);
    }


    @Test
    void testGetName() {
        assertEquals("Snowboard", testItem.getName());
    }


    @Test
    void testGetCost() {
        assertEquals(599.99, testItem.getCost());
    }

    @Test
    void testSetCost() {
        testItem.setCost(799.89);
        assertEquals(799.89, testItem.getCost());

        testItem.setCost(499.99);
        assertEquals(499.99, testItem.getCost());
    }


    @Test
    void testPrintableNameAndCost() {
        assertEquals("Snowboard $599.99", testItem.printableNameAndCost());
        testItem.setCost(395.97);
        assertEquals("Snowboard $395.97", testItem.printableNameAndCost());
    }
}
