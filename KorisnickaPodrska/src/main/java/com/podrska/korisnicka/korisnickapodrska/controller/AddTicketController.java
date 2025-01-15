package com.podrska.korisnicka.korisnickapodrska.controller;

import com.podrska.korisnicka.korisnickapodrska.KorisnickaPodrskaApplication;
import com.podrska.korisnicka.korisnickapodrska.database.TicketsDAO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
//import org.managment.system.managmentsystem.RequestWithDocumentController;
//import org.managment.system.managmentsystem.enums.ChoserType;


public class AddTicketController {

    private Stage primaryStage;

    @FXML
    TextField fileName;
    @FXML
    Label title,errorMessage;
    @FXML
    TextArea description;
    private String shortTitle;
    //private RequestWithDocumentController requestWithDocumentController;

    public AddTicketController(Stage stage, String title1/*, RequestWithDocumentController requestWithDocumentController*/) {
        this.primaryStage = stage;
        this.shortTitle = title1;
       // this.requestWithDocumentController = requestWithDocumentController;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                title.setText(title.getText() + title1);
            }
        });
    }

    @FXML
    public void onChosFileClick(){
      /*  if(file != null && !"".equals(file.getName())) {
            if(!file.getName().equals(shortTitle)){
                errorMessage.setText("File must have same name an type !");
            }else {
                fileName.setText(file.getName());
            }
        }*/
    }

    @FXML
    public void onSaveButtonClick(){
        TicketsDAO.addTicket(description.getText(), KorisnickaPodrskaApplication.user.getUsername(),"open","system");
        KorisnickaPodrskaApplication.tickets =TicketsDAO.selectTickets();
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
    }


}
