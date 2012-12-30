package vue;

import javax.swing.JFrame;
import config.Config;
import controleur.CtrlResultatCourse;


public class FenetrePrincipale extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FenetrePrincipale()
	{
		this.setTitle("SimCars");
		
		this.setSize(Config.largeurEcran, Config.hauteurEcran);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		/*
		JPanel pan = new JPanel();
		pan.setLayout(new BorderLayout());
		pan.setBackground(Color.white);
		pan.add(new PanelLancerUneCourse(), BorderLayout.NORTH);
		*/
		Config.setF(this);
		
		//this.setContentPane(new PanelPrincipale());
		this.setContentPane(new PanelResultatCourse(new CtrlResultatCourse()));
		
		this.setVisible(true);
	}
	

}
