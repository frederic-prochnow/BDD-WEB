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
@WebServlet("/servlet/ListerA")
public class ListerA extends HttpServlet{
	String sens;
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
		
		Connection con = null;
		ResultSet rs = null;
		Statement stmt;
		String tri = "nom";
	
		
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
			String query = "";
			if(req.getParameter("tri") == null){
				if(req.getParameter("sens") == null) {
					query =("Select * from resultats ORDER BY nom ASC;");
					tri = "nom";
					sens = "ASC";
				}
				if(req.getParameter("sens") != null) {
					query =("Select * from resultats ORDER BY nom "+req.getParameter("sens")+";");
					tri = "nom";
					sens = req.getParameter("sens");
				}
			} else {
				if(req.getParameter("sens") == null) {
					query =("Select * from resultats ORDER BY "+req.getParameter("tri")+" ASC;");
					tri = req.getParameter("tri");
					sens = "ASC";
				}
				if(req.getParameter("sens") != null) {
					query =("Select * from resultats ORDER BY "+req.getParameter("tri")+" "+req.getParameter("sens")+";");
					tri = req.getParameter("tri");
					sens = req.getParameter("sens");
				}
			}
			rs = stmt.executeQuery(query);
			System.out.println("Execution requete OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur requete SQL");
			connexion_fermer(con);
		}
		
		// Affichage requete SQL
		try {
			/*out.println("<button type=\"button\"<a href=\"http://localhost:8080/vide/servlet/ListerA?tri=nom&sens=asc\">Sens ASCendant</button>"+
			            "<button type=\"button\"><a href=\"http://localhost:8080/vide/servlet/ListerA?tri=nom&sens=desc\">Sens DESCendant</button></br>");*/
			out.println( "<head><title>La requete Sql ["+tri+","+sens+"]</title></head><body><center>" +
			             "<h1>Le resultat de la requete :</h1>" );
			out.println("<center><h1>Liste des resultats effectuer par trie de "+tri+" dans le sens "+sens+" :</h1></center>");
			out.println("<table border>"+
			"<tr><th>ASC</th>"+
			"<th><a href=\"http://localhost:8080/vide/servlet/ListerA?tri=nom&sens=ASC\">Nom</th>"+
			"<th><a href=\"http://localhost:8080/vide/servlet/ListerA?tri=annee&sens=ASC\">Annee</th>"+
			"<th><a href=\"http://localhost:8080/vide/servlet/ListerA?tri=nationalite&sens=ASC\">Nationalite</th>"+
			"<th><a href=\"http://localhost:8080/vide/servlet/ListerA?tri=categ&sens=ASC\">Categorie</th>"+
			"<th><a href=\"http://localhost:8080/vide/servlet/ListerA?tri=club&sens=ASC\">Club</th>"+
			"<th><a href=\"http://localhost:8080/vide/servlet/ListerA?tri=temps&sens=ASC\">Temps</th>"+
			"<tr><th>DESC</th>"+
			"<th><a href=\"http://localhost:8080/vide/servlet/ListerA?tri=nom&sens=DESC\">Nom</th>"+
			"<th><a href=\"http://localhost:8080/vide/servlet/ListerA?tri=annee&sens=DESC\">Annee</th>"+
			"<th><a href=\"http://localhost:8080/vide/servlet/ListerA?tri=nationalite&sens=DESC\">Nationalite</th>"+
			"<th><a href=\"http://localhost:8080/vide/servlet/ListerA?tri=categ&sens=DESC\">Categorie</th>"+
			"<th><a href=\"http://localhost:8080/vide/servlet/ListerA?tri=club&sens=DESC\">Club</th>"+
			"<th><a href=\"http://localhost:8080/vide/servlet/ListerA?tri=temps&sens=DESC\">Temps</th>"+
			"</tr>");
			while (rs.next()){
				String n = rs.getString(1);
				int a = rs.getInt(2);
				String na= rs.getString(3);
				String ct = rs.getString(4);
				String cl = rs.getString(5);
				int t = rs.getInt(6);
				out.println(
				"<tr><td border-collapse:collapse>  </td><td>"+n+"</td><td bgcolor=\"grey\">"+a+
				"<td>"+na+"</td><td bgcolor=\"grey\">"+ct+
				"<td>"+cl+"</td><td bgcolor=\"grey\">"+t+
				"</td></tr>"
				);
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
