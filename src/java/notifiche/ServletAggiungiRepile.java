package notifiche;

import restaurants.Restaurant;
import users.User;
import database.DB_Replies;
import database.DB_RestaurantOwner;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author stefano
 */
@WebServlet(name = "ServletAggiungiRepile", urlPatterns = {"/ServletAggiungiRepile"})
public class ServletAggiungiRepile extends HttpServlet {
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String description = request.getParameter("descrizione");

            //CREATORE
            HttpSession session = request.getSession(false);
            User u = (User) session.getAttribute("User");
            
            //COMMENTO
            Integer idRew = Integer.parseInt(request.getParameter("commento"));
            
            //RISTORANETE
            Integer idRes = Integer.parseInt(request.getParameter("ristorante"));
            
            //FILTRO USER
            if ((u==null)||(! new DB_RestaurantOwner().isOwners_perRistoranti(u, new Restaurant(idRes)))) {
                request.setAttribute("error", "Zona protetta!");
                request.getRequestDispatcher("errore.jsp").forward(request, response);                    
            } else {            
                //CREO REP
                Replies rep = new Replies(null, description, null, null, null, u, idRew);
                ArrayList <String> check = new ArrayList <String> ();
                
                //INSERIMENTO DB
                if(! new DB_Replies().checkReplies(rep, idRew, check))
                     request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if(check.contains("yes"))
                    request.getRequestDispatcher("repRifiutato.jsp").forward(request, response);
                else {
                    if(! new DB_Replies().inserisciRisposta(rep, idRew))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);          
                    response.sendRedirect("rispostaInviataSuccesso.jsp");
                }
            }
        } catch (SQLException ex) {
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