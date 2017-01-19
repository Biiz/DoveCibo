package DoveCiboPK;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author postal
 */
@WebServlet(name = "ServletGetRistorantiHomeCucine", urlPatterns = {"/ServletGetRistorantiHomeCucine"})
public class ServletGetRistorantiHomeCucine extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            
            //RICERCA DB
            ArrayList <Restaurant> ALR = new ArrayList<Restaurant>();
            Coordinate coo = new Coordinate(1f, 1f, null, null, null, null);
            
            
            
            for(int i=1; i<7; i++){
                if(! new DB_Manager().ricercaRistorantiPerCucinaEVicinanza(ALR, coo, i )){
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                }     
            }    
                
                
                
                for(Restaurant rest : ALR){                      
                    
                    if( ! new DB_Manager().cercaRistorante_perId(rest))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                    
                    if( ! new DB_Manager().cercaUser_perId(rest.getCreator()))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                    
                    if( ! new DB_Manager().cercaDay_hours_perId(rest.getDay_hours()))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);   
                    
                    if( ! new DB_Manager().cercaPriceRangeId(rest.getPrice_range()))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                    if( ! new DB_Manager().cercaCoordinate_perId(rest.getCordinate()))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
               
                     if( ! new DB_Manager().cercaCusines_perRistoranye(rest))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                               
                     
                     
                }
            
            if( ! ALR.isEmpty()){
                request.setAttribute("listaRistoranti", ALR);
                request.getRequestDispatcher("ElencoRistorantiHome.jsp").forward(request, response);
            }
            
        } catch (Exception ex) {
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