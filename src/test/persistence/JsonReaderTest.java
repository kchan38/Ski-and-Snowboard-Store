package persistence;

import model.Item;
import org.junit.jupiter.api.Test;
import model.ShoppingCart;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noFileExists.json");
        try {
            ShoppingCart sc = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // this is what we expect
        }
    }

    @Test
    void testReaderShoppingCartEmpty() {
        JsonReader reader = new JsonReader("./data/testReaderShoppingCartEmpty.json");
        try {
            ShoppingCart sc = reader.read();
            assertEquals(0, sc.numItems());
        } catch (IOException e) {
            fail("Could not read from file");
        }
    }

    @Test
    void testReaderShoppingCart3Items() {
        JsonReader reader = new JsonReader("./data/testReaderShoppingCart3Items.json");
        try {
            ShoppingCart sc = reader.read();
            List<Item> items = sc.getShoppingCartList();
            assertEquals(3, items.size());
            checkItem("Rainbow Skis", 599.99, true, items.get(0));
            checkItem("Smith Ski Helmet", 196.99, true, items.get(1));
            checkItem("FeelGood Snowboard", 499.99, true, items.get(2));
        } catch (IOException e) {
            fail("Could not read from file");
        }
    }
}
