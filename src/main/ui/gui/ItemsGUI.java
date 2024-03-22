package ui.gui;

import model.Item;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class ItemsGUI extends JPanel {

    private ArrayList<Item> itemsAvailable;
    private JList itemList;
    private StoreGUI storeGUI;
    private DefaultListModel<String> model;


    public ItemsGUI(StoreGUI storeGUI) {
        this.storeGUI = storeGUI;
        initItems();

        model = new DefaultListModel<>();

        for (Item item : itemsAvailable) {
            model.addElement(item.getName());
        }

        itemList = new JList<>(model);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);


        itemList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                storeGUI.updateImagePanel();

            }
        });

        itemList.setPreferredSize(new Dimension(250, 75));

    }


    public Item itemSelected() {
        for (Item item : itemsAvailable) {
            if (item.getName().equals(itemList.getSelectedValue())) {
                return item;
            }
        }
        return null;
    }


    public void initItems() {
        Item item1 = new Item("Nordica Skis", 599.99, "./data/images/Nordica Skis.png");
        Item item2 = new Item("Burton Snowboard", 499.99, "./data/images/Burton Snowboard.png");
        Item item3 = new Item("Salomon Ski Boots", 349.99, "./data/images/Salomon Ski Boots.jpeg");
        Item item4 = new Item("K2 Snowboard Boots", 299.99, "./data/images/K2 Snowboard Boots.png");
        Item item5 = new Item("Oakley Ski Goggles", 189.99, "./data/images/Oakley Ski Goggles.png");
        Item item6 = new Item("Smith Ski Helmet", 196.99, "./data/images/Smith Ski Helmet.png");

        this.itemsAvailable = new ArrayList<Item>();

        itemsAvailable.add(item1);
        itemsAvailable.add(item2);
        itemsAvailable.add(item3);
        itemsAvailable.add(item4);
        itemsAvailable.add(item5);
        itemsAvailable.add(item6);
    }


    public JList<String> getItemList() {
        return itemList;
    }

}
