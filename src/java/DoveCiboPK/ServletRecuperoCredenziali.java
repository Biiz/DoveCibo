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
import javax.servlet.http.HttpSession;

/**
 *
 * @author stefano
 */
@WebServlet(name = "ServletRecuperoCredenziali", urlPatterns = {"/ServletRecuperoCredenziali"})
public class ServletRecuperoCredenziali extends HttpServlet {

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
            String email = request.getParameter("email");
           
            if ((new DB_Manager()).emailEsistente(email)) {
                User u = new User (-1,"","","",email,"","");
            
                if(!(new DB_Manager()).CheckEmail(u)){
                    request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                }
            
                new SendEmail_Recupero_credenziali(u.getName(), u.getSurname(), u.getEmail(), u.getNickname(), u.getPassword());
            
                response.sendRedirect("/DoveCiboGit/confermaRecupero_credenziali.jsp"); 
                
            }else{
                request.setAttribute("error", "Attenzione, l'Email inserita non è valida!");
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
