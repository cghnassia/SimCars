package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.PanelPrincipale;
import vue.PanelResultatCourse;

import config.Config;

public class CtrlLancerCourse implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand() == Config.ACTION_RETOURPRINCIPALE)
		{
			Config.fp.setContentPane(new PanelPrincipale());
			Config.fp.revalidate();
		}
		else if (e.getActionCommand() == Config.ACTION_LANCERSIMUCOURSE)
		{
			Config.fp.setContentPane(new PanelResultatCourse(new CtrlResultatCourse()));
			Config.fp.revalidate();
		}
	}

}
