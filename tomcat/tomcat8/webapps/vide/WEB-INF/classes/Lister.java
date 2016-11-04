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
@WebServlet("/servlet/Lister")
public class Lister extends HttpServlet{

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
		out.println( "<head><title>La requete Sql</title></head><body><center>" );
		out.println( "<h1>Le resultat de la requete</h1>" );
		
		Connection con = null;
		ResultSet rs = null;
		Statement stmt;
		
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
			System.out.println("Connexion base PostreSQL OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur connection de la base");
			connexion_fermer(con);
		}
		
		// Requete SQL
		try {
			stmt = con.createStatement();
			String query =("Select * from resultats");
			rs = stmt.executeQuery(query);
			System.out.println("Execution requete OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur requete SQL");
			connexion_fermer(con);
		}
		
		// Affichage requete SQL
		out.println("<center><h1>Liste des resultats:</h1></center>");
		try {
			while (rs.next()){
				String n = rs.getString("nom");
				int a = rs.getInt("annee");
				String na= rs.getString("nationalite");
				String ct = rs.getString("categ");
				String cl = rs.getString("club");
				int t = rs.getInt("temps");
				out.println("<table border> "+
				"<tr><td>"+n+"</td><td bgcolor=\"grey\">"+a+
				"<td>"+na+"</td><td bgcolor=\"grey\">"+ct+
				"<td>"+cl+"</td><td bgcolor=\"grey\">"+t+
				"</td></tr></table>");
			}
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