package views;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import models.ConfigCircuit;
import models.ConfigGlobal;
import models.DisplayVoitureProperties;
import models.DisplayVoiturePropertiesFactory;
import models.Segment;
import models.TypeSegment;
import models.TypeVoiture;
import models.VoitureEssence;

import controllers.CourseController;

public class CarteView extends JLayeredPane{
		
		protected CourseController courseController;
		
		protected CircuitView circuitView;
		protected VoitureView voitureEssenceView;
		protected VoitureView voitureElectriqueView;
		protected VoitureView voitureHybrideView;
		boolean continuer;

		public CarteView(CourseController pCourseController) {
			super();
			this.courseController = pCourseController;
			
			this.circuitView = new CircuitView();
			this.voitureEssenceView = new VoitureView(TypeVoiture.VOITURE_ESSENCE);
			this.voitureElectriqueView = new VoitureView(TypeVoiture.VOITURE_ELECTRIQUE);
			this.voitureHybrideView = new VoitureView(TypeVoiture.VOITURE_HYBRIDE);
			
			add(this.circuitView, 1);
			add(this.voitureElectriqueView, 0);
			add(this.voitureHybrideView, 0);
			add(this.voitureEssenceView, 0);
			
			//setPreferredSize(new Dimension(ConfigCircuit.NB_CASES_WIDTH * ConfigCircuit.WIDTH_CASE, ConfigCircuit.NB_CASES_HEIGHT * ConfigCircuit.WIDTH_CASE));
		}
		
		public void init() {
			circuitView.init(this.courseController.getCourseModel().getCircuit());
			
			update();
			repaint();
		}
		
		public void update() {
			this.voitureEssenceView.updatePosition(this.courseController.getCourseModel().getVoitureEssence().getPosition());
			this.voitureElectriqueView.updatePosition(this.courseController.getCourseModel().getVoitureElectrique().getPosition());
			this.voitureHybrideView.updatePosition(this.courseController.getCourseModel().getVoitureHybride().getPosition());
		}
		
		
		
}
