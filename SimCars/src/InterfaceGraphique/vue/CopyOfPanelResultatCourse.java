package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import config.Config;
import controleur.CtrlResultatCourse;


public class CopyOfPanelResultatCourse extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CtrlResultatCourse ctrlSimu;
	
	public CopyOfPanelResultatCourse(CtrlResultatCourse ctrlSimu)
	{
		this.setCtrlResultatCourse(ctrlSimu);
		this.setBackground(Color.yellow);
		this.setLayout(new BorderLayout());
		
		// Corps
		JPanel pnlCorps = new JPanel();
		pnlCorps.setLayout(new BorderLayout());
		LblImage imageRes = new LblImage("Data/Images/podium.jpg", 400, 400);
		pnlCorps.add(imageRes, BorderLayout.CENTER);
		
		
		// Footer
		JPanel pnlFooter = new JPanel();
		pnlFooter.setLayout(new BorderLayout());
		JButton btnQuitter = new JButton("Retour vers la liste des courses");
		btnQuitter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnQuitter.setActionCommand(Config.ACTION_QUITTER_SIMU);
		btnQuitter.addActionListener(this.getCtrlResultatCourse());
		
		pnlFooter.setBorder(BorderFactory
				.createMatteBorder(15, 5, 10, 5, Color.white));
		pnlFooter.add(btnQuitter, BorderLayout.EAST);
		pnlFooter.setBackground(Color.white);
		
		
		this.add(pnlCorps, BorderLayout.NORTH);
		this.add(pnlFooter, BorderLayout.SOUTH);
	}

	public CtrlResultatCourse getCtrlResultatCourse() {
		return ctrlSimu;
	}

	public void setCtrlResultatCourse(CtrlResultatCourse ctrlSimu) {
		this.ctrlSimu = ctrlSimu;
	}
}
