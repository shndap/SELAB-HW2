package services;

class PaymentProcessor {
    public void payByCard(double amount){
        System.out.println("[LOG] executing card payment");
        System.out.println("Paid by card: " + amount);
    }

    public void payByCash(double amount){
        System.out.println("[LOG] executing cash payment");
        System.out.println("Paid by cash: " + amount);
    }

    public void payByPayPal(double amount){
        System.out.println("[LOG] executing PayPal payment");
        System.out.println("Paid by PayPal: " + amount);
    }

    public void payByOnSite(double amount){
        System.out.println("[LOG] executing on-site payment");
        System.out.println("Paid on site: " + amount);
    }
}
