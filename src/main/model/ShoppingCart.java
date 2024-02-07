package model;


import java.util.ArrayList;

// User's shopping cart list of items they want to purchase
public class ShoppingCart {
    private ArrayList<Item> shoppingCartList;

    public ShoppingCart() {
        this.shoppingCartList = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        shoppingCartList.add(item);
    }

    public void removeItem(Item item) {
        shoppingCartList.remove(item);
    }

    public void getShoppingCartList() {
        // stub
    }

    public Double getTotalCost() {
        return 0.0;
    }

}
