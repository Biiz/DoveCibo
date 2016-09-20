package DoveCiboPK;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

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

    public Boolean inserisciAccount(User u) throws SQLException {

        PreparedStatement sp = null;
        String query = null;

        try {
            query = "INSERT INTO users(id,name,surname,nickname,email,password,role) VALUES(DEFAULT,?,?,?,?,?,?)";
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
            // r = false;
        } finally {
            sp.close();
            con.close();
            return r;
        }
    }

    public Boolean modificaAccount(User u) throws SQLException {

        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;

        try {
            query = "UPDATE users SET name = ?, surname = ?, nickname = ? WHERE email = ? AND password = ?";
            sp = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            sp.setString(1, u.getName());
            sp.setString(2, u.getSurname());
            sp.setString(3, u.getNickname());
            sp.setString(4, u.getEmail());
            sp.setString(5, u.getPassword());

            sp.execute();

            ResultSet generatedKeys = sp.getGeneratedKeys();
            if (generatedKeys.next()) {
                u.setId(generatedKeys.getInt(1));
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

            query = "INSERT INTO restaurants(id,name,description,web_site_url,id_creator,id_price_range) "
                    + "VALUES(DEFAULT,?,?,?,?,?)";
            sp = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            sp.setString(1, res.getName());
            sp.setString(2, res.getDescription());
            sp.setString(3, res.getWeb_site_url());
            sp.setInt(4, res.getCreator().getId());
            sp.setInt(5, res.getPrice_range().getId());

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
            query = "INSERT INTO coordinates(id_restaurant,longitude,latitude,address)"
                    + "VALUES(?,?,?,?)";
            sp = con.prepareStatement(query);

            sp.setInt(1, coo.getId_resturant());
            sp.setDouble(2, coo.getLongitude());
            sp.setDouble(3, coo.getLatitude());
            sp.setString(4, coo.getAdrers());

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

    public Boolean inserisciOrario(Day_hours dh, Integer wd) throws SQLException {

        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;

        try {
            query = "INSERT INTO opening_hours_ranges(id, day_of_the_week, start_hour_m, end_hour_m, start_hour_p, end_hour_p)"
                    + "VALUES(DEFAULT,?,?,?,?,?)";
            sp = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            sp.setInt(1, wd);
            sp.setString(2, dh.getStartM());
            sp.setString(3, dh.getEndM());
            sp.setString(4, dh.getStartP());
            sp.setString(5, dh.getEndP());

            sp.executeUpdate();

            ResultSet generatedKeys = sp.getGeneratedKeys();
            if (generatedKeys.next()) {
                dh.setId(generatedKeys.getInt(1));
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

    public Boolean inserisciRelazioneRistoranteOrario(Integer id_restourant, Integer id_day_hours) throws SQLException {

        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;

        try {
            query = "INSERT INTO opening_hours_range_restaurant(id_restaurant, id_opening_hours_range)"
                    + "VALUES(?,?)";
            sp = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            sp.setInt(1, id_restourant);
            sp.setInt(2, id_day_hours);

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
            query = "INSERT INTO rewiev(id,"
                    + "global_value,"
                    + "food,"
                    + "service,"
                    + "value_for_money,"
                    + "atmospere,"
                    + "name, "
                    + "description"
                    + "date_creation, "
                    + "id_resturant, "
                    + "id_creator, "
                    + "id_photo) "
                    + "VALUES(DEFAULT,?,?,?,?,?,?,?,DEFAULT,?,?,?)";
            sp = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            Double food = rew.getFood();
            Double service = rew.getService();
            Double atmospere = rew.getAtmosphere();
            Double value = rew.getValue_of_money();
            Double global = rew.getGlobal_value();

            sp.setDouble(1, global);
            sp.setDouble(2, food);
            sp.setDouble(3, service);
            sp.setDouble(4, value);
            sp.setDouble(5, atmospere);
            sp.setString(6, rew.getName());
            sp.setString(7, rew.getDescription());

            sp.setInt(9, id_restaurant);
            sp.setInt(10, rew.getCreator().getId());
            sp.setInt(6, rew.getPhoto().getId());

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
            query = "SELECT  * FROM restaurants WHERE id = ? ";
            sp = con.prepareStatement(query);

            sp.setInt(1, res.getId());

            ResultSet rs = sp.executeQuery();

            if (rs.next()) {
                res.setAltro(
                        rs.getString("description"),
                        rs.getDouble("global_value"),
                        new User(rs.getInt("id_owner")),
                        new Price_range(rs.getInt("id_price_range")),
                        rs.getString("name"),
                        rs.getString("web_site_url"),
                        new User(rs.getInt("id_creator"))
                );

                System.out.println("ristorante: " + res.getName());

            } else {
                System.out.println("non esiste tale ristorante ");
                res = null;
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

    public Boolean cercaUser_perId(User u) throws SQLException {

        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;

        try {
            query = "SELECT  * FROM users WHERE id = ? ";
            sp = con.prepareStatement(query);

            sp.setInt(1, u.getId());

            ResultSet rs = sp.executeQuery();

            if (rs.next()) {

                u.setEmail(rs.getString("email"));
                u.setName(rs.getString("name"));
                u.setSurname(rs.getString("surname"));
                u.setNickname(rs.getString("nickname"));

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

    public Boolean cercaPhoto_perRistorante(Photo p, Integer id_restaurant) throws SQLException {

        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;

        try {
            query = "SELECT  * FROM photos WHERE id_restaurant = ? ";
            sp = con.prepareStatement(query);

            sp.setInt(1, id_restaurant);

            ResultSet rs = sp.executeQuery();

            if (rs.next()) {
                p = new Photo(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("path"),
                        new User(rs.getInt("id_owner")),
                        rs.getInt("validation")
                );

                System.out.println("photo: " + p.getName());

            } else {
                System.out.println("non esiste tale photo ");
                p = null;
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

    public Boolean cercaOrario_perOrario(Day_hours dh, Integer dw) throws SQLException {

        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;

        try {
            query = "SELECT  * FROM opening_hours_ranges WHERE "
                    + "start_hour_m = ? AND end_hour_m = ? AND "
                    + "start_hour_p = ? AND end_hour_p = ? AND day_of_the_week = ?";
            sp = con.prepareStatement(query);

            sp.setString(1, dh.getStartM());
            sp.setString(2, dh.getEndM());
            sp.setString(3, dh.getStartP());
            sp.setString(4, dh.getEndP());
            sp.setInt(5, dw);

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

}
