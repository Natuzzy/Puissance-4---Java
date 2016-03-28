package fr.p4;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

	//D�claration des variables 

	private EtatDuJeu etatDuJeu = new EtatDuJeu(); //Lancement de la class EtatDuJeu stock� dans etatDuJeu
	private Logique logique = new Logique(); // Lancement de la class Logique stock� dans logique

	private String j1; 
	private String j2;

	private GLabel jActuelle; 
	private GRect cActuelle;
	private GOval[][] grille = new GOval[Config.NB_LIGNES][Config.NB_COLONNES];

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

		//JOUER
		GRect cJouer = new GRect(largeur/2, hauteur/5);
		cJouer.setLocation(largeur/2-cJouer.getWidth()/2, hauteur/5-cJouer.getHeight()/2);
		cJouer.setFilled(true);
		cJouer.setFillColor(Color.green);
		cJouer.addMouseListener(new MouseListenerJouer(this));
		GLabel tJouer = new GLabel("Jouer");
		tJouer.setFont("Comic Sans MS-40");
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
		tQuitter.setLocation(cQuitter.getX()+tQuitter.getWidth()/2, cQuitter.getY()+tQuitter.getHeight()/2);
		add(cQuitter);
		add(tQuitter);	
	}


	//Mise en place du plateau
	public void plateau(){
		removeAll();
		double hauteur = getHeight();
		double largeur = getWidth();


		//System de redimensionnement automatique de la fen�tre
		GRect fenetre = new GRect(0,0,largeur,hauteur); 		// Rectangle qui rempli toute la fen�tre
		fenetre.setVisible(false); 								// Pas besoin de voir le rectangle
		fenetre.sendBackward(); 								// l'envoyer derrier les autres GObject
		fenetre.addMouseListener(new MouseListenerWindow(this));
		add(fenetre);


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

		//TODO : Mettre � jour le joueur qui doit joeur
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
		etatDuJeu.setJActuelleDebut();
		plateau();
	}




}
