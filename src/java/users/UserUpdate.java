package users;

import database.DB_GestioneUser;
import emails.SendEmail_Modifica_Profilo;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Gestisce le modifiche dei dati di ogni utente dal sito
 *
 * @author stefano
 */
@WebServlet(name = "UserUpdate", urlPatterns = {"/UserUpdate"})
public class UserUpdate extends HttpServlet {
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
            HttpSession session = request.getSession(false);
           
            String name = request.getParameter("first_name");
            String surname = request.getParameter("last_name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            if(session != null && session.getAttribute("User") != null) {
                User user = (User) session.getAttribute("User");
                String nickName = user.getNickname();
                User u1 = new User(-1,"","",nickName,"","","");
                if (!(new DB_GestioneUser()).CheckProfilo(u1))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                
                //PRECONDIZIONI DB
                if(!u1.getEmail().equals(email)) {
                    if ((new DB_GestioneUser()).emailEsistente(email)) {
                        request.setAttribute("error", "Attenzione, l'email inserita non è valida!");
                        request.getRequestDispatcher("errore.jsp").forward(request, response);
                    }
                }

                User u = new User();
                u.setName(name);
                u.setSurname(surname);
                u.setEmail(email);
                u.setNickname(nickName);
                u.setPassword(password);

                if ((new DB_GestioneUser()).modificaAccount(u, nickName)) {
                    new SendEmail_Modifica_Profilo(name, surname, email, nickName, password);
                    if (!(new DB_GestioneUser()).CheckProfilo(u))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                    
                    session.invalidate();
                    request.getSession(true).setAttribute("User", u);
                    response.sendRedirect("/DoveCiboGit/modificheEffettuate.jsp"); 
                } else {
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                }
            } else {
                response.sendRedirect("/DoveCiboGit/home.jsp"); 
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
