import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseListenerJouer implements MouseListener {
	
	private Affichage affichage;
	
	public MouseListenerJouer(Affichage affichage) {
		this.affichage = affichage;
	}

	public void mouseClicked(MouseEvent arg0) {
		affichage.getNames();
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
