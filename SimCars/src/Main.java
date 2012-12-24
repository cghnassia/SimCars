import java.awt.Dimension;

import javax.swing.JFrame;

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
		frame.setSize(new Dimension(500, 500));
	}

}
