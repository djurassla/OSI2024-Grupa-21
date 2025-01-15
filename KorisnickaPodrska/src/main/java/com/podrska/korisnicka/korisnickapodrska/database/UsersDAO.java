package com.podrska.korisnicka.korisnickapodrska.database;

import com.podrska.korisnicka.korisnickapodrska.database.config.H2Connection;
import com.podrska.korisnicka.korisnickapodrska.model.Ticket;
import com.podrska.korisnicka.korisnickapodrska.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {

    static public ObservableList<User> selectUsers() {
        ObservableList<User> result = FXCollections.observableArrayList();
        String selectSQL = "SELECT * FROM users";
        try (Connection connection = H2Connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

            // Izvršavanje upita
            ResultSet resultSet = preparedStatement.executeQuery();

            // Prolazak kroz rezultate
            while (resultSet.next()) {
                //  int id = resultSet.getInt("id");              // Dobijanje ID-ja
                String firstName = resultSet.getString("first_name");  // Ime korisnika
                String lastName = resultSet.getString("last_name");    // Prezime korisnika
                String username = resultSet.getString("username");    // Korisničko ime
                String role = resultSet.getString("role");
                String password = resultSet.getString("password"); // pass
                // String createdAt = resultSet.getString("created_at"); // Datum kreiranja
                User u = new User(username,firstName,lastName,role,password);
                result.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
return result;
    }

    public static void addUser(String firstName,String lastName,String username,String password, String role){
        String insertSQL = "INSERT INTO users (first_name,last_name,username,password,role) VALUES (?,?,?,?,?)";
        try (Connection connection = H2Connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1,firstName );
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, role.toLowerCase());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Broj unetih redova: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
