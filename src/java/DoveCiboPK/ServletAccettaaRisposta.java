package DoveCiboPK;

import database.DB_Manager;
import database.DB_GestioneUser;
import database.DB_Replies;
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
@WebServlet(name = "ServletAccettaaRisposta", urlPatterns = {"/ServletAccettaaRisposta"})
public class ServletAccettaaRisposta extends HttpServlet {
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
            //CREATORE
            User u = (User) request.getSession(false).getAttribute("User");
            User user = new User (-1, "","","","","", "");

            //FILTRO USER
            if((u==null)||(!u.getRole().equals("1"))) {
                request.setAttribute("error", "Zona protetta!");
                request.getRequestDispatcher("errore.jsp").forward(request, response);                
            } else {
                //RISTORANTE
                Integer idR = Integer.parseInt(request.getParameter("idGen"));            

                //INSERIMENTO DB
                if (! new DB_Replies().updateRepli(idR, u))
                     request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if (! new DB_GestioneUser().findUserRepli(idR, u, user))
                     request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                if (! new DB_Replies().deleteRepli(user))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                response.sendRedirect("/DoveCiboGit/rispostaAccettata.jsp");
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