package notifiche;

import database.DB_GestioneUser;
import database.DB_RestaurantOwner;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import users.User;

/**
 *
 * @author stefano
 */
@WebServlet(name = "ServletAccettaReclamo", urlPatterns = {"/ServletAccettaReclamo"})
public class ServletAccettaReclamo extends HttpServlet {
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
            User user = new User (-1, "","","","","", "");

            //FILTRO USER
            if ((u==null)||(!u.getRole().equals("1"))) {
                request.setAttribute("error", "Zona protetta!");
                request.getRequestDispatcher("errore.jsp").forward(request, response);                
            }
        
            //RISTORANTE
            Integer idRO = Integer.parseInt(request.getParameter("idGen"));            
            
            //INSERIMENTO DB
            if (! new DB_RestaurantOwner().updateResOwn(idRO, u))
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);     
            if (! new DB_RestaurantOwner().selectOwn(idRO, u, user))
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);     
            
            //CAMBIA RUOLO
            if (! new DB_GestioneUser().updateRuolo(user));
            
            response.sendRedirect("/DoveCiboGit/reclamoAccettato.jsp");            
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
