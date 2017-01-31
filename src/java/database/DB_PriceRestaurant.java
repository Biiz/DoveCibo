/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import DoveCiboPK.Price_range;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author michael
 */
public class DB_PriceRestaurant extends HttpServlet {

    private String errore = "";
    DB_Manager connessione;

    public DB_PriceRestaurant() throws SQLException {
        connessione = new DB_Manager ();
    }
    
    public String getErrore() { return errore; }
    
    public Boolean updatePriceRange(Price_range price) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "UPDATE price_ranges SET min_value = ?, max_value = ? WHERE id_restaurant = ?";
            sp = connessione.con.prepareStatement(query);
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
            connessione.con.close();
            return r;
        }
    }
    
    public Boolean inserisciPrice_range(Price_range pr) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "INSERT INTO price_ranges(id_restaurant, min_value, max_value)" + "VALUES(?,?,?)";
            sp = connessione.con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
            connessione.con.close();
            return r;
        }
    }
    
    public Boolean cercaPriceRangeId(Price_range price) throws SQLException {
        PreparedStatement sp = null;
        String query = null;
        Boolean r = null;
 
        try {
            query = "SELECT * FROM price_ranges WHERE id_restaurant = ?";
            sp = connessione.con.prepareStatement(query);
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
            connessione.con.close();
            return r;
        }
    }

}
