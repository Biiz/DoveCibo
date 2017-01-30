package DoveCiboPK;

import java.io.*;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = (User) request.getSession(false).getAttribute("User");
            String nickname = user.getNickname();

            DoveCiboPK.User u = new DoveCiboPK.User(-1, "", "", nickname, "", "", "");
            if (!(new DB_Manager()).CheckProfilo(u))
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            
            ArrayList <Integer> classifica = new ArrayList<Integer>();
            if ( ! new DB_Manager().classificaRisto(classifica))
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            
            request.getSession(false).invalidate();
            HttpSession session = request.getSession(true);
            session.setAttribute("User", u);
            
            List<Integer> id_restaurant = new ArrayList<Integer>();
            if (!(new DB_Manager()).SetIdRestaurant2(u, id_restaurant))
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            
            ArrayList <Restaurant> ALR = new ArrayList<Restaurant>();
            for (Integer idRest : id_restaurant) {
                Restaurant rest = new Restaurant(idRest);
                    
                if( ! new DB_Manager().cercaRistorante_perId(rest))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if( ! new DB_Manager().cercaUser_perId(rest.getCreator()))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if( ! new DB_Manager().cercaDay_hours_perId(rest.getDay_hours()))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);   

                if( ! new DB_Manager().cercaPriceRangeId(rest.getPrice_range()))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if( ! new DB_Manager().cercaCoordinate_perId(rest.getCordinate()))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if( ! new DB_Manager().cercaCusines_perRistoranye(rest))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                
                ALR.add(rest);
            }
            
            request.setAttribute("RistorantiProprietario", ALR);
            request.setAttribute("classifica", classifica);
            request.getRequestDispatcher("VisualizzaRistoranti.jsp").forward(request, response);
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
    }
}
