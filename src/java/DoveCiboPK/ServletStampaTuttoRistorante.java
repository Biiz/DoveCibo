package DoveCiboPK;

import database.DB_Manager;
import database.DB_OrariRestaurant;
import database.DB_GestioneRestaurant;
import database.DB_PriceRestaurant;
import database.DB_CuisineRestaurant;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.DB_Coordinate;
import javax.servlet.http.HttpSession;

/**
 *
 * @author stefano
 */
@WebServlet(name = "ServletStampaTuttoRistorante", urlPatterns = {"/ServletStampaTuttoRistorante"})
public class ServletStampaTuttoRistorante extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            //CREATORE
            HttpSession session = request.getSession(false);
            User u = (User) session.getAttribute("User");
            
            // GIORNI D?APERTURA       
            Day_hours dh = new Day_hours(
                    Integer.parseInt(request.getParameter("StMh")),
                    Integer.parseInt(request.getParameter("StMm")),
                    Integer.parseInt(request.getParameter("FtMh")),
                    Integer.parseInt(request.getParameter("FtMm")),
                    Integer.parseInt(request.getParameter("StPh")),
                    Integer.parseInt(request.getParameter("StPm")),
                    Integer.parseInt(request.getParameter("FtPh")),
                    Integer.parseInt(request.getParameter("FtPm")));

            //PRICE RANGE               
            String pr = request.getParameter("price");
            String[] partPr = pr.split(",");
            Price_range priceRange = new Price_range(null, Double.parseDouble(partPr[0]), Double.parseDouble(partPr[1]));

            //COORDINATE
            Coordinate coordinate = new Coordinate(
                    request.getParameter("lat").isEmpty() ? null : Float.parseFloat(request.getParameter("lat")),
                    request.getParameter("lng").isEmpty() ? null : Float.parseFloat(request.getParameter("lng")),
                    request.getParameter("via").toLowerCase(),
                    Integer.parseInt(request.getParameter("numero_civico")),
                    request.getParameter("city").toLowerCase(),
                    request.getParameter("nazione").toLowerCase());

            //RISTORANTE
            Restaurant restaurant = new Restaurant(
                    null,
                    request.getParameter("nome_ristorante").toLowerCase(),
                    request.getParameter("descrizione"),
                    request.getParameter("link").toLowerCase(),
                    u,
                    null,
                    null,
                    null);


            //INSERISCI RISTORANTE
            if (new DB_GestioneRestaurant().inserisciRistorante(restaurant)) {
                coordinate.setId_resturant(restaurant.getId());
                dh.setId_restaurant(restaurant.getId());
                priceRange.setId_restaurant(restaurant.getId());
            } else {
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            }
            
            //INSERISCI PRICERANGE
            if (!(new DB_PriceRestaurant()).inserisciPrice_range(priceRange)) {
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            }
            
            //INSERISCI COORDINATE
            if (!(new DB_Coordinate().inserisciCoordinate(coordinate))) {
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            }
            
            //INSERISCI ORARI
            if (!new DB_OrariRestaurant().inserisciOrario(dh)) {
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            }
                
           

            //INSERISCI CUSINES
            String[] cucine_arr_string = new String[7];
            cucine_arr_string[1] = request.getParameter("cb1");
            cucine_arr_string[2] = request.getParameter("cb2");
            cucine_arr_string[3] = request.getParameter("cb3");
            cucine_arr_string[4] = request.getParameter("cb4");
            cucine_arr_string[5] = request.getParameter("cb5");
            cucine_arr_string[6] = request.getParameter("cb6");

            //ciclo l'array
            for (int i = 1; i < 7; i++) {
                if (cucine_arr_string[i] != null) {
                    if (!new DB_CuisineRestaurant().inserisciRelazioneCuisinesRestaurant(restaurant.getId(), i)) {
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                    }
                }

            }

            response.sendRedirect("/DoveCiboGit/ristorante_inserito_successo.jsp");

        } catch (Exception ex) {

        } finally {

        }
    }

}
