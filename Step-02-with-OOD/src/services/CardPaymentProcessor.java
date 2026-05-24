package services;

public class CardPaymentProcessor implements PaymentProcessor {
    public void pay(double amount) {
        System.out.println("[LOG] executing card payment");
        System.out.println("Paid by card: " + amount);
    }
}
