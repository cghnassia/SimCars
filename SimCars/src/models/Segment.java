package models;
import java.awt.Point;


public class Segment {
	
		protected TypeSegment type;
		protected int vitesseMax; 
		protected boolean isStand;
		protected int difficulte; // 1 = facile, 2 = moyen et 3 = difficile
		
		protected Point position;

		public Segment(TypeSegment pType, int pVitesseMax, int pDifficulte, boolean pIsStand, Point pPosition) {
			this.type = pType;
			this.vitesseMax = pVitesseMax;
			this.isStand = pIsStand;
			this.difficulte = pDifficulte;
			this.position = pPosition;
		}
		
		public int getVitesseMax() {
			return this.vitesseMax;
		}
		
		public int getVitesseMaxEffective(int habilite) {
			double res;
			
			if(difficulte == 1) {
				res = this.vitesseMax;
			}
			else {
				res = this.vitesseMax * (((double) habilite / 100) / difficulte);
			}

			return (int) res;
		}
		
		public boolean isStand() {
			return this.isStand;
		}
		
		public void setStand(boolean pIsStand) {
			this.isStand = pIsStand;
		}
		
		public Point getPosition() {
			return this.position;
		}
		
		public void setPosition(Point p) {
			this.position = p;
		}
		
		public TypeSegment getType() {
			return this.type;
		}

}
