package com.podrska.korisnicka.korisnickapodrska.controller;

import com.podrska.korisnicka.korisnickapodrska.KorisnickaPodrskaApplication;
import com.podrska.korisnicka.korisnickapodrska.database.UsersDAO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class RegisterUserController {

    private Stage primaryStage;

    public RegisterUserController(Stage primaryStage){
        this.primaryStage = primaryStage;

    }

    @FXML
    TextField username,password,retypePassword,firstName,lastName;
    @FXML
    ImageView checkPassword;
    private  Image image =null;

    @FXML
    ChoiceBox role;

    @FXML
    public void initialize() {
        // Dodavanje stavki u ChoiceBox programatski
        role.getItems().addAll("Administrator", "Operater", "Korisnik");
    }

    @FXML
    public void onLoginClicked(){
        FXMLLoader fxmlLoader = new FXMLLoader(KorisnickaPodrskaApplication.class.getResource("user-page.fxml"));
        fxmlLoader.setController(new UserPageController(primaryStage));
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
        primaryStage.setTitle("Register User");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    public void onSaveButtonClick(){
        UsersDAO.addUser(firstName.getText(),lastName.getText(),username.getText(),password.getText(),role.getValue().toString());
        KorisnickaPodrskaApplication.users = UsersDAO.selectUsers();
        onLoginClicked();
    }

    @FXML
    public void onKeyReleased(KeyEvent keyEvent){
        TextField rp = (TextField) keyEvent.getSource();

        if(password.getText().equals(rp.getText())){
           // image = new Image(RequestWithDocumentController.class.getResource("images/ok.png").toExternalForm());
        }else{
           // image = new Image(RequestWithDocumentController.class.getResource("images/error.png").toExternalForm());
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                checkPassword.setImage(image);
            }
        });

    }

}
