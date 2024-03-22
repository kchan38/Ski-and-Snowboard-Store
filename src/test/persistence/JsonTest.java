package persistence;

import model.Item;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsonTest {
    protected void checkItem(String name, Double cost, Boolean isAvailable, String imagePath, Item item) {
        assertEquals(name, item.getName());
        assertEquals(cost, item.getCost());
        assertEquals(isAvailable, item.getIsAvailable());
        assertEquals(imagePath, item.getImagePath());
    }
}
