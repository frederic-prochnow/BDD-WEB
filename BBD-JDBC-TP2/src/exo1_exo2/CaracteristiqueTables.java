package exo1_exo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class CaracteristiqueTables {
	
	public static void main(String args []){
		
		Connection con = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
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
			String query =("Select NOM,PRENOM,AGE from CLIENTS");
			rs = stmt.executeQuery(query);
			System.out.println("Execution requete OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur requete SQL");
		}
		
		// Recuperation Meta Data
		try {
			rsmd = rs.getMetaData();
			System.out.println("Recuperation Meta Data OK");
		} catch (SQLException e2) {
			e2.printStackTrace();
			System.out.println("!!!!!!Erreur recuperation Meta Data SQL");
		}
		
		// Affichage requete SQL
		try {
			int nbCols = rsmd.getColumnCount();
			System.out.println("Cette table contient "+ nbCols + " colonnes");
			for(int i=1; i<= nbCols; i++) {
				System.out.println("Colonne "+i);
				System.out.println("Nom : " + rsmd.getColumnName(i));
				System.out.println("Type : "+ rsmd.getColumnTypeName(i));
				System.out.println("Prec : "+ rsmd.getPrecision(i));
				System.out.println("Read only : "+ rsmd.isReadOnly(i));
				System.out.println("Auto num : " + rsmd.isAutoIncrement(i));
				System.out.println("Null accepté : "+ rsmd.isNullable(i));
				System.out.println("");
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
