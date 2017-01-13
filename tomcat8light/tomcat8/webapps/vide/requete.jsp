<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.* " %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="javax.servlet.annotation.WebServlet" %>
<!DOCTYPE html>
<html>
<head>
<title> E-commerce </title>			     
</head>
<body>
<center>
<h1>Bienvenue sur mon e-commerce...</h1>
<center><h1>Votre panier</h1></center>
<%
	Map<String,Integer> liste = (Map<String,Integer>) session.getAttribute("liste");
	if(liste==null){
		liste = new HashMap();
	}
	String id2 = request.getParameter("id");
	if(id2!=null){
		if(liste.containsKey(id2)){
			Integer i = liste.get(id2);
			liste.replace(id2,i++);
		} else {
			liste.put(id2,1);
		}
	}
	out.println("<table class=\"table-centered table-hover table-condensed\"><tr><th>id</th><th bgcolor=\"grey\">libelle</th><th>prix</th><th>Quantite</th></tr> ");
	for( java.util.Iterator  ii = liste.keySet().iterator(); ii.hasNext();) {
		String key = (String) ii.next();
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
			String query =("Select * from produits where id ="+key+";");
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
			while (rs.next()){
				String id = rs.getString("id");
				String libelle = rs.getString("libelle");
				int prix = rs.getInt("prix");
				out.println(
				"<tr><td>"+id+"</td><td bgcolor=\"grey\">"+libelle+"</td><td>"+prix+"</td><td>"+liste.get(key)+"</td></tr>");
			}
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
	}
	out.println("</table>");
	session.setAttribute("liste",liste);

%>
<center><h1>Liste des produits:</h1></center>
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
		String query =("Select * from produits;");
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
		out.println("<table class=\"table-centered table-hover table-condensed\"><tr><th>id</th><th bgcolor=\"grey\">libelle</th><th>prix</th></tr> ");
		while (rs.next()){
			String id = rs.getString("id");
			session.setAttribute("id",id);
			String libelle = rs.getString("libelle");
			int prix = rs.getInt("prix");
			out.println(
			"<tr><td>"+id+"</td><td bgcolor=\"grey\"><a href=requete.jsp?id="+id+">"+libelle+"</a></td><td>"+prix+
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