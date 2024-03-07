package ui;

import model.Item;
import model.ShoppingCart;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


// Ski and Snowboard Store application
public class StoreApp {
    private ArrayList<Item> itemsAvailable;
    private ShoppingCart shoppingCart;
    private Scanner input;

    private static final String JSON_STORE = "./data/ShoppingCart.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: initializes the store
    public StoreApp() {
        input = new Scanner(System.in);
        shoppingCart = new ShoppingCart();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runStore();
    }


    // MODIFIES: this
    // EFFECTS: processes user input
    private void runStore() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMainMenu();
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


    // EFFECTS: displays main menu of options to user
    private void displayMainMenu() {
        System.out.println("\nWhat would you like to do today?");
        System.out.println("\tType 'items' = View List of Items Available");
        System.out.println("\tType 'cart' = View Shopping Cart");
        System.out.println("\tType 'save' = Save Shopping Cart to File");
        System.out.println("\tType 'load' = Load Shopping Cart from File");
        System.out.println("\tType 'quit' = Quit Store");
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("items")) {
            doViewItems();
        } else if (command.equals("cart")) {
            decideToViewShoppingCart();
        } else if (command.equals("save")) {
            saveShoppingCart();
        } else if (command.equals("load")) {
            loadShoppingCart();
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
        Item item4 = new Item("K2 Snowboard Boots", 299.99);
        Item item5 = new Item("Oakley Ski Goggles", 189.99);
        Item item6 = new Item("Smith Ski Helmet", 196.99);

        this.itemsAvailable = new ArrayList<Item>();

        itemsAvailable.add(item1);
        itemsAvailable.add(item2);
        itemsAvailable.add(item3);
        itemsAvailable.add(item4);
        itemsAvailable.add(item5);
        itemsAvailable.add(item6);

        this.shoppingCart = new ShoppingCart();

        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }


    // EFFECTS: displays items available to user
    public void displayItemsAvailable() {
        for (Item item : itemsAvailable) {
            System.out.println(item.printableNameAndCost());
        }
    }


    // EFFECTS: prompts user to select item they would like to view
    private void doViewItems() {
        System.out.println("Items Available:");
        displayItemsAvailable();

        System.out.println("Type the name of the item you'd like to view.");
        selectItem();
    }


    // EFFECTS: returns item given item name, null if no match
    private Item findItemByName(String itemName) {

        for (Item item : itemsAvailable) {
            if (itemName.toLowerCase().equals(item.getName().toLowerCase())) {
                return item;
            }
        }
        return null;
    }


    // EFFECTS: displays item that user selected. if null, will prompt user to try again.
    //          if selected, will ask user if they want to add item to cart.
    private void selectItem() {
        String command = null;
        command = input.next();

        Item foundItem = findItemByName(command);

        if (null != foundItem) {
            System.out.println("You've selected " + foundItem.getName());
            decideToAddItem(foundItem);
        } else {
            System.out.println("Selection not valid... Please try again.");
            selectItem();
        }
    }


    // MODIFIES: shoppingCart
    // EFFECTS: allows user to decide to add item to shopping cart or not.
    //          adds item to shopping cart if user types yes.
    //          then allows user to decide to view shopping cart or not.
    private void decideToAddItem(Item item) {
        System.out.println("Would you like to add this item to your shopping cart?");
        System.out.println("Type 'Yes' or 'No'");

        String command = null;
        command = input.next();

        if (command.toLowerCase().equals("yes")) {
            shoppingCart.addItem(item);
            System.out.println("You've successfully added " + item.getName() + " to your shopping cart!");
            decideToViewShoppingCart();
        } else if (command.toLowerCase().equals("no")) {
            System.out.println("You chose not to add item to your shopping cart.");
        } else {
            System.out.println("Your input is invalid... Please try again.");
            decideToAddItem(item);
        }
    }


    // EFFECTS: allows user to decide to view shopping cart or not
    private void decideToViewShoppingCart() {
        System.out.println("Would you like to see your shopping cart?");
        System.out.println("Type 'Yes' or 'No'");

        String command = null;
        command = input.next();

        if (command.toLowerCase().equals("yes")) {
            displayShoppingCart();
        } else if (command.toLowerCase().equals("no")) {
            return;
        } else {
            System.out.println("Your input is invalid... Please try again.");
            decideToViewShoppingCart();
        }
    }


    // EFFECTS: displays all items and total cost in user's shopping cart
    //          then displays shopping cart menu
    private void displayShoppingCart() {
        System.out.println("Shopping Cart: ");

        for (Item item : shoppingCart.getShoppingCartList()) {
            System.out.println(item.getName() + " $" + item.getCost());
        }
        System.out.println("Total Cost: $" + shoppingCart.getTotalCost());
        displayShoppingCartMenu();
    }


    // EFFECTS: displays shopping cart menu
    //          allows user to select check out, remove item, or return to main menu
    private void displayShoppingCartMenu() {
        System.out.println("What you would like to do next?");
        System.out.println("Type 'checkout' = Checkout your current shopping cart");
        System.out.println("Type 'remove' = Remove an item from your cart");
        System.out.println("Type 'main menu' = Return to main menu");

        String command = null;
        command = input.next();

        if (command.toLowerCase().equals("checkout")) {
            doCheckout();
        } else if (command.toLowerCase().equals("remove")) {
            selectItemToRemove();
        } else if (command.toLowerCase().equals("main menu")) {
            return;
        } else {
            System.out.println("Your input is invalid... Please try again.");
            displayShoppingCartMenu();
        }
    }


    // EFFECTS: checkout items in shopping cart
    public void doCheckout() {
        // stub
        // TODO: will implement this method later on
    }


    // REQUIRES: selected item must be in shopping cart list
    // EFFECTS: allows user to select item to remove from shopping cart
    public void selectItemToRemove() {
        System.out.println("Please type which item you would like to remove");
        String command = null;
        command = input.next();

        Item foundItem = findItemByName(command);

        if (shoppingCart.getShoppingCartList().contains(foundItem)) {
            System.out.println("You've removed " + foundItem.getName());
            doRemoveItem(foundItem);
        } else {
            System.out.println("Selection not valid... Please try again.");
            selectItemToRemove();
        }
    }


    // MODIFIES: shoppingCart
    // EFFECTS: removes item from shopping cart list
    public void doRemoveItem(Item item) {
        shoppingCart.removeItem(item);
        displayShoppingCart();
    }


    // EFFECTS: saves the shoppingCart to file
    // CITATION: Copied from JsonSerializationDemo - WorkroomApp.java
    private void saveShoppingCart() {
        try {
            jsonWriter.open();
            jsonWriter.write(shoppingCart);
            jsonWriter.close();
            System.out.println("Saved Shopping Cart to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to file: " + JSON_STORE);
        }
    }


    // MODIFIES: this
    // EFFECTS: loads shoppingCart from file
    // CITATION: Copied from JsonSerializationDemo - WorkroomApp.java
    private void loadShoppingCart() {
        try {
            shoppingCart = jsonReader.read();
            System.out.println("Loaded Shopping Cart from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}

