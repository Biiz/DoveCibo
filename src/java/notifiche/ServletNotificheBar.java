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
import restaurant_comments.Review;
import restaurant_photos.Photo;
import restaurants.Restaurant;
import users.User;

/**
 *
 * @author postal
 */
@WebServlet(name = "ServletNotificheBar", urlPatterns = {"/ServletNotificheBar"})
public class ServletNotificheBar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //USER
            User u = new User(Integer.parseInt(request.getParameter("idU")));
            if(!new DB_GestioneUser().cercaUser_perId(u))
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
        
            if ((u==null) ||(! (u.getRole().equals("2") || u.getRole().equals("1"))) ) {
                request.setAttribute("error", "accesso negato");
                request.getRequestDispatcher("errore.jsp").forward(request, response);  
            }
        
            ArrayList <Notifica> ALN = new ArrayList<Notifica>();
            ArrayList <Notifica> notifica = new ArrayList<Notifica>();
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
                            ALN.add(new Notifica("COMMENTO: "+
                                                 "Ristorante: "+rest.getName()+
                                                 ". Commento: "+rev.getDescription(), 
                                    rev.getDate_creation(), "nuovaRec", rev.getId(), rev.getCreator()));
                        }
                    }

                    new DB_RestaurantPhoto().cercaPhotos_perRistorante(rest, 2);

                    for (Photo ph: rest.getPhotos()) {
                        new DB_GestioneUser().cercaUser_perId(ph.getOwner());
                        if (!ph.getOwner().getRole().equals("1")) {
                            ALN.add( new Notifica("AGGIUNTA NUOVA FOTO "
                                                 +"Ristorante: "+rest.getName(),ph, "nuovaFoto", ph.getId(), ph.getOwner()));
                        } else {
                            ALN.add( new Notifica("RIMOZIONE FOTO ANNULLATA: "
                                                 +"L'amministratore del sito non ritiene che la foto sia impropria per il ristorante. "
                                                 +"Ristorante: "+rest.getName(),ph, "nuovaFoto", ph.getId(), ph.getOwner()));
                        }
                    }
                }
            }
        
            if (u.getRole().equals("1")) {
                if (! new DB_Notifica().setNotificheRepil_daConfermareNoLink(ALN))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);            

                if (! new DB_Notifica().setNotificheReclamoNoLink(ALN))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);  

                ArrayList <Photo> ARP = new ArrayList<Photo>();

                if (! new DB_RestaurantPhoto().cercaPhotos(ARP, 1))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response); 

                for (Photo ph: ARP){
                        new DB_GestioneUser().cercaUser_perId(ph.getOwner());
                        Restaurant res = new Restaurant (ph.getId_Restaurant());
                        new DB_GestioneRestaurant().cercaRistorante_perId(res);
                        ALN.add( new Notifica("SEGNALAZIONE PHOTO: "
                                             +"ristorante: "+res.getName(),ph, "invalidaFoto", ph.getId(), ph.getOwner()));
                }
            }        
        
            ALN.sort(new comparatorNotifiche());
            if (ALN.size() > 5) {
                for (int i = 0; i < 5 ;i++)
                    notifica.add(ALN.get(i));
            } else {
                for (int i = 0; i < ALN.size() ;i++)
                    notifica.add(ALN.get(i));
            }

            request.setAttribute("notifiche", notifica);
            request.setAttribute("id_ristoranti", id);
            request.getRequestDispatcher("notificheBar.jsp").forward(request, response);
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