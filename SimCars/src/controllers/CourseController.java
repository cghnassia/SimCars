package controllers;


import java.awt.Dimension;

import views.CourseView;
import models.Circuit;
import models.CourseModel;
import models.SegmentFactory;
import models.TypeSegment;
import models.VoitureElectrique;
import models.VoitureEssence;
import models.VoitureHybride;

public class CourseController {
	protected CourseModel courseModel;
	protected CourseView courseView;
	
	protected Thread threadModel;
	protected Thread threadView;
	
	public CourseController() {
		
		//construction du circuit
		Circuit circuit = new Circuit(3);
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, false, 1, 0));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_TURN_BOTTOM_TO_RIGHT, false, 0, 0));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_VERTICAL, false, 0, 1));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_VERTICAL, false, 0, 2));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_VERTICAL, false, 0, 3));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_TURN_TOP_TO_RIGHT, false, 0, 4));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, false, 1, 4));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, false, 2, 4));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, false, 3, 4));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_HARD_HORIZONTAL, false, 4, 4));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, true, 5, 4));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, false, 6, 4));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, false, 7, 4));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_TURN_TOP_TO_LEFT, false, 8, 4));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_VERTICAL, false, 8, 3));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_HARD_VERTICAL, false, 8, 2));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_VERTICAL, false, 8, 1));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_TURN_BOTTOM_TO_LEFT, false, 8, 0));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, false, 7, 0));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, false, 6, 0));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, false, 5, 0));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, false, 4, 0));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, true, 3, 0));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, false, 2, 0));
		
		this.courseModel = new CourseModel(this);
		this.courseView = new CourseView(this);
		
		VoitureElectrique voitureElectrique = new VoitureElectrique(this.courseModel);
		VoitureEssence voitureEssence = new VoitureEssence(this.courseModel);
		VoitureHybride voitureHybride = new VoitureHybride(this.courseModel);
		
		courseModel.init(circuit, voitureElectrique, voitureEssence, voitureHybride);
		
		this.threadView = new Thread(courseView, "courseView");
		this.threadModel = new Thread(courseModel, "courseModel");
		
		
			threadModel.start();
			threadView.start();
	}
	
	public boolean isDemarrer() {
		return this.getCourseModel().isDemarrer();
	}
	
	public void courseFinie() {
		courseView.getParametresView().getCourseControlesView().enableAccelerer(false);
		courseView.getParametresView().getCourseControlesView().enableRalentir(false);
		courseView.getParametresView().getCourseControlesView().setCourseFinie();
	}
	
	public CourseModel getCourseModel() {
		return this.courseModel;
	}
	
	public CourseView getCourseView() {
		return this.courseView;
	}
	
	public void demarrerPauseCourse() {
		if(! this.getCourseModel().isCourseFinie()) {
			this.courseModel.setDemarrer(! this.courseModel.isDemarrer());
			this.courseView.getParametresView().getCourseControlesView().enableDemarrer(! this.courseModel.isDemarrer());
		}
	}
	public void accelererCourse() {
		this.courseModel.accelererSimulation();
		this.courseView.setVitesseSimulation(this.courseModel.getVitesseSimulation());
		
		if(this.courseModel.getVitesseSimulation() == 16) {
			this.courseView.getParametresView().getCourseControlesView().enableAccelerer(false);
		}
		this.courseView.getParametresView().getCourseControlesView().enableRalentir(true);
		
	}
	
	public void ralentirCourse() {
		this.courseModel.ralentirSimulation();
		this.courseView.setVitesseSimulation(this.courseModel.getVitesseSimulation());
		
		if(this.courseModel.getVitesseSimulation() == 1) {
			this.courseView.getParametresView().getCourseControlesView().enableRalentir(false);
		}
		this.courseView.getParametresView().getCourseControlesView().enableAccelerer(true);

	}
}
