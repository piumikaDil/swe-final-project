package lk.ijse.swe.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.swe.db.Database;
import lk.ijse.swe.model.Room;
import lk.ijse.swe.tm.RoomTm;

import java.io.IOException;

public class AvailableRoomsFormController {
    public TableView tblAvRooms;
    public TableColumn colId;
    public TableColumn colType;
    public TableColumn colPrice;
    public TableColumn colAvailability;
    public TableColumn colOption;
    public JFXButton btnClose;
    public JFXButton btnBack;



    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("Availability"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadData();


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

    public void loadData() {
        ObservableList<RoomTm> tmList = FXCollections.observableArrayList();
        for (Room r1 : Database.roomTable
        ) {

                Button btn = new Button("Available");
                RoomTm tm = new RoomTm(r1.getId(), r1.getType(), r1.getPrice(), r1.getAvailability(), btn);

                tmList.add(tm);
            }


tblAvRooms.setItems(tmList);
    }

}
