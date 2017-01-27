package DoveCiboPK;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
/**
 *
 * @author stefano
 */
public class DB_Manager {
    Connection con;
    final String DRIVER = "org.postgresql.Driver"; //Nome del driver
    final String DB_NAME = "jdbc:postgresql://localhost:5432/postgres"; //Nome del database completo di percorso
    final String DB_USER = "postgres"; // Nome utente
    final String DB_PASSWORD = "postgres"; //Password
    private String errore = "";
 
    public DB_Manager() throws SQLException {
        try {
            Class.forName(DRIVER); //Carica il driver
            con = DriverManager.getConnection(DB_NAME, DB_USER, DB_PASSWORD);  //Effettua la connessione al database
            //Effettua la connessione al database
            System.out.println("Connessione al server DBMS avvenuta con successo!");
        } catch (Exception e) {
            System.err.println("Errore: " + e.getMessage());  ///da sostituire
            con.close();
        }
    }
 
    public String getErrore() { return errore; }
    
    public boolean SetIdRestaurant2(User u, List<Integer> id_restaurant) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        
        try {
            query = "SELECT id_restaurant FROM restaurant_owner WHERE id_owner = ? AND ID_VALIDATOR IS NOT NULL";
            sp = con.prepareStatement(query);
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
            con.close();
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
 
            if (rs.next())
                r = true;
        } catch (SQLException e) {
            
        } finally {
            sp.close();
            con.close();
            return r;
        }
    }
    
    public Boolean classificaRisto(ArrayList <Integer> classifica) throws SQLException {
        Boolean r = true;
        PreparedStatement sp = null;
        String query = null;
        
        try {
            query = "SELECT id, global_value FROM restaurants ORDER BY (global_value) DESC";
            sp = con.prepareStatement(query);
            ResultSet rs = sp.executeQuery();
            
            while (rs.next())
                classifica.add(rs.getInt("id"));
            
        } catch (SQLException e) {
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
        }
    }
    
    public Boolean classificaRistoCitta(Integer ristoId, ArrayList <Integer> classifica) throws SQLException {
        Boolean r = true;
        PreparedStatement sp = null;
        String query = null;
        PreparedStatement sp2 = null;
        String query2 = null;
        
        try {
            query = "SELECT DISTINCT city " + "FROM coordinates";
            sp = con.prepareStatement(query);
            ResultSet rs = sp.executeQuery();
            
            while (rs.next()) {
                ArrayList <Integer> classificaCitta = new ArrayList <Integer>();
                query2 ="SELECT R.id, R.global_value "
                        +"FROM coordinates AS C, restaurants AS R "
                        +"WHERE R.id = C.id_restaurant AND C.city like ? "
                        +"ORDER BY (R.global_value) DESC";
                sp2 = con.prepareStatement(query2);
                sp2.setString(1, rs.getString("city"));
                ResultSet rs2 = sp2.executeQuery();
            
                while (rs2.next()) {
                    classificaCitta.add(rs2.getInt("id"));
                    if(ristoId == rs2.getInt("id")){
                        classifica.add(classificaCitta.indexOf(rs2.getInt("id")));
                    }
                }
            }
        } catch (SQLException e) {
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
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
 
            if (rs.next())
                r = true;
 
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
        }
    }
    
    public Boolean updateOrario(Day_hours dh) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "UPDATE opening_hours_ranges SET start_hour_m = ?, start_min_m = ?, end_hour_m = ?, end_min_m = ?, start_hour_p = ?, start_min_p = ?, end_hour_p = ?, end_min_p = ? WHERE id_restaurant = ?";
            sp = con.prepareStatement(query);
            sp.setInt(1, dh.getStart_H_M());
            sp.setInt(2, dh.getStart_M_M());
            sp.setInt(3, dh.getEnd_H_M());
            sp.setInt(4, dh.getEnd_M_M());
            sp.setInt(5, dh.getStart_H_P());
            sp.setInt(6, dh.getStart_M_P());
            sp.setInt(7, dh.getEnd_H_P());
            sp.setInt(8, dh.getEnd_M_P());
            sp.setInt(9, dh.getId_restaurant());
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
    
    public Boolean removeCuisine(Integer id) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "DELETE FROM restaurant_cuisine WHERE id_restaurant = ?";
            sp = con.prepareStatement(query);
            sp.setInt(1, id);
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
    
    public Boolean updatePriceRange(Price_range price) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "UPDATE price_ranges SET min_value = ?, max_value = ? WHERE id_restaurant = ?";
            sp = con.prepareStatement(query);
            sp.setDouble(1, price.getMin_value());
            sp.setDouble(2, price.getMax_value());
            sp.setInt(3, price.getId_restaurant());
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
    
    public Boolean updateCoordinate(Coordinate coor) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "UPDATE coordinates SET latitude = ?, longitude = ?, address = ?, numero = ?, city = ?, nazione = ? WHERE id_restaurant = ?";
            sp = con.prepareStatement(query);
            sp.setDouble(1, coor.getLatitude());
            sp.setDouble(2, coor.getLongitude());
            sp.setString(3, coor.getAdrers());
            sp.setInt(4, coor.getNumero());
            sp.setString(5, coor.getCity());
            sp.setString(6, coor.getNazione());
            sp.setInt(7, coor.getId_resturant());
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
    
    public Boolean updateRestaurant(Restaurant res) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "UPDATE restaurants SET name = ?, description = ?, web_site_url = ?  WHERE id = ?";
            sp = con.prepareStatement(query);
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
            con.close();
            return r;
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
        }
    }
    
    public Boolean cercaTuttiRes(ArrayList <Restaurant> RES) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
 
        try {
            query = "SELECT * FROM restaurants";
            sp = con.prepareStatement(query);
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
            con.close();
            return r;
        }
    }
    
    public Boolean inserisciRistorante(Restaurant res) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "INSERT INTO restaurants(id,name,description,web_site_url,id_creator, n_reviews, love, global_value) "
                    + "VALUES(DEFAULT,?,?,?,?,0,0,0)";
            sp = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
            con.close();
            return r;
        }
    }
 
    public Boolean inserisciPrice_range(Price_range pr) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "INSERT INTO price_ranges(id_restaurant, min_value, max_value)" + "VALUES(?,?,?)";
            sp = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            sp.setInt(1, pr.getId_restaurant());
            sp.setDouble(2, pr.getMin_value());
            sp.setDouble(3, pr.getMax_value());
            sp.executeUpdate();
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
            query = "INSERT INTO coordinates(id_restaurant,longitude,latitude,address,numero,city,nazione)"
                    + "VALUES(?,?,?,?,?,?,?)";
            sp = con.prepareStatement(query);
            sp.setInt(1, coo.getId_resturant());
            sp.setDouble(2, coo.getLongitude());
            sp.setDouble(3, coo.getLatitude());
            sp.setString(4, coo.getAdrers());
            sp.setInt(5, coo.getNumero());
            sp.setString(6, coo.getCity());
            sp.setString(7, coo.getNazione());
            sp.executeUpdate();
            r = true;
        } catch (SQLException e) {
            this.errore = e.toString();
            System.err.println("Tab Coordinate " + e);
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
        }
    }
 
    public Boolean inserisciRisposta(Replies rep, Integer idRew) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "INSERT INTO replies(id,description, DATE_CREATION, id_review, id_owner, date_validation, id_validator)"
                    + "VALUES(DEFAULT,?,DEFAULT,?,?,NULL, NULL)";
            sp = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
            con.close();
            return r;
        }
    }
 
    public Boolean inserisciOrario(Day_hours dh) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "INSERT INTO opening_hours_ranges(id_restaurant, start_hour_m, start_min_m, end_hour_m, end_min_m, start_hour_p, start_min_p, end_hour_p, end_min_p)"
                    + "VALUES(?,?,?,?,?,?,?,?,?)";
            sp = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            sp.setInt(1, dh.getId_restaurant());
            sp.setInt(2, dh.getStart_H_M());
            sp.setInt(3, dh.getStart_M_M());
            sp.setInt(4, dh.getEnd_H_M());
            sp.setInt(5, dh.getEnd_M_M());
            sp.setInt(6, dh.getStart_H_P());
            sp.setInt(7, dh.getStart_M_P());
            sp.setInt(8, dh.getEnd_H_P());
            sp.setInt(9, dh.getEnd_M_P());
            sp.executeUpdate();
            r = true;
        } catch (SQLException e) {
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
        }
    }
   
    public Boolean inserisciRelazioneCuisinesRestaurant(Integer id_restourant, Integer id_cuisine) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "INSERT INTO restaurant_cuisine(id, id_restaurant, id_cuisine)" + "VALUES(DEFAULT,?,?)";
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
            query = "INSERT INTO restaurant_owner(id_restaurant, id_owner, id_validator)" + "VALUES(?,?, NULL)";
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
            if (generatedKeys.next())
                p.setId(generatedKeys.getInt(1));
 
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
            con.close();
            return r;
        }
    }
 
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
            con.close();
            return r;
        }
    }
 
    public Boolean checkUserIsRisto(Restaurant res, User user, String risposta[]) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "SELECT * FROM restaurant_owner WHERE id_restaurant = ? AND id_owner = ? AND id_validator IS NOT NULL";
            sp = con.prepareStatement(query);
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
            con.close();
            return r;
        }
    }
 
    public Boolean checkUserIsRistoNoValidation(Restaurant res, User user, String risposta[]) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "SELECT * FROM restaurant_owner WHERE id_restaurant = ? AND id_owner = ?";
            sp = con.prepareStatement(query);
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
            con.close();
            return r;
        }
    }
    
    public Boolean cercaPriceRangeId(Price_range price) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "SELECT * FROM price_ranges WHERE id_restaurant = ?";
            sp = con.prepareStatement(query);
            sp.setInt(1, price.getId_restaurant());
            
            ResultSet rs = sp.executeQuery();
            if (rs.next()) {
                price.setMin_value(rs.getDouble("min_value"));
                price.setMax_value(rs.getDouble("max_value"));
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
            con.close();
            return r;
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
            con.close();
            return r;
        }
    }
    
    public Boolean cercaPhotos(ArrayList <Photo> ALP, Integer val) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "SELECT  * FROM photos WHERE validation = ?";
            sp = con.prepareStatement(query);
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
            con.close();
            return r;
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
            con.close();
            return r;
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
            System.out.println("Query Ok");
            if (rs.next()) {
                        c.setAdrers(rs.getString("ADDRESS"));
                        c.setNumero(rs.getInt("NUMERO"));
                        c.setCity(rs.getString("CITY"));
                        c.setNazione(rs.getString("NAZIONE"));
                        c.setLatitude(rs.getFloat("LATITUDE"));
                        c.setLongitude(rs.getFloat("LONGITUDE"));
            } else {
                System.out.println("Non esiste tale Ristorante");
            }
            
            r = true;
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            System.out.println("Accesso fallito");
            System.out.println("Possibile causa: " + e.getMessage());
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
        }
    }
    
    public Boolean cercaDay_hours_perId(Day_hours h) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "SELECT  * FROM OPENING_HOURS_RANGES WHERE ID_RESTAURANT = ? ";
            sp = con.prepareStatement(query);
            sp.setInt(1, h.getId_restaurant());
 
            ResultSet rs = sp.executeQuery();
            System.out.println("Query Ok");
            if (rs.next()) {
                        h.setStart_H_M(rs.getInt("START_HOUR_M"));
                        h.setStart_M_M(rs.getInt("START_MIN_M"));
                        h.setEnd_H_M(rs.getInt("END_HOUR_M"));
                        h.setEnd_M_M(rs.getInt("END_MIN_M"));
                        h.setStart_H_P(rs.getInt("START_HOUR_P"));
                        h.setStart_M_P(rs.getInt("START_MIN_P"));
                        h.setEnd_H_P(rs.getInt("END_HOUR_P"));
                        h.setEnd_M_P(rs.getInt("END_MIN_P"));
                        
            } else
                System.out.println("Non esiste tale Ristorante");
            
            r = true;
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            System.out.println("Accesso fallito");
            System.out.println("Possibile causa: " + e.getMessage());
            r = false;
        } finally {
            sp.close();
            con.close();
            return r;
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
            while(rs.next())
                r.add(rs.getInt("id"));            
            
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
            while(rs.next())
                res.add(rs.getInt("id_restaurant"));            
            
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
            while(rs.next())
                res.add(rs.getInt("id_restaurant"));            
            
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
            query = "SELECT id_restaurant FROM coordinates WHERE address LIKE ?";
            sp = con.prepareStatement(query);
            sp.setString(1, "%" + cor.getAdrers() + "%");
           
            ResultSet rs = sp.executeQuery();
            while(rs.next())
                res.add(rs.getInt("id_restaurant"));            
            
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
            while(rs.next())
                id_cu.add(rs.getInt("id"));            
            
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
            while(rs.next())
                id_res.add(rs.getInt("id_restaurant"));            
            
            return true;
        } catch (SQLException e) {
            this.errore = e.toString();
            return false;
        } finally {
            sp.close();
            con.close();
        }
    }

    public Boolean cercaCusines_perRistoranye(Restaurant res) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "SELECT  c.* FROM RESTAURANT_CUISINE as rc, CUISINES as c " + "WHERE ? = rc.id_restaurant AND c.id = rc.id_cuisine";
            sp = con.prepareStatement(query);
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
            con.close();
            return r;
        }
    }
    
    public Boolean setCommenti_perRistorante(Restaurant res) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
 
        try {
            query = "SELECT  * FROM REVIEWS WHERE ID_RESTAURANT = ? ORDER BY DATE_CREATION DESC";
            sp = con.prepareStatement(query);
            System.out.println("IDRRR" + res.getId());
            sp.setInt(1, res.getId());
            
            ResultSet rs = sp.executeQuery();            
            while (rs.next()) {
                User user = new User(rs.getInt("ID_CREATOR"));
                new DB_Manager().cercaUser_perId(user);
                
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
            con.close();
            return r;
        }
    }
    
    public Boolean cercaOwners_perRistoranti( Restaurant res) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
 
        try {
            query = "SELECT * FROM RESTAURANT_OWNER WHERE ID_RESTAURANT = ? AND ID_VALIDATOR IS NOT NULL";
            sp = con.prepareStatement(query);
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
            con.close();
            return r;
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
        }
    }
    
    public Boolean updateRate(Restaurant res, double newGV) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        
        try {
            query = "UPDATE RESTAURANTS SET GLOBAL_VALUE = ? WHERE ID = ?";
            sp = con.prepareStatement(query);
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
            con.close();
            return r;
        }
    }   
    
    public ArrayList <Restaurant> cercaRistoranti_perOwner(User u) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        ArrayList <Restaurant> ALR = new ArrayList<Restaurant>();
        
        try {
            query = "SELECT  * FROM restaurant_owner WHERE id_owner = ? AND id_validator is not null";
            sp = con.prepareStatement(query);
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
            con.close();
            return ALR;
        }
    }
    
    public Boolean updateRuolo(User u) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        
        try {
            query = "UPDATE USERS SET ROLE = ? WHERE ID = ?";
            sp = con.prepareStatement(query);
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
            con.close();
            return r;
        }
    }     
    
    public Boolean updatePhotoVal(Integer idPh, Integer val, User u) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        
        try {
            query = "UPDATE PHOTOS SET VALIDATION = ?, ID_OWNER = ? WHERE ID = ?";
            sp = con.prepareStatement(query);
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
            con.close();
            return r;
        }
    } 
    
    public Boolean setRepli_perRew(Review rew, ArrayList <Replies> replies) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
 
        try {
            query = "SELECT * FROM REPLIES WHERE ID_REVIEW = ? AND ID_VALIDATOR IS NOT NULL";
            sp = con.prepareStatement(query);
            sp.setInt(1, rew.getId());
            
            ResultSet rs = sp.executeQuery();            
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id_owner"));
                new DB_Manager().cercaUser_perId(user);
                
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
            con.close();
            return r;
        }
    }
    
    public Boolean countReviews(Restaurant res, Double reviews_value[]) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
        int count_reviews_res = 0, gv = 0, food = 0, atm = 0, serv = 0, money = 0, count = 0;
        
        try {
            query = "SELECT SUM(global_value) AS s1, SUM(food) AS s2, SUM(service) AS s3, SUM (atmosphere) AS s4, SUM (value_for_money) AS s5, COUNT(*) AS count "+
                    "FROM reviews "+
                    "WHERE id_restaurant = ? ";
            sp = con.prepareStatement(query);
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
            con.close();
            return r;
        }
    }
    
    public Boolean setRepli(Review rew) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
 
        try {
            query = "SELECT * FROM REPLIES WHERE ID_REVIEW = ? AND ID_VALIDATOR IS NOT NULL";
            sp = con.prepareStatement(query);
            sp.setInt(1, rew.getId());
            
            ResultSet rs = sp.executeQuery();            
            if (rs.next()) {
                User user = new User(rs.getInt("id_owner"));
                new DB_Manager().cercaUser_perId(user);
                
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
            con.close();
            return r;
        }
    }
    
    public Boolean updateRepli(Integer idRep, User val) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        
        try {
            query = "UPDATE REPLIES SET ID_VALIDATOR = ? , DATE_VALIDATION = DEFAULT WHERE ID = ?";
            sp = con.prepareStatement(query);
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
            con.close();
            return r;
        }
    } 
       
    public Boolean findUserRepli(Integer idRep, User val, User user) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        
        try {
            query = "SELECT id_owner FROM REPLIES WHERE ID_VALIDATOR = ? AND id = ?";
            sp = con.prepareStatement(query);
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
            con.close();
            return r;
        }
    } 
    
    public Boolean deleteRepli(User user) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        
        try {
            query = "DELETE FROM REPLIES WHERE ID_VALIDATOR IS NULL AND id_owner = ?";
            sp = con.prepareStatement(query);
            sp.setInt(1, user.getId());
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
        }
    }
    
    public Boolean rifiutaRisposta(Integer id) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        
        try {
            query = "DELETE FROM REPLIES WHERE ID = ?";
            sp = con.prepareStatement(query);
            sp.setInt(1, id);
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
        }
    } 

    public Boolean updateResOwn(Integer idRO, User val) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        
        try {
            query = "UPDATE RESTAURANT_OWNER SET ID_VALIDATOR = ? WHERE ID = ?";
            sp = con.prepareStatement(query);
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
            con.close();
            return r;
        }
    }  
    
    public Boolean selectOwn(Integer idRO, User val, User user) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        
        try {
            query = "SELECT id_owner FROM RESTAURANT_OWNER WHERE ID_VALIDATOR = ? AND ID = ?";
            sp = con.prepareStatement(query);
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
            con.close();
            return r;
        }
    }  
   
    public Boolean rifiutaReclamo(Integer idRO) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
        
        try {
            query = "DELETE FROM RESTAURANT_OWNER WHERE ID = ?";
            sp = con.prepareStatement(query);
            sp.setInt(1, idRO);
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
        }
    }
      
    public Boolean setNotificheReclamo (ArrayList <Notifica> ALN) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
        
        try {
            query = "SELECT * FROM restaurant_owner WHERE  id_validator is null";
            sp = con.prepareStatement(query);
       
            ResultSet rs = sp.executeQuery();
            while (rs.next()) {
                Restaurant res = new Restaurant(rs.getInt("id_restaurant"));
                new DB_Manager().cercaRistorante_perId(res);
                
                User u = new User(rs.getInt("id_owner"));
                new DB_Manager().cercaUser_perId(u);
  
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
            con.close();
            return r;
        }
    }
    
    public Boolean setNotificheReclamoNoLink (ArrayList <Notifica> ALN) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
        
        try {
            query = "SELECT * FROM restaurant_owner WHERE  id_validator is null";
            sp = con.prepareStatement(query);
       
            ResultSet rs = sp.executeQuery();
            while (rs.next()) {
                Restaurant res = new Restaurant(rs.getInt("id_restaurant"));
                new DB_Manager().cercaRistorante_perId(res);
                
                User u = new User(rs.getInt("id_owner"));
                new DB_Manager().cercaUser_perId(u);
  
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
            con.close();
            return r;
        }
    }
    
    public Boolean setNotificheRepil_daConfermare(ArrayList <Notifica> ALN ) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
 
        try {
            query = "SELECT  * FROM REPLIES WHERE ID_VALIDATOR IS NULL";
            sp = con.prepareStatement(query);

            ResultSet rs = sp.executeQuery();            
            while (rs.next()) {
                User user = new User(rs.getInt("id_owner"));
                new DB_Manager().cercaUser_perId(user);
                Review rev = new Review();
                rev.setId(rs.getInt("id_review"));
                new DB_Manager().setReviewPerId(rev);
                
                Restaurant rest = new Restaurant(rev.getLike());
                new DB_Manager().cercaRistorante_perId(rest);
                
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
            con.close();
            return r;
        }
    }
    
    public Boolean setNotificheRepil_daConfermareNoLink(ArrayList <Notifica> ALN ) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
        
        try {
            query = "SELECT  * FROM REPLIES WHERE ID_VALIDATOR IS NULL";
            sp = con.prepareStatement(query);

            ResultSet rs = sp.executeQuery();            
            while (rs.next()) {
                User user = new User(rs.getInt("id_owner"));
                new DB_Manager().cercaUser_perId(user);
                Review rev = new Review();
                rev.setId(rs.getInt("id_review"));
                new DB_Manager().setReviewPerId(rev);
                
                Restaurant rest = new Restaurant(rev.getLike());
                new DB_Manager().cercaRistorante_perId(rest);
                
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
            con.close();
            return r;
        }
    }
    
    public Boolean setReviewPerId(Review rev) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
 
        try {
            query = "SELECT * FROM reviews WHERE ID = ?";
            sp = con.prepareStatement(query);
            sp.setInt(1, rev.getId());
            
            ResultSet rs = sp.executeQuery();            
            if (rs.next()) {
                User user = new User(rs.getInt("id_creator"));
                new DB_Manager().cercaUser_perId(user);
                
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
            con.close();
            return r;
        }
    }
    
    public Boolean checkReplies(Replies rep, Integer idRew, ArrayList <String> check ) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = true;
 
        try {
            query = "SELECT * FROM REPLIES WHERE ID_VALIDATOR IS NOT NULL AND id_review = ? AND id_owner=?";
            sp = con.prepareStatement(query);
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
            con.close();
            return r;
        }
    }
    
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
            sp = con.prepareStatement(query);

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
                        sp2 = con.prepareStatement(query2);
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
                        sp2 = con.prepareStatement(query2);
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
            con.close();
            return r;
        }
    }   
    
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
            sp = con.prepareStatement(query);

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
                        sp2 = con.prepareStatement(query2);
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
                        sp2 = con.prepareStatement(query2);                       
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
            con.close();
            return r;
        }
    }  
    
    public Boolean isOwners_perRistoranti(User user, Restaurant res) throws SQLException { 
        PreparedStatement sp = null;
        String query = null;
        Boolean r = false;
 
        try {
            query = "SELECT * FROM RESTAURANT_OWNER WHERE ID_RESTAURANT = ? AND ID_VALIDATOR IS NOT NULL AND ID_OWNER = ?";
            sp = con.prepareStatement(query);            
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
            con.close();
            return r;
        }
    }  
}