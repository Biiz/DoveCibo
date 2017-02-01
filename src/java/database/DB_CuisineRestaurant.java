package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import restaurants.Cusine;
import restaurants.Restaurant;

/**
 *
 * @author michael
 */
public class DB_CuisineRestaurant extends HttpServlet {
    private String errore = "";
    DB_Manager connessione;

    public DB_CuisineRestaurant() throws SQLException {
        connessione = new DB_Manager ();
    }
    
    public String getErrore() { return errore; }
    
    public Boolean removeCuisine(Integer id) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "DELETE FROM restaurant_cuisine WHERE id_restaurant = ?";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, id);
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
    
    public Boolean inserisciRelazioneCuisinesRestaurant(Integer id_restourant, Integer id_cuisine) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "INSERT INTO restaurant_cuisine(id, id_restaurant, id_cuisine)" + "VALUES(DEFAULT,?,?)";
            sp = connessione.con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            sp.setInt(1, id_restourant);
            sp.setInt(2, id_cuisine);
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
    
    public Boolean SetResForCuisine (String cuis, ArrayList <Integer> id_cu) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
       
        try {
            query = "SELECT id FROM cuisines WHERE name LIKE ? ";
            sp = connessione.con.prepareStatement(query);
            sp.setString(1, "%" + cuis + "%");
           
            ResultSet rs = sp.executeQuery();
            while(rs.next())
                id_cu.add(rs.getInt("id"));            
            
            return true;
        } catch (SQLException e) {
            this.errore = e.toString();
            return false;
        } finally {
            sp.close();
            connessione.con.close();
        }
    }
    
    public Boolean SetResForCuisineId (Integer id_cu, ArrayList <Integer> id_res) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
       
        try {
            query = "SELECT id_restaurant FROM restaurant_cuisine WHERE id_cuisine = ? ";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, id_cu);
           
            ResultSet rs = sp.executeQuery();
            while(rs.next())
                id_res.add(rs.getInt("id_restaurant"));            
            
            return true;
        } catch (SQLException e) {
            this.errore = e.toString();
            return false;
        } finally {
            sp.close();
            connessione.con.close();
        }
    }

    public Boolean cercaCusines_perRistoranye(Restaurant res) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "SELECT  c.* FROM RESTAURANT_CUISINE as rc, CUISINES as c " + "WHERE ? = rc.id_restaurant AND c.id = rc.id_cuisine";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, res.getId());
 
            ResultSet rs = sp.executeQuery();            
            while (rs.next())
                res.addCusine(new Cusine(rs.getString("name")));
            
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
