package fr.chrono;
import fr.p4.Affichage;

import java.awt.Color;
import java.awt.event.ActionEvent;

import acm.program.GraphicsProgram;
import acm.util.SwingTimer;

public class ChronoJ2 extends GraphicsProgram {

	public static int DELTA_T = 1000;
	private SwingTimer chronometre;

	private  int tempsEcoule ;
	private int minute;
	private int seconde;
	private Affichage affichage;

	//Initialisation du chrono Joueur 2
	public void init(Affichage affichage) {
		this.affichage = affichage;
		chronometre = new SwingTimer(DELTA_T, this);
		tempsEcoule = 0;
		minute = 0;
		seconde = 0;
	}
	//Lancer le chrono du joueur 2
	public void startChronoJ2(){
		chronometre.start();
	}
	//Arreter le chrono du joueur 2
	public void stopChronoJ2(){
		chronometre.stop();
	}

	//Calcul des minutes et des secondes selon le temps du chrono en ms
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == chronometre) {
			tempsEcoule = tempsEcoule + DELTA_T;
			minute = tempsEcoule/1000 / 60;
			seconde = tempsEcoule/1000 % 60;
			affichage.updateChronoJ2(minute, seconde);
		}	

	}
	//Obtenir le temps ecoulé
	public int getTempsJ2(){
		return tempsEcoule;
	}
	//Définir le temps ecoulé et calcul des minutes et secondes
	public void setTempsJ2(int tempsEcoule){
		this.tempsEcoule = tempsEcoule;
		minute = tempsEcoule/1000 / 60;
		seconde = tempsEcoule/1000 % 60;
	}
	//Obtenir le nombre de minute
	public int getMin(){
		return minute;
	}
	//Obtenir le nombre de second
	public int getSec(){
		return seconde;
	}
}