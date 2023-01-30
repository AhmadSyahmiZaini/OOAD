package com.example.ooadfinale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MainPageViewController extends SwitchSceneController implements Initializable {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField userField;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginMessage;

    private Data data = new Data();

    public void loginButtonOnAction(ActionEvent event){
        if (userField.getText().isEmpty() || passwordField.getText().isEmpty()){
            loginMessage.setText("Enter all fields");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill In All Details");
            alert.showAndWait();
        }
        else{
            validateLogin(event);
        }
    }

    public void validateLogin(ActionEvent event){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        boolean login = false;

        String verifyLogin = "select count(1) from student where username = '" + userField.getText() + "' and studentPassword = '" + passwordField.getText() + "'";
        String verifyLoginFaculty = "select count(1) from degree where degreeID = '" + userField.getText() + "' and password = '" + passwordField.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    login = true;
                    data.setStudentUsername(userField.getText());
                    root = FXMLLoader.load(getClass().getResource("facultyView.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Degree Course Information");
                    stage.show();
                }
                else{
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLoginFaculty);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    login = true;
                    data.setDegreeID(userField.getText());
                    root = FXMLLoader.load(getClass().getResource("facultyProfileView.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Faculty");
                    stage.show();
                }
                else{
                    if (login == false){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid Login");
                    alert.showAndWait();
                    loginMessage.setText("Invalid Login");
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data.setStudentUsername("");
    }
}
