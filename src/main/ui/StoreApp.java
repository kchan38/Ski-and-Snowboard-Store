package ui;


import model.Item;
import model.ShoppingCart;

import java.util.ArrayList;
import java.util.Scanner;

// Ski and Snowboard Store application
public class StoreApp {
    private ArrayList<Item> itemsAvailable;
    private ShoppingCart shoppingCart;
    private Scanner input;


    public StoreApp() {
        runStore();
    }


    // MODIFIES: this
    // EFFECTS: processes user input
    private void runStore() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThanks for shopping at Ski and Snowboard Store!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("view")) {
            doViewList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes items
    private void init() {
        Item item1 = new Item("Rainbow Skis", 599.99);
        Item item2 = new Item("FeelGood Snowboard", 499.99);
        Item item3 = new Item("Salomon Ski Boots", 349.99);

        this.itemsAvailable = new ArrayList<Item>();

        itemsAvailable.add(item1);
        itemsAvailable.add(item2);
        itemsAvailable.add(item3);

        this.shoppingCart = new ShoppingCart();

        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWhat would you like to do today?");
        System.out.println("\tType 'view' = View List of Items Available");
        System.out.println("\tType 'quit' = Quit Store");
    }

    public void displayItemsAvailable() {
        for (Item item : itemsAvailable) {
            item.printItem();
        }
    }


    private void doViewList() {
        System.out.println("Items Available:");
        displayItemsAvailable();

        System.out.println("Select which item you would like to view.");
        selectItem();
    }

    private void selectItem() {
        String command = null;
        command = input.next();

        for (Item item : itemsAvailable) {
            if (command.equals(item.getName())) {
                System.out.println("You've selected " + item.getName());
                addItemToShoppingCart(item);
            }
        }

    }

    private void addItemToShoppingCart(Item item) {
        System.out.println("Would you like to add this item to your shopping cart?");
        System.out.println("Type 'Yes' or 'No'");

        String command = null;
        command = input.next();

        if (command.equals("Yes")) {
            shoppingCart.addItem(item);
        }
        if (command.equals("No")) {
            displayItemsAvailable();
        }
    }

}
