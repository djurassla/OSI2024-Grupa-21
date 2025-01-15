package com.podrska.korisnicka.korisnickapodrska.database;

import com.podrska.korisnicka.korisnickapodrska.database.config.H2Connection;

import java.sql.*;

public class DatabaseCrateSettingTable {

    static public void createTable(){
        try (Connection conn = H2Connection.getConnection(); Statement stmt = conn.createStatement();){
            String createTableQuery = "CREATE TABLE IF NOT EXISTS settings (\n" +
                    "    id INT AUTO_INCREMENT PRIMARY KEY, -- Jedinstveni ID korisnika\n" +
                    "    code VARCHAR(100) NOT NULL,  -- Ime korisnika\n" +
                    "    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Datum kreiranja zapisa\n" +
                    ");";
            stmt.execute(createTableQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static public void insertLicenceKey(){
        String insertSQL = "INSERT INTO users (code,value) VALUES (?,?)";
        String countSQL = "SELECT COUNT(*) AS user_count FROM users where role = 'licence_key'";
        int userCount = 0;
        try (Connection connection = H2Connection.getConnection();
             PreparedStatement preparedStatementCount = connection.prepareStatement(countSQL)) {

            ResultSet resultSet = preparedStatementCount.executeQuery();

            if (resultSet.next()) {
                 userCount = resultSet.getInt("user_count");
                System.out.println("Ukupan broj settingsa: " + userCount);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        if(userCount == 0){

        try (Connection connection = H2Connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, "licence_key");
            preparedStatement.setString(2, "");

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Broj unetih redova: " + rowsAffected);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
            try (Connection connection = H2Connection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                preparedStatement.setString(1, "title");
                preparedStatement.setString(2, "Korisnicka podrska");

                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Broj unetih redova: " + rowsAffected);
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    }
}
