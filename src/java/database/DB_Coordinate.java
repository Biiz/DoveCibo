/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import DoveCiboPK.Coordinate;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author michael
 */
public class DB_Coordinate extends HttpServlet {

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

    public DB_Coordinate() throws SQLException {
        connessione = new DB_Manager ();
    }
    
    public String getErrore() { return errore; }
    
    public Boolean classificaRistoCitta(Integer ristoId, ArrayList <Integer> classifica) throws SQLException {
        Boolean r = true;
        PreparedStatement sp = null;
        String query = null;
        PreparedStatement sp2 = null;
        String query2 = null;
        
        try {
            query = "SELECT DISTINCT city " + "FROM coordinates";
            sp = connessione.con.prepareStatement(query);
            ResultSet rs = sp.executeQuery();
            
            while (rs.next()) {
                ArrayList <Integer> classificaCitta = new ArrayList <Integer>();
                query2 ="SELECT R.id, R.global_value "
                        +"FROM coordinates AS C, restaurants AS R "
                        +"WHERE R.id = C.id_restaurant AND C.city like ? "
                        +"ORDER BY (R.global_value) DESC";
                sp2 = connessione.con.prepareStatement(query2);
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
            connessione.con.close();
            return r;
        }
    }
    
    public Boolean updateCoordinate(Coordinate coor) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "UPDATE coordinates SET latitude = ?, longitude = ?, address = ?, numero = ?, city = ?, nazione = ? WHERE id_restaurant = ?";
            sp = connessione.con.prepareStatement(query);
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
            connessione.con.close();
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
            sp = connessione.con.prepareStatement(query);
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
            connessione.con.close();
            return r;
        }
    }
    
    public Boolean cercaCoordinate_perId(Coordinate c) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "SELECT  * FROM COORDINATES WHERE ID_RESTAURANT = ? ";
            sp = connessione.con.prepareStatement(query);
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
            connessione.con.close();
            return r;
        }
    }
    
    public Boolean SetResForNazione (Coordinate cor, ArrayList <Integer> res) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
       
        try {
            query = "SELECT id_restaurant FROM coordinates WHERE nazione LIKE ? ";
            sp = connessione.con.prepareStatement(query);
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
            connessione.con.close();
        }
    }
    
    public Boolean SetResForCity (Coordinate cor, ArrayList <Integer> res) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
       
        try {
            query = "SELECT id_restaurant FROM coordinates WHERE city LIKE ? ";
            sp = connessione.con.prepareStatement(query);
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
            connessione.con.close();
        }
    }
    
    public Boolean SetResForAdrers (Coordinate cor, ArrayList <Integer> res) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
       
        try {
            query = "SELECT id_restaurant FROM coordinates WHERE address LIKE ?";
            sp = connessione.con.prepareStatement(query);
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
            connessione.con.close();
        }
    }
    
    

}
