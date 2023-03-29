package lk.ijse.swe.tm;

import javafx.scene.control.Button;

public class MealTm {
    private String id;
    private String name;
    private Double price;
    private String type;
    private Button btn;

    public MealTm() {
    }

    public MealTm(String id, String name, Double price, String type, Button btn) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.btn = btn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
