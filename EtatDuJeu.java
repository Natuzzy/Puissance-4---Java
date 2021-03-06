package fr.p4;
import java.awt.Color;


public class EtatDuJeu {

	//D�claratino des variables
	private Color[][] cColor = new Color[Config.NB_LIGNES][Config.NB_COLONNES];
	private Color cActuelle;
	private String jActuelle;
	private String j1;
	private String j2;
	private int coupJ1 = 0;
	private int coupJ2 = 0;

	//Initialisation de l'�tat du jeu
	public EtatDuJeu(){
		//Le joueur qui commence � la couleur colorJ1 de la class Config
		cActuelle = Config.colorJ1;

		//Initialement les cases de la couleur colorVide
		for(int x=0; x < Config.NB_COLONNES; x++){
			for(int y = 0; y < Config.NB_LIGNES; y++){
				cColor[y][x] = Config.colorVide;
			}
		}

	}


	//D�finir la couleur du joueur actuelle
	public void setCActuelle(Color color){
		cActuelle = color;
	}
	//Renvoie de la couleur du joueur actuelle
	public Color getCActuelle(){
		return cActuelle;
	}


	//D�finir le Joueur 1
	public void setJ1(String j1){
		this.j1 = j1;
	}
	//D�finir le Joueur 2
	public void setJ2(String j2){
		this.j2 = j2;
	}
	
	//Obtenir le nom du joueur 1
	public String getJ1() {
		return j1;
	}
	//Obtenir le nom du joueur 2
	public String getJ2() {
		return j2;
	}
	
	//D�finir le nom du joueur actuelle
	public void setJActuelle(String j){
			jActuelle = j;
	}
	//Obtenir le nom du joueur actuelle
	public String getJActuelle(){
		return jActuelle;
	}


	//Renvoie de la couleur de la case cColor[y][x]
	public Color getCColor(int y, int x) {
		return cColor[y][x];
	}

	//D�finir la couleur de la case cColor[y][x]
	public void setCColor(int y, int x){
		cColor[y][x] = cActuelle;
	}
	
	
	//Nombre de coup du joueur 1 :
	public void setCJ1(int nb){
		coupJ1 = nb;
	}
	public int getCJ1(){
		return coupJ1;
	}
	
	//Nombre de coup du joueur 2 :
	public void setCJ2(int nb){
		coupJ2 = nb;
	}
	public int getCJ2(){
		return coupJ2;
	}
}
