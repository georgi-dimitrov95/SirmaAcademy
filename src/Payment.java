import java.util.Scanner;

public class Payment implements PaymentMethod {
//    will be also used as a pseudo "Buyer" class hence the balance field
    public double balance = 10000;

    public double getBalance() {
        return balance;
    }

    public void processPayment(double moneySpent) {
        this.balance -= moneySpent;
        System.out.printf("The payment has been completed successfully! Your remaining balance in the payment method you just used is: $%.2f%n", this.balance);
    }

    @Override
    public boolean validatePayment() {
        return false;
    }

    @Override
    public boolean authorize(double amount) {
        return false;
    }

    @Override
    public void pollAndSetPaymentInfo(Scanner scanner) {

    }
}
