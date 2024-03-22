package gui;

import model.Item;
import model.ShoppingCart;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StoreGUI extends JFrame {

    private static final int WIDTH = 230;
    private static final int HEIGHT = 325;

    private ItemsGUI itemsGUI;
    private ShoppingCartGUI shoppingCartGUI;

    private JPanel mainPanel = new JPanel();
    private JPanel itemsPanel = new JPanel();
    private JPanel cartPanel = new JPanel();
    private JPanel controlPanel = new JPanel();
    private JPanel imagePanel = new JPanel();

    private JLabel imageAsLabel = null;

    private static final String JSON_STORE = "./data/ShoppingCart.json";
    private ShoppingCart shoppingCart;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;


    public StoreGUI() {
        super("Ski And Snowboard Store UI");
        shoppingCart = new ShoppingCart();
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        mainPanel.setLayout(new BorderLayout());

        createItemsPanel();
        createShoppingCartPanel();
        createControlPanel();
        createImagePanel();

        add(mainPanel, BorderLayout.NORTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900,450);
        setVisible(true);
    }


    private void createItemsPanel() {
        itemsPanel.setBorder(BorderFactory.createTitledBorder("Items Available"));
        itemsGUI = new ItemsGUI(this);
        JScrollPane scrollPane = new JScrollPane(itemsGUI.getItemList());
        itemsPanel.add(scrollPane);
        mainPanel.add(itemsPanel, BorderLayout.WEST);
    }


    private void createShoppingCartPanel() {
        cartPanel.setBorder(BorderFactory.createTitledBorder("Shopping Cart"));
        shoppingCartGUI = new ShoppingCartGUI(this);
        JScrollPane scrollPane = new JScrollPane(shoppingCartGUI.getCartList());
        cartPanel.add(scrollPane);
        mainPanel.add(cartPanel, BorderLayout.EAST);
    }


    private void createImagePanel() {
        imagePanel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        mainPanel.add(imagePanel, BorderLayout.CENTER);
    }

    public void updateImagePanel() {
        if (imageAsLabel != null) {
            imagePanel.remove(imageAsLabel);
        }

        Item itemSelected = itemsGUI.itemSelected();
        ImageIcon image = new ImageIcon(itemSelected.getImagePath());
        imageAsLabel = new JLabel(image);
        imagePanel.add(imageAsLabel);
        imagePanel.validate();
        imagePanel.repaint();
    }


    private void createControlPanel() {
        controlPanel.setBorder(BorderFactory.createTitledBorder("Control Panel"));

        JButton addButton = getAddButton();
        JButton removeButton = getRemoveButton();
        JButton clearAllButton = getClearAllButton();
        JButton saveButton = getSaveButton();
        JButton loadButton = getLoadButton();

        controlPanel.add(addButton);
        controlPanel.add(removeButton);
        controlPanel.add(clearAllButton);
        controlPanel.add(saveButton);
        controlPanel.add(loadButton);

        mainPanel.add(controlPanel, BorderLayout.SOUTH);
    }


    private JButton getRemoveButton() {
        JButton removeButton = new JButton("Remove Item");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item selectedItem = shoppingCartGUI.itemSelected();
                shoppingCart.removeItem(selectedItem);
                shoppingCartGUI.update();
            }
        });
        return removeButton;
    }

    private JButton getAddButton() {
        JButton addButton = new JButton("Add Item");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item selectedItem = itemsGUI.itemSelected();
                shoppingCart.addItem(selectedItem);
                shoppingCartGUI.update();
            }
        });
        return addButton;
    }

    private JButton getClearAllButton() {
        JButton clearAllButton = new JButton("Clear All");
        clearAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shoppingCart.removeAllItems();
                shoppingCartGUI.update();
            }
        });
        return clearAllButton;
    }

    private JButton getLoadButton() {
        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    shoppingCart = jsonReader.read();
                    System.out.println("Loaded Shopping Cart from " + JSON_STORE);
                    shoppingCartGUI.update();

                } catch (IOException e) {
                    System.out.println("Unable to read from file: " + JSON_STORE);
                }
            }
        });
        return loadButton;
    }

    private JButton getSaveButton() {
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(shoppingCart);
                    jsonWriter.close();
                    System.out.println("Saved Shopping Cart to " + JSON_STORE);
                } catch (FileNotFoundException e) {
                    System.out.println("Unable to save to file: " + JSON_STORE);
                }
            }
        });
        return saveButton;
    }


    public static void main(String[] args) {
        new StoreGUI();
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }


}
