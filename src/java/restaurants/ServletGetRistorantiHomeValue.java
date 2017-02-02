package restaurants;

import database.DB_Coordinate;
import database.DB_CuisineRestaurant;
import database.DB_GestioneRestaurant;
import database.DB_GestioneUser;
import database.DB_OrariRestaurant;
import database.DB_PriceRestaurant;
import database.DB_RestaurantPhoto;
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
@WebServlet(name = "ServletGetRistorantiHomeValue", urlPatterns = {"/ServletGetRistorantiHomeValue"})
public class ServletGetRistorantiHomeValue extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Float lat = Float.parseFloat( request.getParameter("lat") );
            Float lng = Float.parseFloat( request.getParameter("lng") );
            
            //RICERCA DB
            ArrayList <Restaurant> ALR = new ArrayList<Restaurant>();
            ArrayList <Integer> classifica = new ArrayList<Integer>();
            if ( ! new DB_GestioneRestaurant().classificaRisto(classifica))
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            
            Coordinate coo = new Coordinate(lat,lng, null, null, null, null);
            double rangeLat = 0.05;
            double rangeLon = 0.1;
            
            if ( !new DB_GestioneRestaurant().ricercaRistorantiPerClassificaEVicinanza(ALR, coo, rangeLat, rangeLon))
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            
            for (Restaurant rest : ALR) {                                        
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

                if (!new DB_RestaurantPhoto().cercaPhotos_perRistorante(rest, 2))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                               
                if (!new DB_RestaurantPhoto().cercaPhotos_perRistorante(rest, 1))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);                   
            }
            
            request.setAttribute("listaRistoranti", ALR);
            request.setAttribute("mieCoordinate", coo);
            request.setAttribute("classifica", classifica);            
            request.getRequestDispatcher("cercaRistoHomeValue.jsp").forward(request, response);
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