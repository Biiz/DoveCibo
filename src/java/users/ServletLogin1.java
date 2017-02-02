package users;

import database.DB_GestioneUser;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Gestisce il log-in di ogni utente dal sito
 *
 * @author stefano
 */
@WebServlet(name = "ServletLogin1", urlPatterns = {"/ServletLogin1"})
public class ServletLogin1 extends HttpServlet {
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
            String nickname = request.getParameter("nickname");
            String password = request.getParameter("password");

            //PRECONDIZIONI
            if (nickname.isEmpty()) {
                request.setAttribute("error", "Nickname non inserito");
                request.getRequestDispatcher("errore.jsp").forward(request, response);
            }

            if (password.isEmpty()) {
                request.setAttribute("error", "Password non inserita");
                request.getRequestDispatcher("errore.jsp").forward(request, response);
            }

            //RICERCA DB
            User u = new User(nickname, password);           
            if ((new DB_GestioneUser()).accedi(u)) {               
                if (u.getId() == null) {
                    request.setAttribute("error", "Nickname e/o password incorretti");
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