package vue;

import java.awt.GridLayout;
import javax.swing.JPanel;
import config.Config;

public class PanelPrincipale extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanelPrincipale() {

		this.setBackground(Config.couleurDeFond);

		PanelMenu pnlSaison = new PanelMenu("Data/Images/LancerSaison.jpg",
				"Lancer une saison", 250, 180);

		PanelMenu pnlCourse = new PanelMenu("Data/Images/LancerCourse.jpg",
				"Lancer une course", 350, 180);

		PanelMenu pnlEditer = new PanelMenu("Data/Images/Editeur.png",
				"Editeur", 100, 100);

		PanelMenu pnlInfoVoitures = new PanelMenu(
				"Data/Images/InfoVoitures.jpg",
				"Informations sur les voitures", 250, 180);

		PanelMenu pnlInfoLogiciel = new PanelMenu(
				"Data/Images/InfoLogiciel.png", "Informations sur le logiciel",
				180, 180);

		// 2 ligne et 3 colonnes
		this.setLayout(new GridLayout(2, 3));
		
		pnlSaison.putClientProperty("ID", Config.ACTION_LANCERSAISON);
		pnlCourse.putClientProperty("ID", Config.ACTION_LANCERUNECOURSE);
		pnlEditer.putClientProperty("ID", Config.ACTION_EDITER);
		pnlInfoVoitures.putClientProperty("ID", Config.ACTION_INFOVOITURE);
		pnlInfoLogiciel.putClientProperty("ID", Config.ACTION_INFOLOGICIEL);
		
		
		this.add(pnlSaison);
		this.add(pnlCourse);
		this.add(pnlEditer);
		this.add(pnlInfoVoitures);
		this.add(pnlInfoLogiciel);

	}

}
