package views;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import models.Circuit;
import models.ConfigCircuit;
import models.Segment;
import models.TypeSegment;

public class CircuitView extends JPanel {
	
	protected JLayeredPane[][] paneHolder;
	protected ArrayList<BufferedImage> segments;
	
	public CircuitView() {
		super();
		this.setOpaque(false);
		
		this.segments = new ArrayList<BufferedImage>();
		this.paneHolder = new JLayeredPane[ConfigCircuit.NB_CASES_HEIGHT][ConfigCircuit.NB_CASES_WIDTH];
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
		this.segments.add(chargerImageSegment(ConfigCircuit.IMAGE_STAND));
		
		setPreferredSize(new Dimension(ConfigCircuit.WIDTH_CASE * ConfigCircuit.NB_CASES_WIDTH, ConfigCircuit.WIDTH_CASE * ConfigCircuit.NB_CASES_HEIGHT));
		setBounds(0, 0, ConfigCircuit.WIDTH_CASE * ConfigCircuit.NB_CASES_WIDTH, ConfigCircuit.WIDTH_CASE * ConfigCircuit.NB_CASES_HEIGHT);
	}
	
	public void init(Circuit circuit) {
	
		int res;
		JLabel jlb;
		
		for(int i = 0; i < ConfigCircuit.NB_CASES_HEIGHT; i++) {
			for(int j = 0; j < ConfigCircuit.NB_CASES_WIDTH; j++) {
				jlb = new JLabel(new ImageIcon(segments.get(0)));
				jlb.setBounds(0, 0, 50, 50);
				jlb.setPreferredSize(new Dimension(ConfigCircuit.WIDTH_CASE, ConfigCircuit.WIDTH_CASE));
				this.paneHolder[i][j] = new JLayeredPane();
				this.paneHolder[i][j].add(jlb, 0);
				this.paneHolder[i][j].setPreferredSize(new Dimension(ConfigCircuit.WIDTH_CASE, ConfigCircuit.WIDTH_CASE));
				add(this.paneHolder[i][j]);
			}
		}
		
		Segment segment;
		for(int i = 0; i < circuit.getLongueur(); i++) {
			segment = circuit.getSegmentAt(i);
			if(paneHolder[segment.getPosition().y][segment.getPosition().x].getComponentCount() > 0) {
					paneHolder[segment.getPosition().y][segment.getPosition().x].removeAll();
			}
		
			
			switch(segment.getType()) {
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
					res = 0;
					break;
			}
			
			jlb = new JLabel(new ImageIcon(segments.get(res)));
			jlb.setBounds(0, 0, 50, 50);
			jlb.setPreferredSize(new Dimension(50, 50));
			paneHolder[segment.getPosition().y][segment.getPosition().x].add(jlb, 2);
			
			if(i == 0) {
				if(segment.getType() == TypeSegment.TYPE_STRAIGHT_HORIZONTAL) {
					jlb = new JLabel(new ImageIcon(ChargementImage.chargerImage("img/ligne_vertical.png")));
					jlb.setBounds(46, 11, 4, 28);
					jlb.setPreferredSize(new Dimension(4, 18));
				}
				else {
					jlb = new JLabel(new ImageIcon(ChargementImage.chargerImage("img/ligne_horizontal.png")));
					jlb.setBounds(11, 0, 28, 4);
					jlb.setPreferredSize(new Dimension(28, 4));
				}
				paneHolder[segment.getPosition().y][segment.getPosition().x].add(jlb, 0);
			}
			
			if(segment.isStand()) {
				jlb = new JLabel(new ImageIcon(segments.get(9)));
				jlb.setBounds(0, 0, 50, 50);
				jlb.setPreferredSize(new Dimension(50, 50));
				paneHolder[segment.getPosition().y][segment.getPosition().x].add(jlb, 0);
			}
		}
		
		//setVisible(true);
	}
			
	protected BufferedImage chargerImageSegment(String location) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(location));
		} catch (IOException e) {
			System.out.println("Erreur lors du chargement de : " +  location);
			e.printStackTrace();
		}
		
		return img;
	}
}
