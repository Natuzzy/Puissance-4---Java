package fr.mouse.listeners;
import fr.p4.Affichage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseListenerNewGame implements MouseListener {
	
	private Affichage affichage;
	
	public MouseListenerNewGame(Affichage affichage) {
		this.affichage = affichage;
	}

	public void mouseClicked(MouseEvent arg0) {
		affichage.initJeu();
	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent arg0) {

	}

	public void mouseReleased(MouseEvent arg0) {

	}

}
