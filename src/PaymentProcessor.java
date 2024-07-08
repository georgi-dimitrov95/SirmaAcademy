import java.util.ArrayList;

public class PaymentProcessor {
    private ArrayList<String> paymentMethods = new ArrayList<>();
    Payment paymentMethod;

    public PaymentProcessor() {
        this.paymentMethods.add("card");
        this.paymentMethods.add("paypal");
    }

    public Payment getPaymentMethod() {
        return paymentMethod;
    }

    public boolean paymentMethodIsValid(String response) {
//        System.out.println("Please choose a payment method. The options are 'card' and 'paypal'");
        return this.paymentMethods.contains(response.toLowerCase());
    }

    public void setPaymentMethod(String response) {
        if (response.equalsIgnoreCase("card")) {
            paymentMethod = new CardPayment();
        } else if (response.equalsIgnoreCase("paypal")) {
            paymentMethod = new PayPalPayment();
        }
    }

    public static void choosePaymentMenu() {
        System.out.println("Please choose a payment method - you have the following options:");
        System.out.println("1. Credit/Debit Card");
        System.out.println("2. PayPal");
        System.out.print("Payment method: ");
    }
}
