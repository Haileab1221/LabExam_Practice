package com.itsc.LabExam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class StudentRepo {
    public String databaseName = "StudentsDB";
    public String createDatabase = "CREATE DATABASE " + databaseName;
    public String useDatabase = "USE " + databaseName;
    public String tableName = "Grade";
    public String createTable = "CREATE TABLE " + tableName + " (id int auto_increment primary key, name VARCHAR(255), email VARCHAR(255))";

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;

    public void createConnection() {
        String url = "jdbc:mysql://localhost:3306/?user=root";
        String username = "root";
        String password = "1221";

        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void createDBAndTable() {
        try {
            createConnection();
            statement = connection.createStatement();
            statement.executeUpdate(createDatabase);
            statement.executeUpdate(useDatabase);
            statement.executeUpdate(createTable);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void insertIntoTable(Student student) {
        String insertData = "INSERT INTO " + tableName + " (name, email) VALUES (?, ?)";
        try {
            createConnection();
            statement.executeUpdate(useDatabase);
            preparedStatement = connection.prepareStatement(insertData);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
}