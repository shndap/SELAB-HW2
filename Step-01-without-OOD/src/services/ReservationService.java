package services;

import constants.Notifier;
import constants.PaymentMethods;

public class ReservationService {
    private PaymentProcessor paymentProcessor = new PaymentProcessor();

    public void makeReservation(Reservation res, PaymentMethods paymentType, Notifier notifier){
        System.out.println("[LOG] reservation started for " + res.customer.name);
        System.out.println("Processing reservation for " + res.customer.name);

        System.out.println("[LOG] checking city discount for " + res.customer.city);
        if(res.customer.city.equals("Paris")){
            System.out.println("[LOG] city discount applied");
            System.out.println("Apply city discount for Paris!");
            res.room.price *= 0.9;
        }

        System.out.println("[LOG] processing payment using " + paymentType);
        switch (paymentType){
            case CARD:
                paymentProcessor.payByCard(res.totalPrice());
                break;
            case PAYPAL:
                paymentProcessor.payByPayPal(res.totalPrice());
                break;
            case CASH:
                paymentProcessor.payByCash(res.totalPrice());
                break;
            case ONSITE:
                paymentProcessor.payByOnSite(res.totalPrice());
                break;
        }

        System.out.println("----- INVOICE -----");
        System.out.println("hotel.Customer: " + res.customer.name);
        System.out.println("hotel.Room: " + res.room.number + " (" + res.room.type + ")");
        System.out.println("Total: " + res.totalPrice());
        System.out.println("-------------------");

        System.out.println("[LOG] sending confirmation message using " + notifier);
        switch (notifier){
            case EMAIL:
                EmailSender emailSender = new EmailSender();
                emailSender.sendEmail(res.customer.email, "Your reservation confirmed!");
                break;
            case SMS:
                SmsSender smsSender = new SmsSender();
                smsSender.sendSmsMessage(res.customer.mobile, "Your reservation confirmed!");
                break;
            default:
                System.out.println("There is no Message Provider");
        }
        System.out.println("[LOG] reservation completed for " + res.customer.name);
    }
}
