package views;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controllers.CourseController;

public class ParametresView extends JPanel {
	
	protected ParametresVoitureEssenceView parametresVoitureEssenceView;
	protected ParametresVoitureElectriqueView parametresVoitureElectriqueView;
	protected ParametresVoitureHybrideView parametresVoitureHyrbrideView;
	protected CourseControlesView courseControlesView;
	protected CourseTimeView courseTimeView;

	protected CourseController courseController;
	
	protected ImageIcon imageJaugeEssence;
	protected ImageIcon imageJaugeElectricite;
	protected ImageIcon imageStandAllume;
	protected ImageIcon imageStandEteint;

	public ParametresView(CourseController pCourseController) {
		super(new GridLayout(6, 1));
		this.courseController = pCourseController;
		
		this.imageJaugeElectricite = new ImageIcon(ChargementImage.chargerImage("img/jauge_electricite.png"));
		this.imageJaugeEssence = new ImageIcon(ChargementImage.chargerImage("img/jauge_essence.png"));
		this.imageStandAllume = new ImageIcon(ChargementImage.chargerImage("img/symbole_stand_allume.png"));
		this.imageStandEteint = new ImageIcon (ChargementImage.chargerImage("img/symbole_stand_eteint.png"));
	}
	
	public void init() {
		this.parametresVoitureEssenceView = new ParametresVoitureEssenceView(this.courseController.getCourseModel().getVoitureEssence(), this.imageJaugeEssence, this.imageStandAllume, this.imageStandEteint);
		this.parametresVoitureElectriqueView = new ParametresVoitureElectriqueView(this.courseController.getCourseModel().getVoitureElectrique(), this.imageJaugeElectricite, this.imageStandAllume, this.imageStandEteint);
		this.parametresVoitureHyrbrideView = new ParametresVoitureHybrideView(this.courseController.getCourseModel().getVoitureHybride(), this.imageJaugeEssence, this.imageJaugeElectricite, this.imageStandAllume, this.imageStandEteint);
		this.courseControlesView = new CourseControlesView(this.courseController);
		this.courseTimeView = new CourseTimeView(this.courseController.getCourseModel());
		
		add(this.parametresVoitureEssenceView);
		add(this.parametresVoitureElectriqueView);
		add(this.parametresVoitureHyrbrideView);
		add(new JPanel());
		add(this.courseTimeView);
		add(this.courseControlesView);
		
	}
	
	public void update() {
		this.parametresVoitureEssenceView.update();
		this.parametresVoitureElectriqueView.update();
		this.parametresVoitureHyrbrideView.update();
		this.courseTimeView.update();
	}
	
	public CourseControlesView getCourseControlesView() {
		return this.courseControlesView;
	}
	
	public CourseTimeView getCourseTimeView() {
		return this.courseTimeView;
	}

}
