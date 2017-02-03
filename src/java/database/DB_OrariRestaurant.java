package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServlet;
import restaurants.Day_hours;

/**
 * Gestisce le informazioni sugli orari dei ristoranti nel database
 *
 * @author michael
 */
public class DB_OrariRestaurant extends HttpServlet {
    private String errore = "";
    DB_Manager connessione;

    /**
     * Costruttore
     *
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public DB_OrariRestaurant() throws SQLException {
        connessione = new DB_Manager ();
    }
    
    /**
     * Ritorna messaggio di errore
     *
     * @return stringa con messaggio di errore
     */
    public String getErrore() { return errore; }
    
    /**
     * Aggiorna l'orario di apertura di un ristorante
     *
     * @param dh orari apertura
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean updateOrario(Day_hours dh) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "UPDATE opening_hours_ranges SET start_hour_m = ?, start_min_m = ?, end_hour_m = ?, end_min_m = ?, start_hour_p = ?, start_min_p = ?, end_hour_p = ?, end_min_p = ? WHERE id_restaurant = ?";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, dh.getStart_H_M());
            sp.setInt(2, dh.getStart_M_M());
            sp.setInt(3, dh.getEnd_H_M());
            sp.setInt(4, dh.getEnd_M_M());
            sp.setInt(5, dh.getStart_H_P());
            sp.setInt(6, dh.getStart_M_P());
            sp.setInt(7, dh.getEnd_H_P());
            sp.setInt(8, dh.getEnd_M_P());
            sp.setInt(9, dh.getId_restaurant());
            sp.executeUpdate();
            r = true;
        } catch (SQLException e) {
            System.out.println("Possibile causa: " + e.getMessage());
            r = false;
        } finally {
            sp.close();
            connessione.con.close();
            return r;
        }
    }
    
    /**
     * Aggiunge un orario di apertura di un ristorante
     *
     * @param dh orari apertura
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean inserisciOrario(Day_hours dh) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "INSERT INTO opening_hours_ranges(id_restaurant, start_hour_m, start_min_m, end_hour_m, end_min_m, start_hour_p, start_min_p, end_hour_p, end_min_p)"
                    + "VALUES(?,?,?,?,?,?,?,?,?)";
            sp = connessione.con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            sp.setInt(1, dh.getId_restaurant());
            sp.setInt(2, dh.getStart_H_M());
            sp.setInt(3, dh.getStart_M_M());
            sp.setInt(4, dh.getEnd_H_M());
            sp.setInt(5, dh.getEnd_M_M());
            sp.setInt(6, dh.getStart_H_P());
            sp.setInt(7, dh.getStart_M_P());
            sp.setInt(8, dh.getEnd_H_P());
            sp.setInt(9, dh.getEnd_M_P());
            sp.executeUpdate();
            r = true;
        } catch (SQLException e) {
            r = false;
        } finally {
            sp.close();
            connessione.con.close();
            return r;
        }
    }
    
    /**
     * Cerca l'orario di apertura dato un id
     *
     * @param h orari apertura
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean cercaDay_hours_perId(Day_hours h) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "SELECT  * FROM OPENING_HOURS_RANGES WHERE ID_RESTAURANT = ? ";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, h.getId_restaurant());
 
            ResultSet rs = sp.executeQuery();
            System.out.println("Query Ok");
            if (rs.next()) {
                        h.setStart_H_M(rs.getInt("START_HOUR_M"));
                        h.setStart_M_M(rs.getInt("START_MIN_M"));
                        h.setEnd_H_M(rs.getInt("END_HOUR_M"));
                        h.setEnd_M_M(rs.getInt("END_MIN_M"));
                        h.setStart_H_P(rs.getInt("START_HOUR_P"));
                        h.setStart_M_P(rs.getInt("START_MIN_P"));
                        h.setEnd_H_P(rs.getInt("END_HOUR_P"));
                        h.setEnd_M_P(rs.getInt("END_MIN_P"));
                        
            } else
                System.out.println("Non esiste tale Ristorante");
            
            r = true;
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            System.out.println("Accesso fallito");
            System.out.println("Possibile causa: " + e.getMessage());
            r = false;
        } finally {
            sp.close();
            connessione.con.close();
            return r;
        }
    }
}
