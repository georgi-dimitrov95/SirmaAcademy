import java.util.Scanner;

public class CardPayment extends Payment {
    private static final String number_regex = "^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$";
    private static final String ownerName_regex = "^[A-Z][a-z]+ [A-Z][a-z]+$";
    private static final String expiryDate_regex = "^[0-1][0-2]/[2-3][0-9]$";
    private static final String cvv_regex = "^[0-9][0-9][0-9]$";

    private String number;
    private String ownerName;
    private String expiryDate;
    private String cvv;

    @Override
    public boolean validatePayment() {
        if (Integer.parseInt(String.valueOf(expiryDate.charAt(4))) < 4) {
            return false;
        }
        return number.matches(number_regex) && ownerName.matches(ownerName_regex) && expiryDate.matches(expiryDate_regex) && cvv.matches(cvv_regex);
    }

    @Override
    public boolean authorize(double amount) {
        return this.balance >= amount;
    }

    @Override
    public void pollAndSetPaymentInfo(Scanner scanner) {
        System.out.print("Card number (XXXX-XXXX-XXXX-XXXX): \n");
        this.number = scanner.nextLine().trim();

        System.out.print("Owner name (Jane Doe): \n");
        this.ownerName = scanner.nextLine().trim();

        System.out.print("Expiry date (MM/YY): \n");
        this.expiryDate = scanner.nextLine().trim();

        System.out.print("CVV (3-digit): \n");
        this.cvv = scanner.nextLine().trim();
    }
}
