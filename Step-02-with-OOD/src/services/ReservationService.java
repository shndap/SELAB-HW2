package services;

public class ReservationService {
    public void makeReservation(Reservation res, PaymentProcessor paymentProcessor, MessageSender messageSender){
        System.out.println("Processing reservation for " + res.customerName());

        if (res.applyParisDiscountIfNeeded()) {
            System.out.println("Apply city discount for Paris!");
        }

        paymentProcessor.pay(res.totalPrice());

        System.out.println("----- INVOICE -----");
        System.out.println("hotel.Customer: " + res.customerName());
        System.out.println("hotel.Room: " + res.roomSummary());
        System.out.println("Total: " + res.totalPrice());
        System.out.println("-------------------");

        messageSender.send(res.getCustomer(), "Your reservation confirmed!");
    }
}
