package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class errore_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        ");
      out.write(" \r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta charset=\"utf-8\">\r\n");
      out.write("        <!-- Latest compiled and minified CSS -->\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"homepageCSS.css\">\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\">\r\n");
      out.write("        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js\"></script>\r\n");
      out.write("        <script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\"></script>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body style=\"padding-top: 70px;\">\r\n");
      out.write("        <div class=\"navbar navbar-inverse navbar-static-top navbar-fixed-top\" role=\"navigation\">\r\n");
      out.write("\r\n");
      out.write("            <!-- bottone menù che compare quando la finestra è piccola-->\r\n");
      out.write("            <div class=\"navbar-header\">\r\n");
      out.write("                <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-ex1-collapse\">\r\n");
      out.write("                    <span class=\"sr-only\">Toggle navigation</span>\r\n");
      out.write("                    <span class=\"icon-bar\"></span>\r\n");
      out.write("                    <span class=\"icon-bar\"></span>\r\n");
      out.write("                    <span class=\"icon-bar\"></span>\r\n");
      out.write("                </button>\r\n");
      out.write("                <a class=\"navbar-brand\" href=\"home.jsp\">DoveCibo</a>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <!-- il contenuto del menù -->\r\n");
      out.write("            <div class=\"collapse navbar-collapse navbar-ex1-collapse\">\r\n");
      out.write("                <ul class=\"nav navbar-nav navbar-right\">\r\n");
      out.write("                    ");

                        Cookie cookies[] = request.getCookies();
                        if (cookies != null) {
                            if(cookies.length > 1){
                                String value = cookies[1].getValue();
                                //String value = "3"; per provare la navbar
                                DoveCiboPK.DB_Manager db = new DoveCiboPK.DB_Manager();
                                String nickName = cookies[1].getName();
                                DoveCiboPK.User u = new DoveCiboPK.User (-1,"","",nickName,"","","");
                                db.niknameEsistente_login(u);
                                if (value.equals("1")) {
                                    
                    
      out.write("\r\n");
      out.write("                    <!-- bottone che puppa la finestrella delle notifiche-->\r\n");
      out.write("                    <li><a href=\"#\" data-toggle=\"modal\" data-target=\"#notifiche\" style=\"padding-right: 15px;\"><span class=\"glyphicon glyphicon-tags\"></span> Notifiche</a></li>\r\n");
      out.write("\r\n");
      out.write("                    <!-- Nome e Cognome dropdown -->\r\n");
      out.write("                    <li class=\"dropdown\">\r\n");
      out.write("                        <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\" style=\"padding-right: 35px;\"><span class=\"glyphicon glyphicon-user\"></span> ");
      out.print(u.getName());
      out.write(' ');
      out.print(u.getSurname());
      out.write(" <span class=\"caret\"></span></a>\r\n");
      out.write("                        <ul class=\"dropdown-menu\">\r\n");
      out.write("                            <li><a href=\"profiloUtente.jsp\"><span class=\"glyphicon glyphicon-cog\" disabled></span> Profilo</a></li>\r\n");
      out.write("                            <li role=\"separator\" class=\"divider\"></li>\r\n");
      out.write("                            <form class=\"form\" action=\"ExitProfilo\" method=\"post\">\r\n");
      out.write("                                <div class=\"row text-center\">\r\n");
      out.write("                                <li><button href=\"home.jsp\" type=\"submit\" class=\"btn btn-danger\"><span class=\"glyphicon glyphicon-log-out\"></span> Esci </button></li>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </form>\r\n");
      out.write("                        </ul>\r\n");
      out.write("                    </li>\r\n");
      out.write("                </ul> \r\n");
      out.write("            </div><!-- fine menù -->\r\n");
      out.write("        </div><!-- fine navBar -->\r\n");
      out.write("        ");

            }
            if (value.equals("2")) {
        
      out.write("\r\n");
      out.write("        <!-- bottone che puppa la finestrella delle notifiche-->\r\n");
      out.write("    <li><a href=\"#\" data-toggle=\"modal\" data-target=\"#notifiche\"><span class=\"glyphicon glyphicon-tags\" style=\"padding-right: 15px;\"></span> Notifiche</a></li>\r\n");
      out.write("    <!-- Ristorante dropdown -->\r\n");
      out.write("    <li class=\"dropdown\">\r\n");
      out.write("        <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\" style=\"padding-right: 15px;\"><span class=\"glyphicon glyphicon-cutlery\"></span> Ristorante <span class=\"caret\"></span></a>\r\n");
      out.write("        <ul class=\"dropdown-menu\">\r\n");
      out.write("            <li><a href=\"aggiungiRistorante.jsp\"><span class=\"glyphicon glyphicon-plus\"></span> Aggiungi ristorante</a></li>\r\n");
      out.write("            <li role=\"separator\" class=\"divider\"></li>\r\n");
      out.write("            <li><a href=\"ristorante.jsp\"><span class=\"glyphicon glyphicon-eye-open\"></span> Vedi il tuo ristorante</a></li>\r\n");
      out.write("            <li role=\"separator\" class=\"divider\"></li>\r\n");
      out.write("            <li><a href=\"modificaRistorante.jsp\"><span class=\"glyphicon glyphicon-wrench\"></span> Modifica il tuo ristorante</a></li>\r\n");
      out.write("        </ul>\r\n");
      out.write("    </li>\r\n");
      out.write("    <!-- Nome e Cognome dropdown -->\r\n");
      out.write("    <li class=\"dropdown\">\r\n");
      out.write("        <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\" style=\"padding-right: 35px;\"><span class=\"glyphicon glyphicon-user\"></span> ");
      out.print(u.getName());
      out.write(' ');
      out.print(u.getSurname());
      out.write(" <span class=\"caret\"></span></a>\r\n");
      out.write("        <ul class=\"dropdown-menu\">\r\n");
      out.write("            <li><a href=\"profiloUtente.jsp\"><span class=\"glyphicon glyphicon-cog\"></span> Profilo</a></li>\r\n");
      out.write("            <li role=\"separator\" class=\"divider\"></li>\r\n");
      out.write("            <form class=\"form\" action=\"ExitProfilo\" method=\"post\">\r\n");
      out.write("                <div class=\"row text-center\">\r\n");
      out.write("                <li><button href=\"home.jsp\" type=\"submit\" class=\"btn btn-danger\"><span class=\"glyphicon glyphicon-log-out\"></span> Esci </button></li>\r\n");
      out.write("                </div>\r\n");
      out.write("            </form>\r\n");
      out.write("        </ul>\r\n");
      out.write("    </li>\r\n");
      out.write("</ul> \r\n");
      out.write("</div><!-- fine menù -->\r\n");
      out.write("</div><!-- fine navBar -->\r\n");

    }
    if (value.equals("3")) {

      out.write("\r\n");
      out.write("<!-- Ristorante dropdown -->\r\n");
      out.write("    <li class=\"dropdown\">\r\n");
      out.write("        <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\" style=\"padding-right: 15px;\"><span class=\"glyphicon glyphicon-cutlery\"></span> Ristorante <span class=\"caret\"></span></a>\r\n");
      out.write("        <ul class=\"dropdown-menu\">\r\n");
      out.write("            <li><a href=\"aggiungiRistorante.jsp\"><span class=\"glyphicon glyphicon-plus\"></span> Aggiungi ristorante</a></li>\r\n");
      out.write("        </ul>\r\n");
      out.write("    </li>\r\n");
      out.write("<!-- Nome e Cognome dropdown -->\r\n");
      out.write("\r\n");
      out.write("<li class=\"dropdown\">\r\n");
      out.write("    <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\" style=\"padding-right: 35px;\"><span class=\"glyphicon glyphicon-user\"></span>  ");
      out.print(u.getName());
      out.write(' ');
      out.print(u.getSurname());
      out.write(" <span class=\"caret\"></span></a>\r\n");
      out.write("    <ul class=\"dropdown-menu\">\r\n");
      out.write("        <li><a href=\"profiloUtente.jsp\"><span class=\"glyphicon glyphicon-cog\"></span> Profilo</a></li>\r\n");
      out.write("        <li role=\"separator\" class=\"divider\"></li>\r\n");
      out.write("        <form class=\"form\" action=\"ExitProfilo\" method=\"post\">\r\n");
      out.write("            <div class=\"row text-center\">\r\n");
      out.write("                <li><button href=\"home.jsp\" type=\"submit\" class=\"btn btn-danger\"><span class=\"glyphicon glyphicon-log-out\"></span> Esci </button></li>\r\n");
      out.write("            </div>\r\n");
      out.write("        </form>\r\n");
      out.write("    </ul>\r\n");
      out.write("</li>\r\n");
      out.write("</ul> \r\n");
      out.write("</div><!-- fine menù -->\r\n");
      out.write("</div><!-- fine navBar -->\r\n");
      out.write("\r\n");

    }
}else {

      out.write("\r\n");
      out.write("<!-- registrati -->\r\n");
      out.write("</a><li style=\"padding-right: 15px;\" ><a href=\"aggiungiUtente.jsp\"><span class=\"glyphicon glyphicon-pencil\"></span><b> Registrati </b></a></li>\r\n");
      out.write("<!-- bottone che puppa la finestrella per accedere-->\r\n");
      out.write("<li><a href=\"#\" data-toggle=\"modal\" data-target=\"#accedi\" style=\"padding-right: 35px;\"><span class=\"glyphicon glyphicon-log-in\"></span> Accedi</a></li>     \r\n");
      out.write("</ul> \r\n");
      out.write("</div><!-- fine menù -->\r\n");
      out.write("</div><!-- fine navBar -->\r\n");

    }
}


      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- Modal accedi-->\r\n");
      out.write("<div class=\"modal fade\" id=\"accedi\" role=\"dialog\">\r\n");
      out.write("    <div class=\"modal-dialog modal-sm\">\r\n");
      out.write("        <div class=\"modal-content\">\r\n");
      out.write("            <div class=\"modal-header\">\r\n");
      out.write("                <button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span class=\"glyphicon glyphicon-remove\"></span></button>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"modal-body\">\r\n");
      out.write("                <div class=\"row\" style=\"\">\r\n");
      out.write("                    <div class=\"col-md-12\">\r\n");
      out.write("                        <form class=\"form\" action=\"ServletLogin\" method=\"post\">\r\n");
      out.write("                            <div class=\"form-group\">\r\n");
      out.write("                                <div class=\"input-group\">\r\n");
      out.write("                                    <div class=\"input-group-addon\"><span class=\"glyphicon glyphicon-user\"></span></div>\r\n");
      out.write("                                    <input type=\"text\" class=\"form-control\" name=\"nickname\" placeholder=\"Nickname\">\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div class=\"form-group\">\r\n");
      out.write("                                <div class=\"form-group\">\r\n");
      out.write("                                    <div class=\"input-group\">\r\n");
      out.write("                                        <div class=\"input-group-addon\">\r\n");
      out.write("                                            <span class=\"glyphicon glyphicon-lock\"></span>\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                        <input type=\"password\" class=\"form-control\" name=\"password\" placeholder=\"Password\">\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <div class=\"help-block text-right\">\r\n");
      out.write("                                    <a href=\"recupero_credenziali.jsp\">Password dimenticata ?</a>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("\r\n");
      out.write("                            <button type=\"reset\" class=\"btn btn-sm btn-warning\">Clear</button>\r\n");
      out.write("                            <button href=\"home.jsp\" type=\"submit\" class=\"btn btn-success pull-right\">Log in</button>\r\n");
      out.write("                            <div class=\"checkbox\">\r\n");
      out.write("                                <label class=\"pull-right\">\r\n");
      out.write("                                    <input type=\"checkbox\" name = \"mantieni_accesso\" value= \"true\"> mantieni l'accesso\r\n");
      out.write("                                </label>\r\n");
      out.write("                            </div>  \r\n");
      out.write("                        </form>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>    \r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"modal-footer\">\r\n");
      out.write("                <div class=\"bottom text-center\">\r\n");
      out.write("                    Prima volta ? <a href=\"aggiungiUtente.jsp\"><span class=\"glyphicon glyphicon-pencil\"></span><b> Registrati !</b></a>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>   \r\n");
      out.write("    </div>\r\n");
      out.write("</div> <!-- fine modal accedi -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- Modal notifiche-->\r\n");
      out.write("<div class=\"modal fade\" id=\"notifiche\" role=\"dialog\">\r\n");
      out.write("    <div class=\"modal-dialog modal-sm\">\r\n");
      out.write("        <div class=\"modal-content\">\r\n");
      out.write("            <div class=\"modal-header\">\r\n");
      out.write("                <button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span class=\"glyphicon glyphicon-remove\"></span></button>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"modal-body\">\r\n");
      out.write("                <div class=\"row\">\r\n");
      out.write("                    <div class=\"col-md-12\">\r\n");
      out.write("                        <div class=\"bottom\">\r\n");
      out.write("                            <p style=\"color: black; font-size: 18px\"><b>Notifiche recenti:</b></p>\r\n");
      out.write("                            <hr align=?left? size=?1? width=?300? style=\"border-top-color: #e5e5e5;\" noshade>\r\n");
      out.write("                            John ha caricato una foto\r\n");
      out.write("                            <hr align=?left? size=?1? width=?300? style=\"border-top-color: #e5e5e5;\" noshade>\r\n");
      out.write("                            John reclama un ristorante\r\n");
      out.write("                            <hr align=?left? size=?1? width=?300? style=\"border-top-color: #e5e5e5;\" noshade>\r\n");
      out.write("                            John chiede l'eliminazione di una foto \r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>   \r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"modal-footer\">\r\n");
      out.write("                <div class=\"bottom text-center\">\r\n");
      out.write("                    <button class=\"btn btn-info btn-justified\" onclick=\"window.location.href = 'notifiche.jsp'\">Vedi tutte le notifiche</button>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>   \r\n");
      out.write("    </div>\r\n");
      out.write("</div> <!-- fine modal notifiche -->\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>\r\n");
      out.write("\n");
      out.write("        <title>DoveCibo</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <br><br><br>\n");
      out.write("        <div class=\"container\">  \n");
      out.write("            <div class=\"alert alert-warning\" role=\"alert\">\n");
      out.write("                <!-- riceve l'attributo dalla servletRegistrazione-->\n");
      out.write("                <div id=\"tagline\">\n");
      out.write("                    <h1>");
      out.print( request.getAttribute("error").toString());
      out.write("</h1>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <hr align=”left” size=”1″ width=”300″ color=”grey” noshade>\n");
      out.write("\n");
      out.write("                <!-- 1° row contenente i bottoni-->\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <div class=\"col-md-12\">\n");
      out.write("                        <button class=\"btn btn-success btn-lg pull-right\" onclick=\"window.location.href = 'home.jsp'\"><span class=\"glyphicon glyphicon-home\"></span> Torna alla home</button>\n");
      out.write("                        <button class=\"btn btn-sm btn-error\" onclick=\"goBack()\"><span class=\"glyphicon glyphicon-backward\"></span> Annulla</button>\n");
      out.write("                        <!-- script per tornare indietro di pagina nel browser-->\n");
      out.write("                        <script>\n");
      out.write("                            function goBack() {\n");
      out.write("                                window.history.back();\n");
      out.write("                            }\n");
      out.write("                        </script>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div><!-- fine container-->\n");
      out.write("\n");
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
