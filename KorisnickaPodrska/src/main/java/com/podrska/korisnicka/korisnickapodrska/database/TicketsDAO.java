package com.podrska.korisnicka.korisnickapodrska.database;

import com.podrska.korisnicka.korisnickapodrska.KorisnickaPodrskaApplication;
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

public class TicketsDAO {

    static public ObservableList<Ticket> selectTickets() {
        ObservableList<Ticket> result = FXCollections.observableArrayList();
        String selectSQL = "SELECT * FROM tickets";
        try (Connection connection = H2Connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

            // Izvršavanje upita
            ResultSet resultSet = preparedStatement.executeQuery();

            // Prolazak kroz rezultate
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                String createdBy = resultSet.getString("created_by");
                String updatedBy = resultSet.getString("updated_by");
                String assigned = resultSet.getString("assigned");
                String status = resultSet.getString("status");

                if("korisnik".equals(KorisnickaPodrskaApplication.user.getRole()) && assigned.equals(KorisnickaPodrskaApplication.user.getUsername())) {
                   if(!"closed".equals(status)) {
                       result.add(new Ticket(id, description, status, createdBy,updatedBy,assigned));
                   }
                }else if("operater".equals(KorisnickaPodrskaApplication.user.getRole())&& assigned.equals(KorisnickaPodrskaApplication.user.getUsername())){
                    if(!"closed".equals(status) && !"open".equals(status)) {
                        result.add(new Ticket(id, description, status, createdBy,updatedBy,assigned));
                    }
                }else if("admin".equals(KorisnickaPodrskaApplication.user.getRole())){
                        result.add(new Ticket(id, description, status, createdBy,updatedBy,assigned));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
return result;
    }

    public static void addTicket(String description,String username,String status,String assigned){
        String insertSQL = "INSERT INTO tickets (description,created_by,status,assigned) VALUES (?,?,?,?)";
        try (Connection connection = H2Connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1,description );
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, status);
            preparedStatement.setString(4, assigned);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Broj unetih redova: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean updateTicket(int id, String status, String username) {
        String sql = "UPDATE tickets SET status = ?, updated_by = ? WHERE id = ?";

        try (Connection conn = H2Connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Postavljanje parametara
            //pstmt.setString(1, description);
            pstmt.setString(1, status);
            pstmt.setString(2, username);
            pstmt.setInt(3, id);

            // Izvršavanje upita
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;  // Vraća true ako je bar jedan red ažuriran

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean assignTicket(String username) {
        String sql = "UPDATE tickets SET assigned = ?,status = ?  WHERE assigned = 'system'";
        try (Connection conn = H2Connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Postavljanje parametara
            //pstmt.setString(1, description);
            pstmt.setString(1, username);
            pstmt.setString(2, "dodjeljen");

            // Izvršavanje upita
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;  // Vraća true ako je bar jedan red ažuriran

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
