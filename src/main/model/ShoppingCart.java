package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;


// User's shopping cart list of items they want to purchase
public class ShoppingCart implements Writable {
    private ArrayList<Item> shoppingCartList;


    // EFFECTS: constructs a list of items added to shopping cart, initialized as empty
    public ShoppingCart() {
        this.shoppingCartList = new ArrayList<Item>();
    }


    // REQUIRES: item != null
    // MODIFIES: this
    // EFFECTS: adds item to shopping cart list
    public void addItem(Item item) {
        EventLog.getInstance().logEvent(new Event("Added " + item.getName() + " to the Shopping Cart."));
        shoppingCartList.add(item);
    }


    // REQUIRES: item != null
    // MODIFIES: this
    // EFFECTS: removes item from shopping cart list
    public void removeItem(Item item) {
        EventLog.getInstance().logEvent(new Event("Removed " + item.getName() + " from the Shopping Cart."));
        shoppingCartList.remove(item);
    }


    // MODIFIES: this
    // EFFECTS: removes all items from shopping cart list
    public void removeAllItems() {
        EventLog.getInstance().logEvent(new Event("Removed all items from the Shopping Cart."));
        while (shoppingCartList.size() > 0) {
            shoppingCartList.remove(0);
        }
    }


    // EFFECTS: returns shopping cart list
    public ArrayList<Item> getShoppingCartList() {
        return shoppingCartList;
    }


    // EFFECTS: returns number of items in this shoppingCart
    public int numItems() {
        return shoppingCartList.size();
    }


    // EFFECTS: returns total cost of all items in shopping cart list
    public Double getTotalCost() {
        Double currentAmount = 0.00;

        for (Item item : shoppingCartList) {
            currentAmount += item.getCost();
        }

        return currentAmount;
    }

    // EFFECTS: returns a JSON Object with the shoppingCartList as key, and
    //          the items in shoppingCart as value
    // CITATION: Copied from JsonSerializationDemo - WorkroomApp.java
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("shoppingCartList", shoppingCartListToJson());
        return json;
    }


    // EFFECTS: returns items in this shoppingCart as a JSON array
    // CITATION: Copied from JsonSerializationDemo - WorkroomApp.java
    private JSONArray shoppingCartListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item item : shoppingCartList) {
            jsonArray.put(item.toJson());
        }

        return jsonArray;
    }


}
