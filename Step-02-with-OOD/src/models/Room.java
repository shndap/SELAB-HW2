package models;

public class Room {
    private final String number;
    private final String type;
    private double price;

    public Room(String number, String type, double price){
        this.number = number;
        this.type = type;
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public void applyDiscount(double factor) {
        price *= factor;
    }

    public String description() {
        return number + " (" + type + ")";
    }
}
