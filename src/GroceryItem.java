import java.util.Scanner;

public class GroceryItem extends InventoryItem{
    private double weight;
    private String countryProducer;

    public GroceryItem(Scanner sc) {
        System.out.print("Weight: ");
        weight = Double.parseDouble(sc.nextLine().trim());

        System.out.print("Country producer: ");
        countryProducer = sc.nextLine().trim();
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setCountryProducer(String countryProducer) {
        this.countryProducer = countryProducer;
    }

    public String getCountryProducer() {
        return countryProducer;
    }

    @Override
    public void getDetails() {
        super.getDetails();
        System.out.println("Item weight: " + this.weight);
        System.out.println("Item origin: " + this.countryProducer);
    }

    @Override
    public double calculateValue() {
        return (super.getPrice() * super.getQuantity() * this.weight);
    }
}
