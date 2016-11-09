package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import DoveCiboPK.User;
import java.util.ArrayList;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/navBar.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>DoveCibo</title>\n");
      out.write("\n");
      out.write("        <style>\n");
      out.write("            body {\n");
      out.write("                background-image: url('img/img (1)big.jpeg');\n");
      out.write("                background-repeat: no-repeat;\n");
      out.write("                background-attachment: fixed;\n");
      out.write("                background-size: cover;\n");
      out.write("            }\n");
      out.write("            h1 {\n");
      out.write("                /* text-shadow: 5px 5px 13px black; */\n");
      out.write("            }\n");
      out.write("            .ombra {\n");
      out.write("                /* box-shadow: 5px 5px 13px black; */\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\">\n");
      out.write("        \n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css\">\n");
      out.write("        \n");
      out.write("        \n");
      out.write("        <script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js\"></script>\n");
      out.write("        <script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\"></script>\n");
      out.write("        \n");
      out.write("        \n");
      out.write("        <script src=\"http://code.jquery.com/jquery-1.12.3.js\"></script>\n");
      out.write("        <script src=\"https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js\"></script>\n");
      out.write("        <script src=\"https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js\"></script>\n");
      out.write("\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body style=\"padding-top: 70px;\">\n");
      out.write("        <div class=\"navbar navbar-inverse navbar-static-top navbar-fixed-top\" role=\"navigation\">\n");
      out.write("\n");
      out.write("            <!-- bottone menù che compare quando la finestra è piccola-->\n");
      out.write("            <div class=\"navbar-header\">\n");
      out.write("                <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-ex1-collapse\">\n");
      out.write("                    <span class=\"sr-only\">Toggle navigation</span>\n");
      out.write("                    <span class=\"icon-bar\"></span>\n");
      out.write("                    <span class=\"icon-bar\"></span>\n");
      out.write("                    <span class=\"icon-bar\"></span>\n");
      out.write("                </button>\n");
      out.write("                <a class=\"navbar-brand\" href=\"home.jsp\">DoveCibo</a>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <!-- il contenuto del menù -->\n");
      out.write("            <div class=\"collapse navbar-collapse navbar-ex1-collapse\">\n");
      out.write("                <ul class=\"nav navbar-nav navbar-right\">\n");
      out.write("                    ");

                        if(session.isNew()){
                            
      out.write("\n");
      out.write("                            <!-- registrati -->\n");
      out.write("</a><li style=\"padding-right: 15px;\" ><a href=\"aggiungiUtente.jsp\"><span class=\"glyphicon glyphicon-pencil\"></span><b> Registrati </b></a></li>\n");
      out.write("<!-- bottone che puppa la finestrella per accedere-->\n");
      out.write("<li><a href=\"#\" data-toggle=\"modal\" data-target=\"#accedi\" style=\"padding-right: 35px;\"><span class=\"glyphicon glyphicon-log-in\"></span> Accedi</a></li>     \n");
      out.write("</ul> \n");
      out.write("</div><!-- fine menù -->\n");
      out.write("</div><!-- fine navBar -->\n");
      out.write("                    ");
        
                        }else{
                        Cookie cookies[] = request.getCookies();
                        if (cookies != null && cookies.length > 1) {   
                            for(int i = 0; i < cookies.length ;i++){
                                if (cookies[i].getValue().equals("1")) { 
                                        
                                                        
      out.write("\n");
      out.write("                    <!-- bottone che puppa la finestrella delle notifiche-->\n");
      out.write("                    <li><a href=\"#\" data-toggle=\"modal\" data-target=\"#notifiche\" style=\"padding-right: 15px;\"><span class=\"glyphicon glyphicon-tags\"></span> Notifiche</a></li>\n");
      out.write("\n");
      out.write("                    <!-- Nome e Cognome dropdown -->\n");
      out.write("                    <li class=\"dropdown\">\n");
      out.write("                        <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\" style=\"padding-right: 35px;\"><span class=\"glyphicon glyphicon-user\"></span> ");
      out.print(session.getAttribute("user_name") );
      out.write(' ');
      out.print(session.getAttribute("user_surname") );
      out.write(" <span class=\"caret\"></span></a>\n");
      out.write("                        <ul class=\"dropdown-menu\">\n");
      out.write("                            <li><a href=\"profiloUtente.jsp\"><span class=\"glyphicon glyphicon-cog\" disabled></span> Profilo</a></li>\n");
      out.write("                            <li role=\"separator\" class=\"divider\"></li>\n");
      out.write("                            <form class=\"form\" action=\"ExitProfilo\" method=\"post\">\n");
      out.write("                                <div class=\"row text-center\">\n");
      out.write("                                <li><button href=\"home.jsp\" type=\"submit\" class=\"btn btn-danger\"><span class=\"glyphicon glyphicon-log-out\"></span> Esci </button></li>\n");
      out.write("                                </div>\n");
      out.write("                            </form>\n");
      out.write("                        </ul>\n");
      out.write("                    </li>\n");
      out.write("                </ul> \n");
      out.write("            </div><!-- fine menù -->\n");
      out.write("        </div><!-- fine navBar -->\n");
      out.write("        ");

            }
 if (cookies[i].getValue().equals("2")) {
        
      out.write("\n");
      out.write("        <!-- bottone che puppa la finestrella delle notifiche-->\n");
      out.write("    <li><a href=\"#\" data-toggle=\"modal\" data-target=\"#notifiche\"><span class=\"glyphicon glyphicon-tags\" style=\"padding-right: 15px;\"></span> Notifiche</a></li>\n");
      out.write("    <!-- Ristorante dropdown -->\n");
      out.write("    <li class=\"dropdown\">\n");
      out.write("        <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\" style=\"padding-right: 15px;\"><span class=\"glyphicon glyphicon-cutlery\"></span> Ristorante <span class=\"caret\"></span></a>\n");
      out.write("        <ul class=\"dropdown-menu\">\n");
      out.write("            <li><a href=\"aggiungiRistorante.jsp\"><span class=\"glyphicon glyphicon-plus\"></span> Aggiungi ristorante</a></li>\n");
      out.write("            <li role=\"separator\" class=\"divider\"></li>\n");
      out.write("            <form action=\"VisualizzaRistorantiUtente2\" method=\"post\">\n");
      out.write("                <div class=\"row text-center\">\n");
      out.write("                    <li><button type=\"submit\" class=\"btn btn-info\"><span class=\"glyphicon glyphicon-eye-open\"></span> Vedi i tuoi ristoranti</button></li>\n");
      out.write("                </div>\n");
      out.write("            </form>\n");
      out.write("            <li role=\"separator\" class=\"divider\"></li>\n");
      out.write("            <form action=\"VisualizzaRistoranti\" method=\"post\">\n");
      out.write("                <div class=\"row text-center\">\n");
      out.write("                    <li><button type=\"submit\" class=\"btn btn-info\"><span class=\"glyphicon glyphicon-eye-open\"></span> Vedi i ristoranti inseriti</button></li>\n");
      out.write("                </div>\n");
      out.write("            </form>\n");
      out.write("        </ul>\n");
      out.write("    </li>\n");
      out.write("    <!-- Nome e Cognome dropdown -->\n");
      out.write("    <li class=\"dropdown\">\n");
      out.write("        <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\" style=\"padding-right: 35px;\"><span class=\"glyphicon glyphicon-user\"></span> ");
      out.print(session.getAttribute("user_name") );
      out.write(' ');
      out.print(session.getAttribute("user_surname") );
      out.write(" <span class=\"caret\"></span></a>\n");
      out.write("        <ul class=\"dropdown-menu\">\n");
      out.write("            <li><a href=\"profiloUtente.jsp\"><span class=\"glyphicon glyphicon-cog\"></span> Profilo</a></li>\n");
      out.write("            <li role=\"separator\" class=\"divider\"></li>\n");
      out.write("            <form class=\"form\" action=\"ExitProfilo\" method=\"post\">\n");
      out.write("                <div class=\"row text-center\">\n");
      out.write("                <li><button href=\"home.jsp\" type=\"submit\" class=\"btn btn-danger\"><span class=\"glyphicon glyphicon-log-out\"></span> Esci </button></li>\n");
      out.write("                </div>\n");
      out.write("            </form>\n");
      out.write("        </ul>\n");
      out.write("    </li>\n");
      out.write("</ul> \n");
      out.write("</div><!-- fine menù -->\n");
      out.write("</div><!-- fine navBar -->\n");

    }
 if (cookies[i].getValue().equals("3") && session.getAttribute("user_res").equals("yes") ) {


      out.write("\n");
      out.write("<!-- Ristorante dropdown -->\n");
      out.write("    <li class=\"dropdown\">\n");
      out.write("        <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\" style=\"padding-right: 15px;\"><span class=\"glyphicon glyphicon-cutlery\"></span> Ristorante <span class=\"caret\"></span></a>\n");
      out.write("        <ul class=\"dropdown-menu\">\n");
      out.write("            <li><a href=\"aggiungiRistorante.jsp\"><span class=\"glyphicon glyphicon-plus\"></span> Aggiungi ristorante</a></li>\n");
      out.write("            <li role=\"separator\" class=\"divider\"></li>\n");
      out.write("            <form action=\"VisualizzaRistoranti\" method=\"post\">\n");
      out.write("                <div class=\"row text-center\">\n");
      out.write("                    <li><button type=\"submit\" class=\"btn btn-info\"><span class=\"glyphicon glyphicon-eye-open\"></span> Vedi i ristoranti inseriti</button></li>\n");
      out.write("                </div>\n");
      out.write("            </form>\n");
      out.write("        </ul>\n");
      out.write("    </li>\n");
      out.write("<!-- Nome e Cognome dropdown -->\n");
      out.write("\n");
      out.write("<li class=\"dropdown\">\n");
      out.write("    <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\" style=\"padding-right: 35px;\"><span class=\"glyphicon glyphicon-user\"></span>  ");
      out.print(session.getAttribute("user_name") );
      out.write(' ');
      out.print(session.getAttribute("user_surname") );
      out.write(" <span class=\"caret\"></span></a>\n");
      out.write("    <ul class=\"dropdown-menu\">\n");
      out.write("        <li><a href=\"profiloUtente.jsp\"><span class=\"glyphicon glyphicon-cog\"></span> Profilo</a></li>\n");
      out.write("        <li role=\"separator\" class=\"divider\"></li>\n");
      out.write("        <form class=\"form\" action=\"ExitProfilo\" method=\"post\">\n");
      out.write("            <div class=\"row text-center\">\n");
      out.write("                <li><button href=\"home.jsp\" type=\"submit\" class=\"btn btn-danger\"><span class=\"glyphicon glyphicon-log-out\"></span> Esci </button></li>\n");
      out.write("            </div>\n");
      out.write("        </form>\n");
      out.write("    </ul>\n");
      out.write("</li>\n");
      out.write("</ul> \n");
      out.write("</div><!-- fine menù -->\n");
      out.write("</div><!-- fine navBar -->\n");
      out.write("\n");

        }
 if (cookies[i].getValue().equals("3")  && session.getAttribute("user_res").equals("no")){

      out.write("\n");
      out.write("  <!-- Ristorante dropdown -->\n");
      out.write("    <li class=\"dropdown\">\n");
      out.write("        <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\" style=\"padding-right: 15px;\"><span class=\"glyphicon glyphicon-cutlery\"></span> Ristorante <span class=\"caret\"></span></a>\n");
      out.write("        <ul class=\"dropdown-menu\">\n");
      out.write("            <li><a href=\"aggiungiRistorante.jsp\"><span class=\"glyphicon glyphicon-plus\"></span> Aggiungi ristorante</a></li>\n");
      out.write("        </ul>\n");
      out.write("    </li>\n");
      out.write("<!-- Nome e Cognome dropdown -->\n");
      out.write("\n");
      out.write("<li class=\"dropdown\">\n");
      out.write("    <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\" style=\"padding-right: 35px;\"><span class=\"glyphicon glyphicon-user\"></span> ");
      out.print(session.getAttribute("user_name") );
      out.write(' ');
      out.print(session.getAttribute("user_surname") );
      out.write(" <span class=\"caret\"></span></a>\n");
      out.write("    <ul class=\"dropdown-menu\">\n");
      out.write("        <li><a href=\"profiloUtente.jsp\"><span class=\"glyphicon glyphicon-cog\"></span> Profilo</a></li>\n");
      out.write("        <li role=\"separator\" class=\"divider\"></li>\n");
      out.write("        <form class=\"form\" action=\"ExitProfilo\" method=\"post\">\n");
      out.write("            <div class=\"row text-center\">\n");
      out.write("                <li><button href=\"home.jsp\" type=\"submit\" class=\"btn btn-danger\"><span class=\"glyphicon glyphicon-log-out\"></span> Esci </button></li>\n");
      out.write("            </div>\n");
      out.write("        </form>\n");
      out.write("    </ul>\n");
      out.write("</li>\n");
      out.write("</ul> \n");
      out.write("</div><!-- fine menù -->\n");
      out.write("</div><!-- fine navBar -->\n");

        }
}
}else{


      out.write("\n");
      out.write("<!-- registrati -->\n");
      out.write("</a><li style=\"padding-right: 15px;\" ><a href=\"aggiungiUtente.jsp\"><span class=\"glyphicon glyphicon-pencil\"></span><b> Registrati </b></a></li>\n");
      out.write("<!-- bottone che puppa la finestrella per accedere-->\n");
      out.write("<li><a href=\"#\" data-toggle=\"modal\" data-target=\"#accedi\" style=\"padding-right: 35px;\"><span class=\"glyphicon glyphicon-log-in\"></span> Accedi</a></li>     \n");
      out.write("</ul> \n");
      out.write("</div><!-- fine menù -->\n");
      out.write("</div><!-- fine navBar -->\n");

}
}

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!-- Modal accedi-->\n");
      out.write("<div class=\"modal fade\" id=\"accedi\" role=\"dialog\">\n");
      out.write("    <div class=\"modal-dialog modal-sm\">\n");
      out.write("        <div class=\"modal-content\">\n");
      out.write("            <div class=\"modal-header\">\n");
      out.write("                <button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span class=\"glyphicon glyphicon-remove\"></span></button>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-body\">\n");
      out.write("                <div class=\"row\" style=\"\">\n");
      out.write("                    <div class=\"col-md-12\">\n");
      out.write("                        <form class=\"form\" action=\"ServletLogin\" method=\"post\">\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <div class=\"input-group\">\n");
      out.write("                                    <div class=\"input-group-addon\"><span class=\"glyphicon glyphicon-user\"></span></div>\n");
      out.write("                                    <input type=\"text\" class=\"form-control\" name=\"nickname\" placeholder=\"Nickname\">\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <div class=\"form-group\">\n");
      out.write("                                    <div class=\"input-group\">\n");
      out.write("                                        <div class=\"input-group-addon\">\n");
      out.write("                                            <span class=\"glyphicon glyphicon-lock\"></span>\n");
      out.write("                                        </div>\n");
      out.write("                                        <input type=\"password\" class=\"form-control\" name=\"password\" placeholder=\"Password\">\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"help-block text-right\">\n");
      out.write("                                    <a href=\"recupero_credenziali.jsp\">Password dimenticata ?</a>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("\n");
      out.write("                            <button type=\"reset\" class=\"btn btn-sm btn-warning\">Clear</button>\n");
      out.write("                            <button href=\"home.jsp\" type=\"submit\" class=\"btn btn-success pull-right\">Log in</button>\n");
      out.write("                        </form>\n");
      out.write("                    </div>\n");
      out.write("                </div>    \n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-footer\">\n");
      out.write("                <div class=\"bottom text-center\">\n");
      out.write("                    Prima volta ? <a href=\"aggiungiUtente.jsp\"><span class=\"glyphicon glyphicon-pencil\"></span><b> Registrati !</b></a>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>   \n");
      out.write("    </div>\n");
      out.write("</div> <!-- fine modal accedi -->\n");
      out.write("\n");
      out.write("\n");
      out.write("<!-- Modal notifiche-->\n");
      out.write("<div class=\"modal fade\" id=\"notifiche\" role=\"dialog\">\n");
      out.write("    <div class=\"modal-dialog modal-sm\">\n");
      out.write("        <div class=\"modal-content\">\n");
      out.write("            <div class=\"modal-header\">\n");
      out.write("                <button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span class=\"glyphicon glyphicon-remove\"></span></button>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-body\">\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <div class=\"col-md-12\">\n");
      out.write("                        <div class=\"bottom\">\n");
      out.write("                            <p style=\"color: black; font-size: 18px\"><b>Notifiche recenti:</b></p>\n");
      out.write("                            <hr align=?left? size=?1? width=?300? style=\"border-top-color: #e5e5e5;\" noshade>\n");
      out.write("                            John ha caricato una foto\n");
      out.write("                            <hr align=?left? size=?1? width=?300? style=\"border-top-color: #e5e5e5;\" noshade>\n");
      out.write("                            John reclama un ristorante\n");
      out.write("                            <hr align=?left? size=?1? width=?300? style=\"border-top-color: #e5e5e5;\" noshade>\n");
      out.write("                            John chiede l'eliminazione di una foto \n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>   \n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-footer\">\n");
      out.write("                <div class=\"bottom text-center\">\n");
      out.write("                    <button class=\"btn btn-info btn-justified\" onclick=\"window.location.href = 'notifiche.jsp'\">Vedi tutte le notifiche</button>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>   \n");
      out.write("    </div>\n");
      out.write("</div> <!-- fine modal notifiche -->\n");
      out.write("</body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div class=\"row\" style=\"margin-top: 25%;\">\n");
      out.write("\n");
      out.write("                <div class=\"col-md-3\"></div>\n");
      out.write("                <div class=\"col-md-6\" style=\"background-color: rgba(255, 255, 255, 0.80); border-radius: 5px;\">\n");
      out.write("                    <form action=\"CercaRistorantiHome\" method=\"post\">\n");
      out.write("                        <h1 style=\"color: black; font-size: 50px; padding-top: 5%; \">Cerca un ristorante</h1>\n");
      out.write("\n");
      out.write("                        <div class=\"input-group input-group-lg ombra\" style=\"padding-bottom: 5%; \">\n");
      out.write("                            <input type=\"text\" name=\"go\" class=\"form-control\" placeholder=\"Cerca un ristorante\">\n");
      out.write("                            <span class=\"input-group-btn\">\n");
      out.write("                                <!-- questo bottone submitta la ricerca, per ora linka solo la pagina dei ristoranti -->\n");
      out.write("                                <button class=\"btn btn-success\" type=\"submit\">Go!</button> \n");
      out.write("                        </div> \n");
      out.write("                    </form>                                 \n");
      out.write("                </div> \n");
      out.write("                <div class=\"col-md-3\"></div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
