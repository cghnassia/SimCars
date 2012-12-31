package models;

import java.awt.event.ActionListener;

import controllers.CourseController;


public class CourseModel implements Runnable {
	protected CourseController courseController;
	protected Circuit circuit;
	
	protected VoitureElectrique voitureElectrique;
	protected VoitureEssence voitureEssence;
	protected VoitureHybride voitureHybride;
	
	protected boolean continuer;
	
	public CourseModel(CourseController pCourseController) {
		this.courseController = pCourseController;
		this.circuit = null;
		this.voitureElectrique = null;
		this.voitureEssence = null;
		this.voitureHybride = null;
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
	
	public void update() {
		voitureEssence.update();
		voitureElectrique.update();
		voitureHybride.update();
	}
	
	public void run() {
		this.continuer = true;
		while(this.continuer) {
			update();
			updateClassement();
			try {
				Thread.currentThread().sleep((int) (ConfigGlobal.FPS_RATE * 1000));
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Circuit getCircuit() {
		return this.circuit;
	}
	
	public void setCircuit(Circuit pCircuit) {
		this.circuit = pCircuit;
	}
	
	public void hasFinished(Voiture voiture) {
		//this.timer.stop();
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
	
	public void updateClassement() {
		int distanceVoitureEssence = this.voitureEssence.getDistanceParcourue();
		int distanceVoitureElectrique = this.voitureElectrique.getDistanceParcourue();
		int distanceVoitureHybride = this.voitureHybride.getDistanceParcourue();
		
		if(distanceVoitureEssence >= distanceVoitureElectrique && distanceVoitureEssence >= distanceVoitureHybride) {
			this.voitureEssence.setClassement(0);
		}
		else if(distanceVoitureEssence < distanceVoitureElectrique && distanceVoitureEssence < distanceVoitureHybride) {
			this.voitureEssence.setClassement(2);
		}
		else {
			this.voitureEssence.setClassement(1);
		}
		
		if(distanceVoitureElectrique >= distanceVoitureEssence && distanceVoitureElectrique >= distanceVoitureHybride) {
			this.voitureElectrique.setClassement(0);
		}
		else if(distanceVoitureElectrique < distanceVoitureEssence && distanceVoitureElectrique < distanceVoitureHybride) {
			this.voitureElectrique.setClassement(2);
		}
		else {
			this.voitureElectrique.setClassement(1);
		}
		
		if(distanceVoitureHybride >= distanceVoitureEssence && distanceVoitureHybride >= distanceVoitureElectrique) {
			this.voitureHybride.setClassement(0);
		}
		else if(distanceVoitureHybride < distanceVoitureEssence && distanceVoitureHybride < distanceVoitureElectrique) {
			this.voitureHybride.setClassement(2);
		}
		else {
			this.voitureHybride.setClassement(1);
		}
	}
	
	
	
}
