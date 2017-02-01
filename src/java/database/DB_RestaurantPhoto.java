package database;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import restaurant_photos.Photo;
import restaurants.Restaurant;
import users.User;

/**
 *
 * @author michael
 */
public class DB_RestaurantPhoto extends HttpServlet {
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

    public DB_RestaurantPhoto() throws SQLException {
        connessione = new DB_Manager ();
    }
    
    public String getErrore() { return errore; }
    
    public Boolean inserisciPhoto(Photo p, Integer id_restourant) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "INSERT INTO photos(id, name, description, id_restaurant, path, id_owner, validation)"
                    + "VALUES(DEFAULT,?,?,?,?,?,?)";
            sp = connessione.con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            sp.setString(1, p.getName());
            sp.setString(2, p.getDescription());
            sp.setInt(3, id_restourant);
            sp.setString(4, p.getPath());
            sp.setInt(5, p.getOwner().getId());
            sp.setInt(6, 2);
            sp.executeUpdate();
            
            ResultSet generatedKeys = sp.getGeneratedKeys();
            if (generatedKeys.next())
                p.setId(generatedKeys.getInt(1));
 
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
    
    public Boolean cercaPhotos_perRistorante(Restaurant res, Integer val) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "SELECT  * FROM photos WHERE id_restaurant = ? AND validation = ?";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, res.getId());
            sp.setInt(2, val);
 
            ResultSet rs = sp.executeQuery();
            while (rs.next()) {
                res.setPhoto(new Photo(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("path"),
                        new User(rs.getInt("id_owner")),
                        rs.getInt("validation"),
                        rs.getDate("date_creation"),
                        rs.getInt("id_restaurant")
                ));
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
    
    public Boolean cercaPhotos(ArrayList <Photo> ALP, Integer val) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "SELECT  * FROM photos WHERE validation = ?";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, val);
 
            ResultSet rs = sp.executeQuery();
            while (rs.next()) {
                ALP.add(new Photo(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("path"),
                        new User(rs.getInt("id_owner")),
                        rs.getInt("validation"),
                        rs.getDate("date_creation"),
                        rs.getInt("id_restaurant")
                ));
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
    
    public Boolean updatePhotoVal(Integer idPh, Integer val, User u) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        
        try {
            query = "UPDATE PHOTOS SET VALIDATION = ?, ID_OWNER = ? WHERE ID = ?";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, val);
            sp.setInt(2, u.getId());
            sp.setInt(3, idPh);
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
