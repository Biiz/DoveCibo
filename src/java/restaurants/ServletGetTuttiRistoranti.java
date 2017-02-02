package restaurants;

import database.DB_Coordinate;
import database.DB_CuisineRestaurant;
import database.DB_GestioneRestaurant;
import database.DB_GestioneUser;
import database.DB_OrariRestaurant;
import database.DB_PriceRestaurant;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author postal
 */
@WebServlet(name = "ServletGetTuttiRistoranti", urlPatterns = {"/ServletGetTuttiRistoranti"})
public class ServletGetTuttiRistoranti extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //RICERCA DB
            ArrayList <Restaurant> ALR = new ArrayList<Restaurant>();
            
            if ( new DB_GestioneRestaurant().tuttiRistoranti(ALR)) {
                for (Restaurant rest : ALR){
                    if ( ! new DB_GestioneUser().cercaUser_perId(rest.getCreator()))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                    
                    if ( ! new DB_OrariRestaurant().cercaDay_hours_perId(rest.getDay_hours()))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);   
                    
                    if ( ! new DB_PriceRestaurant().cercaPriceRangeId(rest.getPrice_range()))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                    if ( ! new DB_Coordinate().cercaCoordinate_perId(rest.getCordinate()))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
               
                    if( ! new DB_CuisineRestaurant().cercaCusines_perRistoranye(rest))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);                    
                }
            } else {
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            }
            
            request.setAttribute("listaRistoranti", ALR);
            request.getRequestDispatcher("QEL_CHE_LE.jsp").forward(request, response);
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