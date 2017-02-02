package restaurant_photos;

import database.DB_RestaurantPhoto;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import users.User;

/**
 * Gestisce la rimozione delle foto
 *
 * @author stefano
 */
@WebServlet(name = "ServletModificaFoto", urlPatterns = {"/ServletModificaFoto"})
public class ServletModificaFoto extends HttpServlet {
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
            //CREATORE
            User u = (User) request.getSession(false).getAttribute("User");
            
            //RISTORANTE
            Integer idF = Integer.parseInt(request.getParameter("foto"));
            Integer val = Integer.parseInt(request.getParameter("val")); 
                       
            if ((u==null) || (!u.getRole().equals("1")) && (val==2 || val==0)) {
                request.setAttribute("error", "Zona protetta!");
                request.getRequestDispatcher("errore.jsp").forward(request, response);                
            } else if ((!u.getRole().equals("2")) && (val==1)) {               //CONTROLLA OWNWER!!! DA FARE
                request.setAttribute("error", "Zona protetta!");
                request.getRequestDispatcher("errore.jsp").forward(request, response);                
            } else {
                //INSERIMENTO DB
                if (! new DB_RestaurantPhoto().updatePhotoVal(idF, val, u))
                     request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                if (u.getRole().equals("1") && val == 0)
                   response.sendRedirect("confermaDeletePhoto.jsp");
                else if (!u.getRole().equals("1") && val == 1)
                    response.sendRedirect("confermaSegnalazionePhoto.jsp");
                else if (u.getRole().equals("1") && val == 2)
                    response.sendRedirect("cancellaNotificaPhoto.jsp");           
            }       
        } catch (SQLException ex) {
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
