package fr.p4;


public class Logique {

	//Déclaration des variables
	private EtatDuJeu etatDuJeu;
	private Affichage affichage;

	//Class necessaires au fonctionnement de celle-ci
	public void init(EtatDuJeu etatDuJeu, Affichage affichage) {
		this.etatDuJeu = etatDuJeu;
		this.affichage = affichage;
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
			etatDuJeu.setCActuelle(Config.colorJ2);
			etatDuJeu.setJActuelle(etatDuJeu.getJ2());
		}else{
			etatDuJeu.setCActuelle(Config.colorJ1);
			etatDuJeu.setJActuelle(etatDuJeu.getJ1());
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
}
