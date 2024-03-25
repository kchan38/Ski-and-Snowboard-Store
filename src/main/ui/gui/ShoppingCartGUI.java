package ui.gui;

import model.Item;
import model.ShoppingCart;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.util.ArrayList;

// ShoppingCart GUI
public class ShoppingCartGUI extends JPanel {

    private StoreGUI storeGUI;
    private JList<String> cartList;
    private DefaultListModel<String> model;
    private JLabel totalCostAsLabel = null;


    // EFFECTS: initializes fields for ShoppingCartGUI (and creates DefaultListModel and JList)
    public ShoppingCartGUI(StoreGUI storeGUI) {
        this.storeGUI = storeGUI;
        model = new DefaultListModel<>();
        cartList = new JList<>(model);
    }


    // MODIFIES: this
    // EFFECTS: clears model and adds all items from ShoppingCartList to model and updates Cost Label
    public void update() {
        ShoppingCart shoppingCart = storeGUI.getShoppingCart();
        model.clear();

        ArrayList<Item> items = shoppingCart.getShoppingCartList();

        for (Item item : items) {
            String nameAndCost = item.getName() + "  $" + item.getCost();
            model.addElement(nameAndCost);
        }

        storeGUI.updateCostLabel();
    }


    // EFFECTS: returns total cost of all items in shopping cart
    public Double getTotalCost() {
        ShoppingCart shoppingCart = storeGUI.getShoppingCart();
        return shoppingCart.getTotalCost();
    }


    // MODIFIES: this
    // EFFECTS: returns Item that is selected by user (in the cartList), or null if none selected
    public Item itemSelected() {
        ShoppingCart shoppingCart = storeGUI.getShoppingCart();
        ArrayList<Item> items = shoppingCart.getShoppingCartList();

        for (Item item : items) {
            String nameAndCost = item.getName() + "  $" + item.getCost();

            if (nameAndCost.equals(cartList.getSelectedValue())) {
                return item;
            }
        }
        return null;
    }


    // EFFECTS: returns cartList
    public JList<String> getCartList() {
        return cartList;
    }

}
