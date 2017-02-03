package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
/**
 * Gestisce la connessione con il database
 *
 * @author stefano
 */
public class DB_Manager {
    Connection con;
    final String DRIVER = "org.postgresql.Driver"; //Nome del driver
    final String DB_NAME = "jdbc:postgresql://localhost:5432/DoveCibo"; //Nome del database completo di percorso
    final String DB_USER = "postgres"; // Nome utente
    final String DB_PASSWORD = "postgres"; //Password
 
    /**
     * Costruttore, connessione al db
     *
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public DB_Manager() throws SQLException {
        try {
            Class.forName(DRIVER); //Carica il driver
            con = DriverManager.getConnection(DB_NAME, DB_USER, DB_PASSWORD);  //Effettua la connessione al database
            //Effettua la connessione al database
            System.out.println("Connessione al server DBMS avvenuta con successo!");
        } catch (Exception e) {
            System.err.println("Errore: " + e.getMessage());  ///da sostituire
            con.close();
        }
    }
}