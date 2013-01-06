import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import models.ConfigCircuit;

import controllers.CourseController;
import controllers.EditionController;


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
		//CourseController courseController = new CourseController();
		EditionController editionController = new EditionController();
		try {
			Thread.currentThread().sleep(1000); //Sinon l'affichage ne se passe pas bien
		}
		catch (InterruptedException e ){
			//
		}
		JFrame frame = new JFrame();
		//frame.add(courseController.getCourseView());
		frame.add(editionController.getEditionView());
		frame.setSize(new Dimension(ConfigCircuit.WIDTH_CASE * ConfigCircuit.NB_CASES_WIDTH + 100, ConfigCircuit.WIDTH_CASE * ConfigCircuit.NB_CASES_HEIGHT));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                new Main();
            }

        });
    }

}
