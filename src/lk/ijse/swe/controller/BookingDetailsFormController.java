package lk.ijse.swe.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.swe.db.Database;
import lk.ijse.swe.model.Book;
import lk.ijse.swe.tm.BookTm;

import java.io.IOException;
import java.util.Optional;

public class BookingDetailsFormController {
    public TableView<BookTm> tblDetails;
    public TableColumn colCId;
    public TableColumn colMId;
    public TableColumn colRId;
    public TableColumn colDays;
    public TableColumn colTotal;
    public TableView label;
    public JFXButton btnClose;
    public JFXButton btnBack;
    public TableView tblBooks;
    public TableColumn colAction;

    public void initialize() {
        setTableData();
        colCId.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        colMId.setCellValueFactory(new PropertyValueFactory<>("mealId"));
        colRId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colDays.setCellValueFactory(new PropertyValueFactory<>("days"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));

    }

    public void setTableData() {
        ObservableList<BookTm> tmList = FXCollections.observableArrayList();
        for (Book o1 : Database.BookingTable
        ) {
            Button btn = new Button("Cancel");

            BookTm tm = new BookTm(o1.getCustomerId(), o1.getMealId(), o1.getRoomId(), o1.getDays(), o1.getTotal(), btn);
            tmList.add(tm);

            btn.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "Are you sure whether do you want to delete this Room?",
                        ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get() == ButtonType.YES) {
                    boolean isDeleted = Database.roomTable.remove(o1);
                    tblDetails.refresh();
                    if (isDeleted) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Room Deleted...").show();
                    } else {
                        new Alert(Alert.AlertType.CONFIRMATION, "Try Again").show();
                    }
                }

            });
        }
        tblBooks.setItems(tmList);

    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void btnBackIOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/BookinForm.fxml"))));
        stage.show();
    }
}




