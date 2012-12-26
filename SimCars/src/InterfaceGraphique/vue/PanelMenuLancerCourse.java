package vue;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import config.Config;
import controleur.MouseListControl;

public class PanelMenuLancerCourse extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LblImage image;
	private JLabel lblBas;
	private int largeur;
	private int hauteur;
	private JPanel pnlCorps;
	
	public PanelMenuLancerCourse(String pathImage, String lblName, int largeur,
			int hauteur, JPanel pnlCorps) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.setPnlCorps(pnlCorps);

		this.addMouseListener(new MouseListControl(this, pnlCorps));
		
		
		image = new LblImage(pathImage, largeur, hauteur);

		lblBas = new JLabel(lblName, JLabel.CENTER);

		lblBas.setForeground(Color.white);

		lblBas.setFont(Config.font);

		this.setBorder(BorderFactory.createMatteBorder(3, 5, 5, 5, Color.black));
		// Couleur
		this.setBackground(Color.black);

		this.setLayout(new BorderLayout());
		this.add(image, BorderLayout.CENTER);
		this.add(lblBas, BorderLayout.SOUTH);
		
	}

	/*
	private PanelMenuLancerCourse copier() {
		PanelMenuLancerCourse tmp = new PanelMenuLancerCourse(this.getImage()
				.getPathImage(), this.getLblBas().getText(), this.getLargeur(),
				this.getHauteur());

		return tmp;
	}*/

	public JLabel getLblBas() {
		return this.lblBas;
	}

	public LblImage getImage() {
		return this.image;
	}
	public int getLargeur() {
		return this.largeur;
	}

	public int getHauteur() {
		return this.hauteur;
	}

	public JPanel getPnlCorps() {
		return pnlCorps;
	}

	public void setPnlCorps(JPanel pnlCorps) {
		this.pnlCorps = pnlCorps;
	}

	

	
}