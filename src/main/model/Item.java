package model;


// An item being sold in the Ski and Snowboard Store
public class Item {
    private String name;
    private Double cost;
    private boolean isAvailable;


    // REQUIRES: name must be non-empty, cost > 0.00
    // EFFECTS: constructs an item with name and cost and is available
    public Item(String name, Double cost) {
        this.name = name;
        this.cost = cost;
        this.isAvailable = true;
    }


    // EFFECTS: returns name of item
    public String getName() {
        return this.name;
    }


    // EFFECTS: returns cost of item
    public Double getCost() {
        return this.cost;
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
    // TODO: future method to add to ui store app (allows store owner to make item not available)
    public void makeItemAvailable() {
        isAvailable = true;
    }

}
