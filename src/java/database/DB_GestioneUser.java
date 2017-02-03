package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServlet;
import restaurants.Restaurant;
import users.User;

/**
 * Gestisce le informazioni sugli user nel database
 *
 * @author stefano
 */
public class DB_GestioneUser extends HttpServlet {
    private String errore = "";
    DB_Manager connessione;

    /**
     * Costruttore
     *
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public DB_GestioneUser() throws SQLException {
        connessione = new DB_Manager ();
    }
    
    /**
     * Ritorna messaggio di errore
     *
     * @return stringa con messaggio di errore
     */
    public String getErrore() { return errore; }
    
    /**
     * Inserisce nuovo account
     *
     * @param u utente
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean inserisciAccount(User u) throws SQLException {
            PreparedStatement sp = null;
            String query = null;

            try {
                query = "INSERT INTO users(id,name,surname,nickname,email,password,role,love) VALUES(DEFAULT,?,?,?,?,?,?,0)";
                sp = connessione.con.prepareStatement(query);
                sp.setString(1, u.getName());
                sp.setString(2, u.getSurname());
                sp.setString(3, u.getNickname());
                sp.setString(4, u.getEmail());
                sp.setString(5, u.getPassword());
                sp.setString(6, u.getRole());
                sp.executeUpdate();
                return true;
            } catch (SQLException e) {
                this.errore = e.toString();
                return false;
            } finally {
                sp.close();
                connessione.con.close();
            }
    }

    /**
     * Controlla se il nickname esiste gia'
     *
     * @param nik nickname
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean niknameEsistente(String nik) throws SQLException {
            Boolean r = false;
            PreparedStatement sp = null;
            String query = null;

            try {
                query = "SELECT  * FROM users WHERE nickname LIKE ? ";
                sp = connessione.con.prepareStatement(query);
                sp.setString(1, nik);
                ResultSet rs = sp.executeQuery();

                if (rs.next())
                    r = true;
            } catch (SQLException e) {

            } finally {
                sp.close();
                connessione.con.close();
                return r;
            }
    }
    
    /**
     * Controlla se la mail esiste gia'
     *
     * @param em email
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean emailEsistente(String em) throws SQLException {
        Boolean r = false;
        PreparedStatement sp = null;
        String query = null;
 
        try {
            query = "SELECT  * FROM users WHERE email LIKE ? ";
            sp = connessione.con.prepareStatement(query);
            sp.setString(1, em);
            ResultSet rs = sp.executeQuery();
 
            if (rs.next())
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
     * Prende le info dell'utente attraverso la mail 
     *
     * @param u utente
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public boolean CheckEmail (User u) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
       
        try {
            query = "SELECT id, name, surname, nickname, password, role FROM users WHERE email LIKE ? ";
            sp = connessione.con.prepareStatement(query);
            sp.setString(1, u.getEmail());
            ResultSet rs = sp.executeQuery();
           
            if(rs.next()){
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));          
                u.setSurname(rs.getString("surname"));
                u.setNickname(rs.getString("nickname"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
            }
            return true;
        } catch (SQLException e) {
            this.errore = e.toString();
            return false;
        } finally {
            sp.close();
            connessione.con.close();
        }
    }
    
    /**
     * Modifica l'account 
     *
     * @param u utente
     * @param pw password
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean modificaAccount(User u, String pw) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "UPDATE users SET name = ?, surname = ?, email = ?, password = ? WHERE nickname like ?";
            sp = connessione.con.prepareStatement(query);
            sp.setString(1, u.getName());
            sp.setString(2, u.getSurname());
            sp.setString(3, u.getEmail());
            sp.setString(4, u.getPassword());
            sp.setString(5, pw);
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
     * Prende le info dell'utente attraverso il nickname
     *
     * @param u utente
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public boolean CheckProfilo (User u) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
       
        try {
            query = "SELECT id, name, surname, email, password, role, love FROM users WHERE nickname LIKE ? ";
            sp = connessione.con.prepareStatement(query);
            sp.setString(1, u.getNickname());
            ResultSet rs = sp.executeQuery();
           
            if(rs.next()){
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));          
                u.setSurname(rs.getString("surname"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                u.setLike(rs.getInt("love"));
            }
            
            return true;
        } catch (SQLException e) {
            this.errore = e.toString();
            return false;
        } finally {
            sp.close();
            connessione.con.close();
        }
    }
    
    /**
     * Esegue il login al sito
     *
     * @param u utente
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean accedi(User u) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
 
        try {
            query = "SELECT  * FROM users WHERE nickname LIKE ? AND password LIKE ?";
            sp = connessione.con.prepareStatement(query);
            sp.setString(1, u.getNickname());
            sp.setString(2, u.getPassword());
            ResultSet rs = sp.executeQuery();
 
            if (rs.next()) {
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setSurname(rs.getString("surname"));
                u.setRole(rs.getString("role"));
                u.setLike(rs.getInt("love"));
            } else {
                u = null;
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
     * Cerca utente in base all'id
     *
     * @param u utente
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean cercaUser_perId(User u) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            if(u.getId()==null) return true;
            query = "SELECT  * FROM users WHERE id = ? ";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, u.getId());
 
            ResultSet rs = sp.executeQuery();
            if (rs.next()) {
                u.setEmail(rs.getString("email"));
                u.setName(rs.getString("name"));
                u.setSurname(rs.getString("surname"));
                u.setNickname(rs.getString("nickname"));
                u.setRole(rs.getString("role"));
                u.setLike(rs.getInt("love"));
                System.out.println("Account: " + u.getName());
            } else {
                System.out.println("Non esiste tale User");
                u = null;
            }
            
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
    
    /**
     * Aggiorna il ruolo dell'utente
     *
     * @param u utente
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean updateRuolo(User u) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        
        try {
            query = "UPDATE USERS SET ROLE = ? WHERE ID = ?";
            sp = connessione.con.prepareStatement(query);
            sp.setString(1, "2");
            sp.setInt(2, u.getId());
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
     * Controlla se l'utente e' un ristoratore
     *
     * @param res ristorante
     * @param user utente
     * @param risposta risposta
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean checkUserIsRisto(Restaurant res, User user, String risposta[]) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "SELECT * FROM restaurant_owner WHERE id_restaurant = ? AND id_owner = ? AND id_validator IS NOT NULL";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, res.getId());
            sp.setInt(2, user.getId());
            ResultSet rs = sp.executeQuery();
            
            if (rs.next())
                risposta[0]= "yes";
            else
                risposta[0]= "no";
            
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
     * Controlla se l'utente e' un ristoratore del ristorante
     *
     * @param res ristorante
     * @param user utente
     * @param risposta risposta
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean checkUserIsRistoNoValidation(Restaurant res, User user, String risposta[]) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "SELECT * FROM restaurant_owner WHERE id_restaurant = ? AND id_owner = ?";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, res.getId());
            sp.setInt(2, user.getId());
            
            ResultSet rs = sp.executeQuery();
            if (rs.next())
                risposta[0]= "yes";
            else
                risposta[0]= "no";
            
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
     * Trova creatore commento
     *
     * @param idRep id risposta
     * @param val utente validatore (admin)
     * @param user utente
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean findUserRepli(Integer idRep, User val, User user) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        
        try {
            query = "SELECT id_owner FROM REPLIES WHERE ID_VALIDATOR = ? AND id = ?";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, val.getId());
            sp.setInt(2, idRep);
            
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
     * Aumenta il numero di like dell'utente
     *
     * @param u utente
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean increaseLikeUser(User u) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        
        try {
            query = "UPDATE USERS SET LOVE = LOVE+1 WHERE ID = ?";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, u.getId());
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
