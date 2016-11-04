
import java.sql.*;

public class Selection {
	
	public static void connexion_fermer(Connection con){
		try {
			con.close();
			System.out.println("Fermeture connexion OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur fermeture de connexion");
		}
	}
	
	public static void main(String args []){
		
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
		}
		
		// Parametre Base
		String url = "jdbc:postgresql://psqlserv/n3p1";
		String user = "prochnof";
		String password = "moi";
		try {
			con = DriverManager.getConnection(url,user,password);
			System.out.println("Connexion base acess OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur connection de la base");
			connexion_fermer(con);
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
			connexion_fermer(con);
		}
		
		// Affichage requete SQL
		System.out.println("Liste des personnes:");
		try {
			while (rs.next()){
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				System.out.println(id+"--"+nom+"--"+prenom);
			}	
			System.out.println("Execution affichage OK");
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("!!!!!!Erreur affichage resultat SQL");
			connexion_fermer(con);
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
