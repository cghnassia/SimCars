package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import config.Config;
import controleur.CtrlResultatCourse;


public class PanelResultatCourse extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CtrlResultatCourse ctrlSimu;
	
	public PanelResultatCourse(CtrlResultatCourse ctrlSimu)
	{
		this.setCtrlResultatCourse(ctrlSimu);
		this.setBackground(Color.white);
		this.setLayout(new BorderLayout());
		
		// Resulat sur le Podium
		JPanel pnlRes = new JPanel();
		pnlRes.setBackground(Color.white);
		pnlRes.setLayout(new BorderLayout());
		
		JPanel pnlCorps = new JPanel();
		pnlCorps.setLayout(null);
		pnlCorps.setBackground(Color.white);
		
		LblImage imageRes = new LblImage("Data/Images/podium2.jpg", 500, 200);
		JLabel lblPosUn = new JLabel("Voiture à moteur essence");
		JLabel lblPosDeux = new JLabel("Voiture à moteur hybride");
		JLabel lblPosTrois = new JLabel("Voiture à moteur éléctrique");
		
		
		//x, y, width, height
		imageRes.setBounds (350,250,500,200);
		
		lblPosUn.setBounds(520, 145, 200, 200);
		lblPosDeux.setBounds(360, 200, 200, 200);
		lblPosTrois.setBounds(680, 230, 200, 200);
		
		
		//Résultat en s
		JLabel lblUn = new JLabel("Voiture à moteur essence : 120 s");
		JLabel lblDeux = new JLabel("Voiture à moteur hybride : 100 s");
		JLabel lblTrois = new JLabel("Voiture à moteur éléctrique : 98 s");
		
		JPanel pnlCorps2 = new JPanel();
		// 3 lignes et 1 colonne
		pnlCorps2.setLayout(new GridLayout(3, 1));
		pnlCorps2.setBackground(Color.white);
		
		pnlCorps2.add(lblUn);
		pnlCorps2.add(lblDeux);
		pnlCorps2.add(lblTrois);
		
		JPanel pnlReEnS = new JPanel();
		pnlReEnS.setLayout(new BorderLayout());
		pnlReEnS.add(pnlCorps2, BorderLayout.NORTH);
		pnlReEnS.setBackground(Color.white);
		
		
		pnlCorps.add(lblPosUn);
		pnlCorps.add(lblPosDeux);
		pnlCorps.add(lblPosTrois);
		pnlCorps.add(imageRes);
		
		pnlRes.add(pnlCorps, BorderLayout.CENTER);
		
		
		
		
		// Footer
		JPanel pnlFooter = new JPanel();
		pnlFooter.setLayout(new BorderLayout());
		JButton btnQuitter = new JButton("Retour vers la liste des courses");
		btnQuitter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JButton btnEnregistrerResultat = new JButton("Enregistrer le résultat");
		btnEnregistrerResultat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnQuitter.setActionCommand(Config.ACTION_QUITTER_SIMU);
		btnQuitter.addActionListener(this.getCtrlResultatCourse());
		
		
		btnEnregistrerResultat.setActionCommand(Config.ACTION_ENREGISTRER_RES);
		btnEnregistrerResultat.addActionListener(this.getCtrlResultatCourse());
		
		JPanel tmp = new JPanel();
		tmp.setLayout(new GridLayout(1, 2));
		
		tmp.add(btnQuitter);
		tmp.add(btnEnregistrerResultat);
		
		pnlFooter.setBorder(BorderFactory
				.createMatteBorder(15, 5, 10, 5, Color.white));
		pnlFooter.add(tmp, BorderLayout.EAST);
		//pnlFooter.add(btnEnregistrerResultat, BorderLayout.NORTH);
		
		pnlFooter.setBackground(Color.white);
		
		
		this.add(pnlRes, BorderLayout.CENTER);
		this.add(pnlReEnS, BorderLayout.WEST);
		this.add(pnlFooter, BorderLayout.SOUTH);
	}

	public CtrlResultatCourse getCtrlResultatCourse() {
		return ctrlSimu;
	}

	public void setCtrlResultatCourse(CtrlResultatCourse ctrlSimu) {
		this.ctrlSimu = ctrlSimu;
	}
}
