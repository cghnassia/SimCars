import java.awt.Point;


public class Segment {
	
		protected int vitesseMax; 
		protected boolean isStand;
		protected int difficulte; // 1 = facile, 2 = moyen et 3 = difficile
		
		protected Point position;

		public Segment(int pVitesseMax, int pDifficulte, boolean isStand, Point pPosition) {
			this.vitesseMax = pVitesseMax;
			this.isStand = false;
			this.difficulte = pDifficulte;
			this.position = pPosition;
		}
		
		public int getVitesseMax() {
			return this.vitesseMax;
		}
		
		//la vitesse maximume d'une voiture sur ce troncon en fonction de son habilité
		public int getVitesseMaxEffective(int habilite) {
			int res;
			
			if(difficulte == 1) {
				res = this.vitesseMax;
			}
			else {
				res = (int) (2 / habilite) * this.vitesseMax;
			}

			return res;
		}
		
		public boolean isStand() {
			return this.isStand;
		}
		
		public void setStand(boolean pIsStand) {
			this.isStand = pIsStand;
		}
}
