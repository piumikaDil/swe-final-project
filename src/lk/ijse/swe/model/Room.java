package lk.ijse.swe.model;

public class Room {
    private String id;
    private String type;
    private double price;
    private String Availability;

    public Room() {
    }

    public Room(String id, String type, double price, String availability) {
        this.id = id;
        this.type = type;
        this.price = price;
        Availability = availability;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAvailability() {
        return Availability;
    }

    public void setAvailability(String availability) {
        Availability = availability;
    }
}
