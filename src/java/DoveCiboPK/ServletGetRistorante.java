package DoveCiboPK;

import database.DB_Coordinate;
import database.DB_CuisineRestaurant;
import database.DB_GestioneRestaurant;
import database.DB_GestioneUser;
import database.DB_Manager;
import database.DB_OrariRestaurant;
import database.DB_PriceRestaurant;
import database.DB_Replies;
import database.DB_RestaurantOwner;
import database.DB_RestaurantPhoto;
import database.DB_Reviews;
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
@WebServlet(name = "ServletGetRistorante", urlPatterns = {"/ServletGetRistorante"})
public class ServletGetRistorante extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //CATTURARE ID RISTORANTE
            Integer idR = Integer.parseInt(request.getParameter("idR"));
            String risposta[] = new String[1];
            String risposta1[] = new String[1];
            ArrayList<Replies> replies = new ArrayList<Replies>();

            Restaurant rest = new Restaurant(idR);
            DB_Manager dbm = new DB_Manager();
            if (new DB_GestioneRestaurant().cercaRistorante_perId(rest)) {
                if (!new DB_RestaurantOwner().cercaOwners_perRistoranti(rest))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                for (User u : rest.getOwners()) {
                    if (!new DB_GestioneUser().cercaUser_perId(u))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                }

                if (!new DB_GestioneUser().cercaUser_perId(rest.getCreator()))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if (!new DB_OrariRestaurant().cercaDay_hours_perId(rest.getDay_hours()))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if (!new DB_PriceRestaurant().cercaPriceRangeId(rest.getPrice_range()))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if (!new DB_Coordinate().cercaCoordinate_perId(rest.getCordinate()))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if (!new DB_Reviews().setCommenti_perRistorante(rest))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if (!new DB_CuisineRestaurant().cercaCusines_perRistoranye(rest))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if (!new DB_RestaurantPhoto().cercaPhotos_perRistorante(rest, 2))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                
                if (!new DB_RestaurantPhoto().cercaPhotos_perRistorante(rest, 1))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if (request.getSession(false).getAttribute("User") != null) {
                    User user = (User) request.getSession(false).getAttribute("User");
                    if (!new DB_GestioneUser().checkUserIsRisto(rest, user, risposta))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                    
                    if (!new DB_GestioneUser().checkUserIsRistoNoValidation(rest, user, risposta1))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                } else {
                    risposta[0] = "no";
                    risposta1[0] = "no";
                }
                
                for (Review rew : rest.getReviews()) {
                    if (!new DB_GestioneUser().cercaUser_perId(rew.getCreator()))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                    if (!new DB_Replies().setRepli_perRew(rew, replies))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                }
            } else
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            
            ArrayList <Integer> classifica = new ArrayList <Integer> ();           
            if (!new DB_Coordinate().classificaRistoCitta(idR, classifica))
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            
            //Creo l'oggetto QR
            QR_generator qr = new QR_generator(rest);
            //Ottengo la stringa descrittiva da inserire nel QR
            String qrCode = qr.qr_Gen();

            request.setAttribute("ristorante", rest);
            request.setAttribute("classificaCity", classifica);
            request.setAttribute("qrCode", qrCode);
            request.setAttribute("rispostaUserIsOwner", risposta);
            request.setAttribute("rispostaUserIsOwnerNoValidation", risposta1);
            request.setAttribute("repliesOwner", replies);
            request.getRequestDispatcher("ristorante.jsp").forward(request, response);
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
