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
import javax.servlet.http.Cookie;
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

        try {
            
            //CREATORE
            Cookie cookies[] = request.getCookies();
            String NickName = cookies[1].getName();
            User u = new User(-1, "", "", NickName, "", "", "");
            (new DB_Manager()).CheckProfilo(u);
            
            // GIORNI D?APERTURA        
            Day_hours[] dh = new Day_hours[7];
            for (int i = 0; i < 7; i++) {

                dh[i] = new Day_hours(
                        request.getParameter("StM" + i),
                        request.getParameter("FtM" + i),
                        request.getParameter("StP" + i),
                        request.getParameter("FtP" + i));
            }

            //PRICE RANGE               
            String pr = request.getParameter("price");
            String[] partPr = pr.split(",");
            Price_range priceRange = new Price_range(null, Double.parseDouble(partPr[0]), Double.parseDouble(partPr[1]));
            

            //COORDINATE
            Coordinate coordinate = new Coordinate(
                    request.getParameter("lat").isEmpty() ? null : Float.parseFloat(request.getParameter("lat")),
                    request.getParameter("lng").isEmpty() ? null : Float.parseFloat(request.getParameter("lng")),
                    request.getParameter("via") + ", " + request.getParameter("numero_civico") + ", " + request.getParameter("city") + ", " + request.getParameter("nazione"));
            

            
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

            //INSERISCI PRICERANGE
            DB_Manager prc = new DB_Manager();
            if (prc.cercaPriceRange_perRange(priceRange)) {
               
                if (priceRange.getId() == null) {
                    DB_Manager ipr = new DB_Manager();
                    if (!ipr.inserisciPrice_range(priceRange)) {
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                    }
                }
            } else {
                //request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            }

            //INSERISCI RISTORANTE
            if (new DB_Manager().inserisciRistorante(restaurant)) {
                coordinate.setId_resturant(restaurant.getId());

            } else {
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            }

            //INSERISCI COORDINATE
            if (!(new DB_Manager().inserisciCoordinate(coordinate))) {
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            }

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
              
            }
            //STAMPA ORARI
            Enumeration parameterList = request.getParameterNames();
            while (parameterList.hasMoreElements()) {
                String sName = parameterList.nextElement().toString();
            }

            //INSERISCI CUSINES
            String[] cucine_arr_string = new String[7];
            cucine_arr_string[1] = request.getParameter("cb1");
            cucine_arr_string[2] = request.getParameter("cb2");
            cucine_arr_string[3] = request.getParameter("cb3");
            cucine_arr_string[4] = request.getParameter("cb4");
            cucine_arr_string[5] = request.getParameter("cb5");
            cucine_arr_string[6] = request.getParameter("cb6");

            //ciclo l'array
            for (int i = 1; i < 7; i++) {
                if (cucine_arr_string[i] != null) {
                    if (!new DB_Manager().inserisciRelazioneCuisinesRestaurant(restaurant.getId(), i)) {
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                    }
                    new DB_Manager().inserisciRelazioneCuisinesRestaurant(restaurant.getId(), i);
                }

            }
            
            response.sendRedirect("/DoveCiboGit/ristorante.jsp");

        } catch (Exception ex) {

        } finally {
            
        }
    }

}