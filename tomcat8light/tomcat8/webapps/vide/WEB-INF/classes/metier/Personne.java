package metier;
public class Personne{

	private Integer i;
	
	public Personne(){
		i=0;
	}
	
	public String toString(){
		i++;
		return "C'est le "+i+"eme Hello World !";
	}
	
	public int getI(){
		return i;
	}
	
	public void incrementeI(){
		i++;
	}
	
	public void setI(Integer i){
		this.i = i;
	}
}
