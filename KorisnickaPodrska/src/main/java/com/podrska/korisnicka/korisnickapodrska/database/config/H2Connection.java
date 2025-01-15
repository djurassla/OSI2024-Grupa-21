package com.podrska.korisnicka.korisnickapodrska.database.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection {
    public static Connection getConnection() throws SQLException {
        // In-memory baza (baza postoji samo dok je aplikacija pokrenuta)
        String url = "jdbc:h2:C:\\Users\\PC\\IdeaProjects\\KorisnickaPodrska\\DB\\h2;AUTO_SERVER=TRUE";
        String user = "sa";
        String password = "";

        return DriverManager.getConnection(url, user, password);
    }
}
