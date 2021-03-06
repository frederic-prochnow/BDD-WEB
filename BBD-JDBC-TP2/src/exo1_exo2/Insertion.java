package exo1_exo2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Insertion {

	public static void main(String args []){
		
		Connection con = null;
		
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
			Statement stmt = con.createStatement();
			stmt.executeUpdate("insert into CLIENTS values('Durand','paul',10)");
			System.out.println("Execution requete OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur requete SQL");
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
