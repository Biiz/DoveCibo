package notifiche;

import database.DB_Notifica;
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
 * Gestisce il rifiuto da parte dell’Admin di assegnare un ristorante ad un utente
 *
 * @author stefano
 */
@WebServlet(name = "ServletRifiutaReclamo", urlPatterns = {"/ServletRifiutaReclamo"})
public class ServletRifiutaReclamo extends HttpServlet {

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
            //RISTORANTE
            Integer idRO = Integer.parseInt(request.getParameter("idGen"));            
            
            //CREATORE
            Restaurant R = (Restaurant) request.getSession(false).getAttribute("ristorante"); 
            User u = (User) request.getSession(false).getAttribute("User");
            
            //FILTRO USER
            if ((u==null) || (! u.getRole().equals("1")) ) {
                request.setAttribute("error", "Zona protetta!");
                request.getRequestDispatcher("errore.jsp").forward(request, response);                    
            } else {  
                //INSERIMENTO DB
                if(! new DB_Notifica().rifiutaReclamo(idRO))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);     
                    response.sendRedirect("/DoveCiboGit/reclamoRifiutato.jsp");
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
