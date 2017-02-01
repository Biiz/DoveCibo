package restaurants;

import restaurant_comments.Review;
import users.User;
import database.DB_Coordinate;
import database.DB_CuisineRestaurant;
import database.DB_GestioneRestaurant;
import database.DB_GestioneUser;
import database.DB_Manager;
import database.DB_OrariRestaurant;
import database.DB_PriceRestaurant;
import database.DB_RestaurantOwner;
import database.DB_RestaurantPhoto;
import database.DB_Reviews;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author postal
 */
@WebServlet(name = "ServletGetRistoranteProprietario", urlPatterns = {"/ServletGetRistoranteProprietario"})
public class ServletGetRistoranteProprietario extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //CATTUTARE ID RISTORANTE
            Integer idR = Integer.parseInt( request.getParameter("idR") );
            Restaurant rest = new Restaurant(idR);

            DB_Manager dbm = new DB_Manager();
            if ( new DB_GestioneRestaurant().cercaRistorante_perId(rest)) {
                if (!new DB_RestaurantOwner().cercaOwners_perRistoranti(rest))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                for (User u : rest.getOwners()) {
                    if ( ! new DB_GestioneUser().cercaUser_perId(u))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                }

                if ( ! new DB_GestioneUser().cercaUser_perId(rest.getCreator()))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if ( ! new DB_OrariRestaurant().cercaDay_hours_perId(rest.getDay_hours()))
                      request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if ( ! new DB_PriceRestaurant().cercaPriceRangeId(rest.getPrice_range()))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if ( ! new DB_Coordinate().cercaCoordinate_perId(rest.getCordinate()))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if ( ! new DB_Reviews().setCommenti_perRistorante(rest))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);     

                if ( ! new DB_CuisineRestaurant().cercaCusines_perRistoranye(rest))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);                    

                if ( ! new DB_RestaurantPhoto().cercaPhotos_perRistorante(rest, 0))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                for (Review rew : rest.getReviews()) {                        
                    if (! new DB_GestioneUser().cercaUser_perId(rew.getCreator()))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);  
                }    
            } else {
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            }
            
            request.getSession(false).removeAttribute("ristorante");
            request.getSession(false).setAttribute("ristorante", rest);
            request.getRequestDispatcher("modificaRistorante.jsp").forward(request, response);
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