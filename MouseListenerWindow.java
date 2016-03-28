package fr.mouse.listeners;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fr.p4.Affichage;


public class MouseListenerWindow implements MouseListener {

	private Affichage affichage;
	
	public MouseListenerWindow(Affichage affichage) {
		this.affichage = affichage;
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		affichage.plateau();
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
