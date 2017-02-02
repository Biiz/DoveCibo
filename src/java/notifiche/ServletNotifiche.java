package notifiche;

import database.DB_GestioneRestaurant;
import database.DB_GestioneUser;
import database.DB_Notifica;
import database.DB_Replies;
import database.DB_RestaurantOwner;
import database.DB_RestaurantPhoto;
import database.DB_Reviews;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import restaurant_comments.Review;
import restaurant_photos.Photo;
import restaurants.Restaurant;
import users.User;

/**
 *
 * @author postal
 */
@WebServlet(name = "ServletNotifiche", urlPatterns = {"/ServletNotifiche"})
public class ServletNotifiche extends HttpServlet {
    /**
     *
     * @param request oggetto di tipo HttpServletRequest contentente tutte le richieste fatte dal client alla servlet
     * @param response oggetto di tipo HttpServletResponse contenente tutte le risposte inviate dalla servlet al client
     * @throws ServletException se la richiesta per la GET non puo' essere gestita
     * @throws IOException se viene rilevato un errore di I/O quando la servlet gestisce la richiesta GET
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //CREATORE
            HttpSession session = request.getSession(false);
            User u = (User) session.getAttribute("User");

            if ((u==null) || ! (u.getRole().equals("2") || u.getRole().equals("1")) ){
                request.setAttribute("error", "accesso negato");
                request.getRequestDispatcher("errore.jsp").forward(request, response);  
            }

            ArrayList <Notifica> ALN = new ArrayList<Notifica>();
            ArrayList <Integer> id = new ArrayList<Integer>();
            ArrayList <Restaurant> ALR = new DB_RestaurantOwner().cercaRistoranti_perOwner(u);
 
            //NOTIFICHE PER RISTO
            if (u.getRole().equals("2")) {
                for (Restaurant rest: ALR) {
                    new DB_Reviews().setCommenti_perRistorante(rest);
                    new DB_GestioneRestaurant().cercaRistorante_perId(rest);

                    for (Review rev: rest.getReviews()) {
                        if (!new DB_Replies().setRepli(rev))
                            request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                        if (rev.getRepile() == null){
                            new DB_GestioneUser().cercaUser_perId(rev.getCreator());
                            id.add(rest.getId());
                            ALN.add(new Notifica("COMMENTO - " +
                                                 "Ristorante: " + rest.getName() +
                                                 "\nCommento: " + rev.getDescription(), 
                                                 rev.getDate_creation(), "nuovaRec", rev.getId(), rev.getCreator()));
                        }
                    }

                    new DB_RestaurantPhoto().cercaPhotos_perRistorante(rest, 2);

                    for (Photo ph: rest.getPhotos()) {
                        new DB_GestioneUser().cercaUser_perId(ph.getOwner());
                        if (!ph.getOwner().getRole().equals("1")) {
                            ALN.add( new Notifica("AGGIUNTA NUOVA FOTO - "
                                                 + "Ristorante: " + rest.getName(),ph, "nuovaFoto", ph.getId(), ph.getOwner()));
                        } else {
                            ALN.add( new Notifica("RIMOZIONE FOTO ANNULLATA - "
                                                 + "L'amministratore del sito non ritiene che la foto sia impropria per il ristorante."
                                                 + "\nRistorante: " + rest.getName(),ph, "nuovaFoto", ph.getId(), ph.getOwner()));
                        }
                    }
                }
            }
        
            //NOTIFICHE ADMIN
            if (u.getRole().equals("1")) {           
                if (! new DB_Notifica().setNotificheRepil_daConfermare(ALN))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);            

                if (! new DB_Notifica().setNotificheReclamo(ALN))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);  

                ArrayList <Photo> ARP = new ArrayList<Photo>();

                if (! new DB_RestaurantPhoto().cercaPhotos(ARP, 1))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response); 

                for (Photo ph: ARP){
                        new DB_GestioneUser().cercaUser_perId(ph.getOwner());
                        Restaurant res = new Restaurant (ph.getId_Restaurant());
                        new DB_GestioneRestaurant().cercaRistorante_perId(res);
                        ALN.add( new Notifica("SEGNALAZIONE FOTO - "
                                              + "Ristorante: " + res.getName(),ph,"invalidaFoto", ph.getId(), ph.getOwner()));
                } 
            }        
        
            //ORDINO E MANDO
            ALN.sort(new comparatorNotifiche());

            request.setAttribute("notifiche", ALN);
            request.setAttribute("id_ristoranti", id);
            request.getRequestDispatcher("notifiche.jsp").forward(request, response);
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
