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
@WebServlet("/servlet/authent")
public class authent extends HttpServlet{

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
		out.println( "<h1><center> Service d'authentification :</h1>"+
			     "<h3><center> Voici le resultat : </h3>");
		
		Connection con = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		Statement stmt;
		//RequestDispatcher dispatcher;
		
		String login = "";
		boolean sql = false;
		String mdp = "";
		Integer type = 01;
		
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
		
		// Requete SQL et recuperation du mot de passe
		try {
			stmt = con.createStatement();
			login = req.getParameter("login");
			mdp = req.getParameter("mdp");
			String query =("Select type from personne where login = \'"+login+"\' and mdp = \'"+mdp+"\';");
			System.out.println("Requete : "+ query);
			rs = stmt.executeQuery(query);
			System.out.println("Execution requete identification OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur requete identification SQL");
			connexion_fermer(con);
		}

		// Verification du login et Affichage resultat precense login
		try {
		    sql= rs.next();
		    if(sql) {
			type = rs.getInt(1);
			System.out.println("<h3>Votre login "+login+" est correct et ton mot de passe aussi !!</h3>");
			//dispatcher=req.getRequestDispatcher("menu.html");
			System.out.println("<h3>Veuillez patientez .... Vous allez etre redirigee ...</h3>");
			if(type.equals(01)) {
			    System.out.println("<h3>Veuillez patientez .... Vous allez etre redirigee vers le menu etudiant ...</h3>");
			    res.sendRedirect("../menu_etudiant.html");
			}
			if(type.equals(02)) {
			    System.out.println("<h3>Veuillez patientez .... Vous allez etre redirigee vers le menu profeseur ...</h3>");
			    res.sendRedirect("../menu_professeur.html");
			}
			if(type.equals(03)) {
			    System.out.println("<h3>Veuillez patientez .... Vous allez etre redirigee vers le menu secretaire ...</h3>");
			    res.sendRedirect("../menu_secretaire.html");
			}
		    }else{
			System.out.println("<h3>Votre login "+login+" est inconnu au bataillon ou tu t'es trompe dans ton mot de passe !! </h3></br> ");// +
					   // "<h3>Reviens plus tard soldat ou recommence!!</h3>");
			//dispatcher=req.getRequestDispatcher("login.html");
			System.out.println("<h3>Veuillez patientez .... Vous allez etre redirigee ...</h3>");
			res.sendRedirect("../login.html");
		    }
		    System.out.println("Execution verification login et mot de passe  OK");
		} catch (SQLException e1) {
		    System.out.println("!!!!!!Erreur verification login et mot de passe ");
		    e1.printStackTrace();
		    connexion_fermer(con);
		}

		// Verification du mot de passe et affichage si mot de passe bon
		req.setAttribute("login",login);
		req.setAttribute("mdp",mdp);
		req.setAttribute("type",type);
		System.out.println("Mise en attribut OK");

		// Redirection vers la page
		System.out.println("Redirection OK");
		
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
