package exo4;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Generique {

	public static File creer_fichier(String driver,String url,String login,String mdp) {
		File f = new File("info_serveur");
		try {
			PrintWriter pw = new PrintWriter(f);
			pw.println(	"d=" + driver + "d;" + "\n" +
						"u=" + url + "u;" + "\n" +
						"l=" + login + "l;" + "\n" +
						"m=" + mdp + "m;");
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur creation et ecriture fichier");
		}
		return f;
	}
	
	public static String lire_fichier(File f){
		String chaine ="";
		try {
			InputStream ips = new FileInputStream(f);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while((ligne=br.readLine())!=null){
				chaine+=ligne;
			}
			return chaine;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur lecture fichier");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur lecture fichier");
		}
		return chaine;
	}

	public static String getDriver(File f) {
		String chaine = lire_fichier(f);
		int idxd = 0;
		int idxf = 0;
		for(int i=0; i< chaine.length(); i++) {
			if(chaine.charAt(i)== 'd') {
				idxd = i + 2;
			}
			if(chaine.charAt(i)==';'&&chaine.charAt(i-1)=='d') {
				idxf = i - 2;
			}
		}
		return chaine.substring(idxd, idxf);
	}

	public static String getUrl(File f) {
		String chaine = lire_fichier(f);
		int idxd = 0;
		int idxf = 0;
		for(int i=0; i< chaine.length(); i++) {
			if(chaine.charAt(i)== 'u') {
				idxd = i + 2;
			}
			if(chaine.charAt(i)==';'&&chaine.charAt(i-1)=='u') {
				idxf = i - 2;
			}
		}
		return chaine.substring(idxd, idxf);
	}

	public static String getLogin(File f) {
		String chaine = lire_fichier(f);
		int idxd = 0;
		int idxf = 0;
		for(int i=0; i< chaine.length(); i++) {
			if(chaine.charAt(i)== 'l') {
				idxd = i + 2;
			}
			if(chaine.charAt(i)==';'&&chaine.charAt(i-1)=='l') {
				idxf = i - 2;
			}
		}
		return chaine.substring(idxd, idxf);
	}

	public static String getMdp(File f) {
		String chaine = lire_fichier(f);
		int idxd = 0;
		int idxf = 0;
		for(int i=0; i< chaine.length(); i++) {
			if(chaine.charAt(i)== 'm') {
				idxd = i + 2;
			}
			if(chaine.charAt(i)==';'&&chaine.charAt(i-1)=='m') {
				idxf = i - 2;
			}
		}
		return chaine.substring(idxd, idxf);
	}
	
	public static void enregistrement_driver(Connection con,File f) {
		try {
			Class.forName(getDriver(f));
			System.out.println("Connexion driver OK");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur driver");
			connexion_fermer(con);
		}
	}
	
	public static void connexion_base_sans_mdp(Connection con,File f){
		try {
			con = DriverManager.getConnection(getUrl(f), getLogin(f), getMdp(f));
			System.out.println("Connexion base acess OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur connection de la base");
			connexion_fermer(con);
		}
	}
	public static void connexion_base(Connection con,File f){
		try {
			con = DriverManager.getConnection(getUrl(f));
			System.out.println("Connexion base acess OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur connection de la base");
			connexion_fermer(con);
		}
	}
	
	public static void lancer_requete(Connection con,String requete){
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(requete);
			System.out.println("Execution requete OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur requete SQL");
			connexion_fermer(con);
		}
	}
	
	public static void connexion_fermer(Connection con){
		try {
			con.close();
			System.out.println("Fermeture connexion OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur fermeture de connexion");
		}
	}
	
	public static void main(String [] args) {
		Connection con = null;
		File f = creer_fichier("sun.jdbc.odbc.JdbcOdbcDriver","jdbc:odbc:base","","");
		enregistrement_driver(con,f);
		connexion_base_sans_mdp(con,f);
		lancer_requete(con,"insert into CLIENTS values('PROCHNOW','FREDERIC',19)");
		connexion_fermer(con);
	}
	
}
