package services;

import models.Customer;

public class SmsSender implements MessageSender {
    public void send(Customer customer, String message) {
        System.out.println("Sending sms to " + customer.getMobile() + ": " + message);
    }
}
