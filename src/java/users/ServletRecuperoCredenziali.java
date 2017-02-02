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
 * Gestisce il recupero di credenziali di ogni utente dal sito
 *
 * @author stefano
 */
@WebServlet(name = "ServletRecuperoCredenziali", urlPatterns = {"/ServletRecuperoCredenziali"})
public class ServletRecuperoCredenziali extends HttpServlet {
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
