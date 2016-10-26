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
@WebServlet(name = "CercaRistorantiHome", urlPatterns = {"/CercaRistorantiHome"})
public class CercaRistorantiHome extends HttpServlet {

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
            
            String string = request.getParameter("go").toLowerCase();
            
            String[] separated = string.split("\\s*,\\s*");
            
            
            Set <Integer> id = new HashSet <Integer> ();
            
            for(int i = 0;i<separated.length;i++){
                ArrayList <Integer> list = new ArrayList <Integer>();
                Restaurant res = new Restaurant(-1, separated[i], "", "",null, null, null, null);
                (new DB_Manager()).SetResForName(res, list);
                for(int j = 0;j<list.size();j++){
                    id.add(list.get(j));
                }
                
                ArrayList <Integer> list1 = new ArrayList <Integer>();
                Coordinate cor = new Coordinate(null,null,separated[i],separated[i],separated[i]);
                (new DB_Manager()).SetResForNazione(cor, list1);
                for(int j = 0;j<list1.size();j++){
                    id.add(list1.get(j));
                }
                
                ArrayList <Integer> list2 = new ArrayList <Integer>();
                (new DB_Manager()).SetResForCity(cor, list2);
                for(int j = 0;j<list2.size();j++){
                    id.add(list2.get(j));
                }
                
                ArrayList <Integer> list3 = new ArrayList <Integer>();
                (new DB_Manager()).SetResForAdrers(cor, list3);
                for(int j = 0;j<list3.size();j++){
                   id.add(list3.get(j));
                }
                
                ArrayList <Integer> list4 = new ArrayList <Integer>();
                ArrayList <Integer> list5 = new ArrayList <Integer>();
                String str = separated[i];
                (new DB_Manager()).SetResForCuisine(str,list4);
                for(int j = 0;j<list4.size();j++){
                    (new DB_Manager()).SetResForCuisineId(list4.get(j),list5);
                }
                for(int j = 0;j<list5.size();j++){
                   id.add(list5.get(j));
                }
            }
            
           
            
            
            
            session.removeAttribute("id_restaurant");
            session.removeAttribute("res_name");
            session.removeAttribute("cuisine_name");
            session.removeAttribute("prezzo_min");
            session.removeAttribute("prezzo_max");
            session.removeAttribute("res_address");
            
            session.setAttribute("id_restaurant", id);
            
            int i= 0;   
            for (Iterator<Integer> it = id.iterator(); it.hasNext(); ) {
                Integer f = it.next();
                Restaurant res = new Restaurant(f, "", "", "",null, null, null, null);
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
            
            response.sendRedirect("/DoveCiboGit/CercaRistorantiHome.jsp");
            
    
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