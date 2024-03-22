package gui;

import model.Item;
import model.ShoppingCart;

import javax.swing.*;
import java.util.ArrayList;

public class ShoppingCartGUI extends JPanel {

    private StoreGUI storeGUI;
    private JList<String> cartList;
    private DefaultListModel<String> model;

    public ShoppingCartGUI(StoreGUI storeGUI) {
        this.storeGUI = storeGUI;
        model = new DefaultListModel<>();
        cartList = new JList<>(model);

    }

    public JList<String> getCartList() {
        return cartList;
    }

    public void update() {
        ShoppingCart shoppingCart = storeGUI.getShoppingCart();
        model.clear();

        ArrayList<Item> items = shoppingCart.getShoppingCartList();

        for (Item item : items) {
            model.addElement(item.getName());
        }
    }

    public Item itemSelected() {
        ShoppingCart shoppingCart = storeGUI.getShoppingCart();
        ArrayList<Item> items = shoppingCart.getShoppingCartList();

        for (Item item : items) {
            if (item.getName().equals(cartList.getSelectedValue())) {
                return item;
            }
        }
        return null;
    }



}
