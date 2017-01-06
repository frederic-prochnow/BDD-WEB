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
* Connection a une base PostgreSQL
* serveur : psqlserv
* database : n3p1
*
* Necessite le parametrage du pc avant execution.
*
* Affichage terminal des étapes d'execution.
* Affichage html du resultat.
*/
@WebServlet("/servlet/RequeteJava")
public class RequeteJava extends HttpServlet{

	public static void connexion_fermer(Connection con){
		try {
			con.close();
			System.out.println("Fermeture connexion OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur fermeture de connexion");
		}
	}
	
	//public static void main(String args []){
	public void service( HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException
	{

		PrintWriter out = res.getWriter();
		res.setContentType( "text/html" );
		out.println("<!Doctype html><html lang=\"en\">");
		out.println( "<head><title>La requete Sql</title>"+
			     "<meta charset=\"utf-8\">"+
			     "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"+
			     "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"+
			     "<link rel=\"stylesheet\" "+
			     "href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">"+
			     "</head><body><center>" );
		out.println( "<h1>Le resultat de la requete</h1>" );
		
		Connection con = null;
		ResultSet rs = null;
		Statement stmt;
		
		// Driver
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Connexion driver OK");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur driver");
			connexion_fermer(con);
		}
		
		// Parametre Base
		String url = "jdbc:postgresql://psqlserv/n3p1";
		String user = "prochnof";
		String password = "moi";
		try {
		        con = DriverManager.getConnection(url,user,password);
		        System.out.println("Connexion base PostreSQL OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur connection de la base");
			connexion_fermer(con);
		}
		
		// Requete SQL
		try {
			stmt = con.createStatement();
			String query =("Select * from personne;");
			rs = stmt.executeQuery(query);
			System.out.println("Execution requete OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur requete SQL");
			connexion_fermer(con);
		}
		
		// Affichage requete SQL
		try {
		        out.println("<center><h1>Liste des clients:</h1></center>");
	         	out.println("<table class=\"table-centered table-hover table-condensed\"><tr><th>ID</th><th bgcolor=\"grey\">nom</th><th>prenom</th></tr> ");
			while (rs.next()){
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				out.println(
				"<tr><td>"+id+"</td><td bgcolor=\"grey\">"+nom+
				"</td><td>"+prenom+
				"</td></tr>");
			}
			out.println("</table>");
			System.out.println("Execution affichage OK");
		} catch (SQLException e1) {
			System.out.println("!!!!!!Erreur affichage resultat SQL");
			e1.printStackTrace();
			connexion_fermer(con);
		}
		
		// fermeture des connection
		try {
			con.close();
			System.out.println("Fermeture connexion OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur fermeture de connexion");
			connexion_fermer(con);
		}
		
		out.println( "</body>" );
	}
}
