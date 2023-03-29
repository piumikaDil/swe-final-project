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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.swe.db.Database;
import lk.ijse.swe.model.Room;
import lk.ijse.swe.tm.RoomTm;

import java.io.IOException;
import java.util.Optional;

public class RoomManageFormController {
    public AnchorPane roomPageContext;
    public JFXButton btnClose;
    public TableView tblRoom;
    public TableColumn colId;
    public TableColumn colType;
    public TableColumn colPrice;
    public TableColumn colAvailable;
    public TableColumn colOption;
    public TextField txtId;
    public ComboBox cmbType;
    public ComboBox cmbAvailable;
    public TextField txtSearch;
    public JFXButton btnSave;
    public TextField txtPrice;
    public String searchText = "";

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colAvailable.setCellValueFactory(new PropertyValueFactory<>("Availability"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        setRoomId();

        searchRoom(searchText);

        tblRoom.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        setData((RoomTm) newValue);
                    }

                });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            searchRoom(searchText);
        });
        cmbType.getItems().addAll(
                "Single Room",
                "Double Room",
                "Triple Room",
                "Quad Rooms"
        );
        cmbAvailable.getItems().addAll(
                "Available",
                "Not Available"
        );
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }


    private void setData(RoomTm tm) {
        txtId.setText(tm.getId());
        cmbType.setValue(tm.getType());
        cmbAvailable.setValue(tm.getAvailability());
        txtPrice.setText(String.valueOf(tm.getPrice()));

        btnSave.setText("Update Room");


    }

    private void searchRoom(String text) {
        ObservableList<RoomTm> tmList = FXCollections.observableArrayList();
        for (Room r1 : Database.roomTable
        ) {
            if (r1.getType().contains(text) || r1.getType().contains(text)) {
                Button btn = new Button("Delete");
                RoomTm tm = new RoomTm(r1.getId(), r1.getType(), r1.getPrice(), r1.getAvailability(), btn);

                tmList.add(tm);

                btn.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "Are you sure whether do you want to delete this Room?",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get() == ButtonType.YES) {
                        boolean isDeleted = Database.roomTable.remove(r1);
                        if (isDeleted) {
                            searchRoom(searchText);
                            new Alert(Alert.AlertType.CONFIRMATION, "Room Deleted...").show();
                            setRoomId();
                        } else {
                            new Alert(Alert.AlertType.CONFIRMATION, "Try Again").show();
                        }
                    }

                });
            }


        }
        tblRoom.setItems(tmList);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (txtId.getText().equals("") || txtPrice.getText().equals("") || cmbType.getValue().equals("") || cmbAvailable.getValue().equals("") ){
            new Alert(Alert.AlertType.ERROR,"Please enter details first!").show();

        }else{ Room r1 = new Room(
                txtId.getText(),
                cmbType.getSelectionModel().getSelectedItem().toString(),
                Double.parseDouble(txtPrice.getText()),
                cmbAvailable.getSelectionModel().getSelectedItem().toString());

            if (btnSave.getText().equalsIgnoreCase("SAVE ROOM")) {
                boolean isSaved = Database.roomTable.add(r1);
                if (isSaved) {
                    searchRoom(searchText);
                    new Alert(Alert.AlertType.CONFIRMATION, "Room Saved").show();
                    clear();



            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Try Again").show();
            }
        } else {
                for (int i = 0; i < Database.roomTable.size(); i++) {
                    if (txtId.getText().equalsIgnoreCase(Database.roomTable.get(i).getId())) {
                        Database.roomTable.get(i).setId(txtId.getText());
                        Database.roomTable.get(i).setPrice(Double.parseDouble(txtPrice.getText()));
                        Database.roomTable.get(i).setType((String) cmbType.getValue());
                        Database.roomTable.get(i).setAvailability((String) cmbAvailable.getValue());
                        searchRoom(searchText);
                        new Alert(Alert.AlertType.CONFIRMATION, "Room Updated...").show();
                        clear();
                        btnSave.setText("Save Room");
                    }

                }
            }


        }
    }

    public void clear() {
        txtId.clear();
        txtPrice.clear();
        cmbType.getSelectionModel().clearSelection();
        cmbAvailable.getSelectionModel().clearSelection();

        setRoomId();

    }

    private void setRoomId() {
        if (Database.roomTable.isEmpty()) {
            txtId.setText("RM-1");
            return;
        }

        String tempRoomId = Database.roomTable.get(Database.roomTable.size() - 1).getId();
        String array[] = tempRoomId.split("-");
        int tempNum = Integer.parseInt(array[1]);
        int finalRoomId = tempNum + 1;
        txtId.setText("R-" + finalRoomId);
    }

    public void btnOn(ActionEvent actionEvent) {
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdminForm.fxml"))));
        stage.show();
    }
}
