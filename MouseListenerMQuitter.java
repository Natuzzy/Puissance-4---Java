package fr.mouse.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fr.p4.Logique;

public class MouseListenerMQuitter implements MouseListener {

	private Logique logique;

	public MouseListenerMQuitter(Logique logique) {
		this.logique = logique;
	}

	public void mouseClicked(MouseEvent arg0) {
		System.out.print("Save jeu");
		logique.saveJeu();
		System.exit(0);
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

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
