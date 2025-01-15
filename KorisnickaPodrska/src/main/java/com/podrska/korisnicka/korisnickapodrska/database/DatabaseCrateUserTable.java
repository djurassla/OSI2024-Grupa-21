package com.podrska.korisnicka.korisnickapodrska.database;

import com.podrska.korisnicka.korisnickapodrska.database.config.H2Connection;

import java.sql.*;

public class DatabaseCrateUserTable {

    static public void createTable(){
        try (Connection conn = H2Connection.getConnection(); Statement stmt = conn.createStatement();){
            String createTableQuery = "CREATE TABLE IF NOT EXISTS users (\n" +
                    "    id INT AUTO_INCREMENT PRIMARY KEY, -- Jedinstveni ID korisnika\n" +
                    "    first_name VARCHAR(100) NOT NULL,  -- Ime korisnika\n" +
                    "    last_name VARCHAR(100) NOT NULL,   -- Prezime korisnika\n" +
                    "    username VARCHAR(100) NOT NULL UNIQUE, -- Jedinstveno korisničko ime\n" +
                    "    password VARCHAR(100) NOT NULL UNIQUE, -- password\n" +
                    "    role VARCHAR(50) NOT NULL,         -- Uloga korisnika (npr. admin, user)\n" +
                    "    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Datum kreiranja zapisa\n" +
                    ");";
            stmt.execute(createTableQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static public void insertDefaultAdminIfNotExist(){
        String insertSQL = "INSERT INTO users (first_name,last_name,username,password,role) VALUES (?,?,?,?,?)";
        String countSQL = "SELECT COUNT(*) AS user_count FROM users where role = 'admin'";
        int userCount = 0;
        try (Connection connection = H2Connection.getConnection();
             PreparedStatement preparedStatementCount = connection.prepareStatement(countSQL)) {

            ResultSet resultSet = preparedStatementCount.executeQuery();

            if (resultSet.next()) {
                 userCount = resultSet.getInt("user_count");
                System.out.println("Ukupan broj korisnika: " + userCount);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        if(userCount == 0){

        try (Connection connection = H2Connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, "AdminFN");
            preparedStatement.setString(2, "AdminLN");
            preparedStatement.setString(3, "admin");
            preparedStatement.setString(4, "admin");
            preparedStatement.setString(5, "admin");

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Broj unetih redova: " + rowsAffected);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
            try (Connection connection = H2Connection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                preparedStatement.setString(1, "systemFN");
                preparedStatement.setString(2, "systemLN");
                preparedStatement.setString(3, "system");
                preparedStatement.setString(4, "system");
                preparedStatement.setString(5, "system");

                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Broj unetih redova: " + rowsAffected);
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    }
}
