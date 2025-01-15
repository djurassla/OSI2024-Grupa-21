package com.podrska.korisnicka.korisnickapodrska.controller;

import com.podrska.korisnicka.korisnickapodrska.KorisnickaPodrskaApplication;
import com.podrska.korisnicka.korisnickapodrska.model.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.File;
import java.io.IOException;

public class UserPageController {

    private Stage primaryStage;

    public UserPageController(Stage primaryStage) {
        this.primaryStage = primaryStage;

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                usernameColumn.setCellValueFactory(cellData -> cellData.getValue().getUsernamePropery());
                firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstNamePropery());
                roleColumn.setCellValueFactory(cellData -> cellData.getValue().getRolePropery());
                usernameColumn.setCellValueFactory(cellData -> cellData.getValue().getUsernamePropery());
                userTable.setItems(KorisnickaPodrskaApplication.users);
            }
        });
    }

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, String> firstNameColumn;
    @FXML
    private TableColumn<User, String> lastNameColumn;
    @FXML
    private TableColumn<User, String> roleColumn;

    @FXML
    Button register,statistic,settings,addTicket;


    @FXML
    public void onLoginButtonClick(){
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
    @FXML
    public void onImportTemplateButtonClick(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterTxt = new FileChooser.ExtensionFilter("json files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilterTxt);
        File f = fileChooser.showOpenDialog(primaryStage);
        if(f!= null && f.getPath().contains("user")){
//            try {
//                //DBImporter.importUsers(f);
//               // message.setImage(new Image(RequestWithDocumentController.class.getResource("images/ok.png").toExternalForm()));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        }else if(f.getPath().contains("request")){
//            try {
//              //  DBImporter.importRequests(f);
//               // message.setImage(new Image(RequestWithDocumentController.class.getResource("images/ok.png").toExternalForm()));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        }else if(f.getPath().contains("document")){
//            try {
//               // DBImporter.importDocumnets(f);
//               // message.setImage(new Image(RequestWithDocumentController.class.getResource("images/ok.png").toExternalForm()));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        }else{
           // message.setImage(new Image(RequestWithDocumentController.class.getResource("images/error.png").toExternalForm()));
        }
            }




    @FXML
    public void onLRegisterButtonClick(){
        FXMLLoader fxmlLoader = new FXMLLoader(KorisnickaPodrskaApplication.class.getResource("register-user.fxml"));
        fxmlLoader.setController(new RegisterUserController(primaryStage));
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
    public void onAddTicketButtonClick(){
        //updateStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(KorisnickaPodrskaApplication.class.getResource("add-ticket.fxml"));
        fxmlLoader.setController(new AddTicketController(primaryStage,""));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 500);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(500);
        primaryStage.setMaxWidth(600);
        primaryStage.setMaxHeight(500);
        primaryStage.setTitle("Update document");
        primaryStage.setScene(scene);
        //primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    public void addUserButtonClick(){
        FXMLLoader fxmlLoader = new FXMLLoader(KorisnickaPodrskaApplication.class.getResource("register-user.fxml"));
        fxmlLoader.setController(new RegisterUserController(primaryStage));
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
    public void editUserButtonClick(){
        FXMLLoader fxmlLoader = new FXMLLoader(KorisnickaPodrskaApplication.class.getResource("register-user.fxml"));
        fxmlLoader.setController(new RegisterUserController(primaryStage));
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
}
