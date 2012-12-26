package controleur;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import config.Config;
import vue.PanelLancerUneCourse;
import vue.PanelMenu;
import vue.infoVoiture.PnlInfoVoiture;

public class MouseList implements MouseListener {

	private PanelMenu pnl;
	
	public MouseList(PanelMenu pnl) {
		this.setPnl(pnl);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		// TODO Auto-generated method stub
		JPanel pnl = (JPanel) arg0.getSource();
        System.out.println("clicked ID " + pnl.getClientProperty("ID"));
                
        if ((int)pnl.getClientProperty("ID") == Config.ACTION_LANCERUNECOURSE)
        {
        	CtrlLancerCourse controleur = new CtrlLancerCourse();
    		Config.fp.setContentPane(new PanelLancerUneCourse(controleur));
    	}
        else if ((int)pnl.getClientProperty("ID") == Config.ACTION_INFOVOITURE)
        {
        	CtrlLancerCourse controleur = new CtrlLancerCourse();
    		PnlInfoVoiture pnlInfoVoiture = new PnlInfoVoiture(controleur);
    		Config.fp.setContentPane(pnlInfoVoiture);
        }
        	
    	Config.fp.revalidate();
    }


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.getPnl().getLblBas().setForeground(Color.red);

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.getPnl().getLblBas().setForeground(Color.blue);
	
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public PanelMenu getPnl() {
		return pnl;
	}

	public void setPnl(PanelMenu pnl) {
		this.pnl = pnl;
	}

}
