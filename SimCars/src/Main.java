import java.awt.Dimension;

import javax.swing.JFrame;

import models.ConfigCircuit;

import controllers.CourseController;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CourseController courseController = new CourseController();
		JFrame frame = new JFrame();
		frame.add(courseController.getCourseView());
		frame.setVisible(true);
		frame.setSize(new Dimension(ConfigCircuit.WIDTH_CASE * ConfigCircuit.NB_CASES_WIDTH, ConfigCircuit.WIDTH_CASE * ConfigCircuit.NB_CASES_HEIGHT));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		courseController.demarrer();
	}

}
