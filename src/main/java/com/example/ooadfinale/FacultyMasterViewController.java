package com.example.ooadfinale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author
 *
 */
public class FacultyMasterViewController extends SwitchSceneController implements Initializable {

    @FXML
    private TableView<StringTableData> ERTable;

    @FXML
    private TableView<StringTableData> STable;

    @FXML
    private TableColumn<StringTableData, String> scholarshipColumn;

    @FXML
    private TableColumn<StringTableData, String> eeeColumn;

    @FXML
    private TableColumn<StringTableData, String> eceColumn;

    @FXML
    private TableColumn<StringTableData, String> ceColumn;

    @FXML
    private TableColumn<StringTableData, String> meColumn;

    private ObservableList<StringTableData> list = FXCollections.observableArrayList();

    private ArrayList<String> fac = new ArrayList<String>();

    @FXML
    private Button courseButton;

    @FXML
    private Button durationButton;

    @FXML
    private Button entryButton;

    @FXML
    private Button feeButton;

    @FXML
    private Button scholarshipButton;

    @FXML
    private GridPane loginPane;

    @FXML
    private GridPane guestPane;

    private Data data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        entryButton.setStyle("-fx-background-color: #adbdff");
        feeButton.setStyle("-fx-background-color: #dee2fa");
        courseButton.setStyle("-fx-background-color: #dee2fa");
        durationButton.setStyle("-fx-background-color: #dee2fa");
        scholarshipButton.setStyle("-fx-background-color: #dee2fa");

        list.removeAll();

        STable.getItems().clear();

        ERTable.getItems().clear();

        STable.setVisible(false);
        STable.setDisable(true);
        ERTable.setVisible(true);
        ERTable.setDisable(false);

        if (data.getStudentUsername().isEmpty())
        {
            loginPane.setDisable(true);
            loginPane.setVisible(false);
            guestPane.setDisable(false);
            guestPane.setVisible(true);
        }
        else{
            loginPane.setDisable(false);
            loginPane.setVisible(true);
            guestPane.setDisable(true);
            guestPane.setVisible(false);
        }

        try {
            getEntryReq();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param event
     * switch table details for entry requirements,
     * @throws SQLException
     */
    public void switchTable(ActionEvent event) throws SQLException {
        STable.setVisible(false);
        STable.setDisable(true);
        ERTable.setVisible(true);
        ERTable.setDisable(false);
        if (event.getSource() == entryButton){
            entryButton.setStyle("-fx-background-color: #adbdff");
            feeButton.setStyle("-fx-background-color: #dee2fa");
            courseButton.setStyle("-fx-background-color: #dee2fa");
            durationButton.setStyle("-fx-background-color: #dee2fa");
            scholarshipButton.setStyle("-fx-background-color: #dee2fa");
            getEntryReq();
        } else if (event.getSource() == feeButton) {
            feeButton.setStyle("-fx-background-color: #adbdff");
            entryButton.setStyle("-fx-background-color: #dee2fa");
            courseButton.setStyle("-fx-background-color: #dee2fa");
            durationButton.setStyle("-fx-background-color: #dee2fa");
            scholarshipButton.setStyle("-fx-background-color: #dee2fa");
            getFeeStructure();
        } else if (event.getSource() == durationButton) {
            durationButton.setStyle("-fx-background-color: #adbdff");
            entryButton.setStyle("-fx-background-color: #dee2fa");
            courseButton.setStyle("-fx-background-color: #dee2fa");
            feeButton.setStyle("-fx-background-color: #dee2fa");
            scholarshipButton.setStyle("-fx-background-color: #dee2fa");
            getDuration();
        } else if (event.getSource() == courseButton) {
            courseButton.setStyle("-fx-background-color: #adbdff");
            entryButton.setStyle("-fx-background-color: #dee2fa");
            feeButton.setStyle("-fx-background-color: #dee2fa");
            durationButton.setStyle("-fx-background-color: #dee2fa");
            scholarshipButton.setStyle("-fx-background-color: #dee2fa");
            getCourseStructure();
        }
    }

    public void viewScholarship() throws SQLException {
        STable.setVisible(true);
        STable.setDisable(false);
        ERTable.setVisible(false);
        ERTable.setDisable(true);
        scholarshipButton.setStyle("-fx-background-color: #adbdff");
        entryButton.setStyle("-fx-background-color: #dee2fa");
        feeButton.setStyle("-fx-background-color: #dee2fa");
        durationButton.setStyle("-fx-background-color: #dee2fa");
        courseButton.setStyle("-fx-background-color: #dee2fa");

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "select scholarshipDetail from scholarship where scholarshipType = 'master'";

        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        list.removeAll();

        STable.getItems().clear();

        while (queryResult.next()){
            list.add(new StringTableData(queryResult.getString(1)));
        }

        scholarshipColumn.setCellValueFactory(new PropertyValueFactory<StringTableData, String>("scholarship"));

        STable.setItems(list);
    }

    public void getEntryReq() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "select entryReq from degree where degreeID = 102 or degreeID = 302 or degreeID = 402 or degreeID = 502";

        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        list.removeAll();

        ERTable.getItems().clear();

        fac.clear();

        while (queryResult.next()){
            fac.add(queryResult.getString(1));
        }
        list.add(new StringTableData(fac.get(0), fac.get(1), fac.get(2), fac.get(3)));

        eeeColumn.setCellValueFactory(new PropertyValueFactory<StringTableData, String>("eee"));
        eceColumn.setCellValueFactory(new PropertyValueFactory<StringTableData, String>("ece"));
        ceColumn.setCellValueFactory(new PropertyValueFactory<StringTableData, String>("ce"));
        meColumn.setCellValueFactory(new PropertyValueFactory<StringTableData, String>("me"));

        ERTable.setItems(list);
    }

    public void getFeeStructure() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "select feeStructure from degree where degreeID = 102 or degreeID = 302 or degreeID = 402 or degreeID = 502";

        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        list.removeAll();

        ERTable.getItems().clear();

        fac.clear();

        while (queryResult.next()){
            fac.add(queryResult.getString(1));
        }
        list.add(new StringTableData(fac.get(0), fac.get(1), fac.get(2), fac.get(3)));

        eeeColumn.setCellValueFactory(new PropertyValueFactory<StringTableData, String>("eee"));
        eceColumn.setCellValueFactory(new PropertyValueFactory<StringTableData, String>("ece"));
        ceColumn.setCellValueFactory(new PropertyValueFactory<StringTableData, String>("ce"));
        meColumn.setCellValueFactory(new PropertyValueFactory<StringTableData, String>("me"));

        ERTable.setItems(list);
    }

    public void getCourseStructure() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "select courseStructure from degree where degreeID = 102 or degreeID = 302 or degreeID = 402 or degreeID = 502";

        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        list.removeAll();

        ERTable.getItems().clear();

        fac.clear();

        while (queryResult.next()){
            fac.add(queryResult.getString(1));
        }
        list.add(new StringTableData(fac.get(0), fac.get(1), fac.get(2), fac.get(3)));

        eeeColumn.setCellValueFactory(new PropertyValueFactory<StringTableData, String>("eee"));
        eceColumn.setCellValueFactory(new PropertyValueFactory<StringTableData, String>("ece"));
        ceColumn.setCellValueFactory(new PropertyValueFactory<StringTableData, String>("ce"));
        meColumn.setCellValueFactory(new PropertyValueFactory<StringTableData, String>("me"));

        ERTable.setItems(list);
    }

    public void getDuration() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "select duration from degree where degreeID = 102 or degreeID = 302 or degreeID = 402 or degreeID = 502";

        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        list.removeAll();

        ERTable.getItems().clear();

        fac.clear();

        while (queryResult.next()){
            fac.add(queryResult.getString(1));
        }
        list.add(new StringTableData(fac.get(0), fac.get(1), fac.get(2), fac.get(3)));

        eeeColumn.setCellValueFactory(new PropertyValueFactory<StringTableData, String>("eee"));
        eceColumn.setCellValueFactory(new PropertyValueFactory<StringTableData, String>("ece"));
        ceColumn.setCellValueFactory(new PropertyValueFactory<StringTableData, String>("ce"));
        meColumn.setCellValueFactory(new PropertyValueFactory<StringTableData, String>("me"));

        ERTable.setItems(list);
    }

    public void visibleEEE(){
        if (eeeColumn.isVisible() == true)
            eeeColumn.setVisible(false);
        else
            eeeColumn.setVisible(true);
    }

    public void visibleECE(){
        if (eceColumn.isVisible() == true)
            eceColumn.setVisible(false);
        else
            eceColumn.setVisible(true);
    }

    public void visibleCE(){
        if (ceColumn.isVisible() == true)
            ceColumn.setVisible(false);
        else
            ceColumn.setVisible(true);
    }

    public void visibleME(){
        if (meColumn.isVisible() == true)
            meColumn.setVisible(false);
        else
            meColumn.setVisible(true);
    }
}

