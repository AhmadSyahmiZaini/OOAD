package com.example.ooadfinale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;

/**
 * @author Ahmad Syahmi
 * Controller for registerViewFXML
 * registerViewFXML displays the registration page for students
 */
public class RegisterViewController extends SwitchSceneController{

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField icField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    /**
     * @param event
     * Takes in all input fields and registers the student into the database
     */
    public void registerStudent(ActionEvent event) {
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty() || nameField.getText().isEmpty() || emailField.getText().isEmpty() || icField.getText().isEmpty() || phoneField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill In All Details");
            alert.showAndWait();
        }
        else {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String register = "insert into student (username, studentPassword, name, ic, email, phone) values ('" + usernameField.getText() + "', '" + passwordField.getText() + "','" + nameField.getText() + "','" + icField.getText() + "','" + emailField.getText() + "','" + phoneField.getText() + "')";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(register);

                root = FXMLLoader.load(getClass().getResource("mainPageView.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
