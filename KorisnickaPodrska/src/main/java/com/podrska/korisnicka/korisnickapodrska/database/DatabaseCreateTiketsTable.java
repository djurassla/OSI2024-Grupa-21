package com.podrska.korisnicka.korisnickapodrska.database;

import com.podrska.korisnicka.korisnickapodrska.database.config.H2Connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCreateTiketsTable {

    static public void createTable(){
        try (Connection conn = H2Connection.getConnection(); Statement stmt = conn.createStatement();){
            String createTableQuery = "CREATE TABLE IF NOT EXISTS tickets (\n" +
                    "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "    description TEXT NOT NULL,\n" +
                    "    status VARCHAR(50) NOT NULL,\n" +
                    "    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Datum kreiranja zapisa\n" +
                    "    created_by VARCHAR(50) NOT NULL,\n" +
                    "    updated_by VARCHAR(50) NULL,\n" +
                    "    assigned VARCHAR(50) NOT NULL,\n" +
                    "    FOREIGN KEY (created_by) REFERENCES users(username),\n" +
                    "    FOREIGN KEY (updated_by) REFERENCES users(username),\n" +
                    "    FOREIGN KEY (assigned) REFERENCES users(username)\n" +
                    ");";
            stmt.execute(createTableQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
