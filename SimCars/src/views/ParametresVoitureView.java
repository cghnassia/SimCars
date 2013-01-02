package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class ParametresVoitureView extends JPanel {
	
	protected JPanel northPanel;
	protected JPanel southPanel;
	
	protected JLabel labelStand;
	protected ImageIcon imageStandAllume;
	protected ImageIcon imageStandEteint;
	
	protected JTextField txtVitesse;
	protected JTextField txtNbTours;
	protected JTextField txtClassement;
	
	public void init() {
		this.northPanel = new JPanel();
		this.southPanel = new JPanel();
		
		setLayout(new GridLayout(2, 1));
		add(this.northPanel);
		add(this.southPanel);
		
		this.txtVitesse = new JTextField();
		this.txtNbTours = new JTextField();
		this.txtClassement = new JTextField();
		
		this.txtVitesse.setEditable(false);
		this.txtNbTours.setEditable(false);
		this.txtClassement.setEditable(false);		
		//this.northPanel.setPreferredSize(new Dimension(300, 20));
		//this.southPanel.setPreferredSize(new Dimension(300, 20));
		
		this.setPreferredSize(new Dimension(315, 50));
	}
	
	public abstract void update();
	
	public void updateStand(boolean hasToFill, boolean isFilling) {
		if(isFilling) {
			/*this.labelStand.removeAll();
			Date date = new Date();
			if(date.getTime() % 1000 == 0) {
				if(this.labelJauge.getIcon() == this.imageStandAllume) {
					this.labelStand.setIcon(this.imageStandAllume);
				}
				else {
					this.labelStand.setIcon(this.imageStandEteint);
				}
			}*/
		}
		else if(hasToFill) {
			this.labelStand.removeAll();
			this.labelStand.setIcon(this.imageStandAllume);
		}
		else {
			this.labelStand.removeAll();
			this.labelStand.setIcon(this.imageStandEteint);
		}
	}
	
	public void updateNbTours(int nbTours, int nbToursTotal) {
		if(nbTours > nbToursTotal) {
			this.txtNbTours.setText("Terminé");
		}
		else {
			this.txtNbTours.setText("Tour : " + nbTours + "/" + nbToursTotal);
		}
	}
	
	public void updateClassement(int classement) {
		this.txtClassement.setText("Position : " + classement + "/3");
	}
	
	public void updateVitesse(int vitesse) {
		this.txtVitesse.setText("Vitesse : " + vitesse + " ");
	}

}
