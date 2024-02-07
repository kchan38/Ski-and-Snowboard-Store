package model;


// An item in the Ski and Snowboard Store
public class Item {
    private String name;
    private Double cost;

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

    public void printItem() {
        System.out.println(this.getName() + " " + "$" + this.getCost());
    }

}
