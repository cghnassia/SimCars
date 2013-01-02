package views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import controllers.CourseController;

import models.ConfigCircuit;
import models.ConfigGlobal;

public class CourseView extends JPanel implements Runnable {
	
	protected boolean continuer;
	protected CourseController courseController;
	protected CarteView carteView;
	protected ParametresView parametresView;
	
	public CourseView(CourseController pCourseController) {
		super(new BorderLayout());
		
		this.courseController = pCourseController;
		
		this.carteView = new CarteView(this.courseController);
		this.parametresView = new ParametresView(this.courseController);
		
		add(this.carteView, BorderLayout.CENTER);
		add(this.parametresView, BorderLayout.EAST);
		
		/*this.setPreferredSize(new Dimension(ConfigCircuit.NB_CASES_WIDTH * ConfigCircuit.WIDTH_CASE + 300, ConfigCircuit.NB_CASES_HEIGHT * ConfigCircuit.WIDTH_CASE));
		this.setBounds(0, 0, getWidth(), getHeight());
		this.repaint();
		this.carteView.repaint();
		this.parametresView.repaint();*/
	}
	
	public void run() {
		this.carteView.init();
		this.parametresView.init();
		this.continuer = true;
		
		while(this.continuer) {
			try {
				Thread.currentThread().sleep((int) (ConfigGlobal.FPS_RATE * 1000));
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			carteView.update();
			parametresView.update();
		}
	}
	
	public ParametresView getParametresView() {
		return this.parametresView;
	}
	
	public void setVitesseSimulation(int value) {
		this.parametresView.getCourseControlesView().setVitesseSimulation(value);
	}
}
