/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoveCiboPK;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import java.util.Date;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author stefano
 */
@WebServlet(name = "ServletLogin", urlPatterns = {"/ServletLogin"})
public class ServletLogin extends HttpServlet {

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

            String nickname = request.getParameter("nickname");
            String password = request.getParameter("password");

            //PRECONDIZIONI
            if (nickname.isEmpty()) {
                request.setAttribute("error", "Empty nikname");
                request.getRequestDispatcher("errore.jsp").forward(request, response);
            }

            if (password.isEmpty()) {
                request.setAttribute("error", "Empty password");
                request.getRequestDispatcher("errore.jsp").forward(request, response);
            }

            //RICERCA DB
            User u = new User(nickname, password);

            
            if ((new DB_Manager()).accedi(u)) {
                
                if (u.getId() == null) {
                    request.setAttribute("error", "Nome o password incorretti");
                    request.getRequestDispatcher("errore.jsp").forward(request, response);
                } else {
                    request.getSession(false).invalidate();
                    
                    HttpSession session = request.getSession(true);
                    
                    session.setAttribute("User", u);
                    response.sendRedirect("/DoveCiboGit/home.jsp");
                }
            } else {
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            }

        } catch (Exception ex) {
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