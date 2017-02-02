package restaurants;

import database.DB_Coordinate;
import database.DB_CuisineRestaurant;
import database.DB_GestioneRestaurant;
import database.DB_GestioneUser;
import database.DB_OrariRestaurant;
import database.DB_PriceRestaurant;
import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import users.User;

/**
 *
 * @author stefano
 */
@WebServlet(name = "VisualizzaRistorantiUtente2", urlPatterns = {"/VisualizzaRistorantiUtente2"})
public class VisualizzaRistorantiUtente2 extends HttpServlet {
    /**
     *
     * @param request oggetto di tipo HttpServletRequest contentente tutte le richieste fatte dal client alla servlet
     * @param response oggetto di tipo HttpServletResponse contenente tutte le risposte inviate dalla servlet al client
     * @throws ServletException se la richiesta per la POST non puo' essere gestita
     * @throws IOException se viene rilevato un errore di I/O quando la servlet gestisce la richiesta POST
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = (User) request.getSession(false).getAttribute("User");
            String nickname = user.getNickname();

            users.User u = new users.User(-1, "", "", nickname, "", "", "");
            if (!(new DB_GestioneUser()).CheckProfilo(u))
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            
            ArrayList <Integer> classifica = new ArrayList<Integer>();
            if ( ! new DB_GestioneRestaurant().classificaRisto(classifica))
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            
            request.getSession(false).invalidate();
            HttpSession session = request.getSession(true);
            session.setAttribute("User", u);
            
            List<Integer> id_restaurant = new ArrayList<Integer>();
            if (!(new DB_GestioneRestaurant()).SetIdRestaurant2(u, id_restaurant))
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            
            ArrayList <Restaurant> ALR = new ArrayList<Restaurant>();
            for (Integer idRest : id_restaurant) {
                Restaurant rest = new Restaurant(idRest);
                    
                if( ! new DB_GestioneRestaurant().cercaRistorante_perId(rest))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if( ! new DB_GestioneUser().cercaUser_perId(rest.getCreator()))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if( ! new DB_OrariRestaurant().cercaDay_hours_perId(rest.getDay_hours()))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);   

                if( ! new DB_PriceRestaurant().cercaPriceRangeId(rest.getPrice_range()))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if( ! new DB_Coordinate().cercaCoordinate_perId(rest.getCordinate()))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if( ! new DB_CuisineRestaurant().cercaCusines_perRistoranye(rest))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                
                ALR.add(rest);
            }
            
            request.setAttribute("RistorantiProprietario", ALR);
            request.setAttribute("classifica", classifica);
            request.getRequestDispatcher("VisualizzaRistoranti.jsp").forward(request, response);
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
