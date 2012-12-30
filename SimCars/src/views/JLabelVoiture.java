package views;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JLabelVoiture extends JLabel {
	
		protected double rotation;

		public JLabelVoiture() {
			super();
			this.rotation = 0;
		}
		
		public JLabelVoiture(ImageIcon image) {
			super(image);
			this.rotation = 0;
		}
		
		public void rotate(double pRotation) {
			this.rotation = pRotation;
			//paintComponent(getGraphics());
		}
		
		/* protected void paintComponent(Graphics g) {
			 	super.paintComponent(g);
			 	Graphics2D g2 = (Graphics2D) g;
			 	g2.rotate(Math.PI / 4, this.getWidth() / 2, this.getHeight() / 2);
                g2.drawImage(, 0, 0, null);
		 }*/
}
