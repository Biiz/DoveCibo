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
            HttpSession session = request.getSession(false);
   
            String name = request.getParameter("first_name");
            String surname = request.getParameter("last_name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            Cookie cookies[] = request.getCookies();
            if(cookies != null){
                for (int i = 0; i<cookies.length ;i++){
                    if(cookies[i].getValue().equals("1") || cookies[i].getValue().equals("2") || cookies[i].getValue().equals("3")){
                        String nickName = cookies[i].getName();
                        User u1 = new User(-1,"","",nickName,"","","");
                        if(!(new DB_Manager()).CheckProfilo(u1)){
                            request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                        }
                        //PRECONDIZIONI DB
                        if(!u1.getEmail().equals(email)){

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
                      
                        if((new DB_Manager()).modificaAccount(u, nickName)){
                            new SendEmail_Modifica_Profilo(name, surname, email, nickName, password);
                            session.removeAttribute("user_name");
                            session.removeAttribute("user_surname");
                            session.removeAttribute("user_email");
                            session.removeAttribute("user_pass");
                            
                            session.setAttribute("user_name", name);
                            session.setAttribute("user_surname", surname);
                            session.setAttribute("user_email", email);
                            session.setAttribute("user_pass", password);
                            
                            response.sendRedirect("/DoveCiboGit/modificheEffettuate.jsp"); 
                        }else{
                            request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                        } 
                    }
                }
            }else{
                response.sendRedirect("/DoveCiboGit/home.jsp"); 
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
