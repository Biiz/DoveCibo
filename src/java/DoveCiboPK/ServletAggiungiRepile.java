/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoveCiboPK;

import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet(name = "ServletAggiungiRepil", urlPatterns = {"/ServletAggiungiRepil"})
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {

            String description = request.getParameter("description");
            
            //Photo photo = new Photo(1); //DA SISTEMARE CON UPLOAD ED INSERIMENTO RIST

            //CREATORE
            HttpSession session = request.getSession(false);
            User u = (User) session.getAttribute("user");
            
            //RISTORANTE
            Integer idRew = Integer.parseInt(request.getParameter("commento"));
            
            //CREO REP
            Replies rep = new Replies(null, description, null, null, null);
            

            
            
            //INSERIMENTO DB
            if(! new DB_Manager().inserisciRisposta(rep, u.getId(), idRew))
                 request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);          

            
            response.sendRedirect("/DoveCiboGit/Notifiche");
        
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
    }// </editor-fold>

}
