package metier;
/*
Rappel : pour compiler ou executer, se place au dessus du répertoire mcv
javac -d metier metier/JeuPileOuFace.java
java -cp metier metier.JeuPileOuFace
*/

import java.util.*;
public class JeuPileOuFace{

	private ArrayList<Character> humain;
	private ArrayList<Character> ordi;
	private int pointsHumain;
	private int pointsOrdi;
	private int numPartie;
	
	// réinitialise une partie

	public void init(){
		humain = new ArrayList<Character>();
		ordi = newArrayList<Character>();
		pointsHumain=0;
		pointsOrdi=0;
		numPartie=0;
	}
	
	public boolean termine(){
		return pointsHumain==10 || pointsOrdi==10;
	}
	
	public int getPointsHumain(){
		return pointsHumain;
	}
	
	public int getPointsOrdi(){
		return pointsOrdi;
	}
	
	public char getLastHumain(){
		return humain.get(humain.size()-1).charValue();
	}
	
	public char getLastOrdi(){
		return ordi.get(ordi.size()-1).charValue();
	}
	
	// joue, avec h comme coup du joueur humain
	public void play(char(h) {
		// l'IA : choisit o : le coup de l'ordi
		char o;
		if (Math.random()>0.5) o='P';
		else o='F';
		
		// range dans l'historique des coups
		humain.add(new Character(h));
		ordi.add(new Character(o));

		// et compte les points
		// deux faces identiques donne le point à l'ordi
		if(h==o) pointsOrdi++;
		else pointsHumain++;
		numPartie++;	
	}
	
	public static void main(String [] args){
		int pointhumain = getPointsHumain();
		int pointOrdi = getPointsOrdi();
		init();
		while(!termine()){
			if(args[0] == 'p'){
				play('p');
			}	 
			f (args[0] == 'f') {
				play('f');
			}
		}
	}
}