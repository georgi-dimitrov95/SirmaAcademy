package Inheritance_Interfaces.ItemClasses;

import Inheritance_Interfaces.Interfaces.*;

import java.io.Serializable;
import java.util.Objects;

public class InventoryItem implements Serializable, Item, Categorizable, Breakable, Perishable, Sellable {
    private long id;
    private String name;
    private String category;
    private String type;
    private double price;
    private long quantity;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getQuantity() {
        return quantity;
    }

//    prints various information about the item
    @Override
    public void getDetails() {
        System.out.println("Item ID: " + this.id);
        System.out.println("Item name: " + this.name);
        System.out.println("Item category: " + this.category);
        System.out.println("Item type: " + this.type);
        System.out.println("Item price: " + this.price);
        System.out.println("Item quantity: " + this.quantity);
        System.out.println("Total item value: " + calculateValue());
    }

    @Override
    public double calculateValue() {
        return (this.price * this.quantity);
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public boolean isBroken() {
        return false;
    }

    @Override
    public void handleBrokenItem() {

    }

    @Override
    public boolean isPerished() {
        return false;
    }

    @Override
    public void handlePerishedItem() {

    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public boolean isEmpty() {
        return name == null && category == null && type == null;
    }

//    used to compare two items by their main fields
    public boolean equals(InventoryItem item2) {
        boolean equals = false;
        if (this == item2) return true;
        if (item2 == null || this.getClass() != item2.getClass()) return false;

        if (Objects.equals(this.name, item2.getName()) &&
            Objects.equals(this.category, item2.getCategory()) &&
            Objects.equals(this.type, item2.getType())) {
            equals = true;
        } else {
            return false;
        }

        if (this instanceof GroceryItem && item2 instanceof GroceryItem) {
            if (!Objects.equals(((GroceryItem) this).getCountryProducer(), ((GroceryItem) item2).getCountryProducer())) {
                return false;
            }
        }

        if (this instanceof ElectronicItem && item2 instanceof ElectronicItem) {
            if (((ElectronicItem) this).getWattage() != ((ElectronicItem) item2).getWattage() || !Objects.equals(((ElectronicItem) this).getGrade(), ((ElectronicItem) item2).getGrade())) {
                return false;
            }
        }

        if (this instanceof ClothingItem && item2 instanceof ClothingItem) {
            if (!Objects.equals(((ClothingItem) this).getSize(), ((ClothingItem) item2).getSize()) || !Objects.equals(((ClothingItem) this).getFabric(), ((ClothingItem) item2).getFabric())) {
                return false;
            }
        }

        return equals;
    }

    public boolean orderQuantityIsValid (long orderQuantity) {
        return this.quantity >= orderQuantity;
    }

    public void updateQuantity (long orderQuantity) {
        this.quantity -= orderQuantity;
    }
}
