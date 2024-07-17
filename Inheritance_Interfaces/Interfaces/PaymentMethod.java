package Inheritance_Interfaces.Interfaces;

import java.util.Scanner;

public interface PaymentMethod {
    boolean validatePayment();
    boolean authorize(double amount);
    void pollAndSetPaymentInfo(Scanner scanner);
}
