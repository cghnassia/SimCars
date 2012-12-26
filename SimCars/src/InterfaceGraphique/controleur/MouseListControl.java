package controleur;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vue.LblImage;
import vue.PanelMenuLancerCourse;


public class MouseListControl implements MouseListener {

	private PanelMenuLancerCourse pnl;
	private JPanel pnlCorps;
	/**
	 * 
	 * @param pnl panel du menu 
	 * @param pnlCorps panel qu'on va changer lorsqu'on clique sur le menu
	 */
	public MouseListControl(PanelMenuLancerCourse pnl, JPanel pnlCorps) {
		this.setPnl(pnl);
		this.setPnlCorps(pnlCorps);
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		
		this.getPnlCorps().removeAll();
		
		// Details du cicuit
		
		JPanel pnlInfoGeneralDuCircuit = new JPanel();
		pnlInfoGeneralDuCircuit.setBackground(Color.white);
		pnlInfoGeneralDuCircuit.setLayout(new GridLayout(1, 2));
		
		JPanel pnlDetails = new JPanel();
		pnlDetails.setBackground(Color.white);
		//LblImage imageDuCircuit = this.getPnl().getImage();
		String details = "<html><body>";
		details += "<b>Nombre de tours : </b>2 <br />";
		details += "<b>Niveau de complexité : </b>5 <br />";
		details +="</body></html>";
		JLabel lblDetails = new JLabel(details);
		pnlDetails.setBorder(BorderFactory
				.createTitledBorder("Détails"));
		
		pnlDetails.add(lblDetails);
		pnlInfoGeneralDuCircuit.setBorder(BorderFactory
				.createTitledBorder(this.getPnl().getLblBas().getText()));
		
		//pnlInfoGeneralDuCircui.add(imageDuCircuit);
		pnlInfoGeneralDuCircuit.add(this.getImageFromPnl());
		pnlInfoGeneralDuCircuit.add(pnlDetails);
		
		
		// Déscitpion
		JPanel pnlDescription = new JPanel();
		pnlDescription.setBackground(Color.WHITE);
		pnlDescription.setBorder(BorderFactory
				.createTitledBorder("Déscription"));
		String description = "<html><body>";
		description += "Un circuit idéal pour simuler les voitures à moteur essence et voir leurs compétences";
		description +="</body></html>";
		JLabel lblDescription = new JLabel(description);
		pnlDescription.add(lblDescription);
		
		// Corps 
		this.getPnlCorps().add(pnlInfoGeneralDuCircuit);
		this.getPnlCorps().add(pnlDescription);
		this.getPnlCorps().revalidate();
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.getPnl().setBackground(Color.LIGHT_GRAY);
		this.getPnl().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.getPnl().getLblBas().setForeground(Color.black);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.getPnl().setBackground(Color.black);
		this.getPnl().setCursor(
				Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		this.getPnl().getLblBas().setForeground(Color.white);

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public PanelMenuLancerCourse getPnl() {
		return pnl;
	}

	public void setPnl(PanelMenuLancerCourse pnl) {
		this.pnl = pnl;
	}

	public JPanel getPnlCorps() {
		return pnlCorps;
	}

	public void setPnlCorps(JPanel pnlCorps) {
		this.pnlCorps = pnlCorps;
	}
	private LblImage getImageFromPnl()
	{
		return new LblImage(this.getPnl().getImage().getPathImage(), 400, 300);
	}
}
