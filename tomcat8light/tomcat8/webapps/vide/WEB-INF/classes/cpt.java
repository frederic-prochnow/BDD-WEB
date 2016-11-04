import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
/**
* Affichage html du resultat.
*/
@WebServlet("/servlet/Cpt")
public class Cpt extends HttpServlet{

	public void service( HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException
	{

		// Connection Session
		HttpSession session = req.getSession( true );
		Integer cpt = (Integer)session.getAttribute( "compteur" );
		cpt = new Integer( cpt == null ? 1 : cpt.intValue() + 1 );
		session.setAttribute( "compteur", cpt );
		res.setContentType("text/html;charset=UTF-8");

		// Resultat
		PrintWriter out = res.getWriter();
		res.setContentType( "text/html" );
		out.println("<!Doctype html><html lang=\"en\">");
		out.println( "<head><title>Le compteur</title>"+
			     "<meta charset=\"utf-8\">"+
			     "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"+
			     "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"+
			     "<link rel=\"stylesheet\" "+
			     "href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">"+
			     "</head><body><center>" );
		out.println("<h1> Le nombre de chargements est : "+ cpt + "</h1>");
		out.println("</body>");
	
	}
}
