import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


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
