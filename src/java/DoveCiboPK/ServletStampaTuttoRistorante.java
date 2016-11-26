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
import javax.servlet.http.HttpSession;

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
            if (!(new DB_Manager()).CheckProfilo(u)) {
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            }

            // GIORNI D?APERTURA       
            Day_hours dh = new Day_hours(
                    request.getParameter("StMh")+":"+request.getParameter("StMm"),
                    request.getParameter("FtMh")+":"+request.getParameter("FtMm"),
                    request.getParameter("StPh")+":"+request.getParameter("StPm"),
                    request.getParameter("FtPh")+":"+request.getParameter("FtPm"));
            
            //INSERISCI ORARI
            if (new DB_Manager().cercaOrario_perOrario(dh)) {
                if (dh.getId() == null) {
                    if (!new DB_Manager().inserisciOrario(dh)) {
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                    }
                }
            } else {
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            }

            //PRICE RANGE               
            String pr = request.getParameter("price");
            String[] partPr = pr.split(",");
            Price_range priceRange = new Price_range(null, Double.parseDouble(partPr[0]), Double.parseDouble(partPr[1]));

            //COORDINATE
            Coordinate coordinate = new Coordinate(
                    request.getParameter("lat").isEmpty() ? null : Float.parseFloat(request.getParameter("lat")),
                    request.getParameter("lng").isEmpty() ? null : Float.parseFloat(request.getParameter("lng")),
                    request.getParameter("via").toLowerCase() + " " + request.getParameter("numero_civico"),
                    request.getParameter("city").toLowerCase(),
                    request.getParameter("nazione").toLowerCase());

            //RISTORANTE
            Restaurant restaurant = new Restaurant(
                    null,
                    request.getParameter("nome_ristorante").toLowerCase(),
                    request.getParameter("descrizione"),
                    request.getParameter("link").toLowerCase(),
                    u,
                    null,
                    priceRange,
                    null);
            restaurant.setDay_hours(dh);

            //INSERISCI PRICERANGE
            if ((new DB_Manager()).cercaPriceRange_perRange(priceRange)) {

                if (priceRange.getId() == null) {
                    if (!(new DB_Manager()).inserisciPrice_range(priceRange)) {
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
            
            //INSERISCI RELAZIONE RESTAURANT_OWNER
            if (!(new DB_Manager().inserisciRelazioneOwnerRestaurant(restaurant.getId(), u.getId()))) {
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            }            
            

            //INSERISCI COORDINATE
            if (!(new DB_Manager().inserisciCoordinate(coordinate))) {
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
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
                    if (!(new DB_Manager()).inserisciRelazioneCuisinesRestaurant(restaurant.getId(), i)) {
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                    }
                }

            }

            HttpSession session = request.getSession(false);
            session.removeAttribute("user_res");
            session.setAttribute("user_res", "yes");

            response.sendRedirect("/DoveCiboGit/ristorante_inserito_successo.jsp");

        } catch (Exception ex) {

        } finally {

        }
    }

}
