package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import config.Config;
import controleur.MouseList;


public class PanelMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LblImage image;
	private JLabel lblBas;

	public PanelMenu(String pathImage, String lblName, int largeur, int hauteur) {
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.addMouseListener(new MouseList(this));
		image = new LblImage(pathImage, largeur, hauteur);
		
		lblBas = new JLabel(lblName, JLabel.CENTER);
		
		lblBas.setForeground(Color.blue);
		
		lblBas.setFont(Config.font);
		
		this.setBorder(BorderFactory
				.createMatteBorder(15, 5, 90, 5, Color.black ));
	
		
	
		
		// Couleur
		this.setBackground(Color.black);
		
		this.setLayout(new BorderLayout());
		this.add(image, BorderLayout.CENTER);
		this.add(lblBas, BorderLayout.SOUTH);
	}

	public JLabel getLblBas()
	{
		return this.lblBas;
	}
	
	
}
