package fr.p4;
import java.awt.Color;
import java.awt.event.MouseEvent;

import fr.chrono.ChronoJ1;
import fr.chrono.ChronoJ2;
import fr.mouse.listeners.*;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.io.IODialog;
import acm.program.GraphicsProgram;


public class Affichage extends GraphicsProgram {
	//22h20 --> 1h : Réecriture du programme
	//12h --> 35min : ""
	//14h30 --> 30 min : ""
	//17h:30 --> 1h10 : Finalisation de la réecriture
	//Dim 27/03 : 40 min : Brouillon : ajout chrono chaque jouer + nb coups
	//Ven/sam 1/2 -04 : 2h --> sauvegarde / fichier
	//lun 04 : 20h50 --> ~30 min : poffinnement + nb coup des joueurs

	//Déclaration des variables 

	private EtatDuJeu etatDuJeu = new EtatDuJeu(); //Lancement de la class EtatDuJeu stocké dans etatDuJeu
	private Logique logique = new Logique(); // Lancement de la class Logique stocké dans logique
	private ChronoJ1 chronoJ1;
	private ChronoJ2 chronoJ2;

	private String j1; 
	private String j2;
	private GLabel timeJ1;
	private GLabel timeJ2;
	private GLabel coupJ1;
	private GLabel coupJ2;

	private GLabel jActuelle; 
	private GRect cActuelle;
	private GOval[][] grille = new GOval[Config.NB_LIGNES][Config.NB_COLONNES];


	//Fin des declaration de variable



	//Debut du programme

	//Initialisation du jeu
	public void run(){
		//Mise à zéro et initialisation des class
		chronoJ1 = new ChronoJ1();
		chronoJ2 = new ChronoJ2();
		chronoJ1.init(this);
		chronoJ2.init(this);
		
		etatDuJeu = new EtatDuJeu();
		logique = new Logique();
		logique.init(etatDuJeu, this, chronoJ1, chronoJ2);
		//Lancement du menu
		menu();
	}


	//Mise en place du menu
	public void menu() {
		//Obtention des dimensions de la fenêtre
		double hauteur = getHeight();
		double largeur = getWidth();
		long font = Math.round(largeur/12);
		removeAll();
	
		//Continuer la partie
		GRect continuer = new GRect(10,10,100,100);
		continuer.addMouseListener(new MouseListenerContinuer(logique));
		add(continuer);
		
		//Nouvelle partie
		GRect cJouer = new GRect(largeur/2, hauteur/5);
		cJouer.setLocation(largeur/2-cJouer.getWidth()/2, hauteur/5-cJouer.getHeight()/2);
		cJouer.setFilled(true);
		cJouer.setFillColor(Color.green);
		cJouer.addMouseListener(new MouseListenerNewGame(this));
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
		long police = Math.round(largeur/18);

		addMouseListeners();//Ajout de lecture de la souris pour redimensionner auto le plateau

		//Afficher la grille de jeu
		for(int x=0; x < Config.NB_COLONNES; x++){ 						//Pour chaque colonne on fait :
			int j = Config.NB_LIGNES-1; 								// Mettre la première case en bas de la fenêtre
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
		
		
		timeJ1.setLocation(largeur*8/9, hauteur*3/9);
		add(timeJ1);
		timeJ2.setLocation(largeur*8/9, hauteur*5/9);
		add(timeJ2);
		
		coupJ1 = new GLabel("Nb coup J1 : "+etatDuJeu.getCJ1(),largeur*7.7/9, hauteur*3.5/9);
		add(coupJ1);
		coupJ2 = new GLabel("Nb coup J2 : "+etatDuJeu.getCJ2(),largeur*7.7/9, hauteur*5.5/9);
		add(coupJ2);
		
		//Nom du joueur actuelle
		jActuelle = new GLabel(""+etatDuJeu.getJActuelle(), largeur*7/8, hauteur*2/8);
		add(jActuelle);	

		
		//Bouton rejouer
		GRect gRejouer = new GRect(0,0,largeur/3.81,hauteur/8);
		gRejouer.setFilled(true);
		gRejouer.setFillColor(Color.gray);		
		gRejouer.addMouseListener(new MouseListenerReJouer(this));
		add(gRejouer);
		//text "rejouer"
		GLabel rejouer = new GLabel ("REJOUER",largeur/95.25,3*hauteur/32);
		rejouer.setFont("Serif-"+police);
		add(rejouer);
		
		//bouton aide
		GRect help = new GRect(largeur/3.81,0,largeur/3.81,hauteur/8);
		help.setFilled(true);
		help.setFillColor(Color.gray);
		add(help);		
		//texte "aide"
		GLabel aide = new GLabel ("AIDE",6.25*largeur/19.05,3*hauteur/32);
		aide.setFont("Serif-"+police);
		add(aide);
		
		//Bouton quitter
		GRect gQuitter = new GRect(2*largeur/3.81,0,largeur/3.81,hauteur/8);
		gQuitter.setFilled(true);
		gQuitter.setFillColor(Color.gray);
		gQuitter.addMouseListener(new MouseListenerMQuitter(logique));
		add(gQuitter);
		//Texte quitter
		GLabel quitter = new GLabel ("QUITTER",29*largeur/53.34,3*hauteur/32);
		quitter.setFont("Serif-" + police);
		add(quitter);

	}


	//Mettre à jour le plateau
	public void update(){
		//Mettre à jour les couleurs de la grille
		for(int x = 0; x < Config.NB_COLONNES; x++){
			for(int y=0; y < Config.NB_LIGNES; y++){
				grille[y][x].setFillColor(etatDuJeu.getCColor(y, x));
			}
		}

		//Mettre à jour le joueur qui doit joueur
		coupJ1.setLabel("Nb coup J1 : "+etatDuJeu.getCJ1());
		coupJ2.setLabel("Nb coup J2 : "+etatDuJeu.getCJ2());
		
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
		stats();
		plateau();
		chronoJ1.startChronoJ1();
	}
	//Initialisation des statistiques
	public void stats() {
		timeJ1 = new GLabel("0:0");
		timeJ2 = new GLabel("0:0");
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

	
	//Redimensionner le plateau si la souris entre dans la fenêtre
	public void mouseEntered(MouseEvent e){
		plateau();
	}

	
	//Affichage des chronos	
	public void updateChronoJ1(int minute, int seconde) {
		timeJ1.setLabel(minute+":"+seconde);
	}
	
	public void updateChronoJ2(int minute, int seconde){
		timeJ2.setLabel(minute+":"+seconde);
	}
}
