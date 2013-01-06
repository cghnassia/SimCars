package EditionView;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.dnd.DropTarget;
import java.util.ArrayList;
import java.util.TooManyListenersException;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import models.ConfigCircuit;
import models.TypeItem;


public class CircuitPanelView extends JPanel {
	
	protected EditionView editionView;
	
	protected JLayeredPane[][] paneHolder;
	
	protected ArrayList<JLabelItem> segmentsLabels;
	protected ArrayList<JLabelItem> arriveesLabels;
	protected JLabelItem standLabel;

	public CircuitPanelView(EditionView pEditionView, ArrayList<JLabelItem> pSegmentsLabels, ArrayList<JLabelItem> pArriveesLabels, JLabelItem pStandLabel) {
		
		this.editionView = pEditionView;
		
		this.segmentsLabels = pSegmentsLabels;
		this.arriveesLabels = pArriveesLabels;
		this.standLabel = pStandLabel;
		this.paneHolder = new JLayeredPane[ConfigCircuit.NB_CASES_HEIGHT][ConfigCircuit.NB_CASES_WIDTH];

		setLayout(new GridLayout(ConfigCircuit.NB_CASES_HEIGHT, ConfigCircuit.NB_CASES_WIDTH));
		
		init();
		
		//setPreferredSize(new Dimension(ConfigCircuit.NB_CASES_WIDTH * ConfigCircuit.LONGUEUR_SEGMENT, ConfigCircuit.NB_CASES_HEIGHT * ConfigCircuit.LONGUEUR_SEGMENT));
		
	}
	
	public void init() {
		JLabel jlb;
		DropTarget dropTarget;
		
		for(int i = 0; i < ConfigCircuit.NB_CASES_HEIGHT; i++) {
			for(int j = 0; j < ConfigCircuit.NB_CASES_WIDTH; j++) {
				jlb = new JLabel(segmentsLabels.get(0).getIcon());
				jlb.setPreferredSize(segmentsLabels.get(0).getPreferredSize());
				jlb.setBounds(segmentsLabels.get(0).getBounds());
				this.paneHolder[i][j] = new JLayeredPane();
				this.paneHolder[i][j].add(jlb, 2);
				
				try {
					dropTarget = new DropTarget();
					dropTarget.addDropTargetListener(new DropTargetListener(this));
					this.paneHolder[i][j].setDropTarget(dropTarget);
				}
				catch (TooManyListenersException e) {
					e.printStackTrace();
				}
				
				add(this.paneHolder[i][j]);
			}
		}
	}
	
	public void drop(JLayeredPane jlp) {
		int ligne = -1;
		int colonne = -1;
		
		for(int i = 0; i < ConfigCircuit.NB_CASES_HEIGHT; i++) {
			for(int j = 0; j < ConfigCircuit.NB_CASES_WIDTH; j++) {
				if(jlp == this.paneHolder[i][j]) {
					ligne = i;
					colonne = j;
					break;
				}
			}
		}
		
		this.editionView.itemDropped(ligne, colonne);
	}
	
	public void addItem(int type, int ligne, int colonne) {
		//System.out.println("type : " + type);
		
		JLabelItem jlbItem;
		int z;
		
		if(type < 9) {	//segments
			jlbItem = segmentsLabels.get(type);
			this.paneHolder[ligne][colonne].removeAll();
			z = 2;
		}
		else if(type < 11) {	//arrivees
			jlbItem = arriveesLabels.get(type - 9);
			z = 0;
		}
		else {		//stand
			jlbItem = standLabel;
			z = 1;
		}
		
		JLabel jlb = new JLabel(jlbItem.getIcon());
		jlb.setPreferredSize(jlbItem.getPreferredSize());
		jlb.setBounds(jlbItem.getBounds());
		this.paneHolder[ligne][colonne].add(jlb, 0);
		this.paneHolder[ligne][colonne].repaint();
		
		//System.out.println(this.paneHolder[ligne][colonne].getComponentCount());
	}
}
