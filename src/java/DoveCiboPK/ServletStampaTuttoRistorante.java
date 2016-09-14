/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoveCiboPK;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stefano
 */
@WebServlet(name = "ServletStampaTuttoRistorante", urlPatterns = {"/ServletStampaTuttoRistorante"})
public class ServletStampaTuttoRistorante extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletStampaTuttoRistorante</title>");
            out.println("</head>");
            out.println("<body>");

            // GIORNI D?APERTURA        
            Day_hours[] dh = new Day_hours[7];
            out.println("wwww " + " = " + null + "<br/>");
            for (int i = 0; i < 7; i++) {

                dh[i] = new Day_hours(
                        request.getParameter("StM" + i),
                        request.getParameter("FtM" + i),
                        request.getParameter("StP" + i),
                        request.getParameter("FtP" + i));

                out.println("wwww " + i + " = " + dh[i].getStartM() + "<br/>");
            }

            //PRICE RANGE               
            String pr = request.getParameter("price");
            String[] partPr = pr.split(",");
            Price_range priceRange = new Price_range(null, Double.parseDouble(partPr[0]), Double.parseDouble(partPr[1]));
            out.println(priceRange.getMax_value());

            //COORDINATE
            Coordinate coordinate = new Coordinate(
                    request.getParameter("lat").isEmpty() ? null : Double.parseDouble(request.getParameter("lat")),
                    request.getParameter("lng").isEmpty() ? null : Double.parseDouble(request.getParameter("lng")),
                    request.getParameter("via") + ", " + request.getParameter("numero_civico") + ", " + request.getParameter("city") + ", " + request.getParameter("nazione"));
            out.println(coordinate.getAdrers());

            //CREATORE PROVA
            User u = new User(1, "Admin", "Admin", "Admin", "admin@admin.it", "pollo");

            //RISTORANTE
            Restaurant restaurant = new Restaurant(
                    null,
                    request.getParameter("nome_ristorante"),
                    request.getParameter("descrizione"),
                    request.getParameter("link"),
                    u,
                    null,
                    priceRange,
                    null);

            out.println(restaurant.getName());

            //INSERISCI PRICERANGE
            DB_Manager prc = new DB_Manager();
            if (prc.cercaPriceRange_perRange(priceRange)) {
                out.println(priceRange.getId());
                if (priceRange.getId() == null) {
                    DB_Manager ipr = new DB_Manager();
                    out.println("inserisci nuovo price range" + priceRange.getMax_value());
                    if (!ipr.inserisciPrice_range(priceRange)) {
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                    }
                }
            } else {
                //request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            }

            System.out.println("<br/>"
                    + restaurant.getName() + "<br/>"
                    + restaurant.getDescription() + "<br/>"
                    + restaurant.getWeb_site_url() + "<br/>"
                    + restaurant.getCreator().getId() + "<br/>"
                    + restaurant.getPrice_range().getId() + "<br/>");
       
            //INSERISCI RISTORANTE
            if (new DB_Manager().inserisciRistorante(restaurant)) {
            coordinate.setId_resturant(restaurant.getId());
            
            } else {
                System.out.println("porco dio");
            request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            }
            
            System.out.println("riga 116 "+ coordinate.getId_resturant().toString() );
                 
            //INSERISCI COORDINATE
            if (!(new DB_Manager().inserisciCoordinate(coordinate))) {
            System.out.println("errore coordinate");
            request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            }
            
            System.out.println("cooooo");
            
            //INSERISCI ORARI
            for (int i = 0; i < 7; i++) {
            if (new DB_Manager().cercaOrario_perOrario(dh[i], i)) {
            if (dh[i].getId() == null) {
            if (!new DB_Manager().inserisciOrario(dh[i], i)) {
            request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            }
            }
            } else {
            request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            }
            new DB_Manager().inserisciRelazioneRistoranteOrario(restaurant.getId(), dh[i].getId());
            out.println("ok" + i + "  ");
            }
            
            //INSERISCI CUSINES
            Enumeration parameterList = request.getParameterNames();
            while (parameterList.hasMoreElements()) {
            String sName = parameterList.nextElement().toString();
            out.println(sName + " = " + request.getParameter(sName) + "<br/>");
            }
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {

        } finally {
            out.close();
        }
    }

}
