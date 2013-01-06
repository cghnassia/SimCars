package EditionView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

public class DragMouseAdapter extends MouseAdapter {
	
	 protected EditionView editionView;
	
	 public DragMouseAdapter(EditionView pEditionView) {
		 this.editionView = pEditionView;
	  }
	 
	  public void mousePressed(MouseEvent e) {
	        JComponent c = (JComponent) e.getSource();
	        TransferHandler handler = c.getTransferHandler();
	        handler.exportAsDrag(c, e, TransferHandler.COPY);
	        
	        JLabelItem source = (JLabelItem) e.getSource();
	        this.editionView.setTypeObjetSelected(source.getType());
	    }
}
