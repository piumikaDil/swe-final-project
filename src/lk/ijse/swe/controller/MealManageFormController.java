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
import lk.ijse.swe.model.Meal;
import lk.ijse.swe.tm.MealTm;


import java.io.IOException;
import java.util.Optional;

public class MealManageFormController {

    public TextField txtId;
    public TextField txtName;
    public ComboBox cmbType;
    public TextField txtPrice;
    public JFXButton btnClose;
    public JFXButton btnSave;
    public TableView tblMeal;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colPrice;
    public TableColumn colType;
    public TableColumn colOption;
    public TextField txtSearch;
    public AnchorPane mealManageContext;
    public String searchText="";
    public JFXButton btnBack;

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchMeal(searchText);

        setMealId();

        tblMeal.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) ->{
                    if (newValue!= null) {
                        setData((MealTm) newValue);
                    }

                });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            searchMeal(searchText);
        });

        cmbType.getItems().addAll(
                "Local Meals",
                "Chinese Meals",
                "French Meals"

        );
    }

    private void setData(MealTm tm){
        txtId.setText(tm.getId());
        txtName.setText(tm.getName());
        txtPrice. setText(String.valueOf(tm.getPrice()));
        cmbType.setValue(tm.getType());

        btnSave.setText("UPDATE MEAL");

    }

    private void searchMeal(String text) {
        ObservableList<MealTm> tmList = FXCollections.observableArrayList();
        for (Meal m1 : Database.mealTable
        ) {
            if (m1.getName().contains(text) || m1.getType().contains(text)){
                Button btn = new Button("Delete");
                MealTm tm = new MealTm(m1.getId(), m1.getName(), m1.getPrice(), m1.getType(), btn);

                tmList.add(tm);

                btn.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "Are you sure whether do you want to delete this meal?",
                            ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES){
                        boolean isDeleted = Database.mealTable.remove(m1);
                        if (isDeleted){
                            searchMeal(searchText);
                            new Alert(Alert.AlertType.CONFIRMATION,"Meal Deleted...").show();
                        }else{
                            new Alert(Alert.AlertType.CONFIRMATION,"Try Again").show();
                        }
                    }

                });
            }




        }
        tblMeal.setItems(tmList);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (txtName.getText().equals("") || txtId.getText().equals("") || txtId.getText().equals("") || cmbType.getValue().equals("")){
            new Alert(Alert.AlertType.ERROR,"Please enter details first!").show();

        }else {
            Meal m1 = new Meal(
                    txtId.getText(),
                    txtName.getText(),
                    Double.parseDouble(txtPrice.getText()),
                    cmbType.getSelectionModel().getSelectedItem().toString());

            if (btnSave.getText().equalsIgnoreCase("MEAL SAVE")) {
                boolean isSaved = Database.mealTable.add(m1);
                if (isSaved) {
                    searchMeal(searchText);
                    new Alert(Alert.AlertType.CONFIRMATION, "Meal Saved").show();
                    clear();


                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "Try Again").show();
                }
            } else {
                for (int i = 0; i < Database.mealTable.size(); i++) {
                    if (txtId.getText().equalsIgnoreCase(Database.mealTable.get(i).getId())) {
                        Database.mealTable.get(i).setId(txtId.getText());
                        Database.mealTable.get(i).setName(txtName.getText());
                        Database.mealTable.get(i).setPrice(Double.parseDouble(txtPrice.getText()));
                        Database.mealTable.get(i).setType((String) cmbType.getValue());

                        searchMeal(searchText);
                        new Alert(Alert.AlertType.CONFIRMATION, "Meal Updated...").show();
                        clear();
                        btnSave.setText("MEAL SAVE ");
                    }

                }


            }
        }



    }
    public void clear(){
        txtId.clear();
        txtName.clear();
        txtPrice.clear();
        cmbType.getSelectionModel().clearSelection();

        setMealId();
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void setMealId(){
        if (Database.mealTable.isEmpty()){
            txtId.setText("M-1");
            return;
        }
        String tempMealId = Database.mealTable.get(Database.mealTable.size() - 1).getId();
        String array[] = tempMealId.split("-");
        int tempNum = Integer.parseInt(array[1]);
        int finalMealId = tempNum + 1;
        txtId.setText("RM-" + finalMealId);
    }


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
        stage.show();
    }
}
