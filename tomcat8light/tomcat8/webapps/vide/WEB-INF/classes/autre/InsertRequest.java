import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.*;

import javax.management.Query;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
/**
* Connection a une base PostgreSQL
* Affichage terminal des étapes d'execution.
* Affichage html du resultat.
*/
@WebServlet("/servlet/InsertRequest")
public class InsertRequest extends HttpServlet{

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
		ResultSetMetaData rsmd = null;
		Statement stmt;
		
		String nom_table = "";
		
		System.out.println("Transfert servlet OK");
		
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
		
		// Requete SQL pour schéma meta-data
		try {
			stmt = con.createStatement();
			nom_table = req.getParameter("nom_table");
			String query =("Select * from "+ nom_table +" ;");
			rs = stmt.executeQuery(query);
			System.out.println("Execution requete pour meta-data OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur requete meta-data SQL");
			connexion_fermer(con);
		}
		
		// Recuperation Meta Data
		try {
			rsmd = rs.getMetaData();
			System.out.println("Recuperation Meta Data OK");
		} catch (SQLException e2) {
			e2.printStackTrace();
			System.out.println("!!!!!!Erreur recuperation Meta Data SQL");
			connexion_fermer(con);
		}
		
		// Requete SQL d'Insertion
		try {
			stmt = con.createStatement();
			String query =("Insert into "+ nom_table +" Values (");
			String nom = req.getParameter(rsmd.getColumnName(1));
			query += nom;
			for(int i=2; i<= rsmd.getColumnCount(); i++) {
				String nomcol = req.getParameter(rsmd.getColumnName(i));
				query += ",\'" + nomcol + "\'" ;	
			}
			query +=(");");
			stmt.executeUpdate(query);
			System.out.println("Execution requete insertion OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur requete insertion SQL");
			connexion_fermer(con);
		}
		
		// Requete SQL de selection
		try {
			stmt = con.createStatement();
			String query =("Select * from "+ nom_table +" ;");
			rs = stmt.executeQuery(query);
			System.out.println("Execution requete OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur requete selection SQL");
			connexion_fermer(con);
		}
		
		// Recuperation Meta Data
		try {
			rsmd = rs.getMetaData();
			System.out.println("Recuperation Meta Data OK");
		} catch (SQLException e2) {
			e2.printStackTrace();
			System.out.println("!!!!!!Erreur recuperation Meta Data SQL");
			connexion_fermer(con);
		}
				
		// Affichage requete SQL
		out.println("<center><h1>Resultat d'insertion : OK</h1></center>");
		out.println("<center><h1>Affichage de la table "+ nom_table  +" :</h1></center>");
     	out.println("<table class=\"table-centered table-hover table-condensed\">");
     	try {
			while (rs.next()){
				out.println("<tr>");
				for(int i=1; i<= rsmd.getColumnCount(); i++){
					String n = (String) rs.getString(i);
					out.println("<td> "+ n + "<td>");
				}
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