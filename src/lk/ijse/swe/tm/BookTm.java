package lk.ijse.swe.tm;

import javafx.scene.control.Button;

public class BookTm {
    private String CustomerId;
    private String mealId;
    private String roomId;
    private int days;
    private double total;
    private Button btn;

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    public BookTm() {
    }

    public BookTm(String customerId, String mealId, String roomId, int days, double total,Button btn) {
        CustomerId = customerId;
        this.mealId = mealId;
        this.roomId = roomId;
        this.days = days;
        this.total = total;
        this.btn = btn;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
