package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import restaurants.Restaurant;
import users.User;

/**
 * Gestisce le informazioni sui proprietari di ristoranti nel database
 *
 * @author michael
 */
public class DB_RestaurantOwner extends HttpServlet {
    /**
     * Gestisce le informazioni sui proprietari di ristoranti nel database
     */
    private String errore = "";
    DB_Manager connessione;

    /**
     * Costruttore
     *
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public DB_RestaurantOwner() throws SQLException {
        connessione = new DB_Manager ();
    }
    
    /**
     * Ritorna messaggio di errore
     *
     * @return stringa con messaggio di errore
     */
    public String getErrore() { return errore; }
    
    /**
     * Crea relazione tra ristorante e ristoratore
     *
     * @param id_restourant id ristorante
     * @param id_owner id proprietario
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean inserisciRelazioneOwnerRestaurant(Integer id_restourant, Integer id_owner) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "INSERT INTO restaurant_owner(id_restaurant, id_owner, id_validator)" + "VALUES(?,?, NULL)";
            sp = connessione.con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            sp.setInt(1, id_restourant);
            sp.setInt(2, id_owner);
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
     * Cerca ristoratori di un ristorante
     *
     * @param res ristorante
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean cercaOwners_perRistoranti( Restaurant res) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
 
        try {
            query = "SELECT * FROM RESTAURANT_OWNER WHERE ID_RESTAURANT = ? AND ID_VALIDATOR IS NOT NULL";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, res.getId());
            
            ResultSet rs = sp.executeQuery();            
            while (rs.next()) {          
                User ow = new User(rs.getInt("ID_OWNER"));
                System.out.println("Owner Id = " + ow.getId());
                res.addOwner(ow); 
            }
        } catch (SQLException e) {
            this.errore = e.toString();
            r = false;
        } finally {
            sp.close();
            connessione.con.close();
            return r;
        }
    } 
    
    /**
     * Cerca ristoranti di un ristoratore
     *
     * @param u utente
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public ArrayList <Restaurant> cercaRistoranti_perOwner(User u) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        ArrayList <Restaurant> ALR = new ArrayList<Restaurant>();
        
        try {
            query = "SELECT  * FROM restaurant_owner WHERE id_owner = ? AND id_validator is not null";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, u.getId());         
            
            ResultSet rs = sp.executeQuery();
            while (rs.next())
                ALR.add( new Restaurant(rs.getInt("id_restaurant")) );
            
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            System.out.println("Accesso fallito");
            System.out.println("Possibile causa: " + e.getMessage());
        } finally {
            sp.close();
            connessione.con.close();
            return ALR;
        }
    }
    
    /**
     * Aggiorna ristoratore 
     *
     * @param idRO id proprietario
     * @param val validatore (admin)
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean updateResOwn(Integer idRO, User val) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        
        try {
            query = "UPDATE RESTAURANT_OWNER SET ID_VALIDATOR = ? WHERE ID = ?";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, val.getId());
            sp.setInt(2, idRO);
            sp.executeUpdate();
            r = true;
        } catch (SQLException e) {
            System.out.println("Possibile causa: " + e.getMessage());
            errore = e.toString();
            r = false;
        } finally {
            sp.close();
            connessione.con.close();
            return r;
        }
    }
    
    /**
     * Cerca risotratore per valitadore e id restaurant owner
     *
     * @param idRO id proprietario
     * @param val utente validatore (admin)
     * @param user utente
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean selectOwn(Integer idRO, User val, User user) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        
        try {
            query = "SELECT id_owner FROM RESTAURANT_OWNER WHERE ID_VALIDATOR = ? AND ID = ?";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, val.getId());
            sp.setInt(2, idRO);
            
            ResultSet rs = sp.executeQuery();
            if (rs.next())
                user.setId(rs.getInt("id_owner"));
            
            r = true;
        } catch (SQLException e) {
            System.out.println("Possibile causa: " + e.getMessage());
            errore = e.toString();
            r = false;
        } finally {
            sp.close();
            connessione.con.close();
            return r;
        }
    }
    
    /**
     * Controlla se un utente e' un ristoratore del ristorante
     *
     * @param user utente
     * @param res ristorante
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean isOwners_perRistoranti(User user, Restaurant res) throws SQLException { 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = false;
 
        try {
            query = "SELECT * FROM RESTAURANT_OWNER WHERE ID_RESTAURANT = ? AND ID_VALIDATOR IS NOT NULL AND ID_OWNER = ?";
            sp = connessione.con.prepareStatement(query);            
            sp.setInt(1, res.getId());
            sp.setInt(2, user.getId());
            
            ResultSet rs = sp.executeQuery();            
            if (rs.next())         
                r = true;
        } catch (SQLException e) {
            this.errore = e.toString();
            r = false;
        } finally {
            sp.close();
            connessione.con.close();
            return r;
        }
    }
}
