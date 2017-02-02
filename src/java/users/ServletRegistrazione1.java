package users;

import database.DB_GestioneUser;
import emails.SendEmail_Attivazione;
import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Gestisce la registrazione di ogni utente dal sito
 *
 * @author stefano
 */
@WebServlet(name = "ServletRegistrazione1", urlPatterns = {"/ServletRegistrazione1"})
public class ServletRegistrazione1 extends HttpServlet {
    /**
     *
     * @param request oggetto di tipo HttpServletRequest contentente tutte le richieste fatte dal client alla servlet
     * @param response oggetto di tipo HttpServletResponse contenente tutte le risposte inviate dalla servlet al client
     * @throws ServletException se la richiesta per la POST non puo' essere gestita
     * @throws IOException se viene rilevato un errore di I/O quando la servlet gestisce la richiesta POST
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String first_name = null;
        String last_name = null;
        String nickname = null;
        String email = null;
        String password = null;
        String role = "3";
        
        try {
            first_name = request.getParameter("first_name");
            last_name = request.getParameter("last_name");
            nickname = request.getParameter("nickname");
            email = request.getParameter("email");
            
            //PRECONDIZIONI DB
            if (new DB_GestioneUser().niknameEsistente(nickname)) {
                request.setAttribute("error", "Attenzione, il nickname inserito non è valido!");
                request.getRequestDispatcher("errore.jsp").forward(request, response);
            }

            if (new DB_GestioneUser().emailEsistente(email)) {
                request.setAttribute("error", "Attenzione, l'email inserita non è valida!");
                request.getRequestDispatcher("errore.jsp").forward(request, response);
            }
            
            //Password random generator
            String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            StringBuilder salt = new StringBuilder();
            Random rnd = new Random();
            while (salt.length() < 8) {
                int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                salt.append(SALTCHARS.charAt(index));
            }
            
            password = salt.toString();
            
            //INSERIMENTO DB
            User u = new User(null, first_name, last_name, nickname, email, password, role);

            if ((new DB_GestioneUser()).inserisciAccount(u)) {
                SendEmail_Attivazione email_registrazione = new SendEmail_Attivazione(first_name, nickname, password, email);
                response.sendRedirect("/DoveCiboGit/registrazioneEffettuata.jsp"); 
            } else {
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
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
