import java.util.Scanner;

public class ElectronicItem extends InventoryItem {
    private int wattage;
    private String grade;

    public ElectronicItem(Scanner sc) {
        System.out.print("Wattage: ");
        wattage = Integer.parseInt(sc.nextLine().trim());

        System.out.print("Grade: ");
        grade = sc.nextLine().trim();
    }

    public void setWattage(int wattage) {
        this.wattage = wattage;
    }

    public int getWattage() {
        return wattage;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public void getDetails() {
        super.getDetails();
        System.out.println("Item wattage: " + this.wattage);
        System.out.println("Item environmental grade: " + this.grade);
    }
}
