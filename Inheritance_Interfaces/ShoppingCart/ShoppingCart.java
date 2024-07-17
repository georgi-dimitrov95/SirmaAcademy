package Inheritance_Interfaces.ShoppingCart;

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Order> orders;

    public ShoppingCart() {
        orders = new ArrayList<>();
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void removeOrder(Order order) {
        this.orders.remove(order);
    }

    public void emptyShoppingCart() {
        this.orders.clear();
    }

    public double totalValue() {
        double total = 0;
        for (Order order : this.orders) {
            total += order.totalValue();
        }
        return total;
    }

    public void displayItems() {
        System.out.println("=".repeat(15));
        for (Order order : this.orders) {
            System.out.printf("Order ID: %d%n", order.getId());
            order.getItem().getDetails();
            System.out.println("-".repeat(10));
        }
        System.out.println("=".repeat(15));
    }

    public void removeOrderById(long id) {
        this.orders.removeIf(order -> order.getId() == id);
    }

    public static void shoppingCartMenu() {
        System.out.println("This is the shopping cart menu. Please choose one of the following options:");
        System.out.println("1. List ---> lists all items in the shopping cart and their specifications");
        System.out.println("2. Purchase ---> purchase all items currently in the shopping cart");
        System.out.println("3. Remove ---> remove a single order from the shopping cart");
        System.out.println("4. Empty ---> empties the contents of the shopping cart");
        System.out.println("5. Menu ---> displays again the shopping cart menu");
        System.out.println("6. Exit ---> exits the shopping cart menu");
    }
}
