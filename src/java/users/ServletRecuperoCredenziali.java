package users;

import database.DB_GestioneUser;
import emails.SendEmail_Recupero_credenziali;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stefano
 */
@WebServlet(name = "ServletRecuperoCredenziali", urlPatterns = {"/ServletRecuperoCredenziali"})
public class ServletRecuperoCredenziali extends HttpServlet {
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
            String email = request.getParameter("email");
           
            if ((new DB_GestioneUser()).emailEsistente(email)) {
                User u = new User (-1,"","","",email,"","");
            
                if(!(new DB_GestioneUser()).CheckEmail(u))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            
                new SendEmail_Recupero_credenziali(u.getName(), u.getSurname(), u.getEmail(), u.getNickname(), u.getPassword());
                response.sendRedirect("/DoveCiboGit/confermaRecupero_credenziali.jsp"); 
            } else {
                request.setAttribute("error", "Attenzione, l'Email inserita non è valida!");
                request.getRequestDispatcher("errore.jsp").forward(request, response);
            }
        } catch (Exception ex) {
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
