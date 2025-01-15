package com.podrska.korisnicka.korisnickapodrska.controller;

import com.podrska.korisnicka.korisnickapodrska.KorisnickaPodrskaApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;


import java.io.IOException;

public class ChangePasswordController {

    private Stage primaryStage;
  //  private User user = null;
    @FXML
    private TextField securityQuestion,txtRepetedPassword,txtSecurityAnswer,txtNewPassword;
    @FXML
    private Button reset,save,username;
    @FXML
    Label labelResetPassword,labelSecurityQuestion,labelNewPassword,labelSecurityAnswer;

    public ChangePasswordController(Stage stage, String userFromOtherPage) {
        this.primaryStage = stage;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //user = DBFinder.findUserInDB(userFromOtherPage);
               // errorMessage.setVisible(false);
                if("user" != null) {
                   // securityQuestion.setText(user.getSecurityQuestion());
                }
            }
        });
    }

    @FXML
    public void onRessetButtonClick(){
        if(txtSecurityAnswer.getText().equals("user.getSecurityAnswer()")){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    reset.setVisible(false);
                    save.setVisible(true);
                    securityQuestion.setText("");
                    txtSecurityAnswer.setText("");
                    securityQuestion.setDisable(false);
                    labelSecurityQuestion.setVisible(false);
                    labelSecurityAnswer.setVisible(false);
                    labelResetPassword.setVisible(true);
                    labelNewPassword.setVisible(true);
                    securityQuestion.setVisible(false);
                    txtSecurityAnswer.setVisible(false);
                    txtNewPassword.setVisible(true);
                    txtRepetedPassword.setVisible(true);

                }
            });

        }else{
           // errorMessage.setVisible(true);
        }
    }
    @FXML
    public void onSaveButtonClick(){
        if(txtNewPassword.getText().equals(txtRepetedPassword.getText())){
           // DBUpdater.updateUserPassword(user.getUserName(),txtNewPassword.getText());
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
           // primaryStage.centerOnScreen();
            primaryStage.show();
        }
    }
    @FXML
    public void onResetButtonClick(){}
}
