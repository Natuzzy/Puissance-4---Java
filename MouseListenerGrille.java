package fr.mouse.listeners;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fr.p4.Logique;


public class MouseListenerGrille implements MouseListener {

	private Logique logique;
	private int y;
	private int x;
	
	public MouseListenerGrille(Logique logique, int y, int x) {
		this.logique = logique;
		this.y = y;
		this.x = x;
	}

	public void mouseClicked(MouseEvent arg0) {
		logique.setJeton(x);
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
