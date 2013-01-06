package EditionView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.EditionController;

import models.ConfigCircuit;
import models.TypeItem;
import models.TypeSegment;

public class EditionView extends JPanel {
	
	protected EditionController editionController;
	
	protected LeftPanelView leftPanelView;
	protected CircuitPanelView circuitPanelView;
	protected BottomPanelView bottomPanelView;
	
	protected ArrayList<JLabelItem> segmentsLabels;
	protected ArrayList<JLabelItem> arriveesLabels;
	protected JLabelItem standLabel;
	
	protected int typeObjetSelected;
	
	public EditionView(EditionController pEditionController) {
		
		this.editionController = pEditionController;
		this.segmentsLabels = new ArrayList<JLabelItem>();
		this.arriveesLabels = new ArrayList<JLabelItem>();
		
		initLabels();
		
		this.leftPanelView = new LeftPanelView(this, segmentsLabels, arriveesLabels.get(1), standLabel);
		this.circuitPanelView = new CircuitPanelView(this, segmentsLabels, arriveesLabels, standLabel);
		this.bottomPanelView = new BottomPanelView();
		
		setLayout(new BorderLayout());
		add(this.leftPanelView, BorderLayout.WEST);
		add(this.circuitPanelView, BorderLayout.CENTER);
		add(this.bottomPanelView, BorderLayout.SOUTH);
	}
	
	public void initLabels() {
		JLabelItem jlb;
		
		String[] imagesSegments = new String[9];
		imagesSegments[TypeItem.SEGMENT_NONE] = ConfigCircuit.IMAGE_NONE;
		imagesSegments[TypeItem.SEGMENT_STRAIGHT_HORIZONTAL] = ConfigCircuit.IMAGE_STRAIGHT_HORIZONTAL;
		imagesSegments[TypeItem.SEGMENT_STRAIGHT_VERTICAL] = ConfigCircuit.IMAGE_STRAIGHT_VERTICAL;
		imagesSegments[TypeItem.SEGMENT_TOP_RIGHT] = ConfigCircuit.IMAGE_TURN_TOP_TO_RIGHT;
		imagesSegments[TypeItem.SEGMENT_TOP_LEFT] = ConfigCircuit.IMAGE_TURN_TOP_TO_LEFT;
		imagesSegments[TypeItem.SEGMENT_BOTTOM_RIGHT] = ConfigCircuit.IMAGE_TURN_BOTTOM_TO_RIGHT;
		imagesSegments[TypeItem.SEGMENT_BOTTOM_LEFT] = ConfigCircuit.IMAGE_TURN_BOTTOM_TO_LEFT;
		imagesSegments[TypeItem.SEGMENT_HARD_HORIZONTAL] = ConfigCircuit.IMAGE_HARD_HORIZONTAL;;
		imagesSegments[TypeItem.SEGMENT_HARD_VERTICAL] = ConfigCircuit.IMAGE_HARD_VERTICAL;
		
		for(int i = 0; i < imagesSegments.length; i++) {
			jlb = new JLabelItem(i);
			jlb.setIcon(new ImageIcon(imagesSegments[i]));
			jlb.setBounds(0, 0, 50, 50);
			jlb.setPreferredSize(new Dimension(50, 50));
			segmentsLabels.add(jlb);
		}
		
		jlb = new JLabelItem(9);
		jlb.setIcon(new ImageIcon(ConfigCircuit.IMAGE_ARRIVEE_HORIZONTAL));
		jlb.setBounds(46, 11, 4, 28);
		jlb.setPreferredSize(new Dimension(4, 18));
		arriveesLabels.add(jlb);
		
		jlb = new JLabelItem(10);
		jlb.setIcon(new ImageIcon(ConfigCircuit.IMAGE_ARRIVEE_VERTICAL));
		jlb.setBounds(11, 0, 28, 4);
		jlb.setPreferredSize(new Dimension(28, 4));
		arriveesLabels.add(jlb);
		
		standLabel = new JLabelItem(11);
		standLabel.setIcon(new ImageIcon(ConfigCircuit.IMAGE_STAND));
		standLabel.setBounds(0, 0, 50, 50);
		standLabel.setPreferredSize(new Dimension(50, 50));
	}
	
	public int getTypeObjetSelected() {
		return this.typeObjetSelected;
	}
	
	public void setTypeObjetSelected(int pTypeObjetSelected) {
		this.typeObjetSelected = pTypeObjetSelected;
	}
	
	public void itemDropped(int ligne, int colonne) {
		this.editionController.dropItem(this.typeObjetSelected, ligne, colonne);
	}
	
	public void addItem(int type, int ligne, int colonne) {
		
		int realType = type;
		
		if(type == TypeItem.ARRIVEE_HORIZONTAL || type == TypeItem.ARRIVEE_VERTICAL) {
			realType = this.editionController.getEditionModel().getArriveeDirection();
		}
			
		this.circuitPanelView.addItem(realType, ligne, colonne);
	}
}
