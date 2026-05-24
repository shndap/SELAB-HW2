package services;

public class PayPalPaymentProcessor implements PaymentProcessor {
    public void pay(double amount) {
        System.out.println("Paid by PayPal: " + amount);
    }
}
