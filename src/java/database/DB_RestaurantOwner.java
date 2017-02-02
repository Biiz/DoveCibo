package database;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import restaurants.Restaurant;
import users.User;

/**
 *
 * @author michael
 */
public class DB_RestaurantOwner extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String errore = "";
    DB_Manager connessione;

    /**
     * Costruttore
     *
     * @throws SQLException
     */
    public DB_RestaurantOwner() throws SQLException {
        connessione = new DB_Manager ();
    }
    
    /**
     * Ritorna messaggio di errore
     *
     * @return
     */
    public String getErrore() { return errore; }
    
    /**
     * Crea relazione tra ristorante e ristoratore
     *
     * @param id_restourant
     * @param id_owner
     * @return
     * @throws SQLException
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
     * @param res
     * @return
     * @throws SQLException
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
     * @param u
     * @return
     * @throws SQLException
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
     * @param idRO
     * @param val
     * @return
     * @throws SQLException
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
     * @param idRO
     * @param val
     * @param user
     * @return
     * @throws SQLException
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
     * @param user
     * @param res
     * @return
     * @throws SQLException
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
