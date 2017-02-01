package database;

import notifiche.Notifica;
import notifiche.Replies;
import restaurants.Restaurant;
import restaurant_comments.Review;
import users.User;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author michael
 */
public class DB_Notifica extends HttpServlet {
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

    public DB_Notifica() throws SQLException {
        connessione = new DB_Manager ();
    }
    
    public String getErrore() { return errore; }
    
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
  
                ALN.add( new Notifica("<p>RECLAMO</p>"+
                                      "<p>ristorante: <b><a href='/DoveCiboGit/ServletGetRistorante?idR="+res.getId()+" '>"+res.getName()+"</a></b></p>", 
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
  
                ALN.add( new Notifica("<p>RECLAMO</p>"+
                                      "<p>ristorante: <b>"+res.getName()+"</b></p>", 
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
                
                ALN.add(new Notifica("<p>RISPOSTA COMMENTO</p>"+
                                     "<p>Ristorante: <b><a href='/DoveCiboGit/ServletGetRistorante?idR="+rest.getId()+" '>"+rest.getName()+"</a></b></p>"+
                                     "<p>commento: <b>"+rev.getDescription()+"</b></p>"+
                                     "<p>autore commento: <b>"+rev.getCreator().getNickname()+"</b></p>"+
                                     "<p>risposta_ristoratore: <b>"+rep.getDescription()+"</b></p>", 
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
                          rs.getInt("id"),  rs.getString("description"), 
                          rs.getDate("date_creation"),rs.getDate("date_creation"),
                          new User(rs.getInt("id_validator")),
                          user,
                          rs.getInt("id_review"));
                
                ALN.add(new Notifica("<p>RISPOSTA COMMENTO</p>"+
                                     "<p>Ristorante: <b>"+rest.getName()+"</b></p>", 
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
