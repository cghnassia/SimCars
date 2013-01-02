package models;

import java.awt.event.ActionListener;

import controllers.CourseController;


public class CourseModel implements Runnable {
	protected CourseController courseController;
	protected Circuit circuit;
	
	protected VoitureElectrique voitureElectrique;
	protected VoitureEssence voitureEssence;
	protected VoitureHybride voitureHybride;
	
	protected boolean isDemarrer;
	protected boolean isCourseFinie;
	protected int vitesseSimulation;
	
	public CourseModel(CourseController pCourseController) {
		this.courseController = pCourseController;
		this.circuit = null;
		this.voitureElectrique = null;
		this.voitureEssence = null;
		this.voitureHybride = null;
		
		this.isDemarrer = false;
		this.isCourseFinie = false;
		this.vitesseSimulation = 1;
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
		updateClassement();
	}
	
	public boolean isCourseFinie() {
		return this.isCourseFinie;
	}
	
	public void run() {
		while(! this.isCourseFinie) {
			if(this.isDemarrer) {
				update();
			}
			try {
				Thread.currentThread().sleep((int) (ConfigGlobal.FPS_RATE * 1000) / vitesseSimulation);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(this.isCourseFinie) {
				courseController.courseFinie();
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
	
	public boolean isDemarrer() {
		return this.isDemarrer;
	}
	
	public void setDemarrer(boolean value) {
		this.isDemarrer = value;
	}
	
	public int getVitesseSimulation() {
		return this.vitesseSimulation;
	}
	
	public void accelererSimulation() {
		if(this.vitesseSimulation < 16) {
			this.vitesseSimulation *= 2;
		}
	}
	
	public void ralentirSimulation() {
		if(this.vitesseSimulation > 1) {
			this.vitesseSimulation /= 2;
		}
	}
	
	public void updateClassement() {
		int distanceVoitureEssence = this.voitureEssence.getDistanceParcourue();
		int distanceVoitureElectrique = this.voitureElectrique.getDistanceParcourue();
		int distanceVoitureHybride = this.voitureHybride.getDistanceParcourue();
		
		int i = 0;
		if(this.voitureElectrique.hasFinished()) {
			i++;
		}
		if(this.voitureEssence.hasFinished()) {
			i++;
		}
		if(this.voitureHybride.hasFinished()) {
			i++;
		}
		
		if(i == 3) {
			this.isCourseFinie = true;
		}
		
		if(! this.voitureEssence.hasFinished()) {
			if(distanceVoitureEssence >= distanceVoitureElectrique && distanceVoitureEssence >= distanceVoitureHybride) {
				this.voitureEssence.setClassement(i + 0);
			}
			else if(distanceVoitureEssence < distanceVoitureElectrique && distanceVoitureEssence < distanceVoitureHybride) {
				this.voitureEssence.setClassement(2);
			}
			else {
				this.voitureEssence.setClassement(1);
			}
		}
		
		if(! this.voitureElectrique.hasFinished()) {
			if(distanceVoitureElectrique >= distanceVoitureEssence && distanceVoitureElectrique >= distanceVoitureHybride) {
				this.voitureElectrique.setClassement(i + 0);
			}
			else if(distanceVoitureElectrique < distanceVoitureEssence && distanceVoitureElectrique < distanceVoitureHybride) {
				this.voitureElectrique.setClassement(2);
			}
			else {
				this.voitureElectrique.setClassement(1);
			}
		}
			
		if(! this.voitureHybride.hasFinished()) {
			if(distanceVoitureHybride >= distanceVoitureEssence && distanceVoitureHybride >= distanceVoitureElectrique) {
				this.voitureHybride.setClassement(i + 0);
			}
			else if(distanceVoitureHybride < distanceVoitureEssence && distanceVoitureHybride < distanceVoitureElectrique) {
				this.voitureHybride.setClassement(2);
			}
			else {
				this.voitureHybride.setClassement(1);
			}
		}
	}
	
	
	
}
