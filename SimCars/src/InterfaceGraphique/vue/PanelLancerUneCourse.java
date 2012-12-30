package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import config.Config;
import controleur.CtrlLancerCourse;

public class PanelLancerUneCourse extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnlCorps;

	
	public PanelLancerUneCourse(CtrlLancerCourse controleur) {

		this.setBackground(Color.white);

		// Corps
		pnlCorps = new JPanel();

		pnlCorps.setBorder(BorderFactory
				.createTitledBorder("Détails du circuit"));
		pnlCorps.setLayout(new GridLayout(2, 1));
		pnlCorps.setBackground(Color.white);

		
		pnlCorps.setPreferredSize(new Dimension(950, 500));

		JPanel pnllisteDeCircuit = new JPanel();

		ArrayList<PanelMenuLancerCourse> listCircuit = new ArrayList<PanelMenuLancerCourse>();

		PanelMenuLancerCourse c1 = new PanelMenuLancerCourse(
				"Data/Images/Circuit/circuit_de_monaco.jpg", "Circuit 1", 200,
				150, pnlCorps);

		PanelMenuLancerCourse c2 = new PanelMenuLancerCourse(
				"Data/Images/Circuit/Circuit_Rouen.jpg", "Circuit 2", 200, 150, pnlCorps);
		PanelMenuLancerCourse c3 = new PanelMenuLancerCourse(
				"Data/Images/Circuit/Circuit_Rouen.jpg", "Circuit 3", 200, 150, pnlCorps);
		PanelMenuLancerCourse c4 = new PanelMenuLancerCourse(
				"Data/Images/Circuit/Circuit_Rouen.jpg", "Circuit 4", 200, 150, pnlCorps);
		PanelMenuLancerCourse c5 = new PanelMenuLancerCourse(
				"Data/Images/Circuit/Circuit_Rouen.jpg", "Circuit 5", 200, 150, pnlCorps);

		JScrollPane scrollPane = new JScrollPane(pnllisteDeCircuit);
		scrollPane.setPreferredSize(new Dimension(230, 620));

		listCircuit.add(c1);
		listCircuit.add(c2);
		listCircuit.add(c3);
		listCircuit.add(c4);
		listCircuit.add(c5);

		// nbCircuit lignes et 1 colonne
		pnllisteDeCircuit.setLayout(new GridLayout(listCircuit.size(), 1));
		for (PanelMenuLancerCourse c : listCircuit) {
			pnllisteDeCircuit.add(c);

		}
		// Footer
		JPanel pnlFooter = new JPanel();
		pnlFooter.setBackground(Color.white);
		JPanel tmp = new JPanel();
		JButton btnRetour = new JButton("Retour");
		JButton btnLancerSimu = new JButton("Lancer la simulation");
		//btnLancerSimu.setEnabled(false);
		tmp.setLayout(new GridLayout(1, 2));
		tmp.add(btnRetour);
		tmp.add(btnLancerSimu);

		btnRetour.setActionCommand(Config.ACTION_RETOURPRINCIPALE);
		btnLancerSimu.setActionCommand(Config.ACTION_LANCERSIMUCOURSE);

		btnRetour.addActionListener(controleur);
		btnLancerSimu.addActionListener(controleur);
		
		btnLancerSimu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnlFooter.setBorder(BorderFactory.createMatteBorder(15, 5, 10, 5,
				Color.white));

		pnlFooter.setLayout(new BorderLayout());
		pnlFooter.add(tmp, BorderLayout.EAST);

		this.setLayout(new BorderLayout());
		// this.add(pnllisteDeCircuit, BorderLayout.WEST);
		this.add(scrollPane, BorderLayout.WEST);
		this.add(pnlCorps, BorderLayout.EAST);
		this.add(pnlFooter, BorderLayout.SOUTH);
	}

	public JPanel getDetailsCircuit() {

		JPanel pnlCorps = new JPanel();

		pnlCorps.setLayout(new BorderLayout());
		LblImage image = new LblImage(
				"Data/Images/Circuit/circuit_de_monaco.jpg", 150, 150);
		pnlCorps.add(image, BorderLayout.WEST);

		return pnlCorps;
	}

	public void setDetails(JPanel pn) {
		// pnlCorps.removeAll();
		pnlCorps.add(pn);
		// pnlCorps.revalidate();
	}

	public JPanel getPnlCorps() {
		return pnlCorps;
	}

	public void setPnlCorps(JPanel pnlCorps) {
		this.pnlCorps = pnlCorps;
	}
	
	
}
