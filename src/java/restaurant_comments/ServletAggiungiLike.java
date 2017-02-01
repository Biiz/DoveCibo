package restaurant_comments;

import restaurants.Restaurant;
import users.User;
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

/**
 *
 * @author stefano
 */
@WebServlet(name = "ServletAggiungiLike", urlPatterns = {"/ServletAggiungiLike"})
public class ServletAggiungiLike extends HttpServlet {
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
