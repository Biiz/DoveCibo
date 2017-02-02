package restaurant_photos;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import database.DB_RestaurantPhoto;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import users.User;

/**
 * Gestisce l’upload delle foto
 *
 * @author giorgio
 */
@WebServlet(urlPatterns = {"/ServletUpload"})
public class ServletUpload extends HttpServlet {
    /**
     *
     * @param request oggetto di tipo HttpServletRequest contentente tutte le richieste fatte dal client alla servlet
     * @param response oggetto di tipo HttpServletResponse contenente tutte le risposte inviate dalla servlet al client
     * @throws ServletException se la richiesta per la POST non puo' essere gestita
     * @throws IOException se viene rilevato un errore di I/O quando la servlet gestisce la richiesta POST
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strPath1 = request.getServletContext().getRealPath("");
        Path path = Paths.get(strPath1).getParent().getParent();
        String strPath = path.toString() + File.separator + "web" + File.separator + "ImmaginiCaricate";

        //Create directory if it doesn't exist
        File directory = new File(strPath);
        if (!directory.exists())
            directory.mkdir();
        
        MultipartRequest m = null;
        System.out.println("Path nel quale si salvano le foto: " + strPath);
        try {
            m = new MultipartRequest(
                    request,
                    strPath,
                    1 * 1024 * 1024,
                    "ISO-8859-1",
                    new DefaultFileRenamePolicy());
            
            Integer idR = Integer.parseInt(m.getParameter("idR"));
            Integer idU = Integer.parseInt(m.getParameter("idU"));
            //metodi di Oreally per ottenere le caratteristiche del files
            Enumeration files = m.getFileNames();
            String name = (String) files.nextElement();
            String filename = m.getFilesystemName(name);
            System.out.println("nome file caricato: " + filename);
            String path_name = strPath + File.separator + m.getFilesystemName(name);

            if (!new DB_RestaurantPhoto().inserisciPhoto(new Photo(null, "", "", filename, new User(idU), 0), idR)) {
                request.setAttribute("error", "Errore connessione database");
                request.getRequestDispatcher("errore.jsp").forward(request, response);
            }
            response.sendRedirect("/DoveCiboGit/foto_caricata_con_successo.jsp");       
        } catch (IOException|SQLException ex) {
            request.setAttribute("error", "Errore caricamento file");
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
