package views;

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

public class CourseView extends JLayeredPane implements Runnable{
		
		protected CourseController courseController;
		
		protected CircuitView circuitView;
		protected VoitureView voitureEssenceView;
		protected VoitureView voitureElectriqueView;
		protected VoitureView voitureHybrideView;
		boolean continuer;

		public CourseView(CourseController pCourseController) {
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
		}
		
		public void init() {
			Segment segmentDepart;
			DisplayVoitureProperties dvp;
			
			circuitView.init(this.courseController.getCourseModel().getCircuit());
			
			/*segmentDepart = this.courseController.getCourseModel().getCircuit().getSegmentAt(0);
			
			dvp = DisplayVoiturePropertiesFactory.getDisplayVoitureProperties(segmentDepart.getType(), DisplayVoiturePropertiesFactory.VOITURE_ESSENCE, DisplayVoiturePropertiesFactory.SENS_NORMAL, 0); 
			voitureEssenceView.moveVoiture(segmentDepart.getPosition().x, segmentDepart.getPosition().y, dvp.getPosition().x, dvp.getPosition().y, dvp.getRotation());
			
			dvp = DisplayVoiturePropertiesFactory.getDisplayVoitureProperties(segmentDepart.getType(), DisplayVoiturePropertiesFactory.VOITURE_ELECTRIQUE, DisplayVoiturePropertiesFactory.SENS_NORMAL, 0); 
			voitureElectriqueView.moveVoiture(segmentDepart.getPosition().x, segmentDepart.getPosition().y, dvp.getPosition().x, dvp.getPosition().y, dvp.getRotation());
			
			dvp = DisplayVoiturePropertiesFactory.getDisplayVoitureProperties(segmentDepart.getType(), DisplayVoiturePropertiesFactory.VOITURE_HYBRIDE, DisplayVoiturePropertiesFactory.SENS_NORMAL, 0); 
			voitureHybrideView.moveVoiture(segmentDepart.getPosition().x, segmentDepart.getPosition().y, dvp.getPosition().x, dvp.getPosition().y, dvp.getRotation());*/
			
			//repaint();
			
			update();
		}
		
		public void run() {
			this.continuer = true;
			while(this.continuer) {
				update();
				try {
					Thread.currentThread().sleep((int) (ConfigGlobal.FPS_RATE * 1000));
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void update() {
			this.voitureEssenceView.updatePosition(this.courseController.getCourseModel().getVoitureEssence().getPosition());
			this.voitureElectriqueView.updatePosition(this.courseController.getCourseModel().getVoitureElectrique().getPosition());
			this.voitureHybrideView.updatePosition(this.courseController.getCourseModel().getVoitureHybride().getPosition());
		}
		
		
		
}
