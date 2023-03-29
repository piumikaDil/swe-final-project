package lk.ijse.swe.tm;

import javafx.scene.control.Button;

public class RoomTm {
    private String id;
    private String type;
    private double price;
    private String Availability;
    private Button btn;

    public RoomTm() {
    }

    public RoomTm(String id, String type, double price, String availability, Button btn) {
        this.id = id;
        this.type = type;
        this.price = price;
        Availability = availability;
        this.btn = btn;
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
