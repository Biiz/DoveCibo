package DoveCiboPK;
 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.security.acl.Owner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import jdk.nashorn.internal.runtime.RewriteException;
 
/**
 *
 * @author stefano
 */
public class DB_Manager {
 
    Connection con;
    final String DRIVER = "org.apache.derby.jdbc.ClientDriver"; //Nome del driver
    final String DB_NAME = "jdbc:derby://localhost:1527/Dovecibo"; //Nome del database completo di percorso
    final String DB_USER = "asd"; // Nome utente
    final String DB_PASSWORD = "asd"; //Password
    private String errore = "";
 
    public DB_Manager() throws SQLException {
 
        try {
            Class.forName(DRIVER); //Carica il driver
            con = DriverManager.getConnection(DB_NAME, DB_USER, DB_PASSWORD);  //Effettua la connessione al database
            //Effettua la connessione al database
            System.out.println("Connessione al server DBMS avvenuta con successo!");
        } catch (Exception e) {
            System.err.println("errore: " + e.getMessage());  ///da sostituire
            con.close();
        }
    }
 
    public String getErrore() {
        return errore;
    }
    
    public boolean SetIdRestaurant(User u, List<Integer> id_restaurant) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        
        try {
            
            query = "SELECT id FROM restaurants WHERE id_creator = ?";
            sp = con.prepareStatement(query);
            
            sp.setInt(1, u.getId());
            
            ResultSet rs = sp.executeQuery();
            
            while(rs.next()){
                id_restaurant.add(rs.getInt(1));
            }
            
            return true;
        } catch (SQLException e) {
            this.errore = e.toString();
            return false;
        } finally {
            sp.close();
            con.close();
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
    }
    
    public boolean SetIdRestaurant2(User u, List<Integer> id_restaurant) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        
        try {
            
            query = "SELECT id FROM restaurants WHERE id_owner = ?";
            sp = con.prepareStatement(query);
            
            sp.setInt(1, u.getId());
            
            ResultSet rs = sp.executeQuery();
            
            while(rs.next()){
                id_restaurant.add(rs.getInt(1));
            }
            
            return true;
        } catch (SQLException e) {
            this.errore = e.toString();
            return false;
        } finally {
            sp.close();
            con.close();
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
    }
 
    public Boolean inserisciAccount(User u) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
 
        try {
            query = "INSERT INTO users(id,name,surname,nickname,email,password,role,love) VALUES(DEFAULT,?,?,?,?,?,?,0)";
            sp = con.prepareStatement(query);
 
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
            con.close();
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
    }
 
    public Boolean niknameEsistente(String nik) throws SQLException {
        Boolean r = false;
 
        PreparedStatement sp = null;
        String query = null;
 
        try {
            query = "SELECT  * FROM users WHERE nickname LIKE ? ";
            sp = con.prepareStatement(query);
 
            sp.setString(1, nik);
 
            ResultSet rs = sp.executeQuery();
 
            if (rs.next()) {
                r = true;
            }
 
        } catch (SQLException e) {
            // r = false;
        } finally {
            sp.close();
            con.close();
            return r;
        }
    }
   
   
    public void niknameEsistente_login (User u) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
       
        try {
            query = "SELECT name, surname FROM users WHERE nickname LIKE ? ";
            sp = con.prepareStatement(query);
           
            sp.setString(1, u.getNickname());
           
            ResultSet rs = sp.executeQuery();
           
            if(rs.next()){
                u.setName(rs.getString("name"));          
                u.setSurname(rs.getString("surname"));
            }
 
        } catch (SQLException e) {
            // r = false;
        } finally {
            sp.close();
            con.close();
        }
    }
   
    public boolean CheckProfilo (User u) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
       
        try {
            query = "SELECT id, name, surname, email, password, role, love FROM users WHERE nickname LIKE ? ";
            sp = con.prepareStatement(query);
           
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
            con.close();
        }
    }
    
    public boolean CheckEmail (User u) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
       
        try {
            query = "SELECT id, name, surname, nickname, password, role FROM users WHERE email LIKE ? ";
            sp = con.prepareStatement(query);
           
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
            con.close();
        }
    }
 
    public Boolean emailEsistente(String em) throws SQLException {
        Boolean r = false;
 
        PreparedStatement sp = null;
        String query = null;
 
        try {
            query = "SELECT  * FROM users WHERE email LIKE ? ";
            sp = con.prepareStatement(query);
 
            sp.setString(1, em);
 
            ResultSet rs = sp.executeQuery();
 
            if (rs.next()) {
                r = true;
            }
 
        } catch (SQLException e) {
             r = false;
        } finally {
            sp.close();
            con.close();
            return r;
        }
    }
 
    public Boolean modificaAccount(User u, String string) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "UPDATE users SET name = ?, surname = ?, email = ?, password = ? WHERE nickname like ?";
            sp = con.prepareStatement(query);
 
            sp.setString(1, u.getName());
            sp.setString(2, u.getSurname());
            sp.setString(3, u.getEmail());
            sp.setString(4, u.getPassword());
            sp.setString(5, string);
 
            sp.executeUpdate();
           
            r = true;
        } catch (SQLException e) {
            System.out.println("Possibile causa: " + e.getMessage());
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
 
    }
 
    public Boolean accedi(User u) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
 
        try {
            query = "SELECT  * FROM users WHERE nickname LIKE ? AND password LIKE ?";
            sp = con.prepareStatement(query);
 
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
            con.close();
            return r;
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
 
    }
 
/////INSERT    
    public Boolean inserisciRistorante(Restaurant res) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
 
            query = "INSERT INTO restaurants(id,name,description,web_site_url,id_creator,id_price_range, id_opening_hours, n_reviews, love, global_value) "
                    + "VALUES(DEFAULT,?,?,?,?,?,?,0,0,0)";
            sp = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
 
            sp.setString(1, res.getName());
            sp.setString(2, res.getDescription());
            sp.setString(3, res.getWeb_site_url());
            sp.setInt(4, res.getCreator().getId());
            sp.setInt(5, res.getPrice_range().getId());
            sp.setInt(6, res.getDay_hours().getId());
 
            sp.executeUpdate();
 
            ResultSet generatedKeys = sp.getGeneratedKeys();
            if (generatedKeys.next()) {
                res.setId(generatedKeys.getInt(1));
            }
 
            r = true;
        } catch (SQLException e) {
            this.errore = e.toString();
            System.out.println(e+"   p");
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
 
    }
 
    public Boolean inserisciPrice_range(Price_range pr) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "INSERT INTO price_ranges(ID,min_value,max_value)"
                    + "VALUES(DEFAULT,?,?)";
            sp = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
 
            sp.setDouble(1, pr.getMin_value());
            sp.setDouble(2, pr.getMax_value());
 
            sp.executeUpdate();
 
            ResultSet generatedKeys = sp.getGeneratedKeys();
            if (generatedKeys.next()) {
                pr.setId(generatedKeys.getInt(1));
            }
 
            r = true;
        } catch (SQLException e) {
            r = false;
            this.errore = e.toString();
            System.out.println("Possibile causa: " + e.getMessage());
        } finally {
            sp.close();
            con.close();
            return r;
        }
    }
 
    public Boolean inserisciCoordinate(Coordinate coo) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "INSERT INTO coordinates(id_restaurant,longitude,latitude,address,city,nazione)"
                    + "VALUES(?,?,?,?,?,?)";
            sp = con.prepareStatement(query);
 
            sp.setInt(1, coo.getId_resturant());
            sp.setDouble(2, coo.getLongitude());
            sp.setDouble(3, coo.getLatitude());
            sp.setString(4, coo.getAdrers());
            sp.setString(5, coo.getCity());
            sp.setString(6, coo.getNazione());
 
            sp.executeUpdate();
 
            r = true;
        } catch (SQLException e) {
            this.errore = e.toString();
            System.err.println("tab coordinate "+e);
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
        }
    }
 
    public Boolean inserisciRisposta(Replies rep, Integer idOwner, Integer idRestaurant) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "INSERT INTO replies(id,description, date_creation, id_review, id_owner, date_validation)"
                    + "VALUES(DEFAULT,?,DEFAULT,?,?,NULL)";
            sp = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
 
            sp.setString(1, rep.getDescription());
            sp.setInt(2, idRestaurant);
            sp.setInt(3, idOwner);
 
            sp.executeUpdate();
 
            ResultSet generatedKeys = sp.getGeneratedKeys();
            if (generatedKeys.next()) {
                rep.setId(generatedKeys.getInt(1));
                rep.setDate_creation(generatedKeys.getDate("date_creation"));
            }
 
            r = true;
        } catch (SQLException e) {
            System.out.println("Possibile causa: " + e.getMessage());
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
        }
    }
 
        public Boolean inserisciOrario(Day_hours dh) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "INSERT INTO opening_hours_ranges(id, start_hour_m, end_hour_m, start_hour_p, end_hour_p)"
                    + "VALUES(DEFAULT,?,?,?,?)";
            sp = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
 
            sp.setString(1, dh.getStartM());
            sp.setString(2, dh.getEndM());
            sp.setString(3, dh.getStartP());
            sp.setString(4, dh.getEndP());
 
            sp.executeUpdate();
 
            ResultSet generatedKeys = sp.getGeneratedKeys();
            if (generatedKeys.next()) {
                dh.setId(generatedKeys.getInt(1));
            }
 
            r = true;
        } catch (SQLException e) {
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
        }
    }
    
    public Boolean checkNavBar_restaurant(User u) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
 
        try {
            query = "SELECT * FROM restaurants WHERE id_creator = ?";
            sp = con.prepareStatement(query);
 
            sp.setInt(1, u.getId());
 
            ResultSet rs = sp.executeQuery();
            
            if (rs.next()) {
                u.setName(rs.getString("name"));
            }
 
            return true;
        } catch (SQLException e) {
            this.errore = e.toString();
            return false;
        } finally {
            sp.close();
            con.close();
        }
    }
 

 
    public Boolean inserisciRelazioneCuisinesRestaurant(Integer id_restourant, Integer id_cuisine) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "INSERT INTO restaurant_cuisine(id_restaurant, id_cuisine)"
                    + "VALUES(?,?)";
            sp = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
 
            sp.setInt(1, id_restourant);
            sp.setInt(2, id_cuisine);
 
            sp.executeUpdate();
 
            r = true;
        } catch (SQLException e) {
            System.out.println("Possibile causa: " + e.getMessage());
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
        }
    }
    
    
    
    
    public Boolean inserisciRelazioneOwnerRestaurant(Integer id_restourant, Integer id_owner) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "INSERT INTO restaurant_owner(id_restaurant, id_owner)"
                    + "VALUES(?,?)";
            sp = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
 
            sp.setInt(1, id_restourant);
            sp.setInt(2, id_owner);
 
            sp.executeUpdate();
 
            r = true;
        } catch (SQLException e) {
            System.out.println("Possibile causa: " + e.getMessage());
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
        }
    }
 
    public Boolean inserisciPhoto(Photo p, Integer id_restourant) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "INSERT INTO photos(id, name, description, id_restaurant, path, id_owner, validation)"
                    + "VALUES(DEFAULT,?,?,?,?,?,?)";
            sp = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
 
            sp.setString(1, p.getName());
            sp.setString(2, p.getDescription());
            sp.setInt(3, id_restourant);
            sp.setString(4, p.getPath());
            sp.setInt(5, p.getOwner().getId());
            sp.setInt(6, 2);
            sp.executeUpdate();
 
            ResultSet generatedKeys = sp.getGeneratedKeys();
            if (generatedKeys.next()) {
                p.setId(generatedKeys.getInt(1));
            }
 
            r = true;
        } catch (SQLException e) {
            System.out.println("Possibile causa: " + e.getMessage());
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
        }
    }
 
  
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
            sp = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
 
            Integer food = rew.getFood();
            Integer service = rew.getService();
            Integer atmospere = rew.getAtmosphere();
            Integer value = rew.getValue_of_money();
            Integer global = rew.getGlobal_value();
 
            System.out.println("SQL OK");
            
            
            sp.setInt(1, global);
            sp.setInt(2, food);
            sp.setInt(3, service);
            sp.setInt(4, value);
            sp.setInt(5, atmospere);
            sp.setString(6, rew.getName());
            sp.setString(7, rew.getDescription());
 
            sp.setInt(8, id_restaurant);
            sp.setInt(9, rew.getCreator().getId());
            //sp.setInt(10, rew.getPhoto().getId());
 
            sp.executeUpdate();
 
            ResultSet generatedKeys = sp.getGeneratedKeys();
            if (generatedKeys.next()) {
                rew.setId(generatedKeys.getInt(1));
            }
 
            r = true;
        } catch (SQLException e) {
            System.out.println("Possibile causa: " + e.getMessage());
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
        }
    }
 
//////SELECT
    public Boolean cercaRistorante_perId(Restaurant res) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "SELECT  * FROM restaurants WHERE id = ?";
            sp = con.prepareStatement(query);
 
            sp.setInt(1, res.getId());
            
            ResultSet rs = sp.executeQuery();
            
            if (rs.next()) {
                res.setAltro(
                        rs.getString("description"),
                        rs.getFloat("global_value"),
                        new Price_range(rs.getInt("id_price_range")),
                        rs.getString("name"),
                        rs.getString("web_site_url"),
                        new User(rs.getInt("id_creator")),
                        new Coordinate(res.getId())
                );
                res.setDay_hours(new Day_hours(rs.getInt("id_opening_hours")));
                res.setN_reviews(rs.getInt("n_reviews"));
 
            } else {
                res = null;
            }
            r = true;
 
        } catch (SQLException e) {
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
    }
 
    public Boolean cercaCuisine_perId_Restaurant(Restaurant res, List list) throws SQLException {
        
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "SELECT id_cuisine FROM restaurant_cuisine WHERE id_restaurant = ?";
            sp = con.prepareStatement(query);
 
            sp.setInt(1, res.getId());
            
            ResultSet rs = sp.executeQuery();
            
            while (rs.next()) {
                list.add(rs.getInt(1));
            } 
            r = true;
 
        } catch (SQLException e) {
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
    }
    
    public Boolean cercaCuisine(Integer id_cuisine, List list) throws SQLException {
        
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
             
                query = "SELECT name FROM cuisines WHERE id = ?";
               
                sp = con.prepareStatement(query);
            
                sp.setInt(1, id_cuisine);
                
                ResultSet rs = sp.executeQuery();
                
                while (rs.next()) {
                    list.add(rs.getString(1));
                } 

            r = true;
 
        } catch (SQLException e) {
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
    }
    
    public Boolean cercaCoordinate_perID_Restaurant(Restaurant res, String string[]) throws SQLException {
        
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "SELECT address, city FROM coordinates WHERE id_restaurant = ?";
            sp = con.prepareStatement(query);
 
            sp.setInt(1, res.getId());
            
            ResultSet rs = sp.executeQuery();
            
            if (rs.next()) {
                string[0] = rs.getString(1);
                string[1] = rs.getString(2);
            } 
            r = true;
 
        } catch (SQLException e) {
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
    }
    
    
    public Boolean cercaPriceRanges_Restaurant(Restaurant res, Integer prezzo[]) throws SQLException {
        
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "SELECT min_value, max_value FROM price_ranges WHERE id = ?";
            sp = con.prepareStatement(query);
 
            sp.setInt(1, res.getPrice_range().getId());
            
            ResultSet rs = sp.executeQuery();
            
            if (rs.next()) {
                prezzo[0] = rs.getInt(1);
                prezzo[1] = rs.getInt(2);
            } 
            r = true;
 
        } catch (SQLException e) {
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
    }
    
    public Boolean cercaUser_perId(User u) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
           
            if(u.getId()==null) return true;
           
            query = "SELECT  * FROM users WHERE id = ? ";
            sp = con.prepareStatement(query);
 
            sp.setInt(1, u.getId());
 
            ResultSet rs = sp.executeQuery();
 
            if (rs.next()) {
 
                u.setEmail(rs.getString("email"));
                u.setName(rs.getString("name"));
                u.setSurname(rs.getString("surname"));
                u.setNickname(rs.getString("nickname"));
                u.setLike(rs.getInt("love"));
                System.out.println("account: " + u.getName());
 
            } else {
                System.out.println("non esiste tale user");
                u = null;
            }
            r = true;
 
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            System.out.println("accesso fallito");
            System.out.println("Possibile causa: " + e.getMessage());
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
    }
 
    public Boolean cercaPriceRange_perId(Price_range pr) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "SELECT  * FROM price_ranges WHERE id = ? ";
            sp = con.prepareStatement(query);
 
            sp.setInt(1, pr.getId());
 
            ResultSet rs = sp.executeQuery();
 
            if (rs.next()) {
 
                pr.setMax_value(rs.getDouble("max_value"));
                pr.setMin_value(rs.getDouble("min_value"));
 
            }
            r = true;
 
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            System.out.println("accesso fallito");
            System.out.println("Possibile causa: " + e.getMessage());
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
    }
 
    public Boolean cercaPriceRange_perRange(Price_range pr) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "SELECT  * FROM price_ranges WHERE max_value = ? AND min_value = ?";
            sp = con.prepareStatement(query);
 
            sp.setDouble(1, pr.getMax_value());
            sp.setDouble(2, pr.getMin_value());
 
            ResultSet rs = sp.executeQuery();
 
            if (rs.next()) {
                pr.setId(rs.getInt("id"));
            }
            r = true;
 
        } catch (SQLException e) {
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
    }
 
    public Boolean cercaPhotos_perRistorante(Restaurant res, Integer val) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "SELECT  * FROM photos WHERE id_restaurant = ? AND validation = ?";
            sp = con.prepareStatement(query);
 
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
                        rs.getInt("validation")
                ));
 
            }
            r = true;
 
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            System.out.println("accesso fallito");
            System.out.println("Possibile causa: " + e.getMessage());
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
    }
 
    public Boolean cercaOrario_perOrario(Day_hours dh) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "SELECT  * FROM opening_hours_ranges WHERE "
                    + "start_hour_m = ? AND end_hour_m = ? AND "
                    + "start_hour_p = ? AND end_hour_p = ? ";
            sp = con.prepareStatement(query);
 
            sp.setString(1, dh.getStartM());
            sp.setString(2, dh.getEndM());
            sp.setString(3, dh.getStartP());
            sp.setString(4, dh.getEndP());
 
            ResultSet rs = sp.executeQuery();
 
            if (rs.next()) {
                dh.setId(rs.getInt("id"));
            }
            r = true;
 
        } catch (SQLException e) {
            System.out.println("Possibile causa: " + e.getMessage());
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
    }
   
   
   
   
       
    public Boolean tuttiRistoranti(ArrayList <Restaurant> ALR) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
 
        try {
            query = "SELECT  * FROM restaurants";
            sp = con.prepareStatement(query);
 
            ResultSet rs = sp.executeQuery();
 
            while (rs.next()) {
                Restaurant res = new Restaurant(rs.getInt("id"));
                System.out.println("find id ok");
                res.setAltro(rs.getString("description"),
                                rs.getFloat("global_value"),
                                new Price_range(rs.getInt("id_price_range")),
                                rs.getString("name"),
                                rs.getString("web_site_url"),
                                new User(rs.getInt("id_creator")),
                                new Coordinate(rs.getInt("id"))
                );
                                System.out.println("porco dio");
                ALR.add(res);
            }
 
        } catch (SQLException e) {
            this.errore = e.toString();
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
    }
   
    public Boolean setOrariPerRistorante(Restaurant res) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
 
        try {
            query = "SELECT START_HOUR_M, END_HOUR_M, START_HOUR_P, END_HOUR_P "
                    + "FROM OPENING_HOURS_RANGES "
                    + "WHERE "
                    + "ID = ?";
           
           
            sp = con.prepareStatement(query);
           
 
            System.out.println("qery giusta");
           
            sp.setInt(1, res.getDay_hours().getId());

            ResultSet rs = sp.executeQuery();        
             
            
            if (rs.next()) {
                res.setDay_hours( new Day_hours(res.getDay_hours().getId(), rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));                  
            }
 
        } catch (SQLException e) {
            System.out.println(e.toString());
            this.errore = e.toString();
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
    }  
   
   
   
    public Boolean cercaCoordinate_perId(Coordinate c) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "SELECT  * FROM COORDINATES WHERE ID_RESTAURANT = ? ";
            sp = con.prepareStatement(query);
 
            sp.setInt(1, c.getId_resturant());
 
            ResultSet rs = sp.executeQuery();
 
            System.out.println("ok query");
           
            if (rs.next()) {
 
                        c.setAdrers(rs.getString("ADDRESS"));
                        c.setCity(rs.getString("CITY"));
                        c.setNazione(rs.getString("NAZIONE"));
                        c.setLatitude(rs.getFloat("LATITUDE"));
                        c.setLongitude(rs.getFloat("LONGITUDE"));
   
            } else {
                System.out.println("non esiste tale ristorante ");
         
            }
            r = true;
 
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            System.out.println("accesso fallito");
            System.out.println("Possibile causa: " + e.getMessage());
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
    }
    
    public Boolean SetResForName (Restaurant res, ArrayList <Integer> r) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
       
        try {
            query = "SELECT id FROM restaurants WHERE name LIKE ? ";
            sp = con.prepareStatement(query);
           
            sp.setString(1, "%" + res.getName() + "%");
           
            ResultSet rs = sp.executeQuery();
           
            while(rs.next()){
                r.add(rs.getInt("id"));            
            }
            
            return true;
        } catch (SQLException e) {
           this.errore = e.toString();
            return false;
        } finally {
            sp.close();
            con.close();
        }
    }
    
    public Boolean SetResForNazione (Coordinate cor, ArrayList <Integer> res) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
       
        try {
            query = "SELECT id_restaurant FROM coordinates WHERE nazione LIKE ? ";
            sp = con.prepareStatement(query);
           
            sp.setString(1, "%" + cor.getNazione() + "%");
           
            ResultSet rs = sp.executeQuery();
           
            while(rs.next()){
                res.add(rs.getInt("id_restaurant"));            
            }
            return true;
        } catch (SQLException e) {
            this.errore = e.toString();
            return false;
        } finally {
            sp.close();
            con.close();
        }
    }
    
    public Boolean SetResForCity (Coordinate cor, ArrayList <Integer> res) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
       
        try {
            query = "SELECT id_restaurant FROM coordinates WHERE city LIKE ? ";
            sp = con.prepareStatement(query);
       
            sp.setString(1, "%" + cor.getCity()+ "%");
           
            ResultSet rs = sp.executeQuery();
           
            while(rs.next()){
                res.add(rs.getInt("id_restaurant"));            
            }
            return true;
        } catch (SQLException e) {
            this.errore = e.toString();
            return false;
        } finally {
            sp.close();
            con.close();
        }
    }
    
    public Boolean SetResForAdrers (Coordinate cor, ArrayList <Integer> res) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
       
        try {
            query = "SELECT id_restaurant FROM coordinates WHERE address LIKE ? ";
            sp = con.prepareStatement(query);
            sp.setString(1, "%" + cor.getAdrers() + "%");
           
            ResultSet rs = sp.executeQuery();
           
            while(rs.next()){
                res.add(rs.getInt("id_restaurant"));            
            }
            return true;
        } catch (SQLException e) {
            this.errore = e.toString();
            return false;
        } finally {
            sp.close();
            con.close();
        }
    }
      
    public Boolean SetResForCuisine (String cuis, ArrayList <Integer> id_cu) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
       
        try {
            query = "SELECT id FROM cuisines WHERE name LIKE ? ";
            sp = con.prepareStatement(query);
            sp.setString(1, "%" + cuis + "%");
           
            ResultSet rs = sp.executeQuery();
           
            while(rs.next()){
                id_cu.add(rs.getInt("id"));            
            }
            return true;
        } catch (SQLException e) {
            this.errore = e.toString();
            return false;
        } finally {
            sp.close();
            con.close();
        }
    }
    
    public Boolean SetResForCuisineId (Integer id_cu, ArrayList <Integer> id_res) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
       
        try {
            query = "SELECT id_restaurant FROM restaurant_cuisine WHERE id_cuisine = ? ";
            sp = con.prepareStatement(query);
           
            sp.setInt(1, id_cu);
           
            ResultSet rs = sp.executeQuery();
           
            while(rs.next()){
                id_res.add(rs.getInt("id_restaurant"));            
            }
            return true;
        } catch (SQLException e) {
            this.errore = e.toString();
            return false;
        } finally {
            sp.close();
            con.close();
        }
    }
    
    
    
//NEW POSTAL

    public Boolean cercaCusines_perRistoranye(Restaurant res) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
           
            
            query = "SELECT  c.* FROM RESTAURANT_CUISINE as rc, CUISINES as c "
                    + "WHERE ? = rc.id_restaurant AND c.id = rc.id_cuisine";
            sp = con.prepareStatement(query);
 
            
            
            sp.setInt(1, res.getId());
 
            ResultSet rs = sp.executeQuery();
 
            
            while (rs.next()) {
                
                res.addCusine(new Cusine(rs.getString("name")));
            }
            r = true;
            
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            System.out.println("accesso fallito");
            System.out.println("Possibile causa: " + e.getMessage());
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
    }
    
    
    
    
    
    public Integer contaPhoto() throws SQLException {
 
        Statement sp = null;
        String query = null;
 
        try {
                  
            query = "SELECT COUNT(*) as n FROM PHOTOS ";
            sp = con.createStatement();
            ResultSet rs = sp.executeQuery(query);
            rs.next();
            return rs.getInt("n");
            
        } catch (SQLException e) {

            System.out.println("Possibile causa: " + e.getMessage());
            return -1;
            
        } finally {
            sp.close();
            con.close();
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
    }        
    
    
    

    public Boolean setCommenti_perRistorante( Restaurant res) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
 
        try {
            query = "SELECT  * FROM REVIEWS WHERE ID_RESTAURANT = ? ORDER BY DATE_CREATION DESC";
            sp = con.prepareStatement(query);
            
            System.out.println("IDRRR"+ res.getId());
            
            sp.setInt(1, res.getId());
            ResultSet rs = sp.executeQuery();            
            
            while (rs.next()) {
                
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
                        new User (rs.getInt("ID_CREATOR")) );
                
                
                res.addReviews(rew); 
            }
 
        } catch (SQLException e) {
            this.errore = e.toString();
            System.out.println(errore);
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
    }
    
    
    public Boolean cercaOwners_perRistoranti( Restaurant res) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
 
        try {
            query = "SELECT * FROM RESTAURANT_OWNER WHERE ID_RESTAURANT = ? ";
            sp = con.prepareStatement(query);
            
            sp.setInt(1, res.getId());
            ResultSet rs = sp.executeQuery();            
            
            while (rs.next()) {          
                
                User ow = new User(rs.getInt("ID_OWNER"));
                System.out.println("ooooo"+ ow.getId());
                res.addOwner(ow); 
            }
 
        } catch (SQLException e) {
            this.errore = e.toString();
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
    }    
    
    
    
    
    public Boolean increaseLikeReview(Review rew) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        try {
            query = "UPDATE REVIEWS SET LOVE = LOVE+1 WHERE ID = ?";
            sp = con.prepareStatement(query);
            sp.setInt(1, rew.getId());
            sp.executeUpdate();
            r = true;
        } catch (SQLException e) {
            System.out.println("Possibile causa: " + e.getMessage());
            errore = e.toString();
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
 
    }
    
    public Boolean increaseLikeUser(User u) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        try {
            query = "UPDATE USERS SET LOVE = LOVE+1 WHERE ID = ?";
            sp = con.prepareStatement(query);
            sp.setInt(1, u.getId());
            sp.executeUpdate();
            r = true;
        } catch (SQLException e) {
            System.out.println("Possibile causa: " + e.getMessage());
            errore = e.toString();
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
 
    }   
    

    public Boolean increaseReviewRestaurant(Restaurant res) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        try {
            query = "UPDATE RESTAURANTS SET N_REVIEWS = N_REVIEWS+1 WHERE ID = ?";
            sp = con.prepareStatement(query);
            sp.setInt(1, res.getId());
            sp.executeUpdate();
            r = true;
        } catch (SQLException e) {
            System.out.println("Possibile causa: " + e.getMessage());
            errore = e.toString();
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
 
    }
    
    
    public Boolean updateRate(Restaurant res, Float newGV) throws SQLException {
 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        try {
            query = "UPDATE RESTAURANTS SET GLOBAL_VALUE = ? WHERE ID = ?";
            sp = con.prepareStatement(query);
            sp.setFloat(1, newGV);
            sp.setInt(2, res.getId());
            sp.executeUpdate();
            r = true;
        } catch (SQLException e) {
            System.out.println("Possibile causa: " + e.getMessage());
            errore = e.toString();
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
            //response.setHeader("Refresh", "5; URL=index.jsp");
        }
 
    }   
    
    
    

    
    
    
    
    
 
}