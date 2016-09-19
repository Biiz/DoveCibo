/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoveCiboPK;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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

            DB_Manager dbm = new DB_Manager();

            if (dbm.accedi(u)) {
                if (u.getId() == null) {
                    request.setAttribute("error", "Non esiste tale account");
                    request.getRequestDispatcher("errore.jsp").forward(request, response);
                } else {
                    request.setAttribute("user", u);
                    request.getRequestDispatcher("home.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            }
            
            // Create cookies for first and last names.      
            Cookie firstName = new Cookie("nick_asd", "asd_nickname");
            Cookie lastName = new Cookie("nick_asd", "asd_password");

            // Set expiry date after 24 Hrs for both the cookies.
            firstName.setMaxAge(60*60*24); 
            lastName.setMaxAge(60*60*24); 

            // Add both the cookies in the response header.
            response.addCookie( firstName );
            response.addCookie( lastName );


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
