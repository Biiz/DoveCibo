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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            Float lat = Float.parseFloat( request.getParameter("lat") );
            Float lng = Float.parseFloat( request.getParameter("lng") );
            
            
            //RICERCA DB
            ArrayList <Restaurant> ALR = new ArrayList<Restaurant>();
            Coordinate coo = new Coordinate(lat,lng, null, null, null, null);
            
            System.out.println(request.getParameter("cucina"));
            
                if(! new DB_Manager().ricercaRistorantiPerCucinaEVicinanza(ALR, coo, request.getParameter("cucina"), 10 )){
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                }     
  
            System.out.println(request.getParameter("cucina"));    
                
                
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
                request.getRequestDispatcher("CercaRistorantiHome.jsp").forward(request, response);
            }else{
                    request.setAttribute("error", "errore: nessun ristorante trovato per questa ricerca");
                    request.getRequestDispatcher("errore.jsp").forward(request, response);
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