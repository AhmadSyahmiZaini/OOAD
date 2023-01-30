package com.example.ooadfinale;

import java.sql.*;

/**
 * @author
 * database connection
 */
public class DatabaseConnection {
    public Connection databaseLink;

    /**
     * gets connection of database
     * @return connection
     */
    public Connection getConnection(){
        //String databaseName = "";
        String databaseUser = "remote1";
        String databasePassword = "password123";
        String url = "jdbc:mysql://localhost/ooad";

        try{
            //Class.forName("com.mysql.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e){
            e.printStackTrace();
        }

        return databaseLink;
    }
}
