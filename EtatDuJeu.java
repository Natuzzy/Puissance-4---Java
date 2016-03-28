package fr.p4;
import java.awt.Color;


public class EtatDuJeu {

	//Déclaratino des variables
	private Color[][] cColor = new Color[Config.NB_LIGNES][Config.NB_COLONNES];
	private Color cActuelle;
	private String jActuelle;
	private String j1;
	private String j2;

	//Initialisation de l'état du jeu
	public EtatDuJeu(){
		//Le joueur qui commence à la couleur colorJ1 de la class Config
		cActuelle = Config.colorJ1;

		//Initialement les cases de la couleur colorVide
		for(int x=0; x < Config.NB_COLONNES; x++){
			for(int y = 0; y < Config.NB_LIGNES; y++){
				cColor[y][x] = Config.colorVide;
			}
		}

	}


	//Définir la couleur du joueur actuelle
	public void setCActuelle(){
		if(cActuelle == Config.colorJ1){
			cActuelle = Config.colorJ2;
		}
		else{
			cActuelle = Config.colorJ1;
		}
	}

	//Renvoie de la couleur du joueur actuelle
	public Color getCActuelle(){
		return cActuelle;
	}


	//Définir le Joueur 1
	public void setJ1(String j1){
		this.j1 = j1;
	}
	//Définir le Joueur 2
	public void setJ2(String j2){
		this.j2 = j2;
	}
	//Définir le nom du joueur initiale
	public void setJActuelleDebut(){
		jActuelle = j1;
	}
	//Définir le nom du joueur actuelle
	public void setJActuelle(){
		if(jActuelle == j1){
			jActuelle = j2;
		}
		else{
			jActuelle = j1;
		}
	}
	//Obtenir le nom du joueur actuelle
	public String getJActuelle(){
		return jActuelle;
	}


	//Renvoie de la couleur de la case cColor[y][x]
	public Color getCColor(int y, int x) {
		return cColor[y][x];
	}

	//Définir la couleur de la case cColor[y][x]
	public void setCColor(int y, int x){
		cColor[y][x] = cActuelle;
	}

}
