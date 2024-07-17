package Inheritance_Interfaces.ShoppingCart;

import Inheritance_Interfaces.ItemClasses.*;
import java.util.Scanner;

public class Order {
    private long id;
    private InventoryItem item;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public InventoryItem getItem() {
        return item;
    }

    public double totalValue() {
        return this.item.calculateValue();
    }

//    prompts the user for input and then creates an item which will serve as the order
    public void takeOrder(Scanner scanner) {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Category: ");
        String category = scanner.nextLine().trim();
        System.out.print("Type: ");
        String type = scanner.nextLine().trim();


        this.item = switch (category) {
            case "grocery" -> new GroceryItem(scanner);
            case "electronic" -> new ElectronicItem(scanner);
            case "clothing" -> new ClothingItem(scanner);
            default -> new InventoryItem();
        };

        System.out.print("Quantity: ");
        long quantity = Long.parseLong(scanner.nextLine().trim());

        this.item.setName(name);
        this.item.setCategory(category);
        this.item.setType(type);
        this.item.setQuantity(quantity);
    }
}


