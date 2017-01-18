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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

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

        String strPath1 = request.getServletContext().getRealPath("");
        Path path = Paths.get(strPath1).getParent().getParent();
        String strPath = path.toString() + "\\web\\immaginiRistoranti";

        //create directory if it doesn't exist
        File directory = new File(strPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        MultipartRequest m = null;
        System.out.println("path nel quale si salvano le foto: " + strPath);
        try {
            m = new MultipartRequest(
                    request,
                    strPath,
                    10 * 1024 * 1024,
                    "ISO-8859-1",
                    new DefaultFileRenamePolicy());
            //String encoded = Base64.encode(FileUtils.readFileToByteArray(m.getFile(strPath)));
            //String encoded = Base64.getEncoder().withoutPadding().encodeToString(FileUtil‌​s.readFileToByteArra‌​y(strPath));

            Integer idR = Integer.parseInt(m.getParameter("idR"));
            Integer idU = Integer.parseInt(m.getParameter("idU"));

            Enumeration files = m.getFileNames();
            String name = (String) files.nextElement();
            String filename = m.getFilesystemName(name);
            System.out.println("nome file caricato: " + filename);
            String path_name = strPath + File.separator + m.getFilesystemName(name);
            String base64 = DatatypeConverter.printBase64Binary(Files.readAllBytes(
                    Paths.get(path_name)));

            System.out.println("\n\nlunghezza: " + base64.length());

            if (!new DB_Manager().inserisciPhoto(new Photo(null, "", "", filename, new User(idU), 0), idR)) {
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            request.setAttribute("error", "file troppo grande");
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
