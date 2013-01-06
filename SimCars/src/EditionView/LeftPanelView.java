package EditionView;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import models.TypeSegment;

public class LeftPanelView extends JPanel {
	
	protected EditionView editionView;
	
	protected ArrayList<JLabelItem> segmentsLabels;
	protected JLabelItem arriveeLabel;
	protected JLabelItem standLabel;
	
	protected JPanel topPanel;
	protected JPanel bottomPanel;
	
	protected DragMouseAdapter dragMouseAdapter;
	
	public LeftPanelView(EditionView pEditionView, ArrayList<JLabelItem> pSegmentsLabels, JLabelItem pArriveeLabel, JLabelItem pStandLabel) {
		this.editionView = pEditionView;
		
		this.segmentsLabels = pSegmentsLabels;
		this.arriveeLabel = pArriveeLabel;
		this.standLabel = pStandLabel;
		
		this.dragMouseAdapter = new DragMouseAdapter(this.editionView);
		
		this.topPanel = new JPanel(new GridLayout(8, 2));
		this.bottomPanel = new JPanel(new BorderLayout());
		
		initPanels();
		
		setLayout(new BorderLayout());
		add(this.topPanel, BorderLayout.NORTH);
		add(this.bottomPanel, BorderLayout.SOUTH);
	}
	
	public void initPanels() {
		int i;
		JLabelItem jlb;
		
		for(i = 1; i < this.segmentsLabels.size(); i++) {
			jlb = new JLabelItem(segmentsLabels.get(i).getType());
			jlb.setIcon(segmentsLabels.get(i).getIcon());
			jlb.setPreferredSize(segmentsLabels.get(i).getPreferredSize());
			jlb.setBounds(segmentsLabels.get(i).getBounds());
			jlb.setTransferHandler(new TransferHandler("type"));
			jlb.addMouseListener(dragMouseAdapter);
			this.topPanel.add(jlb);
		}
		
		this.topPanel.add(new JPanel());
		this.topPanel.add(new JPanel());
		
		jlb = new JLabelItem(segmentsLabels.get(0).getType());
		jlb.setIcon(segmentsLabels.get(0).getIcon());
		jlb.setPreferredSize(segmentsLabels.get(0).getPreferredSize());
		jlb.setBounds(segmentsLabels.get(0).getBounds());
		jlb.setTransferHandler(new TransferHandler("type"));
		jlb.addMouseListener(dragMouseAdapter);
		this.topPanel.add(jlb);
		
		jlb = new JLabelItem(this.standLabel.getType());
		jlb.setIcon(this.standLabel.getIcon());
		jlb.setPreferredSize(this.standLabel.getPreferredSize());
		jlb.setBounds(this.standLabel.getBounds());
		jlb.setTransferHandler(new TransferHandler("type"));
		jlb.addMouseListener(dragMouseAdapter);
		this.topPanel.add(jlb);
		
		jlb = new JLabelItem(this.arriveeLabel.getType());
		jlb.setIcon(this.arriveeLabel.getIcon());
		jlb.setPreferredSize(this.arriveeLabel.getPreferredSize());
		jlb.setBounds(this.arriveeLabel.getBounds());
		jlb.setTransferHandler(new TransferHandler("type"));
		jlb.addMouseListener(dragMouseAdapter);
		this.topPanel.add(jlb);
		
	}
}
