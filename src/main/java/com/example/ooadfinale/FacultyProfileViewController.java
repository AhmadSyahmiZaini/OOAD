package com.example.ooadfinale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * @author Syahmi
 * Controller for facultyProfileView.fxml
 * Allows to update course details and scholarships
 */
public class FacultyProfileViewController extends SwitchSceneController implements Initializable {

    @FXML
    private Button courseButton;

    @FXML
    private Button durationButton;

    @FXML
    private Button entryButton;

    @FXML
    private TextArea entryText;

    @FXML
    private TextArea feeText;

    @FXML
    private TextArea courseText;

    @FXML
    private TextArea durationText;

    @FXML
    private TextArea scholarshipText;

    @FXML
    private Button feeButton;

    @FXML
    private Button scholarshipButton;

    @FXML
    private Button postButton;

    @FXML
    private Button presidentButton;

    @FXML
    private Button presidentMasterButton;

    @FXML
    private Button meritButton;

    @FXML
    private Button talentButton;

    @FXML
    private Button internationalButton;

    @FXML
    private Label usernameLabel;

    @FXML
    private VBox courseBox;

    @FXML
    private VBox feeBox;

    @FXML
    private VBox durationBox;

    @FXML
    private VBox entryBox;

    @FXML
    private VBox scholarshipBox;

    private Data data = new Data();

    private String scholarshipID = "S100";

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            usernameLabel.setText(getDegreeName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        entryButton.setStyle("-fx-background-color: #adbdff");
        feeButton.setStyle("-fx-background-color: #dee2fa");
        courseButton.setStyle("-fx-background-color: #dee2fa");
        durationButton.setStyle("-fx-background-color: #dee2fa");
        scholarshipButton.setStyle("-fx-background-color: #dee2fa");
        try {
            getEntryReq();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        entryBox.setDisable(false);
        entryBox.setVisible(true);
        durationBox.setDisable(true);
        durationBox.setVisible(false);
        courseBox.setDisable(true);
        courseBox.setVisible(false);
        feeBox.setDisable(true);
        feeBox.setVisible(false);
        scholarshipBox.setDisable(true);
        scholarshipBox.setVisible(false);
    }

    /**
     * Gets the degree name
     * @return degree name
     * @throws SQLException
     */
    public String getDegreeName() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String name = "";

        String query = "select degreeName from degree where degreeID = '" + data.getDegreeID() + "'";

        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        while (queryResult.next()) {
            name = queryResult.getString(1);
        }

        return name;
    }

    /**
     * @param event
     * @throws SQLException
     * Switches the view to edit each course details
     */
    public void switchView(ActionEvent event) throws SQLException {
        if (event.getSource() == entryButton){
            entryButton.setStyle("-fx-background-color: #adbdff");
            feeButton.setStyle("-fx-background-color: #dee2fa");
            courseButton.setStyle("-fx-background-color: #dee2fa");
            durationButton.setStyle("-fx-background-color: #dee2fa");
            scholarshipButton.setStyle("-fx-background-color: #dee2fa");
            entryBox.setDisable(false);
            entryBox.setVisible(true);
            durationBox.setDisable(true);
            durationBox.setVisible(false);
            courseBox.setDisable(true);
            courseBox.setVisible(false);
            feeBox.setDisable(true);
            feeBox.setVisible(false);
            scholarshipBox.setDisable(true);
            scholarshipBox.setVisible(false);
            getEntryReq();
        } else if (event.getSource() == feeButton) {
            feeButton.setStyle("-fx-background-color: #adbdff");
            entryButton.setStyle("-fx-background-color: #dee2fa");
            courseButton.setStyle("-fx-background-color: #dee2fa");
            durationButton.setStyle("-fx-background-color: #dee2fa");
            scholarshipButton.setStyle("-fx-background-color: #dee2fa");
            entryBox.setDisable(true);
            entryBox.setVisible(false);
            durationBox.setDisable(true);
            durationBox.setVisible(false);
            courseBox.setDisable(true);
            courseBox.setVisible(false);
            feeBox.setDisable(false);
            feeBox.setVisible(true);
            scholarshipBox.setDisable(true);
            scholarshipBox.setVisible(false);
            getFeeStructure();
        } else if (event.getSource() == durationButton) {
            durationButton.setStyle("-fx-background-color: #adbdff");
            entryButton.setStyle("-fx-background-color: #dee2fa");
            courseButton.setStyle("-fx-background-color: #dee2fa");
            feeButton.setStyle("-fx-background-color: #dee2fa");
            scholarshipButton.setStyle("-fx-background-color: #dee2fa");
            entryBox.setDisable(true);
            entryBox.setVisible(false);
            durationBox.setDisable(false);
            durationBox.setVisible(true);
            courseBox.setDisable(true);
            courseBox.setVisible(false);
            feeBox.setDisable(true);
            feeBox.setVisible(false);
            scholarshipBox.setDisable(true);
            scholarshipBox.setVisible(false);
            getDuration();
        } else if (event.getSource() == courseButton) {
            courseButton.setStyle("-fx-background-color: #adbdff");
            entryButton.setStyle("-fx-background-color: #dee2fa");
            feeButton.setStyle("-fx-background-color: #dee2fa");
            durationButton.setStyle("-fx-background-color: #dee2fa");
            scholarshipButton.setStyle("-fx-background-color: #dee2fa");
            entryBox.setDisable(true);
            entryBox.setVisible(false);
            durationBox.setDisable(true);
            durationBox.setVisible(false);
            courseBox.setDisable(false);
            courseBox.setVisible(true);
            feeBox.setDisable(true);
            feeBox.setVisible(false);
            scholarshipBox.setDisable(true);
            scholarshipBox.setVisible(false);
            getCourseStructure();
        } else if (event.getSource() == scholarshipButton) {
            scholarshipButton.setStyle("-fx-background-color: #adbdff");
            entryButton.setStyle("-fx-background-color: #dee2fa");
            feeButton.setStyle("-fx-background-color: #dee2fa");
            durationButton.setStyle("-fx-background-color: #dee2fa");
            courseButton.setStyle("-fx-background-color: #dee2fa");
            entryBox.setDisable(true);
            entryBox.setVisible(false);
            durationBox.setDisable(true);
            durationBox.setVisible(false);
            courseBox.setDisable(true);
            courseBox.setVisible(false);
            feeBox.setDisable(true);
            feeBox.setVisible(false);
            scholarshipBox.setDisable(false);
            scholarshipBox.setVisible(true);
            presidentButton.setStyle("-fx-background-color: #adbdff");
            meritButton.setStyle("-fx-background-color: #dee2fa");
            talentButton.setStyle("-fx-background-color: #dee2fa");
            internationalButton.setStyle("-fx-background-color: #dee2fa");
            presidentMasterButton.setStyle("-fx-background-color: #dee2fa");
            postButton.setStyle("-fx-background-color: #dee2fa");
            getPresident();
            scholarshipID = "S100";
        }
    }

    /**
     * @param event
     * @throws SQLException
     * switches view to edit different scholarship details
     */
    public void switchScholarshipView(ActionEvent event) throws SQLException {
        if (event.getSource() == presidentButton) {
            presidentButton.setStyle("-fx-background-color: #adbdff");
            meritButton.setStyle("-fx-background-color: #dee2fa");
            talentButton.setStyle("-fx-background-color: #dee2fa");
            internationalButton.setStyle("-fx-background-color: #dee2fa");
            presidentMasterButton.setStyle("-fx-background-color: #dee2fa");
            postButton.setStyle("-fx-background-color: #dee2fa");
            getPresident();
            scholarshipID = "S100";
        } else if (event.getSource() == meritButton) {
            meritButton.setStyle("-fx-background-color: #adbdff");
            presidentButton.setStyle("-fx-background-color: #dee2fa");
            talentButton.setStyle("-fx-background-color: #dee2fa");
            internationalButton.setStyle("-fx-background-color: #dee2fa");
            presidentMasterButton.setStyle("-fx-background-color: #dee2fa");
            postButton.setStyle("-fx-background-color: #dee2fa");
            getMerit();
            scholarshipID = "S200";
        } else if (event.getSource() == talentButton) {
            talentButton.setStyle("-fx-background-color: #adbdff");
            presidentButton.setStyle("-fx-background-color: #dee2fa");
            meritButton.setStyle("-fx-background-color: #dee2fa");
            internationalButton.setStyle("-fx-background-color: #dee2fa");
            presidentMasterButton.setStyle("-fx-background-color: #dee2fa");
            postButton.setStyle("-fx-background-color: #dee2fa");
            getTalent();
            scholarshipID = "S300";
        } else if (event.getSource() == internationalButton) {
            internationalButton.setStyle("-fx-background-color: #adbdff");
            presidentButton.setStyle("-fx-background-color: #dee2fa");
            talentButton.setStyle("-fx-background-color: #dee2fa");
            meritButton.setStyle("-fx-background-color: #dee2fa");
            presidentMasterButton.setStyle("-fx-background-color: #dee2fa");
            postButton.setStyle("-fx-background-color: #dee2fa");
            getInternational();
            scholarshipID = "S400";
        } else if (event.getSource() == presidentMasterButton) {
            presidentMasterButton.setStyle("-fx-background-color: #adbdff");
            presidentButton.setStyle("-fx-background-color: #dee2fa");
            talentButton.setStyle("-fx-background-color: #dee2fa");
            internationalButton.setStyle("-fx-background-color: #dee2fa");
            meritButton.setStyle("-fx-background-color: #dee2fa");
            postButton.setStyle("-fx-background-color: #dee2fa");
            getPresidentMaster();
            scholarshipID = "S500";
        } else if (event.getSource() == postButton) {
            postButton.setStyle("-fx-background-color: #adbdff");
            presidentButton.setStyle("-fx-background-color: #dee2fa");
            talentButton.setStyle("-fx-background-color: #dee2fa");
            internationalButton.setStyle("-fx-background-color: #dee2fa");
            presidentMasterButton.setStyle("-fx-background-color: #dee2fa");
            meritButton.setStyle("-fx-background-color: #dee2fa");
            getPost();
            scholarshipID = "S600";
        }
    }

    /**
     * @throws SQLException
     * get entry requirements
     */
    public void getEntryReq() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "select entryReq from degree where degreeID = '" + data.getDegreeID() + "'";

        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        while (queryResult.next()) {
            entryText.setText(queryResult.getString(1));
        }
    }

    /**
     * @throws SQLException
     * get fee structure
     */
    public void getFeeStructure() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "select feeStructure from degree where degreeID = '" + data.getDegreeID() + "'";

        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        while (queryResult.next()) {
            feeText.setText(queryResult.getString(1));
        }
    }

    /**
     * @throws SQLException
     * get course structure
     */
    public void getCourseStructure() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "select courseStructure from degree where degreeID = '" + data.getDegreeID() + "'";

        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        while (queryResult.next()) {
            courseText.setText(queryResult.getString(1));
        }
    }

    /**
     * @throws SQLException
     * get duration
     */
    public void getDuration() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "select duration from degree where degreeID = '" + data.getDegreeID() + "'";

        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        while (queryResult.next()) {
            durationText.setText(queryResult.getString(1));
        }
    }

    /**
     * @throws SQLException
     * update entry requirements
     */
    public void updateEntryReq() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        try{
            PreparedStatement preparedStatement = connectDB.prepareStatement("Update degree set entryReq = ? where degreeID = ?;");

            preparedStatement.setString(1,entryText.getText());
            preparedStatement.setString(2, data.getDegreeID());
            preparedStatement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @throws SQLException
     * update fee
     */
    public void updateFee() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        try{
            PreparedStatement preparedStatement = connectDB.prepareStatement("Update degree set feeStructure = ? where degreeID = ?;");

            preparedStatement.setString(1,feeText.getText());
            preparedStatement.setString(2, data.getDegreeID());
            preparedStatement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @throws SQLException
     * update duration
     */
    public void updateDuration() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        try{
            PreparedStatement preparedStatement = connectDB.prepareStatement("Update degree set duration = ? where degreeID = ?;");

            preparedStatement.setString(1,durationText.getText());
            preparedStatement.setString(2, data.getDegreeID());
            preparedStatement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @throws SQLException
     * update course structure
     */
    public void updateCourse() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        try{
            PreparedStatement preparedStatement = connectDB.prepareStatement("Update degree set courseStructure= ? where degreeID = ?;");

            preparedStatement.setString(1,courseText.getText());
            preparedStatement.setString(2, data.getDegreeID());
            preparedStatement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @throws SQLException
     * get president scholarship details
     */
    public void getPresident() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "select scholarshipDetail from scholarship where scholarshipID = 'S100'";

        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        while (queryResult.next()) {
            scholarshipText.setText(queryResult.getString(1));
        }
    }

    /**
     * @throws SQLException
     * get merit scholarship details
     */
    public void getMerit() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "select scholarshipDetail from scholarship where scholarshipID = 'S200'";

        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        while (queryResult.next()) {
            scholarshipText.setText(queryResult.getString(1));
        }
    }

    /**
     * @throws SQLException
     * get talent scholarship details
     */
    public void getTalent() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "select scholarshipDetail from scholarship where scholarshipID = 'S300'";

        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        while (queryResult.next()) {
            scholarshipText.setText(queryResult.getString(1));
        }
    }

    /**
     * @throws SQLException
     * get international scholarship details
     */
    public void getInternational() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "select scholarshipDetail from scholarship where scholarshipID = 'S400'";

        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        while (queryResult.next()) {
            scholarshipText.setText(queryResult.getString(1));
        }
    }

    /**
     * @throws SQLException
     * get president master scholarship details
     */
    public void getPresidentMaster() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "select scholarshipDetail from scholarship where scholarshipID = 'S500'";

        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        while (queryResult.next()) {
            scholarshipText.setText(queryResult.getString(1));
        }
    }

    /**
     * @throws SQLException
     * get post scholarship details
     */
    public void getPost() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "select scholarshipDetail from scholarship where scholarshipID = 'S600'";

        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        while (queryResult.next()) {
            scholarshipText.setText(queryResult.getString(1));
        }
    }

    /**
     * update scholarship details
     */
    public void updateScholarship() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        try{
            PreparedStatement preparedStatement = connectDB.prepareStatement("Update scholarship set scholarshipDetail= ? where scholarshipID = ?;");

            preparedStatement.setString(1,scholarshipText.getText());
            preparedStatement.setString(2, scholarshipID);
            preparedStatement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
