package restaurants;

import database.DB_Coordinate;
import database.DB_CuisineRestaurant;
import database.DB_GestioneRestaurant;
import database.DB_GestioneUser;
import database.DB_OrariRestaurant;
import database.DB_PriceRestaurant;
import database.DB_RestaurantPhoto;
import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String string = request.getParameter("go").toLowerCase();
            String[] separated = string.split("\\s*,\\s*");           
            Set <Integer> id = new HashSet <Integer> ();
            
            for (int i=0; i<separated.length; i++) {
                for (int g = 0; g < separated[i].length(); g++) {
                    if (g >= 2) {
                        String str = separated[i].substring(0, g+1);
                        ArrayList <Integer> list = new ArrayList <Integer>();
                        Restaurant res = new Restaurant(-1, str, "", "",null, null, null, null);
                        if (!(new DB_GestioneRestaurant()).SetResForName(res, list))
                            request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                        
                        for (int j = 0;j<list.size();j++)
                            id.add(list.get(j));

                        ArrayList <Integer> list1 = new ArrayList <Integer>();
                        Coordinate cor = new Coordinate(null,null,str,null,str,str);
                        if (!(new DB_Coordinate()).SetResForNazione(cor, list1))
                            request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                        
                        for (int j = 0;j<list1.size();j++)
                            id.add(list1.get(j));

                        ArrayList <Integer> list2 = new ArrayList <Integer>();
                        if (!(new DB_Coordinate()).SetResForCity(cor, list2))
                            request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);   
                        
                        for (int j = 0;j<list2.size();j++)
                            id.add(list2.get(j));

                        ArrayList <Integer> list3 = new ArrayList <Integer>();
                        if (!(new DB_Coordinate()).SetResForAdrers(cor, list3))
                            request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                        
                        for (int j = 0;j<list3.size();j++)
                           id.add(list3.get(j));

                        ArrayList <Integer> list4 = new ArrayList <Integer>();
                        ArrayList <Integer> list5 = new ArrayList <Integer>();
                        
                        if (!(new DB_CuisineRestaurant()).SetResForCuisine(str,list4))
                            request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                        
                        for (int j = 0;j<list4.size();j++) {
                            if (!(new DB_CuisineRestaurant()).SetResForCuisineId(list4.get(j),list5))
                                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                        }
                        
                        for (int j = 0;j<list5.size();j++) {
                           id.add(list5.get(j));
                        }  
                    }
                }
            }    
            
            ArrayList <Restaurant> ALR = new ArrayList<Restaurant>();
            for(Integer idRest : id) { 
                Restaurant rest = new Restaurant(idRest);

                if ( ! new DB_GestioneRestaurant().cercaRistorante_perId(rest))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if ( ! new DB_GestioneUser().cercaUser_perId(rest.getCreator()))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if ( ! new DB_OrariRestaurant().cercaDay_hours_perId(rest.getDay_hours()))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);   

                if ( ! new DB_PriceRestaurant().cercaPriceRangeId(rest.getPrice_range()))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if ( ! new DB_Coordinate().cercaCoordinate_perId(rest.getCordinate()))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if ( ! new DB_CuisineRestaurant().cercaCusines_perRistoranye(rest))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if (!new DB_RestaurantPhoto().cercaPhotos_perRistorante(rest, 2))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if (!new DB_RestaurantPhoto().cercaPhotos_perRistorante(rest, 1))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                ALR.add(rest);
            }
            
            ArrayList <Integer> classifica = new ArrayList<Integer>();
            if ( ! new DB_GestioneRestaurant().classificaRisto(classifica))
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
              
            request.setAttribute("listaRistoranti", ALR);
            request.setAttribute("classifica", classifica);
            request.getRequestDispatcher("CercaRistorantiHome.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("error", "Ops! Si è verificato un errore");
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
    }
}