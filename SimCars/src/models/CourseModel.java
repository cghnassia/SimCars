package models;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import controllers.CourseController;


public class CourseModel implements ActionListener {
	protected CourseController courseController;
	protected Circuit circuit;
	
	protected VoitureElectrique voitureElectrique;
	protected VoitureEssence voitureEssence;
	protected VoitureHybride voitureHybride;
	
	protected Timer timer;
	
	public CourseModel(CourseController pCourseController) {
		this.courseController = pCourseController;
		this.circuit = null;
		this.voitureElectrique = null;
		this.voitureEssence = null;
		this.voitureHybride = null;
		
		//this.timer = new Timer((int)ConfigGlobal.FPS_RATE * 1000, this);
		this.timer = new Timer(100, this);
	}
	
	public void init(Circuit pCircuit, VoitureElectrique pVoitureElectrique, VoitureEssence pVoitureEssence, VoitureHybride pVoitureHybride) {
		this.circuit = pCircuit;
		this.voitureElectrique = pVoitureElectrique;
		this.voitureEssence = pVoitureEssence;
		this.voitureHybride = pVoitureHybride;
		
		//initalisation des voitures
		this.voitureEssence.init();
		this.voitureElectrique.init();
		this.voitureHybride.init();
	}
	
	public void demarrer() {
		System.out.println("démarrer course");
		this.timer.start();
	}
	
	public Circuit getCircuit() {
		return this.circuit;
	}
	
	public void setCircuit(Circuit pCircuit) {
		this.circuit = pCircuit;
	}
	
	public void hasFinished(Voiture voiture) {
		this.timer.stop();
	}
	
	public VoitureElectrique getVoitureElectrique() {
		return this.voitureElectrique;
	}
	
	public void setVoitureElectrique(VoitureElectrique pVoitureElectrique) {
		this.voitureElectrique = pVoitureElectrique;
	}
	
	public VoitureEssence getVoitureEssence() {
		return this.voitureEssence;
	}
	
	public void setVoitureEssence(VoitureEssence pVoitureEssence) {
		this.voitureEssence = pVoitureEssence;
	}
	
	public VoitureHybride getVoitureHybride() {
		return this.voitureHybride;
	}
	
	public void setVoitureHybride(VoitureHybride pVoitureHybride) {
		this.voitureHybride = pVoitureHybride;
	}
	
	public void actionPerformed(ActionEvent e) {
		//System.out.println("action Performed timer");
		
		voitureEssence.update();
		//voitureElectrique.update();
		//voitureHybride.update();
	}
	
}
