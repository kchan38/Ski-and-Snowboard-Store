package ui.gui;

import model.Event;
import model.EventLog;
import model.Item;
import model.ShoppingCart;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

// Store GUI
public class StoreGUI extends JFrame implements WindowListener {

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
    private JLabel totalCostAsLabel = null;
    private JScrollPane scrollPane = null;

    private static final String JSON_STORE = "./data/ShoppingCart.json";
    private ShoppingCart shoppingCart;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;


    // EFFECTS: initializes fields and create panels for StoreGUI
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
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(1000,500);
        setVisible(true);

        addWindowListener(this);

    }


    // MODIFIES: this
    // EFFECTS: creates and adds Items Panel to Main Panel, and instantiates ItemsGUI
    private void createItemsPanel() {
        itemsPanel.setBorder(BorderFactory.createTitledBorder("Items Available"));
        itemsGUI = new ItemsGUI(this);
        JScrollPane scrollPane = new JScrollPane(itemsGUI.getItemList());
        itemsPanel.add(scrollPane);
        mainPanel.add(itemsPanel, BorderLayout.WEST);
    }


    // MODIFIES: this
    // EFFECTS: creates and adds ShoppingCart Panel and Cost Label to Main Panel, and instantiates ShoppingCartGUI
    private void createShoppingCartPanel() {
        cartPanel.setBorder(BorderFactory.createTitledBorder("Shopping Cart"));

        shoppingCartGUI = new ShoppingCartGUI(this);

        scrollPane = new JScrollPane(shoppingCartGUI.getCartList());
        cartPanel.add(scrollPane);

        totalCostAsLabel = new JLabel("Total Cost: $0.00");
        cartPanel.add(totalCostAsLabel, BorderLayout.SOUTH);

        mainPanel.add(cartPanel, BorderLayout.EAST);
    }


    // MODIFIES: this
    // EFFECTS: updates Cost Label with new total cost
    public void updateCostLabel() {
        if (totalCostAsLabel != null) {
            cartPanel.remove(totalCostAsLabel);
        }

        Double totalCostDouble = shoppingCartGUI.getTotalCost();
        String formattedCostString = new DecimalFormat("0.00").format(totalCostDouble).toString();
        String totalCostString = "Total Cost: $" + formattedCostString;

        totalCostAsLabel = new JLabel(totalCostString);
        cartPanel.add(totalCostAsLabel, BorderLayout.SOUTH);
        cartPanel.validate();
        cartPanel.repaint();
    }


    // MODIFIES: this
    // EFFECTS: creates and adds Image Panel to Main Panel
    private void createImagePanel() {
        imagePanel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        mainPanel.add(imagePanel, BorderLayout.CENTER);
    }


    // MODIFIES: this
    // EFFECTS: updates Image Panel with selected item's image
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


    // MODIFIES: this
    // EFFECTS: creates and adds Control Panel to Main Panel, adds all control buttons
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


    // MODIFIES: this
    // EFFECTS: creates and returns Remove Button, removes selected item from ShoppingCart and updates ShoppingCartGUI
    //          when button is clicked
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


    // MODIFIES: this
    // EFFECTS: creates and returns Add Button, adds selected item to ShoppingCart and updates ShoppingCartGUI when
    //          button is clicked
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


    // MODIFIES: this
    // EFFECTS: creates and returns Clear All Button, removes all items from ShoppingCart and updates ShoppingCartGUI
    //          when button is clicked
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


    // MODIFIES: this
    // EFFECTS: creates and returns Load Button, loads ShoppingCart from JSON_STORE when button is clicked
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


    // MODIFIES: this
    // EFFECTS: creates and returns Save Button, saves current ShoppingCart to JSON_STORE when button is clicked
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


    // EFFECTS: calls StoreGUI
    public static void main(String[] args) {
        new StoreGUI();
    }


    // EFFECTS: returns current ShoppingCart
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }


    // EFFECTS: calls printLog, then closes application
    @Override
    public void windowClosing(WindowEvent e) {
        printLog(EventLog.getInstance());
        System.exit(0);
    }


    // EFFECTS: prints each event from EventLog
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next);
        }
    }

    
    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
