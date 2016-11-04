import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/First")
public class First extends HttpServlet{
	
	public void service( HttpServletRequest req, HttpServletResponse res )
		throws ServletException, IOException
	{
	
	PrintWriter out = res.getWriter();
	res.setContentType( "text/html" );
	out.println( "<head><title>servlet first</title></head><body><center>" );
	out.println( "<h1>Test de ma Servlet</h1>" );
	out.println( "<h2>Super ! ça marche</h2>" );
	out.println( "</center> </body>" );
	}
}