import java.util.Scanner;

public class ClothingItem extends InventoryItem {
    private String size;
    private String fabric;

    public ClothingItem(Scanner sc) {
        System.out.print("Size: ");
        size = sc.nextLine().trim();

        System.out.print("Fabric: ");
        fabric = sc.nextLine().trim();
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }

    public String getFabric() {
        return fabric;
    }

    @Override
    public void getDetails() {
        super.getDetails();
        System.out.println("Item size: " + this.size);
        System.out.println("Item fabric: " + this.fabric);
    }
}
