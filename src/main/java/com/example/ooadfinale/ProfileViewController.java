package com.example.ooadfinale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


/**
 * @author Syahmi and Tasha
 * Controller for profileView.fxml
 * profileView.fxml displays the profile of the student
 * allows to view profile, add qualification, and select courses
 */
public class ProfileViewController extends SwitchSceneController implements Initializable {

    @FXML
    private Label myLabel1;
    @FXML
    private Button saveButton;
    @FXML
    private Button qualificationButton;
    @FXML
    private Button selectionButton;
    @FXML
    private AnchorPane qualificationPane;

    @FXML
    private AnchorPane selectionPane;

    @FXML
    private Label usernameLabel;

    @FXML
    private Button detailsButton;

    @FXML
    private AnchorPane detailsPane;

    @FXML
    private ChoiceBox<String> myChoiceBox1,myChoiceBox2,myChoiceBox3;
    private String[] course = {"Electrical and Electronics Engineering(Degree)","Electronics & Communication Engineering(Degree)","Computer Science Engineering(Degree)","Mechanical Engineering(Degree)","Civil Engineering(Degree)",
            "Electrical and Electronics Engineering(Master)","Electronics & Communication Engineering(Master)","Mechanical Engineering(Master)","Civil Engineering(Master)"};

    private Data data = new Data();


    @FXML
    protected void onMySaveButtonClick() {
        myLabel1.setText("Saved!");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        qualificationButton.setStyle("-fx-background-color: #adbdff");
        selectionButton.setStyle("-fx-background-color: #dee2fa");
        detailsButton.setStyle("-fx-background-color: #dee2fa");
        selectionPane.setDisable(true);
        selectionPane.setVisible(false);
        qualificationPane.setDisable(false);
        qualificationPane.setVisible(true);
        detailsPane.setDisable(true);
        detailsPane.setVisible(false);
        myChoiceBox1.getItems().addAll(course);
        myChoiceBox2.getItems().addAll(course);
        myChoiceBox3.getItems().addAll(course);
        usernameLabel.setText(data.getStudentUsername());
    }

    /**
     * @param event
     * switches view from profile details, add qualification, and select courses
     */
    public void switchView(ActionEvent event) {
        if (event.getSource() == qualificationButton) {
            qualificationButton.setStyle("-fx-background-color: #adbdff");
            selectionButton.setStyle("-fx-background-color: #dee2fa");
            detailsButton.setStyle("-fx-background-color: #dee2fa");
            selectionPane.setDisable(true);
            selectionPane.setVisible(false);
            qualificationPane.setDisable(false);
            qualificationPane.setVisible(true);
            detailsPane.setDisable(true);
            detailsPane.setVisible(false);

        } else if (event.getSource() == selectionButton) {
            selectionButton.setStyle("-fx-background-color: #adbdff");
            qualificationButton.setStyle("-fx-background-color: #dee2fa");
            detailsButton.setStyle("-fx-background-color: #dee2fa");
            selectionPane.setDisable(false);
            selectionPane.setVisible(true);
            qualificationPane.setDisable(true);
            qualificationPane.setVisible(false);
            detailsPane.setDisable(true);
            detailsPane.setVisible(false);
        }
        else if (event.getSource() == detailsButton) {
            detailsButton.setStyle("-fx-background-color: #adbdff");
            qualificationButton.setStyle("-fx-background-color: #dee2fa");
            selectionButton.setStyle("-fx-background-color: #dee2fa");
            selectionPane.setDisable(true);
            selectionPane.setVisible(false);
            qualificationPane.setDisable(true);
            qualificationPane.setVisible(false);
            detailsPane.setDisable(false);
            detailsPane.setVisible(true);
            try {
                viewDetails();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * updates the selected course
     */
    public void updateCourse(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        try{
            PreparedStatement preparedStatement = connectDB.prepareStatement("Update student set choice1 = ?, choice2 = ?, choice3 = ? where username = ?;");

            preparedStatement.setString(1, myChoiceBox1.getValue());
            preparedStatement.setString(2, myChoiceBox2.getValue());
            preparedStatement.setString(3, myChoiceBox3.getValue());
            preparedStatement.setString(4, data.getStudentUsername());
            preparedStatement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }

        myLabel1.setText("Updated Successfully");

    }

    @FXML
    private TextField gradeField;

    @FXML
    private TextField qualificationField;

    @FXML
    private Button addButton;

    @FXML
    private Label label;

    /**
     * adds qualification for student
     */
    public void addQualification(){
        if (qualificationField.getText().isEmpty() || gradeField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill In All Details");
            alert.showAndWait();
            label.setText("Please Fill In All Details");
        }
        else{
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String addQ = "insert into qualification (username,qualificationName,qualificationGrade) values ('" + data.getStudentUsername() + "','" + qualificationField.getText() + "','" + gradeField.getText() + "')";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(addQ);
            } catch (Exception e){
                e.printStackTrace();
            }

            label.setText("Added Successfully");
        }
    }

    @FXML
    private Label choice1Label;

    @FXML
    private Label choice2Label;

    @FXML
    private Label choice3Label;

    @FXML
    private Label emailLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label icLabel;

    @FXML
    private TableColumn<StringTableDataProfile, String> qualificationCol;

    @FXML
    private TableColumn<StringTableDataProfile, String> gradeCol;

    @FXML
    private TableView<StringTableDataProfile> qualificationTable;

    private ObservableList<StringTableDataProfile> list = FXCollections.observableArrayList();

    /**
     * view student details
     * @throws SQLException
     */
    public void viewDetails() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "select name, ic, email, phone, choice1, choice2, choice3 from student where username = '" + data.getStudentUsername() + "'";

        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        while (queryResult.next()){
            nameLabel.setText("Name: " + queryResult.getString(1));
            icLabel.setText("IC Number: " + queryResult.getString(2));
            emailLabel.setText("Email Address: " + queryResult.getString(3));
            phoneLabel.setText("Phone: " + queryResult.getString(4));
            choice1Label.setText("Choice 1: " + queryResult.getString(5));
            choice2Label.setText("Choice 2: " + queryResult.getString(6));
            choice3Label.setText("Choice 3: " + queryResult.getString(7));
        }

        list.removeAll();
        qualificationTable.getItems().clear();

        String queryTable = "select qualificationName, qualificationGrade from qualification where username = '" + data.getStudentUsername() + "'";
        Statement statementTable = connectDB.createStatement();
        ResultSet queryResultTable = statementTable.executeQuery(queryTable);

        while (queryResultTable.next()){
            list.add(new StringTableDataProfile(queryResultTable.getString(1), queryResultTable.getString(2)));
        }

        qualificationCol.setCellValueFactory(new PropertyValueFactory<StringTableDataProfile, String>("qualification"));
        gradeCol.setCellValueFactory(new PropertyValueFactory<StringTableDataProfile, String>("grade"));

        qualificationTable.setItems(list);
    }
}
