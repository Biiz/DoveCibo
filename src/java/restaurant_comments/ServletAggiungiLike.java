package restaurant_comments;

import database.DB_GestioneRestaurant;
import database.DB_GestioneUser;
import database.DB_Reviews;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import restaurants.Restaurant;
import users.User;

/**
 *
 * @author stefano
 */
@WebServlet(name = "ServletAggiungiLike", urlPatterns = {"/ServletAggiungiLike"})
public class ServletAggiungiLike extends HttpServlet {
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
            System.out.println("OK");
                        
            //REVIEW
            Integer idRew =Integer.parseInt(request.getParameter("commento"));
            //USER REVIEW
            Integer idUR =Integer.parseInt(request.getParameter("userCommento"));
            //RISTORANTE
            Integer idRes =Integer.parseInt(request.getParameter("ristorante"));            
            Restaurant res = new Restaurant(idRes);
            
            if (!new DB_GestioneRestaurant().cercaRistorante_perId(res))
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

            if (!new DB_GestioneUser().increaseLikeUser(new User(idUR)))
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
        
            if (!new DB_Reviews().increaseLikeReview(new Review(idRew)))
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            
            response.sendRedirect("/DoveCiboGit/ServletGetRistorante?idR="+idRes);            
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
