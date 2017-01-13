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
@WebServlet("/servlet/Formulaire_avec_select")
public class Formulaire_avec_select extends HttpServlet{

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
		Statement stmt = null;
		
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
		out.println("<form method=\"post\">");
		out.println("<fieldset><legend>Detail technique...</legend>");
		out.println("<label for=\"id_abs\">Quel est le numero de votre absence ?</label> "+
			    "<input type=\"text\" name=\"id_abs\" id=\"id_abs\" /> " +
			    "<label for=\"login\">Quel est le login de l'etudiant absent ?</label> "+
			    "<input type=\"text\" name=\"login\" id=\"login\" />"+
			    "<label for=\"id\">Quel est le numero de votre justificatif ?</label> "+
			    "<input type=\"text\" name=\"id\" id=\"id\" />");
		out.println("</fieldset><fieldset><legend>Date de l'absence</legend>");
		out.println("<label for=\"date2\">Veuillez choisir la date de l'absence</label> "+
			    "<input type=\"date\" name=\"date2\" id=\"date2\" />");
		out.println("</fieldset><input type=\"submit\" value=\"Envoyer\" /></form>");
		
		String s1 = getInitParameter("id_abs");
		String s2 = getInitParameter("login");
		String s3 = getInitParameter("id");
		String s4 = getInitParameter("date2");
		
		// Requete SQL INSERTION
		try {
			stmt = con.createStatement();
			String query =("insert into absence values("+s1+","+s2+","+s3+","+s4+");");
			rs = stmt.executeQuery(query);
			System.out.println("Execution requete insertion OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur requete insertion SQL");
			connexion_fermer(con);
		}
		
		// Requete SQL AFFICHAGE
		try {
			stmt = con.createStatement();
			String query =("Select * from absence ;");
			rs = stmt.executeQuery(query);
			System.out.println("Execution requete affichage OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur requete affichage SQL");
			connexion_fermer(con);
		}
		
		
		// Affichage requete SQL
		out.println("<center><h1>Affichage de la table ABSENCE avec valeurs actuel :</h1></center>");
		out.println("<table class=\"table-centered table-hover table-condensed\">");
		try {
			while (rs.next()){
				out.println("<tr>");
				Integer id_abs = rs.getInt(1);
				String login = rs.getString(2);
				Integer id = rs.getInt(3);
				Date date2 = rs.getDate(4);
				out.println("<td> "+ id_abs +" </td><td> "+ login +" </td><td> "+ id +" </td><td> "+ date2 + " </td>");
				out.println("</tr>");
			}
			out.println("</table>");
			System.out.println("Execution affichage OK");
		} catch (SQLException e1) {
			System.out.println("!!!!!!Erreur affichage resultat SQL");
			e1.printStackTrace();
			connexion_fermer(con);
		}
		
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
