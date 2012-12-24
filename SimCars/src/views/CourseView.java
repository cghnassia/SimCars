package views;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Circuit;
import models.ConfigCircuit;
import controllers.CourseController;

public class CourseView extends JPanel {
		
		protected JLabel[][] labelHolder;
		protected CourseController courseController;
		protected ArrayList<BufferedImage> segments;

		public CourseView(CourseController pCourseController) {
			super();
			this.courseController = pCourseController;
			this.segments = new ArrayList<BufferedImage>();
			this.labelHolder = new JLabel[ConfigCircuit.NB_CASES_HEIGHT][ConfigCircuit.NB_CASES_WIDTH];
			setLayout(new GridLayout(ConfigCircuit.NB_CASES_HEIGHT, ConfigCircuit.NB_CASES_WIDTH));
			
			this.segments.add(chargerImageSegment(ConfigCircuit.IMAGE_NONE));
			this.segments.add(chargerImageSegment(ConfigCircuit.IMAGE_STRAIGHT_HORIZONTAL));
			this.segments.add(chargerImageSegment(ConfigCircuit.IMAGE_STRAIGHT_VERTICAL));
			this.segments.add(chargerImageSegment(ConfigCircuit.IMAGE_TURN_TOP_TO_RIGHT));
			this.segments.add(chargerImageSegment(ConfigCircuit.IMAGE_TURN_TOP_TO_LEFT));
			this.segments.add(chargerImageSegment(ConfigCircuit.IMAGE_TURN_BOTTOM_TO_RIGHT));
			this.segments.add(chargerImageSegment(ConfigCircuit.IMAGE_TURN_BOTTOM_TO_LEFT));
			this.segments.add(chargerImageSegment(ConfigCircuit.IMAGE_HARD_HORIZONTAL));
			this.segments.add(chargerImageSegment(ConfigCircuit.IMAGE_HARD_VERTICAL));
		}
		
		public void init() {
			int res;
			
			for(int i = 0; i < ConfigCircuit.NB_CASES_HEIGHT; i++) {
				for(int j = 0; j < ConfigCircuit.NB_CASES_WIDTH; j++) {
					
					labelHolder[i][j] = new JLabel(new ImageIcon(segments.get(0)));
					add(labelHolder[i][j]);
					
				}
			}
			
			Circuit circuit = this.courseController.getCircuit();
			for(int i = 0; i < circuit.getLongueur(); i++) {
				switch(circuit.getSegmentAt(i).getType()) {
					case TYPE_STRAIGHT_HORIZONTAL:
						res = 1;
						break;
					case TYPE_STRAIGHT_VERTICAL:
						res = 2;
						break;
					case TYPE_TURN_TOP_TO_RIGHT:
						res = 3;
						break;
					case TYPE_TURN_TOP_TO_LEFT:
						res = 4;
						break;
					case TYPE_TURN_BOTTOM_TO_RIGHT:
						res = 5;
						break;
					case TYPE_TURN_BOTTOM_TO_LEFT:
						res = 6;
						break;
					case TYPE_HARD_HORIZONTAL:
						res = 7;
						break;
					case TYPE_HARD_VERTICAL:
						res = 8;
						break;
					default :
						res = 9;
						break;
				}
				//labelHolder[circuit.getSegmentAt(i).getPosition().y][circuit.getSegmentAt(i).getPosition().x].removeAll();
				labelHolder[circuit.getSegmentAt(i).getPosition().y][circuit.getSegmentAt(i).getPosition().x].setIcon(new ImageIcon(segments.get(res)));
			}
					
			//this.repaint();
			this.setVisible(true);
		}
		
		
		public BufferedImage chargerImageSegment(String location) {
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File(location));
			} catch (IOException e) {
				System.out.println(location);
				e.printStackTrace();
			}
			
			return img;
		}
}
