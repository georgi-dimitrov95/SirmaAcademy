import java.util.*;

public class InventoryManagerSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//       the absolute path of the file in which the inventory is stored
        InventoryManager inventory = InventoryManager.readFromFile();

//        list of valid commands
        ArrayList<String> commands = new ArrayList<>();
        Collections.addAll(commands, "add", "list", "categorize", "order", "menu", "remove", "reset", "exit");

//        start of program
        System.out.println();
        System.out.println("Welcome to the Inventory Management System");
        System.out.println("-".repeat(20));


        mainMenu();
        long id = 0;

//        main loop of the program
        while (true) {
            System.out.print("What would you like to do?: ");
            String responseTask = scanner.nextLine().toLowerCase();
            if (!commands.contains(responseTask)) {
                System.out.println("Please type a valid task");
                continue;
            }
//            determine which action should be taken based on the user's input
            switch (responseTask) {
                case "add":
                    System.out.println();
                    inventory.addItem(inventory.createItem(inventory.getLastID() + id++));
                    System.out.println();
                    break;
                case "list":
                    inventory.listItems();
                    System.out.println();
                    break;
                case "categorize":
                    inventory.categorizeItems();
                    break;
                case "order":
//                        prompts the user to make an order
                        Order order = new Order();
                        order.takeOrder(scanner);
                        long orderQuantity = order.getItem().getQuantity();
//                        checks if such an item exists in the inventory and validates the quantity
                        if (inventory.suchItemExists(order.getItem())) {
                            InventoryItem inventoryItem = inventory.getFocusItem();
                            if (inventoryItem.orderQuantityIsValid(orderQuantity)) {
                                if (inventoryItem.getQuantity() - orderQuantity == 0) {
                                    inventory.deleteItem(inventoryItem);
                                } else {
                                    inventoryItem.updateQuantity(orderQuantity);
                                }
                                inventory.updateBalance(order.getItem().getQuantity());
                            } else {
                                System.out.println("There is not enough of that  item in the inventory.");
                            }
                        } else {
                            System.out.println("Item not found. Please try again.");
                        }
                    break;
                case "remove":
                    System.out.println("Please enter the id of the item you wish to remove from the inventory");
                    inventory.removeItemByIdIfExists(Long.parseLong(scanner.nextLine().trim()));
                    break;
                case "menu":
                    mainMenu();
                    break;
                case "reset":
//                    clears the contents of the inventory and replaces it with an empty one
                    inventory = new InventoryManager();
                    id = 0;
                    break;
                case "exit":
//                    writes the inventory to a file and makes sure it remembers the last assigned id to an item
                    inventory.setLastID(id);
                    inventory.writeToFile();
                    System.exit(0);
            }
        }
    }
//    displays the main menu of the program
    public static void mainMenu() {
        System.out.println("This is the program's Main Menu. By typing the name of the task you'd like to be executed, choose one of the following options:");
        System.out.println("1. Add ---> adds an item to the inventory");
        System.out.println("2. List ---> displays a list of all inventory items and their quantities");
        System.out.println("3. Categorize ---> displays a list of all the items from a given category and type");
        System.out.println("4. Order ---> buy items from the inventory");
        System.out.println("5. Menu ---> display again the main menu options");
        System.out.println("6. Exit ---> exits the program");
        System.out.println();
    }
}