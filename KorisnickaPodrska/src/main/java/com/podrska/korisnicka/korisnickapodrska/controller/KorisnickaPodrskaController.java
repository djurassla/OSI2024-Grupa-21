package com.podrska.korisnicka.korisnickapodrska.controller;

import com.podrska.korisnicka.korisnickapodrska.KorisnickaPodrskaApplication;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
//import org.managment.system.managmentsystem.enums.Action;
//import org.managment.system.managmentsystem.mongodb.service.DBDeleter;
//import org.managment.system.managmentsystem.mongodb.service.DBFinder;
//import org.managment.system.managmentsystem.mongodb.service.DBInserter;
//import org.managment.system.managmentsystem.mongodb.service.DBUpdater;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KorisnickaPodrskaController {
    @FXML
    private Label welcomeText;
    @FXML
    private GridPane gridPane;
    @FXML
    private AnchorPane anchor00, anchor10, anchor20, anchor30, anchor40, anchor50, anchor60, anchor01, anchor11, anchor21, anchor31, anchor41, anchor51, anchor61,
            anchor02, anchor12, anchor22, anchor32, anchor42, anchor52, anchor62;
    private final Stage primaryStage;
    private List<AnchorPane> anchorPaneList = new ArrayList<>();
    private Button selectedButton = null;
    @FXML
    Button buttonNew,buttonEdit,buttonDelete;

    private TextField mouseEntered =  null;
    //private Action action =null;
    private String oldName = null;
    public KorisnickaPodrskaController(Stage stage) {
        this.primaryStage = stage;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                anchorPaneList.addAll(Arrays.asList(anchor00, anchor10, anchor20, anchor30, anchor40, anchor50, anchor60, anchor01, anchor11, anchor21, anchor31, anchor41, anchor51, anchor61,
                        anchor02, anchor12, anchor22, anchor32, anchor42, anchor52, anchor62));
                setTextFieldEventHandler();
                setGlobalEventHandler();
                Scene scene = stage.getScene();
                scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        for (AnchorPane ap : anchorPaneList) {
                            List<Node> element = ap.getChildren();
                            for (Node n : element) {
                                if (n instanceof TextField) {
                                    TextField tf = (TextField) n;
                                    if(tf.isFocused() && !tf.equals(mouseEntered) && !"".equals(tf.getText())){
                                        tf.setDisable(true);
                                    }
                                }
                            }
                        }
                    }
                });
                List<String> requests = null;//DBFinder.listRequests();
                if(requests.size()>0) {
                    buttonEdit.setDisable(false);
                    buttonDelete.setDisable(false);
                    for (String reguest : requests) {
                        AnchorPane temp = null;
                        for (AnchorPane ap : anchorPaneList) {
                            if (!ap.isVisible()) {
                                ap.setVisible(true);
                                temp = ap;
                                break;
                            }
                        }
                        List<Node> element = temp.getChildren();
                        for (Node n : element) {
                            if (n instanceof TextField) {
                                TextField tf = (TextField) n;
                                tf.setText(reguest);
                                tf.setDisable(true);
                                break;
                            }
                        }
                    }
                }
            }
        });
    }

    @FXML
    protected void onNewRequestButtonClick() {
        setButtonsDisable(true,true,true);
        for (AnchorPane ap : anchorPaneList) {
            if (!ap.isVisible()) {
                ap.setVisible(true);
                List<Node> element = ap.getChildren();
                for (Node n : element) {
                    if (n instanceof TextField) {
                        TextField tf = (TextField) n;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                tf.requestFocus();
                                //action = Action.INSERT;
                            }
                        });
                    }else if (n instanceof Button) {
                        Button b = (Button) n;
                        selectedButton = b;
                    }
                }
                break;
            }
        }



     /*   for(int i=0;i<1000;i++){
            Button b = new Button();
            b.setText("Test "+i);
            hBox.getChildren().add(b);
        }*/
     /*   FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("XLS files (*.xls)", "*.xls");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.getExtensionFilters().add(extFilter1);


        File file = fileChooser.showOpenDialog(primaryStage);
        System.out.println(file.getName());*/
    }
    @FXML
    public void onLogout(){
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
    protected void onEditRequestButtonClick() {
        setButtonsDisable(true,true,true);
      AnchorPane ap =(AnchorPane) selectedButton.getParent();
        List<Node> element = ap.getChildren();
        for (Node n : element) {
            if (n instanceof TextField) {
              TextField tf = (TextField) n;
              tf.setDisable(false);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        tf.requestFocus();
                       // action = Action.UPDATE;
                        oldName = tf.getText();
                    }
                });
                break;
            }
        }
    }
    @FXML
    protected void onDeleteRequestButtonClick() {
        AnchorPane ap = (AnchorPane) selectedButton.getParent();
        ap.setVisible(false);
        List<Node> element = ap.getChildren();
        boolean hasVisible = false;
        for (Node n : element) {
            if (n instanceof TextField) {
                TextField tf = (TextField) n;
               // DBDeleter.deleteRequest(tf.getText());
                tf.setDisable(false);
                tf.setText("");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        tf.requestFocus();
                    }
                });
                break;
            }
        }
        for (int i = anchorPaneList.size()-1;i>=0;i--) {
            AnchorPane ap2 =anchorPaneList.get(i);
            if (ap2.isVisible()) {
                List<Node> element2 = ap2.getChildren();
                for (Node n : element2) {
                   if (n instanceof Button) {
                        Button b = (Button) n;
                        b.requestFocus();
                        selectedButton = b;
                        hasVisible = true;
                    }
                }
                break;
            }
        }
        if(!hasVisible){
            setButtonsDisable(false,true,true);
        }
    }

    @FXML
    public void onMouseEntered(MouseEvent e)
    {
        TextField tf  = (TextField) e.getSource();
        mouseEntered = tf;
    }

    @FXML
    public void onMouseExited(MouseEvent e)
    {
        mouseEntered = null;
    }
    //welcomeText.setText("Welcome to JavaFX Application!");
      /*  try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> coll = db.getCollection("users");
            Document doc = new Document("name", "john").append("age",
                    25) .append("phone", "321-654-987");
            coll.insertOne(doc);
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " +
                    e.getMessage());
        }
    }*/
    private void setTextFieldEventHandler() {
        for (AnchorPane ap : anchorPaneList) {
            List<Node> element = ap.getChildren();
            for (Node n : element) {
                if (n instanceof TextField) {
                    TextField tf = (TextField) n;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                          tf.focusedProperty().addListener(new ChangeListener<Boolean>()
                            {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
                                {
                                    if (!newPropertyValue)
                                    {
                                        tf.setDisable(true);
                                      //  setButtonsDisable(false,false,false);
                                        if(/*action == Action.INSERT &&*/ !"".equals(tf.getText())) {
                                           // DBInserter.insertNewRequest(tf.getText());
                                           // action = null;
                                        }else if(true/*action == Action.UPDATE*/){
                                            if(oldName != null && !oldName.equals(tf.getText())) {
                                               // DBUpdater.updateRequest(oldName, tf.getText());
                                               // action = null;
                                            }
                                            oldName = null;
                                        }
                                    }
                                }
                            });
                        }
                    });
                }else if (n instanceof Button) {
                    Button b = (Button) n;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            b.focusedProperty().addListener(new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                                    if (newPropertyValue) {
                                       selectedButton = b;
                                    }
                                }
                            });
                            b.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                                        if(mouseEvent.getClickCount() == 2){
                                            FXMLLoader fxmlLoader = new FXMLLoader(KorisnickaPodrskaApplication.class.getResource("request-with-document.fxml"));
                                           // fxmlLoader.setController(new RequestWithDocumentController(primaryStage,getFolderName(mouseEvent)));
                                            Scene scene = null;
                                            try {
                                                scene = new Scene(fxmlLoader.load(), 1366, 700);
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
                                            primaryStage.setTitle(getFolderName(mouseEvent));
                                            primaryStage.setScene(scene);
                                            primaryStage.setMaximized(true);
                                            primaryStage.setResizable(false);
                                            primaryStage.show();
                                        }
                                    }
                                }
                            });
                        }
                    });
                }
            }
        }
    }

    private void setGlobalEventHandler() {
        gridPane.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
            if (ev.getCode() == KeyCode.ENTER) {
                for (AnchorPane ap : anchorPaneList) {
                        List<Node> element = ap.getChildren();
                        for (Node n : element) {
                            if (n instanceof TextField) {
                                TextField tf = (TextField) n;
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(tf.isFocused()) {
                                            tf.setDisable(true);
                                            return;
                                        }
                                    }
                                });
                            }
                        }
                }
            }
        });
    }
private String getFolderName(MouseEvent mouseEvent){
    Button button = (Button) mouseEvent.getSource();
    AnchorPane anchorPane = (AnchorPane) button.getParent();
    for(Node node:anchorPane.getChildren()){
        if(node instanceof TextField){
            TextField textField = (TextField) node;
            return textField.getText();
        }
    }
    return "";
}
private void setButtonsDisable(boolean buttonNew,boolean buttonEdit,boolean buttonDelete){
    this.buttonNew.setDisable(buttonNew);
    this.buttonEdit.setDisable(buttonEdit);
    this.buttonDelete.setDisable(buttonDelete);
}
}