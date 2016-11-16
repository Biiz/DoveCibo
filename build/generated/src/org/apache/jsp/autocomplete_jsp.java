package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class autocomplete_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!doctype html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("   <head>\n");
      out.write("      <meta charset=\"utf-8\">\n");
      out.write("      <title>jQuery UI Autocomplete functionality</title>\n");
      out.write("      <link href=\"https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css\" rel=\"stylesheet\">\n");
      out.write("      <script src=\"https://code.jquery.com/jquery-1.10.2.js\"></script>\n");
      out.write("      <script src=\"https://code.jquery.com/ui/1.10.4/jquery-ui.js\"></script>\n");
      out.write("      <!-- Javascript -->\n");
      out.write("      <script>\n");
      out.write("         $(function() {\n");
      out.write("            var availableTutorials = [\n");
      out.write("               \"ActionScript\",\n");
      out.write("               \"Boostrap\",\n");
      out.write("               \"C\",\n");
      out.write("               \"C++\",\n");
      out.write("               \"Ecommerce\",\n");
      out.write("               \"Jquery\",\n");
      out.write("               \"Groovy\",\n");
      out.write("               \"Java\",\n");
      out.write("               \"JavaScript\",\n");
      out.write("               \"Lua\",\n");
      out.write("               \"Perl\",\n");
      out.write("               \"Ruby\",\n");
      out.write("               \"Scala\",\n");
      out.write("               \"Swing\",\n");
      out.write("               \"XHTML\"\t\n");
      out.write("            ];\n");
      out.write("            $( \"#automplete-3\" ).autocomplete({\n");
      out.write("               minLength:2,   \n");
      out.write("               delay:500,   \n");
      out.write("               source: availableTutorials\n");
      out.write("            });\n");
      out.write("         });\n");
      out.write("      </script>\n");
      out.write("   </head>\n");
      out.write("   <body>\n");
      out.write("      <!-- HTML --> \n");
      out.write("      <div class=\"ui-widget\">\n");
      out.write("         <p>Type I OR A</p>\n");
      out.write("         <input id=\"autocomplete-4\">\n");
      out.write("      </div>\n");
      out.write("   </body>\n");
      out.write("</html>");
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
