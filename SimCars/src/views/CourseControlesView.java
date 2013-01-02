package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controllers.CourseController;

public class CourseControlesView extends JPanel implements ActionListener {
	
	private CourseController courseController;
	private JButton demarrer; 
	private JButton accelerer;
	private JButton ralentir;
	private JTextArea vitesseSimulation;
	
	public CourseControlesView(CourseController pCourseController) {
		this.courseController = pCourseController;
		
		setLayout(new BorderLayout());
		
		this.demarrer = new JButton("DŽmarrer");
		this.accelerer = new JButton(">>");
		this.ralentir = new JButton("<<");
		this.vitesseSimulation = new JTextArea();
		this.vitesseSimulation.setEditable(false);
		this.vitesseSimulation.setBackground(getBackground());
		
		JPanel centerJP = new JPanel(new FlowLayout());
		centerJP.add(this.ralentir);
		centerJP.add(this.demarrer);
		centerJP.add(this.accelerer);
		
		add(centerJP, BorderLayout.CENTER);
		add(this.vitesseSimulation, BorderLayout.SOUTH);
		
		this.demarrer.addActionListener(this);
		this.ralentir.addActionListener(this);
		this.accelerer.addActionListener(this);
		
		//enableAccelerer(false);
		enableRalentir(false);
		
		this.demarrer.setPreferredSize(new Dimension(110, 30));	
		this.demarrer.setText("dŽmarrer");
		
		setVitesseSimulation(1);
	}
	
	public void enableDemarrer(boolean value) {
		if(value) {
			this.demarrer.setText("reprendre");
		}
		else {
			this.demarrer.setText("pause");
		}
	}
	
	public void enableAccelerer(boolean value) {
		this.accelerer.setEnabled(value);
	}
	
	public void enableRalentir(boolean value) {
		this.ralentir.setEnabled(value);
	}
	
	public void setVitesseSimulation(int value) {
		this.vitesseSimulation.setText("Vitesse : X" + value);
	}
	
	public void setCourseFinie() {
		this.demarrer.setText("terminer");
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.demarrer) {
			actionDemarrer();
		}
		else if (e.getSource() == this.accelerer) {
			actionAccelerer();
		}
		else if(e.getSource() == this.ralentir) {
			actionRalentir();
		}
	}
	
	private void actionDemarrer() {
		this.courseController.demarrerPauseCourse();
	}
	
	private void actionAccelerer() {
		this.courseController.accelererCourse();
	}
	
	private void actionRalentir() {
		this.courseController.ralentirCourse();
		
	}
	

}
