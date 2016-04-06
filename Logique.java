package fr.p4;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import fr.chrono.ChronoJ1;
import fr.chrono.ChronoJ2;


public class Logique {

	//Déclaration des variables
	private EtatDuJeu etatDuJeu;
	private Affichage affichage;
	private ChronoJ1 chronoJ1;
	private ChronoJ2 chronoJ2;
	
	private String fSave = "save.txt";


	//Class necessaires au fonctionnement de celle-ci
	public void init(EtatDuJeu etatDuJeu, Affichage affichage, ChronoJ1 chronoJ1, ChronoJ2 chronoJ2) {
		this.etatDuJeu = etatDuJeu;
		this.affichage = affichage;
		this.chronoJ1 = chronoJ1;
		this.chronoJ2 = chronoJ2;
	}


	//Définir le jeton selectionné
	public void setJeton(int y, int x){

		int dLigne = Config.NB_LIGNES-1; //Dernière ligne d'une colonne
		int ligne = 0;

		//Determiner la case vide la plus basse dans la colonne selectionné
		if(etatDuJeu.getCColor(dLigne, x) == Config.colorVide){ //Tester si la colonne est pleine
			for(int i=0; i < Config.NB_LIGNES; i++){
				if(etatDuJeu.getCColor(i, x) != Config.colorVide){ //Si la case i est différente de vide
					ligne++; // Alors la ligne augmente de 1
				}
				else{ // Sinon
					break; // On sort de la boucle
				}
			}
			//Remplir la case vide
			etatDuJeu.setCColor(ligne, x);
			affichage.update();
			verification(ligne, x);
		}
	}


	//Verifier si un joueur à gagné
	private void verification(int y, int x) {
		//Vérification par colonne : 
		if(compte(y, x, -1,0) >= 3){
			System.out.println("Fin du jeu (Colonne)");
			affichage.finDuJeu();
			return;
		}

		//Vérification par ligne
		if(compte(y, x, 0,+1)+compte(y,x,0,-1) >= 3){
			System.out.println("Fin du jeu (ligne)");
			affichage.finDuJeu();
			return;
		}

		//Vérification diagonal haut
		if(compte(y, x, +1,+1)+compte(y,x,-1,-1) >= 3){
			System.out.println("Fin du jeu (Diagonal haut)");
			affichage.finDuJeu();
			return;
		}

		//Vérification diagonal bas
		if(compte(y,x, -1,+1)+compte(y,x, +1,-1) >= 3){
			System.out.println("Fin du jeu (Diagonal bas)");
			affichage.finDuJeu();
			return;
		}

		//Vérifier si la grille est pleine (sans vainqueur)
		int i = 0;
		int compteur =0;
			while(i < Config.NB_COLONNES && etatDuJeu.getCColor(Config.NB_LIGNES-1, i) != Config.colorVide){
				compteur++;
				i++;
				if(compteur == 7){
					System.out.println("Pas de gagnant");
				}
			} 
		
		//Changement de joueur
		if(etatDuJeu.getCActuelle() == Config.colorJ1){
			etatDuJeu.setCJ1(etatDuJeu.getCJ1()+1);
			etatDuJeu.setCActuelle(Config.colorJ2);
			etatDuJeu.setJActuelle(etatDuJeu.getJ2());
			chronoJ1.stopChronoJ1();
			chronoJ2.startChronoJ2();
		}else{
			etatDuJeu.setCJ2(etatDuJeu.getCJ2()+1);
			etatDuJeu.setCActuelle(Config.colorJ1);
			etatDuJeu.setJActuelle(etatDuJeu.getJ1());
			chronoJ1.startChronoJ1();
			chronoJ2.stopChronoJ2();
		}
		
		//Mettre à jour l'affichage
		affichage.update();
	}
	//Compter les pions aligné dans la direction indiqué
	public int compte(int y, int x, int dirLigne, int dirColonne){
		int compteur = 0;
		int colonne = x+dirColonne;
		int ligne = y+dirLigne;

		//if(colonne >= 0 && colonne < Config.NB_COLONNES && ligne >= 0 && ligne < Config.NB_LIGNES){
		while(ligne >= 0 && colonne >= 0 && colonne < Config.NB_COLONNES && ligne < Config.NB_LIGNES && etatDuJeu.getCColor(y, x) == etatDuJeu.getCColor(ligne, colonne) &&  etatDuJeu.getCColor(ligne, colonne) != Config.colorVide){
			compteur++;
			colonne = colonne + dirColonne;
			ligne = ligne + dirLigne;
			System.out.println("Bonjour, ligne : "+ligne+", colonne :"+colonne+", compteur :"+compteur);
		}
		return compteur;	
	}
	

	//Création de sauvegarde de la partie
	public void saveJeu(){
		try{
			PrintWriter fSortie = new PrintWriter(fSave);
			fSortie.println(etatDuJeu.getJ1());
			fSortie.println(etatDuJeu.getJ2());
			
			fSortie.println(etatDuJeu.getCJ1());
			fSortie.println(etatDuJeu.getCJ2());
			
			if(etatDuJeu.getCActuelle() == Config.colorJ1){
				fSortie.println(1);
			}
			else{
				fSortie.println(2);
			}

			for(int y = 0; y < Config.NB_LIGNES; y++){
				for(int x = 0; x < Config.NB_COLONNES; x++){
					fSortie.println(saveCase(y,x));
				}
			}

			fSortie.close();
		} catch (IOException e){
			System.out.println("Erreur Traitement fichier "+ e);
		}
	}
	//Transformer la couleur de la case souhaité en un nombre
	private int saveCase(int y, int x) {
		int sCase;
		if(etatDuJeu.getCColor(y, x) == Config.colorJ1){
			sCase = 1;
		}
		else if(etatDuJeu.getCColor(y, x) == Config.colorJ2){
			sCase = 2;
		}
		else{
			sCase = 0;
		}
		return sCase;
	}
	
	//Récuperé la sauvegarde
	public void recupSave() {
		File fichier = new File(fSave);
		try{
			Scanner fEntree = new Scanner(fichier);
			
			//Définir le nom des deux joueurs
			String ligne = fEntree.nextLine();
			etatDuJeu.setJ1(ligne);
			ligne = fEntree.nextLine();
			etatDuJeu.setJ2(ligne);		
			
			int jAct = fEntree.nextInt();
			
			int line = fEntree.nextInt();
			etatDuJeu.setCJ1(line);
			line = fEntree.nextInt();
			etatDuJeu.setCJ2(line);
			
			//Definir la couleur de chaque case
			for(int y = 0; y < Config.NB_LIGNES; y++){
				for(int x = 0; x < Config.NB_COLONNES; x++){
					line = fEntree.nextInt();
					etatDuJeu.setCActuelle(recupColor(line));
					etatDuJeu.setCColor(y, x);
				}
			}
			
			if(jAct == 1){
				etatDuJeu.setCActuelle(Config.colorJ1);
				etatDuJeu.setJActuelle(etatDuJeu.getJ1());
			}
			else{
				etatDuJeu.setCActuelle(Config.colorJ2);
				etatDuJeu.setJActuelle(etatDuJeu.getJ2());
			}
			
			fEntree.close();
		}catch(IOException e){
			System.out.println("Erreur ouverture fichier : "+e);
		}
		affichage.stats();
		affichage.plateau();
	}
	//Transformer chaque nombre en couleur
	public Color recupColor(int nb){
		Color color;
		if(nb == 1){
			color = Config.colorJ1;
		}
		else if(nb == 2){
			color = Config.colorJ2;
		}
		else{
			color =	Config.colorVide;
		}
		return color;
	}

}
