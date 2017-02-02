package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import notifiche.Notifica;
import notifiche.Replies;
import restaurant_comments.Review;
import restaurants.Restaurant;
import users.User;

/**
 * Gestisce le informazioni sulle notifiche nel database
 *
 * @author michael
 */
public class DB_Notifica extends HttpServlet {
    /**
     * Gestisce le informazioni sulle notifiche nel database
     */
    private String errore = "";
    DB_Manager connessione;

    /**
     * Costruttore
     *
     * @throws SQLException
     */
    public DB_Notifica() throws SQLException {
        connessione = new DB_Manager ();
    }
    
    /**
     * Ritorta messaggio di errore
     *
     * @return
     */
    public String getErrore() { return errore; }
    
    /**
     * Cerca notifiche di reclamo di un ristorante
     *
     * @param ALN
     * @return
     * @throws SQLException
     */
    public Boolean setNotificheReclamo (ArrayList <Notifica> ALN) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
        
        try {
            query = "SELECT * FROM restaurant_owner WHERE  id_validator is null";
            sp = connessione.con.prepareStatement(query);
       
            ResultSet rs = sp.executeQuery();
            while (rs.next()) {
                Restaurant res = new Restaurant(rs.getInt("id_restaurant"));
                new DB_GestioneRestaurant().cercaRistorante_perId(res);
                
                User u = new User(rs.getInt("id_owner"));
                new DB_GestioneUser().cercaUser_perId(u);
  
                ALN.add( new Notifica("RECLAMO: " +
                                      "Ristorante: " + res.getName(), 
                                      rs.getDate("date_creation"), "reclama", rs.getInt("id"), u));
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            System.out.println("Accesso fallito");
            System.out.println("Possibile causa: " + e.getMessage());
            r=false;
        } finally {
            sp.close();
            connessione.con.close();
            return r;
        }
    }
    
    /**
     * Cerca notifiche di reclamo di un ristorante senza link
     *
     * @param ALN
     * @return
     * @throws SQLException
     */
    public Boolean setNotificheReclamoNoLink (ArrayList <Notifica> ALN) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
        
        try {
            query = "SELECT * FROM restaurant_owner WHERE  id_validator is null";
            sp = connessione.con.prepareStatement(query);
       
            ResultSet rs = sp.executeQuery();
            while (rs.next()) {
                Restaurant res = new Restaurant(rs.getInt("id_restaurant"));
                new DB_GestioneRestaurant().cercaRistorante_perId(res);
                
                User u = new User(rs.getInt("id_owner"));
                new DB_GestioneUser().cercaUser_perId(u);
  
                ALN.add( new Notifica("RECLAMO: " + 
                                      "ristorante: " + res.getName(), 
                                      rs.getDate("date_creation"), "reclama", rs.getInt("id"), u));
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            System.out.println("Accesso fallito");
            System.out.println("Possibile causa: " + e.getMessage());
            r=false;
        } finally {
            sp.close();
            connessione.con.close();
            return r;
        }
    }
    
    /**
     * Cerca notifiche di risposta da confermare
     *
     * @param ALN
     * @return
     * @throws SQLException
     */
    public Boolean setNotificheRepil_daConfermare(ArrayList <Notifica> ALN ) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
 
        try {
            query = "SELECT  * FROM REPLIES WHERE ID_VALIDATOR IS NULL";
            sp = connessione.con.prepareStatement(query);

            ResultSet rs = sp.executeQuery();            
            while (rs.next()) {
                User user = new User(rs.getInt("id_owner"));
                new DB_GestioneUser().cercaUser_perId(user);
                Review rev = new Review();
                rev.setId(rs.getInt("id_review"));
                new DB_Reviews().setReviewPerId(rev);
                
                Restaurant rest = new Restaurant(rev.getLike());
                new DB_GestioneRestaurant().cercaRistorante_perId(rest);
                
                Replies rep = new Replies(
                          rs.getInt("id"),  rs.getString("description"), 
                          rs.getDate("date_creation"),rs.getDate("date_creation"),
                          new User(rs.getInt("id_validator")),
                          user,
                          rs.getInt("id_review"));
                
                ALN.add(new Notifica("RISPOSTA AL COMMENTO - " +
                                     "Ristorante: " + rest.getName() +
                                     ". Commento: " + rev.getDescription()+
                                     "Autore del commento: " + rev.getCreator().getNickname()+
                                     ". - Risposta del Ristoratore: " + rep.getDescription(), 
                        rep.getDate_creation(), 
                        "confermaRep", rep.getId(), rep.getOwner()));
            }
        } catch (SQLException e) {
            this.errore = e.toString();
            System.out.println(errore);
            r = false;
        } finally {
            sp.close();
            connessione.con.close();
            return r;
        }
    }
    
    /**
     * Cerca notifiche di risposta da condermare senza link
     *
     * @param ALN
     * @return
     * @throws SQLException
     */
    public Boolean setNotificheRepil_daConfermareNoLink(ArrayList <Notifica> ALN ) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
        
        try {
            query = "SELECT  * FROM REPLIES WHERE ID_VALIDATOR IS NULL";
            sp = connessione.con.prepareStatement(query);

            ResultSet rs = sp.executeQuery();            
            while (rs.next()) {
                User user = new User(rs.getInt("id_owner"));
                new DB_GestioneUser().cercaUser_perId(user);
                Review rev = new Review();
                rev.setId(rs.getInt("id_review"));
                new DB_Reviews().setReviewPerId(rev);
                
                Restaurant rest = new Restaurant(rev.getLike());
                new DB_GestioneRestaurant().cercaRistorante_perId(rest);
                
                Replies rep = new Replies(
                          rs.getInt("id"), rs.getString("description"), 
                          rs.getDate("date_creation"),rs.getDate("date_creation"),
                          new User(rs.getInt("id_validator")),
                          user,
                          rs.getInt("id_review"));
                
                ALN.add(new Notifica("RISPOSTA AL COMMENTO - " +
                                     "Ristorante: " + rest.getName(), 
                                     rep.getDate_creation(), 
                                     "confermaRep", rep.getId(), rep.getOwner()));
            }
        } catch (SQLException e) {
            this.errore = e.toString();
            System.out.println(errore);
            r = false;
        } finally {
            sp.close();
            connessione.con.close();
            return r;
        }
    }
    
    /**
     * Rifiuta commento di risposta
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public Boolean rifiutaRisposta(Integer id) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        
        try {
            query = "DELETE FROM REPLIES WHERE ID = ?";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, id);
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
     * Rifiuta reclamo ristorante
     *
     * @param idRO
     * @return
     * @throws SQLException
     */
    public Boolean rifiutaReclamo(Integer idRO) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        
        try {
            query = "DELETE FROM RESTAURANT_OWNER WHERE ID = ?";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, idRO);
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
}
