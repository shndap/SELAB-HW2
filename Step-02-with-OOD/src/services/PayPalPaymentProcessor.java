package services;

public class PayPalPaymentProcessor implements PaymentProcessor {
    public void pay(double amount) {
        System.out.println("[LOG] executing PayPal payment");
        System.out.println("Paid by PayPal: " + amount);
    }
}
