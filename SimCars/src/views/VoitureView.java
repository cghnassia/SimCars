package views;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.crypto.spec.PSource;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.ConfigCircuit;
import models.ConfigVoiture;
import models.DisplayVoitureProperties;
import models.DisplayVoiturePropertiesFactory;
import models.Position;
import models.Segment;
import models.TypeSegment;
import models.TypeVoiture;

public class VoitureView extends JPanel {
	
	public static final String IMAGE_VOITURE_ESSENCE = "img/voiture_essence.png";
	public static final String IMAGE_VOITURE_ELECTRIQUE = "img/voiture_electrique.png";
	public static final String IMAGE_VOITURE_HYBRIDE = "img/voiture_hybride.png";

	protected TypeVoiture type;
	protected JLabelVoiture voitureLabel;

	public VoitureView(TypeVoiture pType) {
		this.type = pType;
		this.setOpaque(false);
		this.setLayout(null);
		
		BufferedImage voiture;
		switch(this.type) {
			case VOITURE_ESSENCE:
				voiture = chargerImageVoiture(VoitureView.IMAGE_VOITURE_ESSENCE);
				break;
			case VOITURE_ELECTRIQUE:
				voiture = chargerImageVoiture(VoitureView.IMAGE_VOITURE_ELECTRIQUE);
				break;
			case VOITURE_HYBRIDE : //VOITURE_HYBRIDE
				voiture = chargerImageVoiture(VoitureView.IMAGE_VOITURE_HYBRIDE);
				break;
			default:
				voiture = null;
				break;
		}
		
		voitureLabel = new JLabelVoiture(new ImageIcon(voiture));
		voitureLabel.setPreferredSize(new Dimension(25, 25));
		voitureLabel.setBounds(0, 0, 25, 25);
		this.add(voitureLabel);
		
		this.setPreferredSize(new Dimension(500, 500));
		this.setBounds(0, 0, 500, 500);
		
		/*JLabel jlb = new JLabel(new ImageIcon(voiture));
		jlb.setPreferredSize(new Dimension(50, 25));
		jlb.setBounds(0, 0, 100, 50);
		this.add(jlb);*/
	}
	
	protected BufferedImage chargerImageVoiture(String location) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(location));
		} catch (IOException e) {
			System.out.println("Erreur lors du chargement de : " +  location);
			e.printStackTrace();
		}
		
		return img;
	}
	
	public void updatePosition(Position position) {
		Segment segment = position.getSegment();
		
		DisplayVoitureProperties dvp = DisplayVoiturePropertiesFactory.getDisplayVoitureProperties(segment.getType(), this.type, position.getDirection(), position.getAvancement());
		moveVoiture((int) segment.getPosition().getX(), (int) segment.getPosition().getY(), (int) dvp.getPosition().getX(), (int) dvp.getPosition().getY(), dvp.getRotation());
		/*if(this.type == TypeVoiture.VOITURE_ESSENCE && segment.getType() == TypeSegment.TYPE_TURN_BOTTOM_TO_RIGHT) {
			System.out.println(dvp.getPosition() + " - " + dvp.getRotation());
		}*/
	}	
	
	protected void moveVoiture(int caseX, int caseY, int x, int y, double rotation) {
		voitureLabel.setBounds(caseX * ConfigCircuit.WIDTH_CASE + (x * ConfigCircuit.WIDTH_CASE / 100) - (voitureLabel.getWidth() / 2), caseY * ConfigCircuit.WIDTH_CASE + (y * ConfigCircuit.WIDTH_CASE / 100) - (voitureLabel.getHeight() / 2), voitureLabel.getWidth(), voitureLabel.getHeight());
		voitureLabel.rotate(rotation);
	}
}
