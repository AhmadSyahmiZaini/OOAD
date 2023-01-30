package com.example.ooadfinale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginViewController extends SwitchSceneController{

    @FXML
    private Button button;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label wrongLogIn;

    private Data data = new Data();

    public void loginButtonOnAction(ActionEvent event){
        if (username.getText().isEmpty() || password.getText().isEmpty()){
            wrongLogIn.setText("Enter all fields");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill In All Details");
            alert.showAndWait();
        }
        else{
            validateLogin(event);
        }
    }

    public void validateLogin(ActionEvent event) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "select count(1) from student where username = '" + username.getText() + "' and studentPassword = '" + password.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    data.setStudentUsername(username.getText());
                    root = FXMLLoader.load(getClass().getResource("facultyView.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Degree Course Information");
                    stage.show();
                } else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid Login");
                    alert.showAndWait();
                    wrongLogIn.setText("Invalid Login");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
