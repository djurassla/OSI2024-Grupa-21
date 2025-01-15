package com.podrska.korisnicka.korisnickapodrska;

import com.podrska.korisnicka.korisnickapodrska.controller.HomePageController;
import com.podrska.korisnicka.korisnickapodrska.controller.LoginController;
import com.podrska.korisnicka.korisnickapodrska.database.DatabaseCrateUserTable;
import com.podrska.korisnicka.korisnickapodrska.database.DatabaseCreateTiketsTable;
import com.podrska.korisnicka.korisnickapodrska.database.TicketsDAO;
import com.podrska.korisnicka.korisnickapodrska.database.UsersDAO;
import com.podrska.korisnicka.korisnickapodrska.database.config.H2Connection;
import com.podrska.korisnicka.korisnickapodrska.model.Ticket;
import com.podrska.korisnicka.korisnickapodrska.model.User;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.h2.tools.Server;

public class KorisnickaPodrskaApplication extends Application {

    public static ObservableList<User> users;
    public static ObservableList<Ticket> tickets;
    public static User user = null;

    @Override
    public void start(Stage primaryStage) throws Exception {
        DatabaseCrateUserTable.createTable();
        DatabaseCreateTiketsTable.createTable();
        DatabaseCrateUserTable.insertDefaultAdminIfNotExist();
        KorisnickaPodrskaApplication.users = UsersDAO.selectUsers();
        System.out.println(users);
        Server.main(new String[]{"-web", "-webAllowOthers", "-tcp", "-tcpAllowOthers"});
        System.out.println("H2 konzola je pokrenuta! Otvorite http://localhost:8082 u pregledaču.");
        FXMLLoader fxmlLoader = new FXMLLoader(KorisnickaPodrskaApplication.class.getResource("login.fxml"));
        fxmlLoader.setController(new LoginController(primaryStage));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        primaryStage.setMaxWidth(600);
        primaryStage.setMaxHeight(400);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}