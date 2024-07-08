import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class EcommerceApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventoryManager inventoryManager = InventoryManager.readFromFile();
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        ShoppingCart shoppingCart = new ShoppingCart();

//        greets the user
        System.out.println();
        System.out.println("Welcome to the E-commerce Console Application");
        System.out.print("-".repeat(20));

//        displays main menu and sets counter for item IDs
        mainMenu();
        long itemID = 0;
        long orderID = 1;

//        main loop of the program
        while (true) {
            System.out.println();
            System.out.print("What would you like to do?: ");
            String responseTask = scanner.nextLine().toLowerCase();
            System.out.println();
            if (!validCommands().contains(responseTask)) {
                System.out.println("Please type a valid command");
                continue;
            }

//            determine which action should be taken based on the user's input
            switch (responseTask) {
                case "add":
//                    creates a new item based on the user's input and adds it to the inventory
                    inventoryManager.addItem(inventoryManager.createItem(inventoryManager.getLastID() + itemID++));
                    break;
                case "list":
//                    lists each item by category and displays the item's specifications
                    System.out.printf("Inventory Manager Balance: $%.2f%n", inventoryManager.getBalance());
                    inventoryManager.listItems();
                    break;
                case "categorize":
//                    lists each category and the name of each item in it
                    inventoryManager.categorizeItems();
                    break;
                case "order":
//                    prompts the user to make an order and adds it to the shopping cart if the info is correct
                    processOrder(scanner, inventoryManager, shoppingCart, orderID++);
                    break;
                case "shopping cart":
//                    shopping cart menu & functionality
                    ShoppingCart.shoppingCartMenu();
                    manageShoppingCart(scanner, paymentProcessor, inventoryManager, shoppingCart);
                    break;
                case "remove":
//                    removes an item from the inventory if such an item is found
                    System.out.println("Please enter the id of the item you wish to remove from the inventory");
                    inventoryManager.removeItemByIdIfExists(Long.parseLong(scanner.nextLine().trim()));
                    break;
                case "menu":
                    mainMenu();
                    break;
                case "reset":
//                    clears the contents of the inventory by replacing it with an empty one
                    inventoryManager = new InventoryManager();
                    itemID = 0;
                    break;
                case "exit":
//                    writes the inventory to a file and makes sure it remembers the last assigned id to an item
                    inventoryManager.setLastID(itemID);
                    inventoryManager.writeToFile();
                    System.exit(0);
            }
        }
    }

    //    list of valid commands that the user can input
    public static ArrayList<String> validCommands() {
        ArrayList<String> commands = new ArrayList<>();
        Collections.addAll(commands, "add", "list", "categorize", "order", "menu", "remove", "reset", "exit", "shopping cart");
        return commands;
    }

    //    populates the list of valid commands for the main menu
    public static ArrayList<String> validCartCommands() {
        ArrayList<String> commands = new ArrayList<>();
        Collections.addAll(commands, "list", "purchase", "remove", "empty", "menu", "exit");
        return commands;
    }

    //    displays the main menu of the program
    public static void mainMenu() {
        System.out.println("This is the program's Main Menu. By typing the name of the task you'd like to be executed, choose one of the following options:");
        System.out.println("1. Add ---> adds an item to the inventory");
        System.out.println("2. List ---> displays a list of all inventory items and their quantities");
        System.out.println("3. Categorize ---> displays a list of all the items from a given category and type");
        System.out.println("4. Order ---> buy items from the inventory");
        System.out.println("5. Shopping cart ---> open the shopping cart and interact with it");
        System.out.println("6. Menu ---> display again the main menu options");
        System.out.println("7. Reset ---> empties the contents of the inventory");
        System.out.println("8. Exit ---> exits the program");
        System.out.println();
    }

    public static void processOrder(Scanner scanner, InventoryManager inventoryManager, ShoppingCart shoppingCart, long orderID) {
        Order order = new Order();
        order.takeOrder(scanner);
        long orderQuantity = order.getItem().getQuantity();
//        checks if such an item exists in the inventory and validates the quantity
        if (inventoryManager.suchItemExists(order.getItem())) {
            if (inventoryManager.getFocusItem().orderQuantityIsValid(orderQuantity)) {
//                sets the order item's price to be the same as the inventory item's price
                order.getItem().setPrice(inventoryManager.getFocusItem().getPrice());
                order.getItem().setId(inventoryManager.getFocusItem().getId());
                order.setId(orderID);
                shoppingCart.addOrder(order);
            } else {
                System.out.println("There is not enough of that item in the inventory.");
            }
        } else {
            System.out.println("Item not found. Please try again.");
        }
    }

    public static void manageShoppingCart(Scanner scanner, PaymentProcessor paymentProcessor, InventoryManager inventoryManager, ShoppingCart shoppingCart) {
        while (true) {
//            validates user input
            System.out.print("Choose a command: ");
            String cartResponse = scanner.nextLine().trim();
            System.out.println();
            if (!validCartCommands().contains(cartResponse)) {
                System.out.println("Please enter a valid command.");
                continue;
            } else if (cartResponse.equalsIgnoreCase("exit")) {
                break;
            }
//            interaction with the shopping cart
            switch (cartResponse.toLowerCase()) {
//                displays each order in the shopping cart
                case "list":
                    if (shoppingCart.getOrders().isEmpty()) {
                        System.out.println("The shopping cart is empty.\n");
                    } else {
                        shoppingCart.displayItems();
                    }
                    break;
//                attempts to purchase all items in the shopping cart
                case "purchase":
                    PaymentProcessor.choosePaymentMenu();
                    processPurchase(scanner, paymentProcessor, inventoryManager, shoppingCart);
                    break;
//                removes an order from the shopping cart by order id
                case "remove":
                    System.out.print("Order id: ");
                    shoppingCart.removeOrderById(Long.parseLong(scanner.nextLine().trim()));
                    System.out.println();
                    break;
                case "empty":
                    shoppingCart.emptyShoppingCart();
                    break;
                case "menu":
                    ShoppingCart.shoppingCartMenu();
                    break;
            }
        }
    }

    public static void processPurchase(Scanner scanner, PaymentProcessor paymentProcessor, InventoryManager inventoryManager, ShoppingCart shoppingCart) {
//        validates and sets the type of payment, based on the user's input
        String paymentResponse = scanner.nextLine().trim();
        System.out.println();
        if (paymentProcessor.paymentMethodIsValid(paymentResponse)) {
            paymentProcessor.setPaymentMethod(paymentResponse);
            Payment payment = paymentProcessor.getPaymentMethod();
            double totalAmountToPay = shoppingCart.totalValue();

//            polls payment info then validates and authorizes the payment (depending on the type)
            payment.pollAndSetPaymentInfo(scanner);
            if (payment.validatePayment()) {
                if (payment.authorize(totalAmountToPay)) {
                    System.out.printf("Total cost: $%.2f%n", totalAmountToPay);
                    System.out.printf("Remaining balance: $%.2f%n", payment.getBalance());
//                    processes the payment and updates the buyer's balance
                    payment.processPayment(totalAmountToPay);
//                    updates inventory quantities, cash balance and empties the shopping cart
                    inventoryManager.updateItemQuantities(shoppingCart);
                    inventoryManager.updateBalance(totalAmountToPay);
                    shoppingCart.emptyShoppingCart();
                } else {
                    System.out.println("Insufficient funds.");
                }
            } else {
                System.out.println("Payment info is not valid. Please try again.");
            }
        } else {
            System.out.println("Invalid payment method. Please try again.");
        }
    }
}



