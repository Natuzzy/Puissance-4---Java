package fr.p4;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import fr.mouse.listeners.*;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.io.IODialog;
import acm.program.GraphicsProgram;


public class Affichage extends GraphicsProgram {
	//22h20 --> 1h : R�ecriture du programme
	//12h --> 35min : ""
	//14h30 --> 30 min : ""
	//17h:30 --> 1h10 : Finalisation de la r�ecriture
	//Dim 27/03 : 40 min : Brouillon : ajout chrono chaque jouer + nb coups
	//2h --> sauvegarde / fichier

	//D�claration des variables 

	private EtatDuJeu etatDuJeu = new EtatDuJeu(); //Lancement de la class EtatDuJeu stock� dans etatDuJeu
	private Logique logique = new Logique(); // Lancement de la class Logique stock� dans logique

	private String j1; 
	private String j2;

	private GLabel jActuelle; 
	private GRect cActuelle;
	private GOval[][] grille = new GOval[Config.NB_LIGNES][Config.NB_COLONNES];

	private String fSave = "save.txt";

	//Fin des declaration de variable



	//Debut du programme

	//Initialisation du jeu
	public void run(){
		//Mise � z�ro et initialisation des class
		etatDuJeu = new EtatDuJeu();
		logique = new Logique();
		logique.init(etatDuJeu, this);

		//Lancement du menu
		menu();
	}


	//Mise en place du menu
	public void menu() {
		//Obtention des dimensions de la fen�tre
		double hauteur = getHeight();
		double largeur = getWidth();
		long font = Math.round(largeur/12);
		System.out.println(font);
		removeAll();

		//JOUER
		GRect cJouer = new GRect(largeur/2, hauteur/5);
		cJouer.setLocation(largeur/2-cJouer.getWidth()/2, hauteur/5-cJouer.getHeight()/2);
		cJouer.setFilled(true);
		cJouer.setFillColor(Color.green);
		cJouer.addMouseListener(new MouseListenerJouer(this));
		GLabel tJouer = new GLabel("Jouer");
		tJouer.setFont("Comic Sans MS-"+font);
		tJouer.setLocation(largeur/2-tJouer.getWidth()/2, hauteur/5-tJouer.getHeight()/2);
		add(cJouer);
		add(tJouer);

		//Case QUITTER
		GRect cQuitter = new GRect(largeur/2, hauteur/5);
		cQuitter.setLocation(largeur/2-cQuitter.getWidth()/2, hauteur*3/5-cQuitter.getHeight()/2);
		cQuitter.setFilled(true);
		cQuitter.setFillColor(Color.red);
		cQuitter.addMouseListener(new MouseListenerQuitter());
		GLabel tQuitter = new GLabel("Quitter");
		tQuitter.setFont("Comic Sans MS-40");
		tQuitter.setLocation(cQuitter.getX()+cQuitter.getWidth()/2-tQuitter.getWidth()/2, cQuitter.getY()+cQuitter.getHeight()/2);
		add(cQuitter);
		add(tQuitter);	
	}


	//Mise en place du plateau
	public void plateau(){
		removeAll();
		double hauteur = getHeight();
		double largeur = getWidth();

		addMouseListeners();//Ajout de lecture de la souris pour redimensionner auto le plateau

		//Afficher la grille de jeu
		for(int x=0; x < Config.NB_COLONNES; x++){ 						//Pour chaque colonne on fait :
			int j = Config.NB_LIGNES-1; 								// Mettre la premi�re case en bas de la fen�tre
			for(int y=0; y < Config.NB_LIGNES; y++){ 					// A chaque ligne y on fait :
				grille[y][x] = new GOval(largeur*x/8, hauteur*(j+1)/8, largeur/10,hauteur/10);
				grille[y][x].setFilled(true);
				grille[y][x].setFillColor(etatDuJeu.getCColor(y,x));
				grille[y][x].addMouseListener(new MouseListenerGrille(logique, y, x));
				add(grille[y][x]);
				j--;
			}
		}

		//TODO : Afficher la barre menu
		//TODO : Afficher la colonne des stats
		cActuelle = new GRect(largeur*7/8, hauteur/8, largeur/10, hauteur/10);
		cActuelle.setFilled(true);
		cActuelle.setFillColor(etatDuJeu.getCActuelle());
		add(cActuelle);
		//Nom du joueur actuelle
		jActuelle = new GLabel(""+etatDuJeu.getJActuelle(), largeur*7/8, hauteur*2/8);
		add(jActuelle);	


	}


	//Mettre � jour le plateau
	public void update(){
		//Mettre � jour les couleurs de la grille
		for(int x = 0; x < Config.NB_COLONNES; x++){
			for(int y=0; y < Config.NB_LIGNES; y++){
				grille[y][x].setFillColor(etatDuJeu.getCColor(y, x));
			}
		}

		//Mettre � jour le joueur qui doit joueur
		cActuelle.setFillColor(etatDuJeu.getCActuelle());
		jActuelle.setLabel(""+etatDuJeu.getJActuelle());
	}


	//Obtenir les noms des joueurs
	public void initJeu(){
		removeAll();
		IODialog io = getDialog();
		j1 = io.readLine("Entrez le nom du joueur 1 (Couleur Jaune)");
		j2 = io.readLine("Entrez le nom du joueur 2 (Couleur Rouge)");
		etatDuJeu.setJ1(j1);
		etatDuJeu.setJ2(j2);
		etatDuJeu.setJActuelle(j1);
		plateau();
	}


	//Fin du jeu
	public void finDuJeu() {
		double hauteur = getHeight();
		double largeur = getWidth();

		String winner = etatDuJeu.getJActuelle();

		removeAll();
		GLabel win = new GLabel("Le vainqueur est : "+winner);
		win.setLocation(largeur/2-win.getWidth(), hauteur/5-win.getHeight());
		add(win);

		GRect rejouer = new GRect(largeur/5, hauteur/5);
		rejouer.setLocation(largeur*2/5-rejouer.getWidth(), hauteur*3/5-rejouer.getHeight());
		rejouer.addMouseListener(new MouseListenerReJouer(this));
		add(rejouer);

	}

	
	//Redimensionner le plateau si la souris entre dans la fen�tre
	public void mouseEntered(MouseEvent e){
		plateau();
	}


	//Cr�ation de sauvegarde de la partie
	public void saveJeu(){
		try{
			PrintWriter fSortie = new PrintWriter(fSave);
			fSortie.println(etatDuJeu.getJ1());
			fSortie.println(etatDuJeu.getJ2());
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
			println("Erreur Traitement fichier "+ e);
		}
	}
	//Transformer la couleur de la case souhait� en un nombre
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
	
	//R�cuper� la sauvegarde
	private void recupSave() {
		File fichier = new File(fSave);
		try{
			Scanner fEntree = new Scanner(fichier);
			
			//D�finir le nom des deux joueurs
			String ligne = fEntree.nextLine();
			etatDuJeu.setJ1(ligne);
			ligne = fEntree.nextLine();
			etatDuJeu.setJ2(ligne);			
			
			//Definir la couleur de chaque case
			for(int y = 0; y < Config.NB_LIGNES; y++){
				for(int x = 0; x < Config.NB_COLONNES; x++){
					int line = fEntree.nextInt();
					etatDuJeu.setCActuelle(recupColor(line));
					etatDuJeu.setCColor(y, x);
				}
			}
			fEntree.close();
		}catch(IOException e){
			println("Erreur ouverture fichier : "+e);
		}
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
