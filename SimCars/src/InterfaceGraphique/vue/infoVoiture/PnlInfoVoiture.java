package vue.infoVoiture;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import config.Config;
import controleur.CtrlLancerCourse;

public class PnlInfoVoiture extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PnlInfoVoiture(CtrlLancerCourse controleur) {
		JPanel pnlPrincipale = new JPanel();
		pnlPrincipale.setBackground(Color.red);

		// Footer
		JPanel pnlFooter = new JPanel();
		pnlFooter.setBackground(Color.white);
		JPanel tmp = new JPanel();
		JButton btnRetour = new JButton("Retour");
		JButton btnLancerSimu = new JButton("Lancer la simulation");
		
		
		tmp.setLayout(new GridLayout(1, 2));
		tmp.add(btnRetour);
		tmp.add(btnLancerSimu);

		btnRetour.setActionCommand(Config.ACTION_RETOURPRINCIPALE);
		btnLancerSimu.setActionCommand(Config.ACTION_LANCERSIMUCOURSE);

		btnRetour.addActionListener(controleur);
		btnLancerSimu.addActionListener(controleur);

		pnlFooter.setBorder(BorderFactory.createMatteBorder(15, 5, 10, 5,
				Color.white));

		pnlFooter.setLayout(new BorderLayout());
		pnlFooter.add(tmp, BorderLayout.EAST);
		
		pnlPrincipale.add(pnlFooter);
		this.add(pnlPrincipale);

	}
}
