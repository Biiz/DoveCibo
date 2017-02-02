package restaurant_comments;

import database.DB_GestioneRestaurant;
import database.DB_Reviews;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import restaurants.Restaurant;
import users.User;

/**
 * Gestisce l’aggiunta di un recensione e la valutazione di un ristorante
 *
 * @author stefano
 */
@WebServlet(name = "ServletAggiungiCommento", urlPatterns = {"/ServletAggiungiCommento"})
public class ServletAggiungiCommento extends HttpServlet {
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
            Integer global_v = Integer.parseInt(request.getParameter("global_value"));
            Integer food = Integer.parseInt(request.getParameter("food"));
            Integer service = Integer.parseInt(request.getParameter("service"));
            Integer value_for_money = Integer.parseInt(request.getParameter("value_for_money"));
            Integer atmospere = Integer.parseInt(request.getParameter("atmospere"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            
            //Verifico che i campi non siano vuoti
            if (name == null && description == null) {
                request.setAttribute("error", "Campi risposta non correttamente compilati");
                request.getRequestDispatcher("errore.jsp").forward(request, response);
            } else {
                //CREATORE
                HttpSession session = request.getSession(false);
                User u = (User) session.getAttribute("User");
                
                if (u==null) {
                    request.setAttribute("error", "accesso negato");
                    request.getRequestDispatcher("errore.jsp").forward(request, response);  
                }

                //RISTORANTE
                Integer idR = Integer.parseInt(request.getParameter("ristorante"));
                
                //CREO REW
                Review rew = new Review(null, global_v, food, service, value_for_money, atmospere, name, description, null, 0, u);
               
                //INSERIMENTO DB
                if (!new DB_Reviews().inserisciReview(rew, idR))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if (!new DB_Reviews().increaseReviewRestaurant(new Restaurant(idR)))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);                
                
                Restaurant res = new Restaurant (idR);
                if (!new DB_GestioneRestaurant().cercaRistorante_perId(res))
                   request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                
                Double reviews_value [] = new Double [1];
                if (!new DB_Reviews().countReviews(res, reviews_value))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                
                double newGV = ((((global_v + food + service + value_for_money + atmospere)/5) + res.getGlobal_value().intValue() + reviews_value[0])/3.0);
                    
                if (newGV <= 5) {
                    if (!new DB_GestioneRestaurant().updateRate(res, newGV))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                } else {
                    if (!new DB_GestioneRestaurant().updateRate(res, 5))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                }
                
                response.sendRedirect("/DoveCiboGit/ServletGetRistorante?idR=" + idR);
            }
        } catch (SQLException ex) {
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
