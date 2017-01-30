package DoveCiboPK;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author postal
 */
@WebServlet(name = "ServletNotifiche", urlPatterns = {"/ServletNotifiche"})
public class ServletNotifiche extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //CREATORE
            HttpSession session = request.getSession(false);
            User u = (User) session.getAttribute("User");

            if (! (u.getRole().equals("2") || u.getRole().equals("1")) ){
                request.setAttribute("error", "accesso negato");
                request.getRequestDispatcher("errore.jsp").forward(request, response);  
            }

            ArrayList <Notifica> ALN = new ArrayList<Notifica>();
            ArrayList <Integer> id = new ArrayList<Integer>();
            ArrayList <Restaurant> ALR = new DB_Manager().cercaRistoranti_perOwner(u);

            if (u.getRole().equals("2")) {
                for (Restaurant rest: ALR) {
                    new DB_Manager().setCommenti_perRistorante(rest);
                    new DB_Manager().cercaRistorante_perId(rest);

                    for (Review rev: rest.getReviews()) {
                        if (!new DB_Manager().setRepli(rev))
                            request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                        if (rev.getRepile() == null){
                            new DB_Manager().cercaUser_perId(rev.getCreator());
                            id.add(rest.getId());
                            ALN.add(new Notifica("<p>COMMETO</p>"+
                                                 "<p>ristorante: <b><a href='/DoveCiboGit/ServletGetRistorante?idR="+rest.getId()+" '>"+rest.getName()+"</a></b></p>"+
                                                 "<p>commento: <b>"+rev.getDescription()+"</b></p>", 
                                    rev.getDate_creation(), "nuovaRec", rev.getId(), rev.getCreator()));
                        }
                    }

                    new DB_Manager().cercaPhotos_perRistorante(rest, 2);

                    for (Photo ph: rest.getPhotos()) {
                        new DB_Manager().cercaUser_perId(ph.getOwner());
                        if (!ph.getOwner().getRole().equals("1")) {
                            ALN.add( new Notifica("<p>NEW PHOTO</p> "
                                                 +"<p>ristorante: <b><a href='/DoveCiboGit/ServletGetRistorante?idR="+rest.getId()+" '>"+rest.getName()+"</a></b></p>",ph, "nuovaFoto", ph.getId(), ph.getOwner()));
                        } else {
                            ALN.add( new Notifica("<p>RIFIUTO DELETE PHOTO</p> "
                                                 +"<p>l'<b>amministratore</b> del sito non ritiene che la foto sia impropria per il ristorante</p> "
                                                 +"<p>ristorante: <b><a href='/DoveCiboGit/ServletGetRistorante?idR="+rest.getId()+" '>"+rest.getName()+"</a></b></p>",ph, "nuovaFoto", ph.getId(), ph.getOwner()));
                        }
                    }
                }
            }
        
            if (u.getRole().equals("1")) {           
                if (! new DB_Manager().setNotificheRepil_daConfermare(ALN))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);            

                if (! new DB_Manager().setNotificheReclamo(ALN))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);  

                ArrayList <Photo> ARP = new ArrayList<Photo>();

                if (! new DB_Manager().cercaPhotos(ARP, 1))
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response); 

                for (Photo ph: ARP){
                        new DB_Manager().cercaUser_perId(ph.getOwner());
                        Restaurant res = new Restaurant (ph.getId_Restaurant());
                        new DB_Manager().cercaRistorante_perId(res);
                        ALN.add( new Notifica("<p>SEGNALAZIONE PHOTO<p> "
                                             +"<p>ristorante: <b><a href='/DoveCiboGit/ServletGetRistorante?idR="+res.getId()+" '>"+res.getName()+"</a></b></p>",ph, "invalidaFoto", ph.getId(), ph.getOwner()));
                } 
            }        
        
            ALN.sort(new comparatorNotifiche());

            request.setAttribute("notifiche", ALN);
            request.setAttribute("id_ristoranti", id);
            request.getRequestDispatcher("notifiche.jsp").forward(request, response);
        } catch (SQLException ex) {
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
    }
}
