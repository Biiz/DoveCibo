/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoveCiboPK;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {

            System.out.println("OK!!!!!!!!!!!!!!!!");
            
            
            //REVIEW
            Integer idRew =Integer.parseInt(request.getParameter("commento"));
            //USER REVIEW
            Integer idUR =Integer.parseInt(request.getParameter("userCommento"));
            //RISTORANTE
            Integer idRes =Integer.parseInt(request.getParameter("ristorante"));
            
            if (!new DB_Manager().increaseLikeUser(new User(idUR)))
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
        
             if (!new DB_Manager().increaseLikeReview(new Review(idRew)))
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
    }// </editor-fold>

}
