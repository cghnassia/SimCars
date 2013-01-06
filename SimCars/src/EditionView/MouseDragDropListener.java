package EditionView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseDragDropListener implements MouseListener {
	
	protected int typeObjet;
	
	public MouseDragDropListener(int pTypeObjet) {
		super();
		this.typeObjet = pTypeObjet;
	}
	
	public int getTypeObjet() {
		return this.typeObjet;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("mouseClicked");
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		System.out.println("mousePressed");
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
