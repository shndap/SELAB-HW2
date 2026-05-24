package services;

public class ReservationService {
    public void makeReservation(Reservation res, PaymentProcessor paymentProcessor, MessageSender messageSender){
        System.out.println("[LOG] reservation started for " + res.customerName());
        System.out.println("Processing reservation for " + res.customerName());

        System.out.println("[LOG] checking city discount for " + res.getCustomer().getCity());
        if (res.applyParisDiscountIfNeeded()) {
            System.out.println("[LOG] city discount applied");
            System.out.println("Apply city discount for Paris!");
        }

        System.out.println("[LOG] processing payment using " + paymentProcessor.getClass().getSimpleName());
        paymentProcessor.pay(res.totalPrice());

        System.out.println("----- INVOICE -----");
        System.out.println("hotel.Customer: " + res.customerName());
        System.out.println("hotel.Room: " + res.roomSummary());
        System.out.println("Total: " + res.totalPrice());
        System.out.println("-------------------");

        System.out.println("[LOG] sending confirmation message using " + messageSender.getClass().getSimpleName());
        messageSender.send(res.getCustomer(), "Your reservation confirmed!");
        System.out.println("[LOG] reservation completed for " + res.customerName());
    }
}
