package users;

import database.DB_RestaurantOwner;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stefano
 */
@WebServlet(name = "ServletAggiungiOwner", urlPatterns = {"/ServletAggiungiOwner"})
public class ServletAggiungiOwner extends HttpServlet {
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
            //CREATORE
            User u = (User) request.getSession(false).getAttribute("User");
            
            //RISTORANTE
            Integer idR = Integer.parseInt(request.getParameter("ristorante"));
            
            //CONTROLLO UTENTE
            if (u==null) {
                request.setAttribute("error", "accesso negato");
                request.getRequestDispatcher("errore.jsp").forward(request, response);  
            }
            //INSERIMENTO DB
            else if (! new DB_RestaurantOwner().inserisciRelazioneOwnerRestaurant(idR, u.getId()));
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);          

            response.sendRedirect("/DoveCiboGit/ServletGetRistorante?idR="+idR);        
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
