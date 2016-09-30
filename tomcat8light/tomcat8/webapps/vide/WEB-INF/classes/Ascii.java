import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Ascii")
public class Ascii extends HttpServlet{
	
	public void service( HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException
	{
	
	PrintWriter out = res.getWriter();
	res.setContentType( "text/html" );
	out.println( "<head><title>Le code ASCII</title></head><body><center>" );
	out.println( "<h1>Le Code A.S.C.I.I.</h1>" );
	out.println( "<h2>Introduction</h2></center>" );
	out.println( "<br>La mémoire de l'ordinateur conserve toutes les données "+
	"sous forme numérique. Il n'existe pas de méthode pour stocker directement"+
	"les caractères. Chaque caractère possède donc son équivalent en code numérique"+
	": c'est le code ASCII (American Standard Code for Information Interchange -"+
	"traduisez « Code Americain Standard pour l'Echange d'Informations »). Le code"+
	"ASCII de base représentait les caractères sur 7 bits (c'est-à-dire 128 caractères"+
	"possibles, de 0 à 127). </br>" );
	out.println("<center><h2>Les caracteres ASCII</h2>");
	out.println("<h3>**INT**ASCII**</h3>");
	out.println("<table border>");
	String reponse = req.getParameter("nbCol") ;
	int nbcol = 2;
	if(reponse != null){
		nbcol = Integer.parseInt(reponse);
	}
	int j=32;
	while (j<255){
		out.println("<tr>");
		for(int i=0; i<nbcol; i++){
			out.print("<td bgcolor=\"white\">"+j+"</td>"+
			          "<td bgcolor=\"grey\">"+(char) j+"</td>");
			j++;
		}
		out.println("</tr>");
	}
	out.println( "</center></body>" );
	}
}