/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@WebServlet(name = "ServletNotificheOwner", urlPatterns = {"/ServletNotificheOwner"})
public class ServletNotificheOwner extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        try {

             
        //CREATORE
        HttpSession session = request.getSession(false);
        User u = (User) session.getAttribute("user");         
        
        if (! u.getRole().equals("2")){
            request.setAttribute("error", "accesso negato");
            request.getRequestDispatcher("errore.jsp").forward(request, response);  
        }
        
        ArrayList <Restaurant> ALR = new DB_Manager().cercaRistoranti_perOwner(u);
        
        ArrayList <Notifica> ALNr = new ArrayList<Notifica>();
        ArrayList <Notifica> ALNp = new ArrayList<Notifica>();
        
        for(Restaurant rest: ALR) {
            new DB_Manager().setCommenti_perRistorante(rest);
            for(Review rev: rest.getReviews()){
                new DB_Manager().cercaUser_perId(rev.getCreator());
                ALNr.add(new Notifica(rev.getCreator().getNickname()+" ha commentato il ristorante "+rest.getName()+": "+rev.getDescription(), 
                        rev.getDate_creation(), "/ServletAggiungiRepile?"+rest.getId()));
            }
            
            new DB_Manager().cercaPhotos_perRistorante(rest, 0);
            
            for(Photo ph: rest.getPhotos()){
                new DB_Manager().cercaUser_perId(ph.getOwner());
                ALNp.add( new Notifica(ph.getOwner().getNickname()+" ha aggiunto una foto", ph.getDate_creation(), "/ServletRisciestaEliminazioneFoto", ph));
            }
            
        }
        
        request.setAttribute("notificheCommenti", ALNr);
        request.setAttribute("notificheFoto", ALNp);
        request.getRequestDispatcher("ristorante.jsp").forward(request, response);
            
        
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
    }// </editor-fold>

}
