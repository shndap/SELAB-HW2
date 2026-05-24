package services;

import models.Customer;

public interface MessageSender {
    void send(Customer customer, String message);
}
