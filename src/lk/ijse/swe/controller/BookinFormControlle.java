package lk.ijse.swe.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.swe.db.Database;
import lk.ijse.swe.model.Book;
import lk.ijse.swe.model.Customer;
import lk.ijse.swe.model.Meal;
import lk.ijse.swe.model.Room;
import lk.ijse.swe.tm.RoomTm;

import java.io.IOException;

public class BookinFormControlle {
    public AnchorPane BookingContext;
    public JFXButton btnClose;
    public JFXButton btnBack;
    public ComboBox cmbCustomer;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtTel;
    public ComboBox cmbRoom;
    public TextField txtType;
    public TextField txtPrice;
    public ComboBox cmbMeal;
    public TextField txtMealName;
    public TextField txtMealType;
    public TextField txtMealPrice;
    public JFXButton btnCheck;
    public TextField txtDay;
    public Label lblTotal;
    public JFXButton btnBook;
    public JFXButton btnBooking;
    public JFXButton btnPrint;
    public Label lblCus;
    public Label lblRoom;
    public Label lblMeal;
    public Label lblPayment;

    public void initialize() {
        loadAllCustomerIds();
        loadAllMealIds();
        setAvailableRoomIds();

        txtDay.textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    calcTotal();
                });

        cmbRoom.getSelectionModel()
                .selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        setRoomDetails();
                    }

                });

        cmbMeal.getSelectionModel()
                .selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        setMealDetails();
                    }

                });

        cmbCustomer.getSelectionModel()
                .selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        setCustomerDetails();
                    }

                });
    }

    private void loadAllCustomerIds() {
        for (Customer c : Database.customerTable) {
            cmbCustomer.getItems().add(c.getId());
        }
    }

    private void loadAllMealIds() {
        for (Meal m : Database.mealTable) {
            cmbMeal.getItems().add(m.getId());
        }
    }

    private void setAvailableRoomIds() {
        for (Room r : Database.roomTable) {
            if (r.getAvailability().equals("Available")) {
                cmbRoom.getItems().add(r.getId());
            }
        }
    }

    public void setRoomDetails() {
        for (Room r : Database.roomTable) {
            if (r.getId().equals(cmbRoom.getValue())) {
                txtType.setText(r.getType());
                txtPrice.setText(String.valueOf(r.getPrice()));
            }
        }
    }

    public void setMealDetails() {
        for (Meal m : Database.mealTable) {
            if (m.getId().equals(cmbMeal.getValue())) {
                txtMealName.setText(m.getName());
                txtMealType.setText(m.getType());
                txtMealPrice.setText(String.valueOf(m.getPrice()));
            }
        }
    }

    public void setCustomerDetails() {
        for (Customer c : Database.customerTable) {
            if (c.getId().equals(cmbCustomer.getValue())) {
                txtName.setText(c.getName());
                txtTel.setText(c.getTp());
                txtAddress.setText(c.getAddress());
            }
        }
    }

    public void calcTotal() {
        Double total = 0.00;
        double roomPrice = Double.parseDouble(txtPrice.getText());
        double MealPrice = Double.parseDouble(txtMealPrice.getText());
        int days = Integer.parseInt(txtDay.getText());
        total = (roomPrice + MealPrice) * days;

        lblTotal.setText(String.valueOf(total));


    }


    public void btnCloseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/RecForm.fxml"))));
        stage.show();
    }


    public void btnCheckOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AvailableRoomsForm.fxml"))));
        stage.show();

    }


    public void btnBookOnAction(ActionEvent actionEvent)  {
        lblCus.setText(txtName.getText());
        lblMeal.setText(txtMealName.getText());
        lblRoom.setText(txtType.getText());
        lblPayment.setText(lblTotal.getText());
        lblTotal.setText("00.00");

        if (txtType.getText().isEmpty() || txtMealType.getText().isEmpty() || txtMealName.getText().isEmpty() || txtPrice.getText().isEmpty() || txtTel.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please enter details first!").show();
        }else {
            Book b1 = new Book(
                    cmbCustomer.getSelectionModel().getSelectedItem().toString(),
                    cmbMeal.getSelectionModel().getSelectedItem().toString(),
                    cmbRoom.getSelectionModel().getSelectedItem().toString(),
                    Integer.parseInt(txtDay.getText()),
                    Double.parseDouble(lblTotal.getText()));

            boolean isAdded = Database.BookingTable.add(b1);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Your booking success!!").show();

            }
        }


    }


    public void clear() {
        cmbCustomer.getSelectionModel().clearSelection();
        cmbMeal.getSelectionModel().clearSelection();
        cmbRoom.getSelectionModel().clearSelection();
        txtAddress.clear();
        txtMealPrice.clear();
        txtTel.clear();
        txtMealType.clear();
        txtName.clear();
        txtPrice.clear();
        txtMealName.clear();
        txtType.clear();


    }

    public void btnBookingOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/BookingDetailsForm.fxml"))));
        stage.show();
    }

    public void btnPrintOnAction(ActionEvent actionEvent) {
        clear();
        lblPayment.setText("");
        lblCus.setText("");
        lblRoom.setText("");
        lblMeal.setText("");

    }
}
