/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posty;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "prova", urlPatterns = {"/prova"})
public class prova extends HttpServlet {

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

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");

        User u = new User(nickname, password);

        try {

            DB_Menaget dbm = new DB_Menaget();

            dbm.accedi(u);

        } catch (SQLException ex) {
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet prova</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet prova at " + u.getEmail() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
