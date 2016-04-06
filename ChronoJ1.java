package fr.chrono;

import fr.p4.Affichage;

import java.awt.Color;
import java.awt.event.ActionEvent;

import acm.program.GraphicsProgram;
import acm.util.SwingTimer;

public class ChronoJ1 extends GraphicsProgram {
	  
	  public static int DELTA_T = 1000;
	  private SwingTimer chronometre;
	  
	  private  int tempsEcoule ;
	  private int minute;
	  private int seconde;
	  private Affichage affichage;
	  
	  public void init(Affichage affichage) {
		  this.affichage = affichage;
		  chronometre = new SwingTimer(DELTA_T, this);
		  tempsEcoule = 0;
		  
	 }
	  public void startChronoJ1(){
		  chronometre.start();
		  System.out.println("StartJC1");
	  }
	  public void stopChronoJ1(){
		  chronometre.stop();
		  System.out.println("StopJC1");

	  }

		  
	 public void actionPerformed(ActionEvent e){
			if(e.getSource() == chronometre) {
				
				tempsEcoule = tempsEcoule + DELTA_T;
				int minute = tempsEcoule/1000 / 60;
				int seconde = tempsEcoule/1000 % 60;
				affichage.updateChronoJ1(minute, seconde);
			}	
		
	  }
	  
}