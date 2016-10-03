/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoveCiboPK;

import java.io.*;

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
@WebServlet(name = "UserUpdate", urlPatterns = {"/UserUpdate"})
public class UserUpdate extends HttpServlet {

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
            String name = request.getParameter("first_name");
            String surname = request.getParameter("last_name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            DB_Manager db = new DB_Manager();
            
            Cookie cookies[] = request.getCookies();
            String nickName = cookies[1].getName();
            User u1 = new User(-1,"","",nickName,"","","");
            db.CheckProfilo(u1);
            
            if(!u1.getEmail().equals(email)){
                
                //PRECONDIZIONI DB
                if ((new DB_Manager()).emailEsistente(email)) {
                    request.setAttribute("error", "Attenzione, l'Email inserita non è valida!");
                    request.getRequestDispatcher("errore.jsp").forward(request, response);
                }
                
            }
            
            User u = new User();
            
            u.setName(name);
            u.setSurname(surname);
            u.setEmail(email);
            u.setPassword(password);

           System.out.println("++++++++++++++++++++++++++++++++++");
           (new DB_Manager()).modificaAccount(u, nickName);
           System.out.println("----------------------------------");
           
           
           response.sendRedirect("/DoveCiboGit/profiloUtente.jsp");

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
