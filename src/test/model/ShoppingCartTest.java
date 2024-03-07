package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ShoppingCartTest {
    private ShoppingCart testShoppingCart;


    @BeforeEach
    void setup() {
        testShoppingCart = new ShoppingCart();
    }


    @Test
    void testAddItem() {
        Item item1 = new Item("Pink Skis", 589.99);
        Item item2 = new Item("Blue Skis", 499.99);
        Item item3 = new Item("Red Skis", 799.99);

        testShoppingCart.addItem(item1);
        assertEquals(1, testShoppingCart.getShoppingCartList().size());
        assertEquals(item1, testShoppingCart.getShoppingCartList().get(0));

        testShoppingCart.addItem(item2);
        assertEquals(2, testShoppingCart.getShoppingCartList().size());
        assertEquals(item1, testShoppingCart.getShoppingCartList().get(0));
        assertEquals(item2, testShoppingCart.getShoppingCartList().get(1));

        testShoppingCart.addItem(item3);
        assertEquals(3, testShoppingCart.getShoppingCartList().size());
        assertEquals(item1, testShoppingCart.getShoppingCartList().get(0));
        assertEquals(item2, testShoppingCart.getShoppingCartList().get(1));
        assertEquals(item3, testShoppingCart.getShoppingCartList().get(2));
    }


    @Test
    void testRemoveItem() {
        Item item1 = new Item("Pink Skis", 589.99);
        Item item2 = new Item("Blue Skis", 499.99);
        Item item3 = new Item("Red Skis", 799.99);

        testShoppingCart.addItem(item1);
        testShoppingCart.addItem(item2);
        testShoppingCart.addItem(item3);
        assertEquals(3, testShoppingCart.getShoppingCartList().size());
        assertEquals(item1, testShoppingCart.getShoppingCartList().get(0));

        testShoppingCart.removeItem(item1);
        assertEquals(2, testShoppingCart.getShoppingCartList().size());
        assertEquals(item2, testShoppingCart.getShoppingCartList().get(0));

        testShoppingCart.removeItem(item2);
        assertEquals(1, testShoppingCart.getShoppingCartList().size());
        assertEquals(item3, testShoppingCart.getShoppingCartList().get(0));
    }


    @Test
    void testGetShoppingCartList() {
        assertFalse(null == testShoppingCart.getShoppingCartList());

        Item item1 = new Item("Pink Skis", 589.99);
        testShoppingCart.addItem(item1);
        assertEquals(item1, testShoppingCart.getShoppingCartList().get(0));

    }


    @Test
    void testGetTotalCost() {
        Item item1 = new Item("Pink Skis", 589.99);
        Item item2 = new Item("Blue Skis", 499.99);
        Item item3 = new Item("Red Skis", 799.99);

        testShoppingCart.addItem(item1);
        testShoppingCart.addItem(item2);
        testShoppingCart.addItem(item3);
        assertEquals(589.99 + 499.99 + 799.99, testShoppingCart.getTotalCost());

        testShoppingCart.removeItem(item2);
        assertEquals(589.99 + 799.99, testShoppingCart.getTotalCost());

        testShoppingCart.removeItem(item1);
        assertEquals( 799.99, testShoppingCart.getTotalCost());

        testShoppingCart.removeItem(item3);
        assertEquals( 0.0, testShoppingCart.getTotalCost());
    }


    @Test
    void testToJsonMultipleItems() {
        Item item1 = new Item("Pink Skis", 589.99);
        assertTrue(item1.getIsAvailable());
        testShoppingCart.addItem(item1);

        Item item2 = new Item("Blue Skis", 799.99);
        assertTrue(item1.getIsAvailable());
        testShoppingCart.addItem(item2);

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(item1.toJson());
        jsonArray.put(item2.toJson());

        JSONObject shoppingCartJsonObject = new JSONObject();
        shoppingCartJsonObject.put("shoppingCartList", jsonArray);

        assertEquals(shoppingCartJsonObject.toString(),testShoppingCart.toJson().toString());

    }


}
