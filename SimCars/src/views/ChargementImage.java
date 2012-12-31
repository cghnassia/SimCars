package views;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ChargementImage {

	private ChargementImage() {
		//classe statique
	}
	
	public static BufferedImage chargerImage(String location) {
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
