<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.* " %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="javax.servlet.annotation.WebServlet" %>
<!DOCTYPE html>
<html>
<head>
<title> MaPage </title>			     
</head>
<body>
<center>
<h1>ma requete</h1>
<%
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
		try {
			con.close();
			System.out.println("Fermeture connexion OK");
		} catch (SQLException e1) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur fermeture de connexion");
		}
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
		try {
			con.close();
			System.out.println("Fermeture connexion OK");
		} catch (SQLException e1) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur fermeture de connexion");
		}
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
		try {
			con.close();
			System.out.println("Fermeture connexion OK");
		} catch (SQLException e1) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur fermeture de connexion");
		}
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
		try {
			con.close();
			System.out.println("Fermeture connexion OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur fermeture de connexion");
		}
	}
	
	// fermeture des connection
	try {
		con.close();
		System.out.println("Fermeture connexion OK");
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("!!!!!!Erreur fermeture de connexion");
	}
		
	out.println( "</body>" );
%>
</body>
</html>