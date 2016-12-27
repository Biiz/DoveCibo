package DoveCiboPK;

import java.io.IOException;
import java.io.File;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 *
 * @author aeon
 */
@WebServlet(urlPatterns = {"/ServletUpload"})
public class ServletUpload extends HttpServlet {

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
 
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

               
        MultipartRequest m = new MultipartRequest(request,
                                            getServletContext().getRealPath("")+File.separator+"immaginiRistoranti",
                                            10*1024*1024,
                                            "ISO-8859-1",
                                            new DefaultFileRenamePolicy());
        
        Integer idR = Integer.parseInt(m.getParameter("idR"));
        Integer idU = Integer.parseInt(m.getParameter("idU"));
        
        Enumeration files = m.getFileNames();
        String name = (String)files.nextElement();
        String filename = m.getFilesystemName(name);
        
        
        try {
            new DB_Manager().inserisciPhoto(new Photo(null, "", "", filename, new User(idU), 0), idR);
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
