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

import models.TypeMoteur;
import models.VoitureEssence;
import models.VoitureHybride;

public class ParametresVoitureHybrideView extends ParametresVoitureView {
	
	protected VoitureHybride voitureModel;
	
	protected ImageIcon imageMoteurEssence;
	protected ImageIcon imageMoteurElectrique;
	protected JLabel labelMoteur;
	
	protected JLabel labelJaugeEssence;
	protected JLabel labelJaugeElectricite;
	protected int widthJauge;

	public ParametresVoitureHybrideView(VoitureHybride pVoitureModel, ImageIcon pImageJaugeEssence, ImageIcon pImageJaugeElectricite, ImageIcon pImageStandAllume, ImageIcon pImageStandEteint) {
		super();
		super.init();
		
		this.widthJauge = 95;
		
		this.voitureModel = pVoitureModel;
		this.labelJaugeEssence = new JLabel(pImageJaugeEssence);
		this.labelJaugeElectricite = new JLabel(pImageJaugeElectricite);
		
		this.imageMoteurEssence = new ImageIcon(ChargementImage.chargerImage("img/moteur_essence.png"));
		this.imageMoteurElectrique = new ImageIcon(ChargementImage.chargerImage("img/moteur_electrique.png"));
		this.labelMoteur = new JLabel(imageMoteurElectrique);
		
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
		
		jaugePanel.add(labelJaugeEssence);
		jaugePanel.add(labelJaugeElectricite);
		
		labelJaugeEssence.setPreferredSize(new Dimension(widthJauge, 50));
		labelJaugeEssence.setBounds(10, 0, 95, 50);
		
		labelJaugeEssence.setPreferredSize(new Dimension(widthJauge, 50));
		labelJaugeElectricite.setBounds(110, 0, 95, 50);
		
		JPanel symbolsPanel = new JPanel();
		symbolsPanel.setLayout(new FlowLayout());
		
		symbolsPanel.add(labelMoteur);
		symbolsPanel.add(labelStand);
		
		this.northPanel.setLayout(new BorderLayout());
		this.northPanel.add(jaugePanel, BorderLayout.CENTER);
		this.northPanel.add(symbolsPanel, BorderLayout.EAST);
		
		this.southPanel.setLayout(new BorderLayout());
		this.southPanel.add(this.txtVitesse, BorderLayout.WEST);
		this.southPanel.add(this.txtNbTours, BorderLayout.CENTER);
		this.southPanel.add(this.txtClassement, BorderLayout.EAST);	
		
		//this.setPreferredSize(new Dimension (300, 50));
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Voiture hybride"));
		
		setVisible(true);
	}

	@Override
	public void update() {
			updateVitesse((int)(Math.round(voitureModel.getCVitesse())));
			updateStand(voitureModel.hasToFill(), voitureModel.isFilling());
			updateNbTours(voitureModel.getNbTours() + 1, voitureModel.getNbToursTotal());
			updateClassement(voitureModel.getClassement() + 1);
			updateJauge(voitureModel.getNiveauReservoir(), voitureModel.getNiveauBatterie(), voitureModel.getNiveauReservoirMax(), voitureModel.getNiveauBatterieMax());
			updateMoteur(voitureModel.getMoteur().getType());
	}
	
	public void updateJauge(double niveauReservoir, double niveauBatterie, double niveauReservoirMax, double niveauBatterieMax) {
		this.labelJaugeEssence.setBounds(10, 0 ,(int)(niveauReservoir * this.widthJauge / 50), this.labelJaugeEssence.getHeight());
		this.labelJaugeElectricite.setBounds(110, 0 ,(int)(niveauBatterie * this.widthJauge / 50), this.labelJaugeElectricite.getHeight());

	}
	
	public void updateMoteur(TypeMoteur moteur) {
		if(moteur == TypeMoteur.TYPE_ESSENCE) {
			this.labelMoteur.removeAll();
			this.labelMoteur.setIcon(imageMoteurEssence);
		}
		else {
			this.labelMoteur.removeAll();
			this.labelMoteur.setIcon(imageMoteurElectrique);
		}
	}

}
