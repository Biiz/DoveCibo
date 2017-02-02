package autocompletamento;

import database.DB_Coordinate;
import database.DB_GestioneRestaurant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import restaurants.Restaurant;

/**
 * Prende tutti i valori dal database e li manda alla home.jsp 
 * per l’autocompletamento nel textfield della ricerca generale
 *
 * @author postal 
 */
@WebServlet(name = "ServletGetAutoC", urlPatterns = {"/ServletGetAutoC"})
public class ServletGetAutoC extends HttpServlet {

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
            //RICERCA DB
            ArrayList <Restaurant> ALR = new ArrayList<Restaurant>();
            HashSet <String> auto = new HashSet<String>();
            
            if ( new DB_GestioneRestaurant().tuttiRistoranti(ALR)) {
                for (Restaurant rest : ALR){
                    if ( ! new DB_Coordinate().cercaCoordinate_perId(rest.getCordinate()))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
               
                    auto.add(rest.getName());
                    auto.add(rest.getCordinate().getAdrers());
                    auto.add(rest.getCordinate().getCity());
                    auto.add(rest.getCordinate().getNazione());
                }
            } else
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            
            request.setAttribute("auto", auto);
            request.getRequestDispatcher("autocompletamento.jsp").forward(request, response);
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