package Inheritance_Interfaces;

import Inheritance_Interfaces.ItemClasses.*;
import Inheritance_Interfaces.ShoppingCart.*;

import java.io.*;
import java.util.*;

public class InventoryManager implements Serializable {
//    the object will be serialized using this file path
    private static String fileName = "E:\\IntelliJ IDEA Community Edition 2024.1.1\\14 - InventoryManagementSystem\\src\\InventoryManagerFile.ser";
//    the inventory of the class, containing {Category=[HashSet of ItemClasses.InventoryItem objects]}
    private LinkedHashMap<String, LinkedHashSet<InventoryItem>> inventory;
//    this variable stores the id of the last added item to the inventory
    private long lastID = 1;
//    the earnings of the InventoryManager like if it was a store
    private double balance;
//    when searching for a particular item, saves it into this variable if found
    private InventoryItem focusItem;

    public InventoryManager() {
        inventory = new LinkedHashMap<>();
    }

    public LinkedHashMap<String, LinkedHashSet<InventoryItem>> getInventory() {
        return inventory;
    }

    public double getBalance() {
        return balance;
    }

    public void setLastID(long id) {
        this.lastID += id;
    }

    public long getLastID() {
        return lastID;
    }

    public InventoryItem getFocusItem() {
        return focusItem;
    }

//    adds an item to the inventory
    public void addItem(InventoryItem item) {
        long quantity = item.getQuantity();
        inventory.putIfAbsent(item.getCategory(), new LinkedHashSet<>());
        if (!namesInCategory(inventory.get(item.getCategory())).contains(item.getName())) {
            inventory.get(item.getCategory()).add(item);
        } else {
//            if an item with the same name already exists in the category, it sums the two identical items` quantities
            for (var i : inventory.get(item.getCategory())) {
                if (Objects.equals(i.getName(), item.getName())) {
                    quantity += i.getQuantity();
                    i.setQuantity(quantity);
                }
            }
        }
    }

//    lists the items and their details
    public void listItems() {
        System.out.println("=".repeat(15));
        for (var category : inventory.entrySet()) {
            System.out.println(category.getKey());
            System.out.println("-".repeat(10));
            for (var item : category.getValue()) {
                item.getDetails();
                System.out.println();
            }
        }
        System.out.println("=".repeat(15));
    }

//    lists each category and only the items` names
    public void categorizeItems() {
        System.out.println("=".repeat(15));
        for (var category : inventory.entrySet()) {
            System.out.println(category.getKey());
            System.out.println("-".repeat(10));
            for (var item : category.getValue()) {
                System.out.println(item.getName());
            }
            System.out.println();
        }
        System.out.println("=".repeat(15));
    }

//    used in addItem method to make an arrayList of all the items names in the given category
    public ArrayList<String> namesInCategory(LinkedHashSet<InventoryItem> category) {
        ArrayList<String> names = new ArrayList<>();
        for (var item : category) {
            names.add(item.getName());
        }
        return names;
    }

//    creates a new item depending on the user input
    public InventoryItem createItem(long id) {
        Scanner scanner = new Scanner(System.in);
        InventoryItem item = new InventoryItem();

//        prompts the user for input
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Category: ");
        String category = scanner.nextLine().trim();
        System.out.print("Type: ");
        String type = scanner.nextLine().trim();
        System.out.print("Price (per kg for grocery items): ");
        double price = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Quantity: ");
        long quantity = Long.parseLong(scanner.nextLine().trim());

        item = switch (category) {
            case "grocery" -> new GroceryItem(scanner);
            case "electronic" -> new ElectronicItem(scanner);
            case "clothing" -> new ClothingItem(scanner);
            default -> item;
        };

        item.setId(id);
        item.setName(name);
        item.setCategory(category);
        item.setType(type);
        item.setPrice(price);
        item.setQuantity(quantity);

        return item;
    }

//    serializes the object to a file
    public void writeToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

//    deserializes the object from a file
    public static InventoryManager readFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (InventoryManager) ois.readObject();
        } catch (IOException | ClassNotFoundException error) {
            return new InventoryManager();
        }
    }

    public void removeItemByIdIfExists(long id) {
        boolean idExists = false;
        for (var category : this.inventory.entrySet()) {
            for (var item : category.getValue()) {
                if (item.getId() == id) {
                    category.getValue().remove(item);
                    idExists = true;
                }
            }
        }
        if (!idExists) {
            System.out.println("There is no item with such an id.");
        }
    }

//    checks if the parameter (item) exists in the inventory
    public boolean suchItemExists(InventoryItem orderItem) {
        if (!this.inventory.containsKey(orderItem.getCategory())) {
            return false;
        }

        for (var item : this.inventory.get(orderItem.getCategory())) {
            if (item.equals(orderItem)) {
                this.focusItem = item;
                return true;
            }
        }
        return false;
    }

    public void deleteItem(InventoryItem item) {
        this.inventory.get(item.getCategory()).remove(item);
    }

//    updates the balance field of the inventory after the order is completed
    public void updateBalance(double sum) {
        this.balance += sum;
    }

    public void updateItemQuantities(ShoppingCart cart) {
        for (Order order : cart.getOrders()) {
            for (var category : this.inventory.entrySet()) {
                for (var item : category.getValue()) {
                    if (item.equals(order.getItem())) {
                        long orderQuantity = order.getItem().getQuantity();
                        if (item.getQuantity() - orderQuantity == 0) {
                            this.deleteItem(item);
                        } else {
                            item.updateQuantity(orderQuantity);
                        }
                    }
                }
            }
        }
    }
}