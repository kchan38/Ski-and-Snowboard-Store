
# Ski and Snowboard Store
<br>

## Project Description
<br>

**What will this application do?**

This application will be an online store that sells ski and snowboard gear. It will allow users to look through
different items to buy, choosing different sizes and colours, and adding it to their shopping cart to purchase. The
types of items this online store will sell ranges from clothing, to skis and snowboards, to accessories needed while 
on the mountain (ie. helmets, gloves, goggles).

<br>

**Who will use it?**

Customers who are looking to buy ski and snowboard gear.

<br>

**Why is this project interesting to me?**

I grew up skiing at a young age with my family, and loved it all these years. The soft snow, fresh air, and excitement
I get from being on the mountain is unmatched. To try something new, I recently learned to snowboard and even bought 
new snowboarding gear. I love the ease and efficiency of online shopping, so that's why I combined both these things
to build an online store. 


<br>

## User Stories 
<br>

- As a user, I want to be able to view all the items available for purchase.
- As a user, I want to be able to add items to my shopping cart.
- As a user, I want to be able to remove items from my shopping cart.
- As a user, I want to be able to view a list of all the items I've added to my shopping cart. 
- As a user, I want to be able to get the total cost of my shopping cart.
- As a user, I want to have the option to save my shopping cart list to file (from the main menu).
- As a user, I want to have the option to load my shopping cart list from file when I start the application.


<br>

## Instructions for Grader
<br>

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by selecting an item
in the "Items Available" panel, then clicking the "Add Item" button in the "Control Panel." 


- You can generate the second required action related to the user story "removing an X from a Y" by selecting an item
in the "Shopping Cart" panel, then clicking the "Remove Item" button in the "Control Panel."


- You can locate my visual component by selecting an item in the "Items Available" panel. It will display an image
of the item that has been selected. 


- You can save the state of my application by clicking the "Save" button in the "Control Panel." 


- You can reload the state of my application by clicking the "Load" button in the "Control Panel." 



<br>

## Phase 4: Task 2
<br>

Tue Apr 02 17:33:12 PDT 2024
Added Salomon Ski Boots to the Shopping Cart.

Tue Apr 02 17:33:13 PDT 2024
Added Oakley Ski Goggles to the Shopping Cart.

Tue Apr 02 17:33:17 PDT 2024
Added K2 Snowboard Boots to the Shopping Cart.

Tue Apr 02 17:33:17 PDT 2024
Added K2 Snowboard Boots to the Shopping Cart.

Tue Apr 02 17:33:20 PDT 2024
Removed K2 Snowboard Boots from the Shopping Cart.

Tue Apr 02 17:33:25 PDT 2024
Removed all items from the Shopping Cart.


<br>

## Phase 4: Task 3
<br>
The first refactoring I would perform if I had more time on this project would be using the Singleton Pattern. I would 
use Singleton on the JsonReader and JsonWriter classes. Singleton Pattern is important for ensuring a class only has 
one instance and to provide a global point of access to it. I would want to only get a single instance of JsonReader 
and JsonWriter in the other classes, instead of allowing this to be instantiated multiple times around the application.
Currently, both JsonReader and JsonWriter are instantiated in the StoreApp and the StoreGUI. Another place I would want
to refactor is using the Singleton Pattern in the ShoppingCart class. ShoppingCart is being instantiated in 3 different
classes currently, the StoreApp, the StoreGUI, and the JsonReader classes. It would be ideal to only have one single
point of access and one instance of this ShoppingCart object. 

<br>
<br>

The next refactoring I would perform on this application in the future would be to move some of the methods regarding the
Control Panel in the StoreGUI into a new class. This would be related to the Single Responsibility Principle as I think
many of the methods related to the buttons in the Control Panel are adding extra functionality to the StoreGUI class
and giving the StoreGUI class an extra responsibility that can be split into two separate classes. Therefore, I would
make a new class called ControlPanelGUI that would be instantiated in the method called createControlPanel in the
StoreGUI. All the functionality related to making the “Add”, “Remove”, “Clear All”, “Load”, and “Save” buttons would be
moved into the ControlPanelGUI class. This ensures that the creation of the buttons and the listener for the buttons
would all be done in a different class, away from the StoreGUI class so that the StoreGUI class does not need to take
on that additional responsibility. In addition, to further refactor the application, I would add the Observer Pattern
for the GUI. Currently, there is a bidirectional relationship in my UML diagram between StoreGUI and ItemsGUI, and
StoreGUI and ShoppingCartGUI. Once I add the ControlPanelGUI, there would be an additional bidirectional relationship
between ControlPanelGUI and StoreGUI. Refactoring using the Observer Pattern can allow for less coupling. In this case,
the ControlPanelGUI (which would have all the button listeners) will be the ConcreteSubject, and whenever the button is
pressed, it will call the method notifyObservers. The Abstract Subject class will have the implementation for
notifyObservers and call an update method. The update method will be in the Observer Interface, and be implemented
in all the ConcreteObservers which would include the ShoppingCartGUI and ItemsGUI classes in my current application.
Therefore, the ShoppingCartGUI and ItemsGUI will be updated to display the new changes that the user selects (either
adding an item, removing an item, clearing all items, saving and loading). This will reduce the coupling between all
the classes in the GUI and allow for all dependents to be notified when one object changes.






<br>

## Notes/Citations
<br>

- Learned JSwing from https://www.youtube.com/watch?v=5o3fMLPY7qY&ab_channel=AlexLee

- Learned JPanel/JFrame from https://www.youtube.com/watch?v=E6aomMUyMUU&t=460s&ab_channel=TheBlindProgrammer

