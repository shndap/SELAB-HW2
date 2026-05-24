package models;

public class Customer {
    private final String name;
    private final String email;
    private final String city;
    private final String mobile;

    public Customer(String name, String email, String mobile, String city){
        this.name = name;
        this.email = email;
        this.city = city;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getMobile() {
        return mobile;
    }

    public boolean isFrom(String targetCity) {
        return city.equalsIgnoreCase(targetCity);
    }
}
