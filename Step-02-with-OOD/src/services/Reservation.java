package services;

import models.Customer;
import models.Room;

public class Reservation {
    private final Room room;
    private final Customer customer;
    private final int nights;

    public Reservation(Room r, Customer c, int nights) {
        this.room = r;
        this.customer = c;
        this.nights = nights;
    }

    public Room getRoom() {
        return room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getNights() {
        return nights;
    }

    public boolean applyParisDiscountIfNeeded() {
        if (customer.isFrom("Paris")) {
            room.applyDiscount(0.9);
            return true;
        }
        return false;
    }

    public double totalPrice(){
        return room.getPrice() * nights;
    }

    public String customerName() {
        return customer.getName();
    }

    public String roomSummary() {
        return room.description();
    }
}
