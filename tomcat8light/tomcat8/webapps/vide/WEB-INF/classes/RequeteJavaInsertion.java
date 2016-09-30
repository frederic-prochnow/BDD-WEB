import java.sql.Connection;
import java.sql.DriverManager;
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
@WebServlet("/servlet/RequeteJavaInsertion")
public class RequeteJavaInsertion {

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
		out.println( "<head><title>Insertion dans la base Clients</title></head><body><center>" );
		out.println( "<h1>Le formulaire</h1>" );
		
		Connection con = null;
		
		// Driver
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			System.out.println("Connexion driver OK");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur driver");
			connexion_fermer(con);
		}
		
		// Parametre Base
		String url = "jdbc:odbc:base";
		try {
			con = DriverManager.getConnection(url);
			System.out.println("Connexion base postgreSQL OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur connection de la base");
			connexion_fermer(con);
		}
		
		// Affichage formulaire html
		out.println("<form>"+
		            "<input type=\"texte\" name=\"Nom ?\" />");
		out.println("<input type=\"texte\" name=\"Prenom ?\" />");
		out.println("<input type=\"texte\" name=\"Age ?\" />");
		String nom = document.getElementById("Nom").innerHTML;
		String prenom = document.getElementById("Prenom").innerHTML;
		String age = document.getElementById("Age").innerHTML;
		out.println("</form>");
		
		// Requete SQL
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("insert into CLIENTS values('Durand','Paul',11)");
			System.out.println("Execution requete OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur requete SQL");
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
	}
}
