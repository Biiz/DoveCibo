package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
import restaurants.Coordinate;
import restaurants.Day_hours;
import restaurants.Price_range;
import restaurants.Restaurant;
import users.User;

/**
 * Gestisce le informazioni sui ristoranti nel database
 *
 * @author michael
 */
public class DB_GestioneRestaurant extends HttpServlet {
    
    private String errore = "";
    DB_Manager connessione;

    /**
     * Costruttore
     *
     * @throws SQLException
     */
    public DB_GestioneRestaurant() throws SQLException {
        connessione = new DB_Manager ();
    }
    
    /**
     * Ritorna strigna di errore
     *
     * @return
     */
    public String getErrore() { return errore; }
    
    /**
     * Crea classifica ristoranti per global_value
     *
     * @param classifica
     * @return
     * @throws SQLException
     */
    public Boolean classificaRisto(ArrayList <Integer> classifica) throws SQLException {
        Boolean r = true;
        PreparedStatement sp = null;
        String query = null;
        
        try {
            query = "SELECT id, global_value FROM restaurants ORDER BY (global_value) DESC";
            sp = connessione.con.prepareStatement(query);
            ResultSet rs = sp.executeQuery();
            
            while (rs.next())
                classifica.add(rs.getInt("id"));
            
        } catch (SQLException e) {
            r = false;
        } finally {
            sp.close();
            connessione.con.close();
            return r;
        }
    }
    
    /**
     * Set id ristorante
     *
     * @param u
     * @param id_restaurant
     * @return
     * @throws SQLException
     */
    public boolean SetIdRestaurant2(User u, List<Integer> id_restaurant) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        
        try {
            query = "SELECT id_restaurant FROM restaurant_owner WHERE id_owner = ? AND ID_VALIDATOR IS NOT NULL";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, u.getId());
            ResultSet rs = sp.executeQuery();
            
            while(rs.next())
                id_restaurant.add(rs.getInt(1));
            
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
     * Aggiorna info ristorante
     *
     * @param res
     * @return
     * @throws SQLException
     */
    public Boolean updateRestaurant(Restaurant res) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "UPDATE restaurants SET name = ?, description = ?, web_site_url = ?  WHERE id = ?";
            sp = connessione.con.prepareStatement(query);
            sp.setString(1, res.getName());
            sp.setString(2, res.getDescription());
            sp.setString(3, res.getWeb_site_url());
            sp.setInt(4, res.getId());
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
     * Cerca tutti i ristoranti
     *
     * @param RES
     * @return
     * @throws SQLException
     */
    public Boolean cercaTuttiRes(ArrayList <Restaurant> RES) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
 
        try {
            query = "SELECT * FROM restaurants";
            sp = connessione.con.prepareStatement(query);
            ResultSet rs = sp.executeQuery();
 
            while (rs.next()) {
                Restaurant res = new Restaurant (rs.getInt("id"));
                RES.add(res);
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
     * Inserisci nuovo ristorante
     *
     * @param res
     * @return
     * @throws SQLException
     */
    public Boolean inserisciRistorante(Restaurant res) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "INSERT INTO restaurants(id,name,description,web_site_url,id_creator, n_reviews, love, global_value) "
                    + "VALUES(DEFAULT,?,?,?,?,0,0,0)";
            sp = connessione.con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            sp.setString(1, res.getName());
            sp.setString(2, res.getDescription());
            sp.setString(3, res.getWeb_site_url());
            sp.setInt(4, res.getCreator().getId());
            sp.executeUpdate();
            
            ResultSet generatedKeys = sp.getGeneratedKeys();
            if (generatedKeys.next())
                res.setId(generatedKeys.getInt(1));
            
            r = true;
        } catch (SQLException e) {
            this.errore = e.toString();
            System.out.println(e + " p");
            r = false;
        } finally {
            sp.close();
            connessione.con.close();
            return r;
        }
    }
    
    /**
     * Cerca ristorante in base all'id
     *
     * @param res
     * @return
     * @throws SQLException
     */
    public Boolean cercaRistorante_perId(Restaurant res) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "SELECT  * FROM restaurants WHERE id = ?";
            sp = connessione.con.prepareStatement(query);
            sp.setInt(1, res.getId());
            
            ResultSet rs = sp.executeQuery();
            if (rs.next()) {
                res.setAltro(
                        rs.getString("description"),
                        rs.getFloat("global_value"),
                        new Price_range(res.getId()),
                        rs.getString("name"),
                        rs.getString("web_site_url"),
                        new User(rs.getInt("id_creator")),
                        new Coordinate(res.getId()),
                        new Day_hours (res.getId())
                );
                res.setN_reviews(rs.getInt("n_reviews"));
            } else
                res = null;
            
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
     * Cerca tutti i ristoranti
     *
     * @param ALR
     * @return
     * @throws SQLException
     */
    public Boolean tuttiRistoranti(ArrayList <Restaurant> ALR) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
 
        try {
            query = "SELECT  * FROM restaurants";
            sp = connessione.con.prepareStatement(query);
            ResultSet rs = sp.executeQuery();
 
            while (rs.next()) {
                Restaurant res = new Restaurant(rs.getInt("id"));
                System.out.println("Find id Ok");
                res.setAltro(rs.getString("description"),
                                rs.getFloat("global_value"),
                                new Price_range(rs.getInt("id")),
                                rs.getString("name"),
                                rs.getString("web_site_url"),
                                new User(rs.getInt("id_creator")),
                                new Coordinate(rs.getInt("id")),
                                new Day_hours (rs.getInt("id"))
                );
                System.out.println("Errore");
                ALR.add(res);
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
     * Cerca ristoranti in base al nome
     *
     * @param res
     * @param r
     * @return
     * @throws SQLException
     */
    public Boolean SetResForName (Restaurant res, ArrayList <Integer> r) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
       
        try {
            query = "SELECT id FROM restaurants WHERE name LIKE ? ";
            sp = connessione.con.prepareStatement(query);
            sp.setString(1, "%" + res.getName() + "%");
           
            ResultSet rs = sp.executeQuery();
            while(rs.next())
                r.add(rs.getInt("id"));            
            
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
     * Cerca ristoranti in base alla classifica e alla vicinanza
     *
     * @param ALR
     * @param coo
     * @param rangeLat
     * @param rangeLon
     * @return
     * @throws SQLException
     */
    public Boolean ricercaRistorantiPerClassificaEVicinanza(ArrayList <Restaurant> ALR, Coordinate coo, double rangeLat, double rangeLon) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
        double ristLat = 0.0;
        double ristLon = 0.0;
        Integer idRes = null;
        
        try {
            query = "SELECT C.latitude, C.longitude, R.id "
                    +"FROM restaurants AS R, COORDINATES AS C "
                    +"WHERE R.ID = C.ID_RESTAURANT ";
            sp = connessione.con.prepareStatement(query);

            ResultSet rs = sp.executeQuery();
            while(rs.next()){
                ristLat = rs.getDouble("latitude");
                ristLon = rs.getDouble("longitude");
                idRes = rs.getInt("id");
                if((coo.getLongitude()+rangeLon > 0 && coo.getLongitude()-rangeLon>0 && coo.getLatitude()+rangeLat>0 && coo.getLatitude()-rangeLat>0) || (coo.getLongitude()+rangeLon < 0 && coo.getLongitude()-rangeLon<0 && coo.getLatitude()+rangeLat<0 && coo.getLatitude()-rangeLat<0) ){
                    if(ristLon > coo.getLongitude()-rangeLon && ristLon < coo.getLongitude()+rangeLon && ristLat > coo.getLatitude()-rangeLat && ristLat < coo.getLatitude()+rangeLat){
                        String query2 = null;
                        PreparedStatement sp2 = null;
                        
                        query2 = "SELECT R.* "
                                +"FROM restaurants AS R, COORDINATES AS C "
                                +"WHERE R.ID = C.ID_RESTAURANT AND R.id = ?";
                        sp2 = connessione.con.prepareStatement(query2);
                        sp2.setInt(1, idRes);

                        ResultSet rs2 = sp2.executeQuery();
                        if(rs2.next()) {
                            Restaurant res = new Restaurant(rs2.getInt("id"));
                            ALR.add(res);
                        }
                    }
                } else if ((coo.getLongitude()+rangeLon < 0 && coo.getLongitude()-rangeLon<0 && coo.getLatitude()+rangeLat>0 && coo.getLatitude()-rangeLat>0) || (coo.getLongitude()+rangeLon > 0 && coo.getLongitude()-rangeLon > 0 && coo.getLatitude()+rangeLat<0 && coo.getLatitude()-rangeLat<0)){
                    if(ristLon > coo.getLongitude()-rangeLon && ristLon < coo.getLongitude()+rangeLon && ristLat > coo.getLatitude()-rangeLat && ristLat < coo.getLatitude()+rangeLat){
                        String query2 = null;
                        PreparedStatement sp2 = null;
                        
                        query2 = "SELECT R.* "
                                +"FROM restaurants AS R, COORDINATES AS C "
                                +"WHERE R.ID = C.ID_RESTAURANT AND R.id = ?";
                        sp2 = connessione.con.prepareStatement(query2);
                        sp2.setInt(1, idRes);

                        ResultSet rs2 = sp2.executeQuery();
                        if(rs2.next()) {
                            Restaurant res = new Restaurant(rs2.getInt("id"));
                            ALR.add(res);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            this.errore = e.toString();
            System.out.println(e.toString());
            r = false;
        } finally {
            sp.close();
            connessione.con.close();
            return r;
        }
    }   
    
    /**
     * Cerca ristoranti per tupo di cuisine e vicinanza
     *
     * @param ALR
     * @param coo
     * @param rangeLat
     * @param rangeLon
     * @param cucina
     * @return
     * @throws SQLException
     */
    public Boolean ricercaRistorantiPerCucinaEVicinanza(ArrayList <Restaurant> ALR, Coordinate coo, double rangeLat, double rangeLon, String cucina) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
        double ristLat = 0.0;
        double ristLon = 0.0;
        Integer idRes = null;

        try {  
            query = "SELECT C.latitude, C.longitude, R.id "
                    +"FROM restaurants AS R, COORDINATES AS C "
                    +"WHERE R.ID = C.ID_RESTAURANT ";
            sp = connessione.con.prepareStatement(query);

            ResultSet rs = sp.executeQuery();
            while(rs.next()){
                ristLat = rs.getDouble("latitude");
                ristLon = rs.getDouble("longitude");
                idRes = rs.getInt("id");
                if((coo.getLongitude()+rangeLon > 0 && coo.getLongitude()-rangeLon>0 && coo.getLatitude()+rangeLat>0 && coo.getLatitude()-rangeLat>0) || (coo.getLongitude()+rangeLon < 0 && coo.getLongitude()-rangeLon<0 && coo.getLatitude()+rangeLat<0 && coo.getLatitude()-rangeLat<0) ){
                    if(ristLon > coo.getLongitude()-rangeLon && ristLon < coo.getLongitude()+rangeLon && ristLat > coo.getLatitude()-rangeLat && ristLat < coo.getLatitude()+rangeLat){
                        String query2 = null;
                        PreparedStatement sp2 = null;

                        query2 ="SELECT R.id_restaurant "
                               +"FROM cuisines AS C, restaurant_cuisine AS R "
                               +"WHERE C.name like ? AND C.id = R.id_cuisine";
                        sp2 = connessione.con.prepareStatement(query2);
                        sp2.setString(1, cucina);
                        
                        ResultSet rs2 = sp2.executeQuery();
                        while(rs2.next()){
                            if(rs2.getInt("id_restaurant") == idRes){
                                Restaurant res = new Restaurant(rs2.getInt("id_restaurant"));
                                ALR.add(res);
                            }
                        }
                    }
                } else if ((coo.getLongitude()+rangeLon < 0 && coo.getLongitude()-rangeLon<0 && coo.getLatitude()+rangeLat>0 && coo.getLatitude()-rangeLat>0) || (coo.getLongitude()+rangeLon > 0 && coo.getLongitude()-rangeLon > 0 && coo.getLatitude()+rangeLat<0 && coo.getLatitude()-rangeLat<0)){
                    if(ristLon > coo.getLongitude()-rangeLon && ristLon < coo.getLongitude()+rangeLon && ristLat > coo.getLatitude()-rangeLat && ristLat < coo.getLatitude()+rangeLat){
                        String query2 = null;
                        PreparedStatement sp2 = null;

                        query2 ="SELECT R.id_restaurant "
                               +"FROM cuisines AS C, restaurant_cuisine AS R "
                               +"WHERE C.name like ? AND C.id = R.id_cuisine";
                        sp2 = connessione.con.prepareStatement(query2);                       
                        sp2.setString(1, cucina);
                                               
                        ResultSet rs2 = sp2.executeQuery(); 
                        while(rs2.next()){
                            if(rs2.getInt("id_restaurant") == idRes){
                                Restaurant res = new Restaurant(rs2.getInt("id_restaurant"));
                                ALR.add(res);
                            }
                        }
                    }
                }
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
     * Aggiorna valuzatione
     *
     * @param res
     * @param newGV
     * @return
     * @throws SQLException
     */
    public Boolean updateRate(Restaurant res, double newGV) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        
        try {
            query = "UPDATE RESTAURANTS SET GLOBAL_VALUE = ? WHERE ID = ?";
            sp = connessione.con.prepareStatement(query);
            sp.setDouble(1, newGV);
            sp.setInt(2, res.getId());
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
