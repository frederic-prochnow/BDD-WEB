package exo1_exo2;
import java.sql.*;

public class Selection {
	
	public static void main(String args []){
		
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
		}
		
		// Parametre Base
		String url = "jdbc:odbc:base";
		try {
			con = DriverManager.getConnection(url);
			System.out.println("Connexion base acess OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur connection de la base");
		}
		
		// Requete SQL
		try {
			stmt = con.createStatement();
			String query =("Select * from CLIENTS");
			rs = stmt.executeQuery(query);
			System.out.println("Execution requete OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur requete SQL");
		}
		
		// Affichage requete SQL
		System.out.println("Liste des clients:");
		try {
			while (rs.next()){
				String n = rs.getString("nom");
				String p = rs.getString("prenom");
				int a = rs.getInt("age");
				System.out.println(n+"--"+p+"--"+a);
			}
			System.out.println("Execution affichage OK");
		} catch (SQLException e1) {
			System.out.println("!!!!!!Erreur affichage resultat SQL");
			e1.printStackTrace();
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
}
