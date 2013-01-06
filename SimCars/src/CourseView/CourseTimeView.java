package CourseView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import models.CourseModel;
import models.TypeVoiture;

public class CourseTimeView extends JPanel {
	
	protected CourseModel courseModel;
	protected JTextArea txtTime;
	protected JTextArea txtResultsTime;
	protected int termines;
	
	public CourseTimeView(CourseModel pCourseModel) {
		super();
		this.courseModel = pCourseModel;
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.insets = new Insets(0, 0, 20, 20);
		
		this.termines = 0;
		this.txtTime = new JTextArea();
		this.txtResultsTime = new JTextArea();
		this.txtTime.setEditable(false);
		
		add(this.txtTime, gbc);
		add(this.txtResultsTime, gbc);
		
		this.txtTime.setBackground(getBackground());
		this.txtResultsTime.setBackground(getBackground());
		
		this.txtTime.setEditable(false);
		this.txtResultsTime.setEditable(false);
		
		this.txtResultsTime.setPreferredSize(new Dimension(200, 50));
		
		setTimeCourse(getTimeFormat(0));
	}
	
	public void update() {
		String time = getTimeFormat(this.courseModel.getTimeCourse());
		setTimeCourse(time);
	}
	
	public void addResult(TypeVoiture voiture) {
		this.termines++;
		
		double time = 0;
		String voitureString = "";
		
		switch(voiture) {
			case VOITURE_ESSENCE:
				voitureString = "Voiture Essence";
				time = this.courseModel.getVoitureEssence().getTimeCourse();
				break;
			case VOITURE_ELECTRIQUE:
				voitureString = "Voiture Electrique";
				time = this.courseModel.getVoitureElectrique().getTimeCourse();
				break;
			case VOITURE_HYBRIDE:
				voitureString = "Voiture Hybride";
				time = this.courseModel.getVoitureHybride().getTimeCourse();
				break;
		}
		
		txtResultsTime.append(this.termines + ". " + voitureString + " : " + getTimeFormat(time) + " \n");
	}
	
	private void setTimeCourse(String timeString) {
		
		
		this.txtTime.setText("Temps écoulé : " + timeString);
	}
	
	private String getTimeFormat(double pTime) {
		
		int time = Math.round((long) (pTime * 10));
		int tenth = time % 10;
		time = (int) (time / 10);
		int secondes = time % 60;
		int minutes =  time / 60;
		
		String minuteDisplay = (minutes < 10) ? "0" + minutes : minutes + "";
		String secondDisplay = (secondes < 10) ? "0" + secondes : secondes + "";
		
		return minuteDisplay + ":" + secondDisplay + ":" + tenth;
	}
}
