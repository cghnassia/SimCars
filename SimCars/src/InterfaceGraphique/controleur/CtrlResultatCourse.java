package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import vue.PanelLancerUneCourse;

import config.Config;

public class CtrlResultatCourse implements ActionListener {

	/*
	private PanelResultatCourse pnl;
	public CtrlResultatCourse(PanelResultatCourse pnlResultat)
	{
		
	}
	*/
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand() == Config.ACTION_QUITTER_SIMU)
		{
			Config.fp.setContentPane(new PanelLancerUneCourse(new CtrlLancerCourse()));
			Config.fp.revalidate();
		}
		else if (arg0.getActionCommand() == Config.ACTION_ENREGISTRER_RES)
		{
			enregistrerLeResultat();
		}
	}
	
	private void enregistrerLeResultat()
	{
		File rep = new File("Resultat");
		if (!rep.exists())
		{
			rep.mkdir();
		}
		FileWriter fw;
		try {
			fw = new FileWriter("Resultat/Res.txt");
			fw.write("Hello ");
			fw.write("World");
			fw.close();
			showMessageBoxInfo("Le résultat a été enregistré sous le nom : Res.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void showMessageBoxInfo(final String message)
	{
		Thread t = new Thread(new Runnable()
		{
			public void run()
			{
				JOptionPane.showMessageDialog(null, message, "Message informatif", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		t.start();
	}
	/*
	public PanelResultatCourse getPnl() {
		return pnl;
	}
	public void setPnl(PanelResultatCourse pnl) {
		this.pnl = pnl;
	}
	*/

}
