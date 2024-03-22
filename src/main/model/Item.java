package model;

import org.json.JSONObject;
import persistence.Writable;


// An item being sold in the Ski and Snowboard Store
public class Item implements Writable {
    private String name;
    private Double cost;
    private boolean isAvailable;

    private String imagePath;


    // REQUIRES: name must be non-empty, cost > 0.00
    // EFFECTS: constructs an item with name and cost and is available and imagePath
    public Item(String name, Double cost, String imagePath) {
        this.name = name;
        this.cost = cost;
        this.isAvailable = true;
        this.imagePath = imagePath;
    }


    // EFFECTS: returns name of item
    public String getName() {
        return this.name;
    }


    // EFFECTS: returns cost of item
    public Double getCost() {
        return this.cost;
    }


    // EFFECTS: returns true if item is available, false otherwise
    public Boolean getIsAvailable() {
        return this.isAvailable;
    }

    // EFFECTS: returns image path
    public String getImagePath() {
        return this.imagePath;
    }


    // MODIFIES: this
    // EFFECTS: sets availability of this item
    public void setIsAvailable(Boolean newAvailability) {
        this.isAvailable = newAvailability;
    }

    // MODIFIES: this
    // EFFECTS: sets cost of item
    //          allows store owner to change cost of item
    // TODO: future method to add to ui store app (allows store owner to change cost)
    public void setCost(Double newCost) {
        this.cost = newCost;
    }


    // EFFECTS: returns the item's name and cost in the format "name $cost"
    public String printableNameAndCost() {
        return this.name + " " + "$" + this.cost;
    }


    // EFFECTS: return true if item is available, false otherwise
    // TODO: future method to add to ui store app
    public boolean isAvailable() {
        return isAvailable;
    }


    // MODIFIES: this
    // EFFECTS: makes item not available
    // TODO: future method to add to ui store app (allows store owner to make item not available)
    public void makeItemNotAvailable() {
        isAvailable = false;
    }


    // MODIFIES: this
    // EFFECTS: makes item available
    // TODO: future method to add to ui store app (allows store owner to make item available)
    public void makeItemAvailable() {
        isAvailable = true;
    }


    // EFFECTS: returns a JSON Object with name/cost/isAvailable/imagePath as key, and
    //          the corresponding name/cost/isAvailable/imagePath as value
    // CITATION: Copied from JsonSerializationDemo - WorkroomApp.java
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("cost", cost);
        json.put("isAvailable", isAvailable);
        json.put("imagePath", imagePath);
        return json;
    }

}
