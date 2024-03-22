package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ItemTest {
    private Item testItem;


    @BeforeEach
    void setUp() {
        testItem = new Item("Snowboard", 599.99, "./data/images/image1.png");
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
    void testGetImagePath() {
        assertEquals("./data/images/image1.png", testItem.getImagePath());
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

        Item item1 = new Item("Pink Skis", 589.99, "./data/images/image1.png");
        assertEquals("Pink Skis $589.99", item1.printableNameAndCost());
        item1.setCost(499.99);
        assertEquals("Pink Skis $499.99", item1.printableNameAndCost());

    }


    @Test
    void testIsAvailable() {
        assertTrue(testItem.isAvailable());

        testItem.makeItemNotAvailable();
        assertFalse(testItem.isAvailable());

        testItem.makeItemAvailable();
        assertTrue(testItem.isAvailable());
    }


    @Test
    void testToJsonItem() {
        assertTrue(testItem.getIsAvailable());

        JSONObject itemJsonObject = new JSONObject();
        itemJsonObject.put("name", testItem.getName());
        itemJsonObject.put("cost", testItem.getCost());
        itemJsonObject.put("isAvailable", testItem.getIsAvailable());
        itemJsonObject.put("imagePath", testItem.getImagePath());

        assertEquals(itemJsonObject.toString(),testItem.toJson().toString());

    }


}
