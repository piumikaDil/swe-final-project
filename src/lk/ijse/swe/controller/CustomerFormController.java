package lk.ijse.swe.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import lk.ijse.swe.model.Customer;
import lk.ijse.swe.tm.CustomerTm;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;

public class CustomerFormController {
    public AnchorPane CustomerContext;
    public TextField txtName;
    public TextField txtNic;
    public TextField txtEmail;
    public TextField txtTp;
    public TextField txtAddress;
    public JFXButton btnSave;
    public TextField txtId;
    public JFXTextField txtSearch;
    public TableView tblCustomer;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colNic;
    public TableColumn colTp;
    public TableColumn colEmail;
    public TableColumn colAddress;
    public String searchText = "";
    public JFXButton btnClose;
    public JFXButton btnBack;

    public void initialize(){

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTp.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        setCustomerId();
        searchCustomer(searchText);
        txtName.requestFocus();

        tblCustomer.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) ->{
                    if (newValue!= null) {
                        setData((CustomerTm) newValue);
                    }

                });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            searchCustomer(searchText);
        });
    }

    private void setData(CustomerTm tm){
        txtId.setText(tm.getId());
        txtName.setText(tm.getName());
        txtNic.setText(tm.getNic());
        txtTp.setText(tm.getTp());
        txtEmail.setText(tm.getEmail());
        txtAddress.setText(tm.getAddress());


        btnSave.setText("Update Customer");

    }

    private void searchCustomer(String text) {
        ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();
        for (Customer c1 : Database.customerTable
        ) {
            if (c1.getName().contains(text) || c1.getAddress().contains(text)){
                CustomerTm tm = new CustomerTm(c1.getId(), c1.getName(), c1.getNic(), c1.getTp(), c1.getEmail(), c1.getAddress());

                tmList.add(tm);


            }

        }
        tblCustomer.setItems(tmList);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (txtName.getText().equals("") || txtId.getText().equals("") || txtNic.getText().equals("") || txtTp.getText().equals("") || txtEmail.getText().equals("") || txtAddress.getText().equals("")){
            new Alert(Alert.AlertType.ERROR,"Please enter details first!").show();

        }else{
            if (validateEmail(txtEmail.getText())){
                Customer c1 = new Customer(
                        txtId.getText(),
                        txtName.getText(),
                        txtNic.getText(),
                        txtTp.getText(),
                        txtEmail.getText(),
                        txtAddress.getText());

                if (btnSave.getText().equalsIgnoreCase("Save Customer")){
                    boolean isSaved = Database.customerTable.add(c1);
                    if (isSaved){
                        searchCustomer(searchText);
                        new Alert(Alert.AlertType.CONFIRMATION,"Customer Saved").show();
                        clear();
                        setCustomerId();



                    }else{
                        new Alert(Alert.AlertType.CONFIRMATION,"Try Again").show();
                    }
                }else{
                    for (int i = 0; i < Database.customerTable.size(); i++) {
                        if (txtId.getText().equalsIgnoreCase(Database.customerTable.get(i).getId())){
                            Database.customerTable.get(i).setName(txtName.getText());
                            Database.customerTable.get(i).setAddress(txtNic.getText());
                            Database.customerTable.get(i).setAddress(txtTp.getText());
                            Database.customerTable.get(i).setAddress(txtEmail.getText());
                            Database.customerTable.get(i).setAddress(txtAddress.getText());
                            searchCustomer(searchText);
                            new Alert(Alert.AlertType.CONFIRMATION,"Customer Updated...").show();
                            clear();
                            btnSave.setText("SAVE CUSTOMER");
                        }

                    }



                }

            }else{
                new Alert(Alert.AlertType.ERROR,"Invalid Email.Enter correct Email").show();


            }

            }





    }
    public void clear(){
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtTp.clear();
        txtNic.clear();

        setCustomerId();


    }

    public void setCustomerId(){
        if (Database.customerTable.isEmpty()){
            txtId.setText("C-1");
            return;
        }
        String tempCustomerId = Database.customerTable.get(Database.customerTable.size() - 1).getId();
        String array[] = tempCustomerId.split("-");
        int tempNum = Integer.parseInt(array[1]);
        int finalCustomerId = tempNum + 1;
        txtId.setText("C-" + finalCustomerId);
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
    public static boolean validateEmail(String email) {
        if(email==null || email.isEmpty()){
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern emailPattern =Pattern.compile(emailRegex);

        if(emailPattern.matcher(email).matches()){
            return true;
        }else {
            return false;
        }
    }
}
