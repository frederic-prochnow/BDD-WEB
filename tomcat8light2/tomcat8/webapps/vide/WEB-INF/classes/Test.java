// Servlet Test.java  de test de la configuration
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Test")
public class Test extends HttpServlet
{
  public void service( HttpServletRequest req, HttpServletResponse res ) 
       throws ServletException, IOException
  {
    PrintWriter out = res.getWriter();
    
    res.setContentType( "text/html" );

    out.println("<!doctype html>");
    out.println("<head><title>servlet Test</title></head><body><center> ");
    out.println("<h1>Test de la Servlet Java</h1> ");
    out.println("<br>");
    out.println("<blink>Une erreur s'est glissee dans cette page. Saurez vous la corriger ?</blink>");
    out.println("<br>");
    out.println("<br>");
    out.println("<br>Testez le retour vers la page html<br> ");
    out.println("</center> ");
    out.println("<ul> ");
    out.println("<li> Retour relatif : <a href=../test.html>../test.html</a> <p>");
    out.println("<li> Retour absolu :<a href=http://localhost/vide/test.html>http://localhost/vide/test.html</a> ");
    out.println("</ul> ");
    out.println("</body></html> ");
  }
}
