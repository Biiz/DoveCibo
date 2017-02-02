package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import notifiche.Replies;
import restaurant_comments.Review;
import users.User;

/**
 * Gestisce le informazioni sulle risposte ai commenti nel database
 *
 * @author michael
 */
public class DB_Replies extends HttpServlet {
    private String errore = "";
    DB_Manager connessione;

    /**
     * Costruttore
     *
     * @throws SQLException
     */
    public DB_Replies() throws SQLException {
        connessione = new DB_Manager ();
    }
    
    /**
     * Ritorna messaggio di errore
     *
     * @return
     */
    public String getErrore() { return errore; }
    
    /**
     * Aggiungi nuova risposta
     *
     * @param rep
     * @param idRew
     * @return
     * @throws SQLException
     */
    public Boolean inserisciRisposta(Replies rep, Integer idRew) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "INSERT INTO replies(id,description, DATE_CREATION, id_review, id_owner, date_validation, id_validator)"
                    + "VALUES(DEFAULT,?,DEFAULT,?,?,NULL, NULL)";
            sp = connessione.con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            sp.setString(1, rep.getDescription());
            sp.setInt(2, idRew);
            sp.setInt(3, rep.getOwner().getId());
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
     * Cerca risposte in base al commento
     *
     * @param rew
     * @param replies
     * @return
     * @throws SQLException
     */
    public Boolean setRepli_perRew(Review rew, ArrayList <Replies> replies) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
 
        try {
            query = "SELECT * FROM REPLIES WHERE ID_REVIEW = ? AND ID_VALIDATOR IS NOT NULL";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, rew.getId());
            
            ResultSet rs = sp.executeQuery();            
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id_owner"));
                new DB_GestioneUser().cercaUser_perId(user);
                
                replies.add(new Replies(
                          rs.getInt("id"), rs.getString("description"), 
                          rs.getDate("date_creation"),rs.getDate("date_creation"),
                          new User(rs.getInt("id_validator")),
                          user,
                          rs.getInt("id_review"))); 
            }
            
            r=true;
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
     * Cerca risposte validate per commenti
     *
     * @param rew
     * @return
     * @throws SQLException
     */
    public Boolean setRepli(Review rew) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
 
        try {
            query = "SELECT * FROM REPLIES WHERE ID_REVIEW = ? AND ID_VALIDATOR IS NOT NULL";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, rew.getId());
            
            ResultSet rs = sp.executeQuery();            
            if (rs.next()) {
                User user = new User(rs.getInt("id_owner"));
                new DB_GestioneUser().cercaUser_perId(user);
                
                Replies rep = new Replies(
                          rs.getInt("id"),  rs.getString("description"), 
                          rs.getDate("date_creation"),rs.getDate("date_creation"),
                          new User(rs.getInt("id_validator")),
                          user,
                          rs.getInt("id_review"));
                
                rew.setRepile(rep); 
            }
            
            r=true;
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
     * Aggiorna commento
     *
     * @param idRep
     * @param val
     * @return
     * @throws SQLException
     */
    public Boolean updateRepli(Integer idRep, User val) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        
        try {
            query = "UPDATE REPLIES SET ID_VALIDATOR = ? , DATE_VALIDATION = DEFAULT WHERE ID = ?";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, val.getId());
            sp.setInt(2, idRep);
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
     * Cancella commento
     *
     * @param user
     * @return
     * @throws SQLException
     */
    public Boolean deleteRepli(User user) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        
        try {
            query = "DELETE FROM REPLIES WHERE ID_VALIDATOR IS NULL AND id_owner = ?";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, user.getId());
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
     * Controlla esistenza risposta per commento e proprietario
     *
     * @param rep
     * @param idRew
     * @param check
     * @return
     * @throws SQLException
     */
    public Boolean checkReplies(Replies rep, Integer idRew, ArrayList <String> check ) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
 
        try {
            query = "SELECT * FROM REPLIES WHERE ID_VALIDATOR IS NOT NULL AND id_review = ? AND id_owner=?";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, idRew);
            sp.setInt(2, rep.getOwner().getId());
            
            ResultSet rs = sp.executeQuery();
            if (rs.next())
                check.add("yes");
            else
                check.add("no");
            
            r = true;
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
}
