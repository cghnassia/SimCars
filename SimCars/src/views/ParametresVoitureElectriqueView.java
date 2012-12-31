package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import models.VoitureElectrique;
import models.VoitureEssence;

public class ParametresVoitureElectriqueView extends ParametresVoitureView {
	
	protected VoitureElectrique voitureModel;
	
	protected JLabel labelJauge;
	protected int widthJauge;

	public ParametresVoitureElectriqueView(VoitureElectrique pVoitureModel, ImageIcon pImageJauge, ImageIcon pImageStandAllume, ImageIcon pImageStandEteint) {
		super();
		super.init();
		
		this.voitureModel = pVoitureModel;
		this.labelJauge = new JLabel(pImageJauge);
		
		this.imageStandAllume = pImageStandAllume;
		this.imageStandEteint = pImageStandEteint;
		this.labelStand = new JLabel(imageStandEteint);
		
		this.txtVitesse = new JTextField();
		this.txtNbTours = new JTextField();
		this.txtClassement = new JTextField();
		
		JPanel jaugePanel = new JPanel();
		jaugePanel.setLayout(null);
		jaugePanel.setPreferredSize(new Dimension(220, 50));
		jaugePanel.setBounds(0, 0, 220, 50);
		jaugePanel.add(labelJauge);
		
		labelJauge.setPreferredSize(new Dimension(200, 50));
		labelJauge.setBounds(10, 0, 200, 50);
		
		this.northPanel.setLayout(new BorderLayout());
		this.northPanel.add(jaugePanel, BorderLayout.CENTER);
		this.northPanel.add(this.labelStand, BorderLayout.EAST);
		
		this.southPanel.setLayout(new BorderLayout());
		this.southPanel.add(this.txtVitesse, BorderLayout.WEST);
		this.southPanel.add(this.txtNbTours, BorderLayout.CENTER);
		this.southPanel.add(this.txtClassement, BorderLayout.EAST);
		
		this.widthJauge = 200;
		
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Voiture electrique"));
		
		setVisible(true);
	}

	@Override
	public void update() {
			updateVitesse((int)(Math.round(voitureModel.getCVitesse())));
			updateStand(voitureModel.hasToFill(), voitureModel.isFilling());
			updateNbTours(voitureModel.getNbTours() + 1, voitureModel.getNbToursTotal());
			updateClassement(voitureModel.getClassement() + 1);
			updateJauge(voitureModel.getNiveauBatterie(), voitureModel.getNiveauBatterieMax());
	}
	
	public void updateJauge(double niveauReservoir, double niveauReservoirMax) {
		this.labelJauge.setBounds(10, 0 ,(int)(niveauReservoir * this.widthJauge / 100), this.labelJauge.getHeight());
	}
	
	public void updateVitesse(int vitesse) {
		this.txtVitesse.setText("Vitesse : " + vitesse);
	}
	
}
