import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import models.ConfigCircuit;

import controllers.CourseController;


public class Main {

	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		CourseController courseController = new CourseController();
		JFrame frame = new JFrame();
		frame.add(courseController.getCourseView());
		frame.setSize(new Dimension(ConfigCircuit.WIDTH_CASE * ConfigCircuit.NB_CASES_WIDTH + 300, ConfigCircuit.WIDTH_CASE * ConfigCircuit.NB_CASES_HEIGHT));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		courseController.demarrer();
		
	}
	*/
	public Main() {
		CourseController courseController = new CourseController();
		JFrame frame = new JFrame();
		frame.add(courseController.getCourseView());
		frame.setSize(new Dimension(ConfigCircuit.WIDTH_CASE * ConfigCircuit.NB_CASES_WIDTH + 300, ConfigCircuit.WIDTH_CASE * ConfigCircuit.NB_CASES_HEIGHT));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		courseController.demarrer();
	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                new Main();
            }

        });
    }

}
