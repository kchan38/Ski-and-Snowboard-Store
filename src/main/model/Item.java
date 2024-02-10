package model;


// An item in the Ski and Snowboard Store
public class Item {
    private String name;
    private Double cost;


    // REQUIRES: name must be non-empty, cost > 0.00
    // EFFECTS: constructs an item with name and cost
    public Item(String name, Double cost) {
        this.name = name;
        this.cost = cost;
    }


    public String getName() {
        return this.name;
    }


    public Double getCost() {
        return this.cost;
    }


    public void setCost(Double newCost) {
        this.cost = newCost;
    }


    // EFFECTS: returns the item's name and cost in the format "name $cost"
    public String printableNameAndCost() {
        return this.name + " " + "$" + this.cost;
    }

}
