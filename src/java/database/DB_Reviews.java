package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServlet;
import restaurant_comments.Review;
import restaurants.Restaurant;
import users.User;

/**
 * Gestisce le informazioni sui commenti dei ristoranti nel database
 *
 * @author michael
 */
public class DB_Reviews extends HttpServlet {
    
    private String errore = "";
    DB_Manager connessione;

    /**
     * Costruttore
     *
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public DB_Reviews() throws SQLException {
        connessione = new DB_Manager ();
    }
    
    /**
     * Ritorna messaggio di errore
     *
     * @return stringa con messaggio di errore
     */
    public String getErrore() { return errore; }
    
    /**
     * Aggiunge nuova recensione
     *
     * @param rew recensione
     * @param id_restaurant id ristorante
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean inserisciReview(Review rew, Integer id_restaurant) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "INSERT INTO reviews(id,"
                    + "global_value,"
                    + "food,"
                    + "service,"
                    + "value_for_money,"
                    + "atmosphere,"
                    + "name, "
                    + "description, "
                    + "date_creation, "
                    + "id_restaurant, "
                    + "id_creator, "
                    + "love) "
                    + "VALUES(DEFAULT,?,?,?,?,?,?,?,DEFAULT,?,?,0)";
            sp = connessione.con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
 
            Integer food = rew.getFood();
            Integer service = rew.getService();
            Integer atmospere = rew.getAtmosphere();
            Integer value = rew.getValue_of_money();
            Integer global = rew.getGlobal_value();
            System.out.println("SQL Ok");
            
            sp.setInt(1, global);
            sp.setInt(2, food);
            sp.setInt(3, service);
            sp.setInt(4, value);
            sp.setInt(5, atmospere);
            sp.setString(6, rew.getName());
            sp.setString(7, rew.getDescription());
            sp.setInt(8, id_restaurant);
            sp.setInt(9, rew.getCreator().getId());
            sp.executeUpdate();
 
            ResultSet generatedKeys = sp.getGeneratedKeys();
            if (generatedKeys.next())
                rew.setId(generatedKeys.getInt(1));
 
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
     * Imposta info commento in base al ristoratore
     *
     * @param res ristorante
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean setCommenti_perRistorante(Restaurant res) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
 
        try {
            query = "SELECT  * FROM REVIEWS WHERE ID_RESTAURANT = ? ORDER BY DATE_CREATION DESC";
            sp = connessione.con.prepareStatement(query);
            System.out.println("IDRRR" + res.getId());
            sp.setInt(1, res.getId());
            
            ResultSet rs = sp.executeQuery();            
            while (rs.next()) {
                User user = new User(rs.getInt("ID_CREATOR"));
                new DB_GestioneUser().cercaUser_perId(user);
                
                Review rew = new Review(
                        rs.getInt("ID"), 
                        rs.getInt("GLOBAL_VALUE"), 
                        rs.getInt("FOOD"),
                        rs.getInt("SERVICE"), 
                        rs.getInt("VALUE_FOR_MONEY"), 
                        rs.getInt("ATMOSPHERE"), 
                        rs.getString("NAME"), 
                        rs.getString("DESCRIPTION"), 
                        rs.getDate("DATE_CREATION"), 
                        rs.getInt("LOVE"), 
                        user );
                res.addReviews(rew); 
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
     * Aumenta il numero di like a un commento
     *
     * @param rew recensione
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean increaseLikeReview(Review rew) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        
        try {
            query = "UPDATE REVIEWS SET LOVE = LOVE+1 WHERE ID = ?";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, rew.getId());
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
     * Aumenta il numero di recensioni di un ristorante
     *
     * @param res ristorante
     * @return  true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean increaseReviewRestaurant(Restaurant res) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        
        try {
            query = "UPDATE RESTAURANTS SET N_REVIEWS = N_REVIEWS+1 WHERE ID = ?";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, res.getId());
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
     * Conta il numero di recensioni di un ristorante
     *
     * @param res ristorante
     * @param reviews_value voti recensione
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean countReviews(Restaurant res, Double reviews_value[]) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
        int count_reviews_res = 0, gv = 0, food = 0, atm = 0, serv = 0, money = 0, count = 0;
        
        try {
            query = "SELECT SUM(global_value) AS s1, SUM(food) AS s2, SUM(service) AS s3, SUM (atmosphere) AS s4, SUM (value_for_money) AS s5, COUNT(*) AS count "+
                    "FROM reviews "+
                    "WHERE id_restaurant = ? ";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, res.getId());
            
            ResultSet rs = sp.executeQuery();            
            if (rs.next()) {
                gv = rs.getInt("s1");
                food= rs.getInt("s2");
                serv= rs.getInt("s3");
                atm= rs.getInt("s4");
                money = rs.getInt("s5");
                
                count = rs.getInt("count");
                reviews_value[0] = ((gv/count) + (food/count)+ (serv/count)+(atm/count)+(money/count))/5.0;
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
     * Imposta info recensione in base all'id
     *
     * @param rev recensione
     * @return true se la procedura e' andata a buon fine, false altrimenti
     * @throws SQLException se c'e' stato un problema di connessione al db
     */
    public Boolean setReviewPerId(Review rev) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
 
        try {
            query = "SELECT * FROM reviews WHERE ID = ?";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, rev.getId());
            
            ResultSet rs = sp.executeQuery();            
            if (rs.next()) {
                User user = new User(rs.getInt("id_creator"));
                new DB_GestioneUser().cercaUser_perId(user);
                
                rev.setCreator(user);
                rev.setDecription(rs.getString("description"));
                rev.setLike(rs.getInt("id_restaurant"));
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
}
