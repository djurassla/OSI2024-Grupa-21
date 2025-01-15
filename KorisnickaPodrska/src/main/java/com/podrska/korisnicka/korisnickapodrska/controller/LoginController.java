package com.podrska.korisnicka.korisnickapodrska.controller;

import com.podrska.korisnicka.korisnickapodrska.KorisnickaPodrskaApplication;
import com.podrska.korisnicka.korisnickapodrska.database.TicketsDAO;
import com.podrska.korisnicka.korisnickapodrska.database.UsersDAO;
import com.podrska.korisnicka.korisnickapodrska.model.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.kordamp.bootstrapfx.BootstrapFX;


import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class LoginController {

    @FXML
    TextField username, password;
    @FXML
    Label forgotenPassword,title;
    @FXML
    AnchorPane root;
    @FXML
    Button errorMessage;
    private Stage primaryStage;
    private Scene scene = null;

    public LoginController(Stage stage) {
        this.primaryStage = stage;

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                title.setText("test");
                root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
                    if (ev.getCode() == KeyCode.ENTER) {
                        login(username.getText(), password.getText());
                    }
                });
            }
        });
    }

    @FXML
    public void onLoginButtonClick() {
        login(username.getText(), password.getText());
    }
    @FXML
    public void onMouseClicked(){
        FXMLLoader fxmlLoader = new FXMLLoader(KorisnickaPodrskaApplication.class.getResource("reset-password.fxml"));
       // fxmlLoader.setController(new ResetPasswordController(primaryStage,username.getText()));
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
        primaryStage.setTitle("Reset password");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    @FXML
    public void onKeyRealeased(KeyEvent keyEvent){
        TextField username = (TextField) keyEvent.getSource();
        //User u = DBFinder.findUserInDB(username.getText());
//        if(u != null){
//            forgotenPassword.setVisible(true);
//        }else{
//            forgotenPassword.setVisible(false);
//        }
    }

    @FXML
    public void onHomeClicked(){
        FXMLLoader fxmlLoader = new FXMLLoader(KorisnickaPodrskaApplication.class.getResource("home-page.fxml"));
        fxmlLoader.setController(new HomePageController(primaryStage));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1366, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        primaryStage.setMinHeight(700);
        primaryStage.setMinWidth(1366);
        primaryStage.setMaxHeight(700);
        primaryStage.setMaxWidth(1366);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void login(String userename, String password) {
        errorMessage.setVisible(false);
        User user = KorisnickaPodrskaApplication.users.stream().filter(user1-> user1.getUsername().equals(userename)).findAny().orElse(null);
        if (user == null) {
            errorMessage.setVisible(true);
        } else {
            if (password != null && (password.equals(user.getPassword()))) {
                startTimer();
                KorisnickaPodrskaApplication.user = user;
                KorisnickaPodrskaApplication.tickets = TicketsDAO.selectTickets();
                FXMLLoader fxmlLoader = new FXMLLoader(KorisnickaPodrskaApplication.class.getResource("home-page.fxml"));
                fxmlLoader.setController(new HomePageController(primaryStage));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 1366, 700);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
                primaryStage.setMinWidth(1366);
                primaryStage.setMinHeight(700);
                primaryStage.setMaxWidth(1366);
                primaryStage.setMaxHeight(700);
                primaryStage.setTitle("Login");
                primaryStage.setScene(scene);
                primaryStage.setMaximized(false);
                primaryStage.setResizable(false);
                primaryStage.centerOnScreen();
                primaryStage.show();

            } else {
                errorMessage.setVisible(true);
            }
        }
    }

    private void startTimer(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    assignUser();
                  System.out.println("timer");
                });
            }
        }, 0, 3000); // Ponavlja se na svaku sekundu
    }

    private void assignUser(){
      TicketsDAO.assignTicket(KorisnickaPodrskaApplication.users.stream().filter(user -> "operater".equals(user.getRole())).findAny().get().getUsername());
      KorisnickaPodrskaApplication.tickets = TicketsDAO.selectTickets();
    }

}
