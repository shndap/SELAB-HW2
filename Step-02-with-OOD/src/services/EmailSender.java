package services;

import models.Customer;

public class EmailSender implements MessageSender {
    public void send(Customer customer, String message){
        System.out.println("Sending email to " + customer.getEmail() + ": " + message);
    }
}
