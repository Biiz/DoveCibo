/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoveCiboPK;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author stefano
 */
@WebServlet(name = "ServletAggiungiCommento", urlPatterns = {"/ServletAggiungiCommento"})
public class ServletAggiungiCommento extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            Integer global_v = Integer.parseInt(request.getParameter("global_value"));
            Integer food = Integer.parseInt(request.getParameter("food"));
            Integer service = Integer.parseInt(request.getParameter("service"));
            Integer value_for_money = Integer.parseInt(request.getParameter("value_for_money"));
            Integer atmospere = Integer.parseInt(request.getParameter("atmospere"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            //verifico che i campi non siano vuoti
            if (name == null && description == null) {
                request.setAttribute("error", "campi risposta non correttamente compilati");
                request.getRequestDispatcher("errore.jsp").forward(request, response);

            } else {

                //Photo photo = new Photo(1); //DA SISTEMARE CON UPLOAD ED INSERIMENTO RIST
                //CREATORE
                HttpSession session = request.getSession(false);
                User u = (User) session.getAttribute("User");

                //RISTORANTE
                Integer idR = Integer.parseInt(request.getParameter("ristorante"));
                Restaurant res = new Restaurant(idR);

                if (!new DB_Manager().cercaRistorante_perId(res)) {
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                }

                //CREO REW
                Review rew = new Review(null, global_v, food, service, value_for_money, atmospere, name, description, null, 0, u);

                //RIMPOSTA GLOBALVALUE DEL RISTORANTE
                //C = NUMERO COMMENTI RISTORANTE
                //GV = GLOBAL VALUE DEL RISTORANTE
                //NEW GV = ((GV*C)+REW.GV)/(C+1)
                //UPDATE GV RIS
                Float newGV
                        = ((float) (res.getN_reviews() * res.getGlobal_value() + global_v))
                        / ((float) (res.getN_reviews() + 1));

                if (!new DB_Manager().updateRate(res, newGV)) {
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                }

                //INSERIMENTO DB
                if (!new DB_Manager().inserisciReview(rew, idR)) {
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                }

                if (!new DB_Manager().increaseReviewRestaurant(new Restaurant(idR))) {
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                }

                response.sendRedirect("/DoveCiboGit/ServletGetRistorante?idR=" + idR);
            }

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
