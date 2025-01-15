module com.podrska.korisnicka.korisnickapodrska {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires com.h2database;


    opens com.podrska.korisnicka.korisnickapodrska to javafx.fxml;
    opens com.podrska.korisnicka.korisnickapodrska.controller to javafx.fxml;
    exports com.podrska.korisnicka.korisnickapodrska;
}