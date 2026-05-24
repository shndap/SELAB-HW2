package services;

public class CardPaymentProcessor implements PaymentProcessor {
    public void pay(double amount) {
        System.out.println("Paid by card: " + amount);
    }
}
