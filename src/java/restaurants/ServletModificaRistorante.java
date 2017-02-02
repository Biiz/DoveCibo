package restaurants;

import database.DB_Coordinate;
import database.DB_CuisineRestaurant;
import database.DB_GestioneRestaurant;
import database.DB_OrariRestaurant;
import database.DB_PriceRestaurant;
import database.DB_RestaurantOwner;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import users.User;

/**
 * Convalida le modifiche apportate ad un ristorante da modificaRistorante.jsp
 *
 * @author stefano
 */
@WebServlet(name = "ServletModificaRistorante", urlPatterns = {"/ServletModificaRistorante"})
public class ServletModificaRistorante extends HttpServlet {
    /**
     *
     * @param request oggetto di tipo HttpServletRequest contentente tutte le richieste fatte dal client alla servlet
     * @param response oggetto di tipo HttpServletResponse contenente tutte le risposte inviate dalla servlet al client
     * @throws ServletException se la richiesta per la POST non puo' essere gestita
     * @throws IOException se viene rilevato un errore di I/O quando la servlet gestisce la richiesta POST
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            //CREATORE
            Restaurant R = (Restaurant) request.getSession(false).getAttribute("ristorante");
            User u = (User) request.getSession(false).getAttribute("User");

            //FILTRO USER
            if ((u == null) || !new DB_RestaurantOwner().isOwners_perRistoranti(u, R)) {
                request.setAttribute("error", "Zona protetta!");
                request.getRequestDispatcher("errore.jsp").forward(request, response);
            } else {
                // ORARI       
                Day_hours dh = new Day_hours(
                        Integer.parseInt(request.getParameter("StMh")),
                        Integer.parseInt(request.getParameter("StMm")),
                        Integer.parseInt(request.getParameter("FtMh")),
                        Integer.parseInt(request.getParameter("FtMm")),
                        Integer.parseInt(request.getParameter("StPh")),
                        Integer.parseInt(request.getParameter("StPm")),
                        Integer.parseInt(request.getParameter("FtPh")),
                        Integer.parseInt(request.getParameter("FtPm")));

                //UPDATE ORARI
                dh.setId_restaurant(R.getId());
                if (!new DB_OrariRestaurant().updateOrario(dh)) {
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                }
                
                //PRICE RANGE               
                String pr = request.getParameter("price");
                String[] partPr = pr.split(",");
                Price_range priceRange = new Price_range(null, Double.parseDouble(partPr[0]), Double.parseDouble(partPr[1]));
                
                //UPDATE PRICE RANGE 
                priceRange.setId_restaurant(R.getId());
                if (!new DB_PriceRestaurant().updatePriceRange(priceRange)) {
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                }
                
                //COORDINATE
                Coordinate coordinate = new Coordinate(
                        request.getParameter("lat").isEmpty() ? null : Float.parseFloat(request.getParameter("lat")),
                        request.getParameter("lng").isEmpty() ? null : Float.parseFloat(request.getParameter("lng")),
                        request.getParameter("txtVia").toLowerCase(),
                        Integer.parseInt(request.getParameter("txtNumero")),
                        request.getParameter("txtCity").toLowerCase(),
                        request.getParameter("txtNazione").toLowerCase());

                //UPDATE COORDINATE
                coordinate.setId_resturant(R.getId());
                if (!(new DB_Coordinate().updateCoordinate(coordinate))) {
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                }
               
                //RESTAURANT
                Restaurant restaurant = new Restaurant(
                        R.getId(),
                        request.getParameter("nome_ristorante").toLowerCase(),
                        request.getParameter("descrizione"),
                        request.getParameter("link").toLowerCase(),
                        null,
                        null,
                        null,
                        null);

                //UPDATE NAME, LINK, DESCRIPTION
                if (!(new DB_GestioneRestaurant().updateRestaurant(restaurant))) {
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                }

                //CUISINE
                String[] cucine_arr_string = new String[7];
                cucine_arr_string[1] = request.getParameter("cb1");
                cucine_arr_string[2] = request.getParameter("cb2");
                cucine_arr_string[3] = request.getParameter("cb3");
                cucine_arr_string[4] = request.getParameter("cb4");
                cucine_arr_string[5] = request.getParameter("cb5");
                cucine_arr_string[6] = request.getParameter("cb6");

                //REMOVE CUISINE
                if (!new DB_CuisineRestaurant().removeCuisine(R.getId())) {
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                }

                //UPDATE CUISINE
                for (int i = 1; i < 7; i++) {
                    if (cucine_arr_string[i] != null) {
                        if (!new DB_CuisineRestaurant().inserisciRelazioneCuisinesRestaurant(R.getId(), i)) {
                            request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                        }
                    }
                }
            }
            request.getRequestDispatcher("ristorante_modificato_successo.jsp").forward(request, response);
        } catch (Exception ex) { } finally { }
    }
}
