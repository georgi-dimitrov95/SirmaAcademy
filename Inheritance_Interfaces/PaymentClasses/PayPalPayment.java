package Inheritance_Interfaces.PaymentClasses;

import java.util.Scanner;

public class PayPalPayment extends Payment {
    private static final String email_regex = "^[\\w.-]+@[\\w.-]+\\.[a-z]{2,6}$";
    private static final String key_regex = "^[a-zA-Z0-9]{6}$";

    private String email;
    private String key;

    @Override
    public boolean validatePayment() {
        return email.matches(email_regex) && key.matches(key_regex);
    }

    @Override
    public boolean authorize(double amount) {
        return this.balance >= amount;
    }

    @Override
    public void pollAndSetPaymentInfo(Scanner scanner) {
        System.out.print("Email: \n");
        this.email = scanner.nextLine().trim();

        System.out.println("Key: \n");
        this.key = scanner.nextLine().trim();
    }
}
