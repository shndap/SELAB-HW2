package services;

public class CashPaymentProcessor implements PaymentProcessor {
    public void pay(double amount) {
        System.out.println("Paid by cash: " + amount);
    }
}
