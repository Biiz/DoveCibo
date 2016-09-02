/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posty;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stefano
 */
@WebServlet(name = "ServletRegistrazione", urlPatterns = {"/ServletRegistrazione"})
public class ServletRegistrazione extends HttpServlet {

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

            String first_name = request.getParameter("first_name");
            String last_name = request.getParameter("last_name");
            String nickname = request.getParameter("nickname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            //PRECONDIZIONI
            if (nickname.isEmpty()) {
                request.setAttribute("error", "Empty nickname");
                request.getRequestDispatcher("errore.jsp").forward(request, response);
            }

            if (password.isEmpty()) {
                request.setAttribute("error", "Empty password");
                request.getRequestDispatcher("errore.jsp").forward(request, response);
            }

            if (first_name.isEmpty()) {
                request.setAttribute("error", "Empty first name");
                request.getRequestDispatcher("errore.jsp").forward(request, response);
            }

            if (last_name.isEmpty()) {
                request.setAttribute("error", "Empty last name");
                request.getRequestDispatcher("errore.jsp").forward(request, response);
            }

            if (email.isEmpty()) {
                request.setAttribute("error", "Empty email");
                request.getRequestDispatcher("errore.jsp").forward(request, response);
            }

            //PRECONDIZIONI DB
            if ((new DB_Menaget()).niknameEsistente(nickname)) {
                request.setAttribute("error", "Attenzione, il Nickname inserito non è valido!");
                request.getRequestDispatcher("errore.jsp").forward(request, response);
            }

            if ((new DB_Menaget()).emailEsistente(email)) {
                request.setAttribute("error", "Attenzione, l'Email inserita non è valida!");
                request.getRequestDispatcher("errore.jsp").forward(request, response);
            }

            //INSERIMENTO DB
            User u = new User(null, first_name, last_name, nickname, email, password);

            DB_Menaget dbm = new DB_Menaget();

            if (dbm.inserisciAccount(u)) {
                request.getRequestDispatcher("registrazioneEffettuata.jsp").forward(request, response);
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
