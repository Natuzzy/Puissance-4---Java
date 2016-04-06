package fr.mouse.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fr.p4.Logique;

public class MouseListenerContinuer implements MouseListener {

	private Logique logique;
	
	public MouseListenerContinuer(Logique logique){
		this.logique = logique;
	}
	
	public void mouseClicked(MouseEvent arg0) {
		logique.recupSave();
	}

	public void mouseEntered(MouseEvent arg0) {
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
