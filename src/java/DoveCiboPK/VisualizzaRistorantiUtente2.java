/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoveCiboPK;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
//import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.SQLException;
//import java.util.Date;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stefano
 */
@WebServlet(name = "VisualizzaRistorantiUtente2", urlPatterns = {"/VisualizzaRistorantiUtente2"})
public class VisualizzaRistorantiUtente2 extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            HttpSession session = request.getSession(false);
            Cookie cookie[] = request.getCookies();
            String nickname = null;
            if (cookie != null) {
                for (int j = 0; j < cookie.length; j++) {
                    if (cookie[j].getValue().equals("1") || cookie[j].getValue().equals("2") || cookie[j].getValue().equals("3")) {
                        nickname = cookie[j].getName();
                    }
                }
            }

            DoveCiboPK.User u = new DoveCiboPK.User(-1, "", "", nickname, "", "", "");
            (new DB_Manager()).CheckProfilo(u);

            List<Integer> id_restaurant = new ArrayList<Integer>();
            (new DB_Manager()).SetIdRestaurant2(u, id_restaurant);
            session.setAttribute("id_restaurant", id_restaurant);
            Iterator itr = id_restaurant.iterator();
            int i = 0;
            while (itr.hasNext()) {
                Integer element = (Integer) itr.next();
                DoveCiboPK.Restaurant res = new DoveCiboPK.Restaurant(element, "", "", "", null, null, null, null);
                (new DB_Manager()).cercaRistorante_perId(res);
                session.setAttribute("res_name"+i, res.getName());
                
                List<Integer> id_cuisine = new ArrayList<Integer>();
                List<String> cuisine_name = new ArrayList<String>();
                (new DoveCiboPK.DB_Manager()).cercaCuisine_perId_Restaurant(res, id_cuisine);
                Iterator itr1 = id_cuisine.iterator();
                while (itr1.hasNext()) {
                    Integer element1 = (Integer) itr1.next();

                    (new DoveCiboPK.DB_Manager()).cercaCuisine(element1, cuisine_name);

                }
                session.setAttribute("cuisine_name"+i, cuisine_name);
                
                Integer prezzo[] = {0,0};
                (new DoveCiboPK.DB_Manager()).cercaPriceRanges_Restaurant(res, prezzo);
                session.setAttribute("prezzo_min"+i, prezzo[0]);
                session.setAttribute("prezzo_max"+i, prezzo[1]);
                
                String address[] = {"w"};
                (new DoveCiboPK.DB_Manager()).cercaCoordinate_perID_Restaurant(res, address);
                session.setAttribute("res_address"+i, address[0]);
                
                i++;   
            }
           
            response.sendRedirect("/DoveCiboGit/VisualizzaRistoranti.jsp");

        } catch (Exception ex) {
            request.setAttribute("error", ex.toString());
            request.getRequestDispatcher("errore.jsp").forward(request, response);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
