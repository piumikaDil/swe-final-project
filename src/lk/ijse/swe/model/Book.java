package lk.ijse.swe.model;

public class Book {
    private String CustomerId;
    private String mealId;
    private String roomId;
    private int days;
    private double total;

    public Book() {
    }

    public Book(String customerId, String mealId, String roomId, int days, double total) {
        CustomerId = customerId;
        this.mealId = mealId;
        this.roomId = roomId;
        this.days = days;
        this.total = total;
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
