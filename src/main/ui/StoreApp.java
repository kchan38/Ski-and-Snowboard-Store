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


    // EFFECTS: initializes the store
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


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("view items")) {
            doViewItems();
        } else if (command.equals("view shopping cart")) {
            decideToViewShoppingCart();
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


    // EFFECTS: displays main menu of options to user
    private void displayMainMenu() {
        System.out.println("\nWhat would you like to do today?");
        System.out.println("\tType 'view items' = View List of Items Available");
        System.out.println("\tType 'view shopping cart' = View Shopping Cart");
        System.out.println("\tType 'quit' = Quit Store");
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


    // EFFECTS: returns item given item name
    private Item findItemByName(String itemName) {

        for (Item item : itemsAvailable) {
            if (itemName.toLowerCase().equals(item.getName().toLowerCase())) {
                return item;
            }
        }
        return null;
    }


    // EFFECTS: displays item that user selected
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


    // MODIFIES: this
    // EFFECTS: allows user to decide to add item to shopping cart or not
    //          will add item to shopping cart if user selects yes
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


    public void doCheckout() {
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


    // MODIFIES: this
    // EFFECTS: removes item from shopping cart list
    public void doRemoveItem(Item item) {
        shoppingCart.removeItem(item);
        displayShoppingCart();
    }


}

