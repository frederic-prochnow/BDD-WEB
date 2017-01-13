import java.sql.Connection;
import java.sql.Date;
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
* Affichage terminal des étapes d'execution.
* Affichage html du resultat.
*/
@WebServlet("/servlet/enregistre_absence")
public class enregistre_absence extends HttpServlet{

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
		out.println( "<head><title>Formulaire</title>"+
			     "<meta charset=\"utf-8\">"+
			     "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"+
			     "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"+
			     "<link rel=\"stylesheet\" "+
			     "href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">"+
			     "</head><body><center>" );
		out.println( "<h1>Formulaire de saisie</h1>" );
		
		Connection con = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		Statement stmt;
		
		String nom_table = "";
		
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

		// Formulaire
		out.println("<form method=\"post\" action=\"insertion_absence\" >");
		out.println("<fieldset><legend>Detail technique...</legend>");
		out.println("<label for=\"login\">Quel est le login de l'etudiant absent ?</label> "+
			    "<input type=\"text\" name=\"login\" id=\"login\" />"+
			    "<label for=\"id\">Quel est le numero de votre justificatif ?(mettre 0 si rien)</label> "+
			    "<input type=\"text\" name=\"id\" id=\"id\" />");
		out.println("</fieldset><fieldset><legend>Date de l'absence</legend>");
		out.println("<label for=\"date2\">Quel est la date de l'absence (au format AAAA-MM-JJ) ?</label> "+
			    "<input type=\"date\" name=\"date2\" id=\"date2\" />" +
			    "<label for=\"heure2\">Quel est l'heure du creneau de l'absence (au format HH:MM:SS) ?</label> "+
			    "<input type\"time\" name =\"heure2\" id=\"heure2\" />");
		out.println("</fieldset><input type=\"submit\" value=\"Envoyer\" /></form>");
		
		// fermeture de connection
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
