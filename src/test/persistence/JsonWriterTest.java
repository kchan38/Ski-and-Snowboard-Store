package persistence;

import model.Item;
import org.junit.jupiter.api.Test;
import model.ShoppingCart;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ShoppingCart sc = new ShoppingCart();
            JsonWriter writer = new JsonWriter("./data/\0invalid:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // this is what we expect
        }
    }

    @Test
    void testWriterShoppingCartEmpty() {
        try {
            ShoppingCart sc = new ShoppingCart();
            JsonWriter writer = new JsonWriter("./data/testWriterShoppingCartEmpty.json");
            writer.open();
            writer.write(sc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterShoppingCartEmpty.json");
            sc = reader.read();
            assertEquals(0, sc.numItems());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterShoppingCart3Items() {
        try {
            ShoppingCart sc = new ShoppingCart();
            sc.addItem(new Item("Rainbow Skis", 599.99));
            sc.addItem(new Item("Smith Ski Helmet", 196.99));
            sc.addItem(new Item("FeelGood Snowboard", 499.99));
            JsonWriter writer = new JsonWriter("./data/testWriterShoppingCart3Items.json");
            writer.open();
            writer.write(sc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterShoppingCart3Items.json");
            sc = reader.read();
            List<Item> items = sc.getShoppingCartList();
            assertEquals(3, items.size());
            checkItem("Rainbow Skis", 599.99, true, items.get(0));
            checkItem("Smith Ski Helmet", 196.99, true, items.get(1));
            checkItem("FeelGood Snowboard", 499.99, true, items.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
