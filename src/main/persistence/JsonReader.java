package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import model.Item;
import model.ShoppingCart;
import org.json.*;


// Represents a reader that reads ShoppingCart from JSON data stored in file
// CITATION: Copied from JsonSerializationDemo - WorkroomApp.java
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ShoppingCart from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ShoppingCart read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseShoppingCart(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ShoppingCart from JSON object and returns it
    private ShoppingCart parseShoppingCart(JSONObject jsonObject) {
        ShoppingCart sc = new ShoppingCart();
        addItems(sc, jsonObject);
        return sc;
    }

    // MODIFIES: sc
    // EFFECTS: parses items from JSON object and adds them to ShoppingCart
    private void addItems(ShoppingCart sc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("shoppingCartList");
        for (Object json : jsonArray) {
            JSONObject currentJsonItem = (JSONObject) json;
            addItem(sc, currentJsonItem);
        }
    }

    // MODIFIES: sc
    // EFFECTS: parses item from JSON object and adds it to ShoppingCart
    private void addItem(ShoppingCart sc, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Double cost = jsonObject.getDouble("cost");
        Boolean isAvailable = jsonObject.getBoolean("isAvailable");
        String imagePath = jsonObject.getString("imagePath");

        Item item = new Item(name, cost, imagePath);
        item.setIsAvailable(isAvailable);
        sc.addItem(item);
    }


}
