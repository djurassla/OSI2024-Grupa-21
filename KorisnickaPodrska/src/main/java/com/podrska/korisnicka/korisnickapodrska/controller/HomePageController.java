package com.podrska.korisnicka.korisnickapodrska.controller;

import com.podrska.korisnicka.korisnickapodrska.KorisnickaPodrskaApplication;
import com.podrska.korisnicka.korisnickapodrska.database.TicketsDAO;
import com.podrska.korisnicka.korisnickapodrska.model.Ticket;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;


import java.io.File;
import java.io.IOException;

public class HomePageController {

    private Stage primaryStage;

    public HomePageController(Stage primaryStage) {
        this.primaryStage = primaryStage;

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
                descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
                statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
                createdByColumn.setCellValueFactory(cellData -> cellData.getValue().createdByProperty());
                updatedByColumn.setCellValueFactory(cellData -> cellData.getValue().updatedByProperty());
                assignedColumn.setCellValueFactory(cellData -> cellData.getValue().assignedProperty());
                ticketTable.setItems(KorisnickaPodrskaApplication.tickets);
                setVisibleElement();
            }
        });
    }

    @FXML
    ImageView message;

    @FXML
    private TableView<Ticket> ticketTable;

    @FXML
    private TableColumn<Ticket, Integer> idColumn;
    @FXML
    private TableColumn<Ticket, String> descriptionColumn;
    @FXML
    private TableColumn<Ticket, String> statusColumn;
    @FXML
    private TableColumn<Ticket, String> createdByColumn;
    @FXML
    private TableColumn<Ticket, String> updatedByColumn;

    @FXML
    private TableColumn<Ticket, String> assignedColumn;

    @FXML
    Button register,statistic,settings,addTicket,backToKorisnik,closeTicket,inDevelopment;


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
        message.setImage(null);
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


    private  void setVisibleElement(){
        if("admin".equals(KorisnickaPodrskaApplication.user.getRole())){
            register.setVisible(true);
            statistic.setVisible(true);
            settings.setVisible(true);
            addTicket.setVisible(false);
        }else if("korisnik".equals(KorisnickaPodrskaApplication.user.getRole())){
            register.setVisible(false);
            statistic.setVisible(false);
            settings.setVisible(false);
            addTicket.setVisible(true);
        }else if("operater".equals(KorisnickaPodrskaApplication.user.getRole())){
            register.setVisible(false);
            statistic.setVisible(false);
            settings.setVisible(false);
            addTicket.setVisible(true);
        }else{
            register.setVisible(false);
            statistic.setVisible(false);
            settings.setVisible(false);
            addTicket.setVisible(false);
        }

    }

    @FXML
    public void onBackToKorisnikButtonClick(){

    }

    @FXML
    public void oninDevelopmentButtonClick(){
        TicketsDAO.updateTicket(Integer.valueOf(ticketTable.getSelectionModel().getSelectedItem().getId()),"inDevelopment",KorisnickaPodrskaApplication.user.getUsername());
        KorisnickaPodrskaApplication.tickets =TicketsDAO.selectTickets();
        ticketTable.setItems(KorisnickaPodrskaApplication.tickets);
    }

    @FXML
    public void onClosedButtonClick(){
        TicketsDAO.updateTicket(Integer.valueOf(ticketTable.getSelectionModel().getSelectedItem().getId()),"closed",KorisnickaPodrskaApplication.user.getUsername());
        KorisnickaPodrskaApplication.tickets =TicketsDAO.selectTickets();
        ticketTable.setItems(KorisnickaPodrskaApplication.tickets);

    }

}
