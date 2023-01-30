package com.example.ooadfinale;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SwitchSceneController {

    public Stage stage;
    public Scene scene;
    public Parent root;


    //for changing scenes, copy & paste the below method and change the name of fxml file and method
    //can view the difference between switchToPseudoMainPage and switchToRegister as reference
    public void switchToMainPageView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainPageView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Main Page");
        stage.show();
    }

    public void switchToRegisterView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("registerView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Registration");
        stage.show();
    }

    public void switchToLoginView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("loginView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

    public void switchToProfileView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("profileView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Profile");
        stage.show();
    }

    public void switchToFacultyView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("facultyView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Degree Course Information");
        stage.show();
    }

    public void switchToFacultyMasterView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("facultyMasterView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Master Course Information");
        stage.show();
    }

    public void switchToMediaView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mediaView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Video");
        stage.show();
    }
}
