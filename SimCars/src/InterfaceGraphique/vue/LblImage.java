package vue;


import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LblImage extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String pathImage;
	public LblImage(String pathImage, int largeur, int hauteur) {
		super("", JLabel.CENTER);
		this.setPathImage(pathImage);
		ImageIcon icon = new ImageIcon(new ImageIcon(pathImage).getImage()
				.getScaledInstance (largeur, hauteur, Image.SCALE_SMOOTH));
		this.setIcon(icon);
	}
	public String getPathImage() {
		return pathImage;
	}
	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}
	


}
