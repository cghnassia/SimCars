package EditionView;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;

import javax.swing.JLayeredPane;

public class DropTargetListener implements java.awt.dnd.DropTargetListener {
	
	protected CircuitPanelView circuitPanelView;

	public DropTargetListener(CircuitPanelView pCircuitPanelView) {
		super();
		this.circuitPanelView = pCircuitPanelView;
	}

	public void dropActionChanged(DropTargetDragEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void drop(DropTargetDropEvent e) {
		DropTarget dt = (DropTarget) e.getSource();
		this.circuitPanelView.drop((JLayeredPane) (dt.getComponent()));
	}
	
	public void dragOver(DropTargetDragEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void dragExit(DropTargetEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void dragEnter(DropTargetDragEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
