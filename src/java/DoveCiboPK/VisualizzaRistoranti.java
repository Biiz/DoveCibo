/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoveCiboPK;

import java.io.*;
import java.util.*;
import javax.servlet.http.*;
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
@WebServlet(name = "VisualizzaRistoranti", urlPatterns = {"/VisualizzaRistoranti"})
public class VisualizzaRistoranti extends HttpServlet {

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
            if(!(new DB_Manager()).CheckProfilo(u)){
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            }

            session.removeAttribute("id_restaurant");
            session.removeAttribute("res_name");
            session.removeAttribute("cuisine_name");
            session.removeAttribute("prezzo_min");
            session.removeAttribute("prezzo_max");
            session.removeAttribute("res_address");
            session.removeAttribute("res_city");
            
            
            List<Integer> id_restaurant = new ArrayList<Integer>();
            if(!(new DB_Manager()).SetIdRestaurant(u, id_restaurant)){
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            }
            session.setAttribute("id_restaurant", id_restaurant);
            Iterator itr = id_restaurant.iterator();
            int i = 0;
            while (itr.hasNext()) {
                Integer element = (Integer) itr.next();
                DoveCiboPK.Restaurant res = new DoveCiboPK.Restaurant(element, "", "", "", null, null, null, null);
                if(!(new DB_Manager()).cercaRistorante_perId(res)){
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                }
                session.setAttribute("res_name"+i, res.getName());
                
                List<Integer> id_cuisine = new ArrayList<Integer>();
                List<String> cuisine_name = new ArrayList<String>();
                if(!(new DoveCiboPK.DB_Manager()).cercaCuisine_perId_Restaurant(res, id_cuisine)){
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                }
                Iterator itr1 = id_cuisine.iterator();
                while (itr1.hasNext()) {
                    Integer element1 = (Integer) itr1.next();

                    if(!(new DoveCiboPK.DB_Manager()).cercaCuisine(element1, cuisine_name)){
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                    }

                }
                session.setAttribute("cuisine_name"+i, cuisine_name);
                
                Integer prezzo[] = {0,0};
                if(!(new DoveCiboPK.DB_Manager()).cercaPriceRanges_Restaurant(res, prezzo)){
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                }
                session.setAttribute("prezzo_min"+i, prezzo[0]);
                session.setAttribute("prezzo_max"+i, prezzo[1]);
                
                String address[] = {"w","W"};
                if(!(new DoveCiboPK.DB_Manager()).cercaCoordinate_perID_Restaurant(res, address)){
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                }
                session.setAttribute("res_address"+i, address[0]);
                session.setAttribute("res_city"+i, address[1]);
                
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
