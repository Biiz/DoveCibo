package DoveCiboPK;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
            
                    if(session != null && session.getAttribute("User") != null){
                        User user = (User) session.getAttribute("User");
                        String nickName = user.getNickname();
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
                        u.setNickname(nickName);
                        u.setPassword(password);
                      
                        if((new DB_Manager()).modificaAccount(u, nickName)){
                            new SendEmail_Modifica_Profilo(name, surname, email, nickName, password);
                            if(!(new DB_Manager()).CheckProfilo(u)){
                                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                            }
                            session.invalidate();
                    
                            request.getSession(true).setAttribute("User", u);
                            response.sendRedirect("/DoveCiboGit/modificheEffettuate.jsp"); 
                        }else{
                            request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
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
