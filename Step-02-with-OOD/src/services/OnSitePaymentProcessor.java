package services;

public class OnSitePaymentProcessor implements PaymentProcessor {
    public void pay(double amount) {
        System.out.println("[LOG] executing on-site payment");
        System.out.println("Paid on site: " + amount);
    }
}
