package lk.ijse.swe.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane loginPageContext;
    public JFXTextField txtName;
    public JFXButton btnAdmin;
    public JFXButton btnRec;
    public JFXButton btnCancel;
    public JFXPasswordField txtPassword;
    public Label lblError;

    public void btnAdminOnAction(ActionEvent actionEvent) throws IOException {
        if (txtName.getText().equals("") || txtPassword.getText().equals("")){
            new Alert(Alert.AlertType.ERROR,"Please enter details first!").show();

        }else if (txtName.getText().equals("Admin") && txtPassword.getText().equals("1234")){
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdminForm.fxml"))));
            stage.show();
        }else{
            txtPassword.clear();
            txtName.clear();
            lblError.setText("Password or user name not match!");

        }
    }

    public void btnRecOnAction(ActionEvent actionEvent) throws IOException {
        if (txtName.getText().equals("") || txtPassword.getText().equals("")){
            new Alert(Alert.AlertType.ERROR,"Please enter details first!").show();

        }else if (txtName.getText().equals("kamal") && txtPassword.getText().equals("abcd")){
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/RecForm.fxml"))));
            stage.show();
        }else{
            txtPassword.clear();
            txtName.clear();
            lblError.setText("Password or user name not match!");

        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
