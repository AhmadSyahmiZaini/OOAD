module com.example.ooadfinale {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires javafx.media;


    opens com.example.ooadfinale to javafx.fxml;
    exports com.example.ooadfinale;
}