
public abstract class Voiture {
	protected int type;
	protected int cVitesse;
	protected Moteur moteur;
	protected int habilite;
	protected int freinage;
	
	protected Course course;
	protected int iSegment; 
	protected Position cPosition;
	protected int vitesseMaxSegment;
	protected boolean isFreinage;
	
	public static int TYPE_VOITURE_ESSENCE = 1;
	public static int TYPE_VOITURE_ELECTRIQUE = 2;
	public static int TYPE_VOITURE_HYBRIDE = 3;
		
	public abstract boolean hasToFill();
	
	public int getType() {
		return this.type;	
	}
	
	public void setId(int pType) {
		this.type = pType;
	}
	
	public int getCVitesse() {
		return this.cVitesse;
	}
	
	public void setCVitesse(int pCVitesse) {
		this.cVitesse = pCVitesse;
	}
	
	public void update() {
		int distanceParcourue  = (int) (((double) this.cVitesse) * ConfigGlobal.FPS_RATE);
		updateVitesse(distanceParcourue);
		updatePosition(distanceParcourue);
		updateConsommation(distanceParcourue);
	}
	
	protected void updateVitesse(int distance) {
		//on regarde si on doit freiner
		if(isFreinage()) {
			//si oui on applique le freinage sur la distance parcourue entre deux appels (A MODIFIER)
			this.cVitesse -= calculeVitesseFreinage(distance, this.cVitesse);
			if(this.cVitesse < 0) {
				this.cVitesse = 0;
			}
		}
		else {
			//on applique la vitesse potentielle maximum juste gr‰ce au moteur
			if(this.cVitesse < this.vitesseMaxSegment) {
				this.cVitesse += this.moteur.getAccelerationPotentielle(this.cVitesse) * distance;
				if(this.cVitesse >= this.vitesseMaxSegment) {
					this.cVitesse = this.vitesseMaxSegment;
				}
				if(this.cVitesse > this.moteur.getVitesseMax()) {
					this.cVitesse = this.moteur.getVitesseMax();
				}
			}
			
		}
		
	}
	
	protected void updatePosition(int distance) {
		try {
			cPosition.update(distance);
		}
		catch (DepassementSegmentException e) {
			this.iSegment++;
			this.cPosition.setSegment(this.course.getCircuit().getSegmentAt(this.iSegment));
			updatePosition(e.depassement);
		}
	}
	
	protected abstract void updateConsommation(int distance);
	
	protected boolean isFreinage() {
		boolean res;
		if(isFreinage) {
			res = true;
		}
		else {
			Segment nextSegment = this.course.getCircuit().getSegmentAt(this.iSegment + 1);
		
			int distanceAvantSegment = Position.AVANCEMENT_MAX - this.cPosition.getAvancement();
			int distanceNecessaire = calculeDistanceFreinage(this.cVitesse) - calculeDistanceFreinage(nextSegment.getVitesseMaxEffective(habilite));
		
			if(distanceNecessaire <= distanceAvantSegment) {
				res = true;
			}
			else {
				res = false;
			}
		}
		
		return res;
	}
	
	/*protected abstract int getAutonomie();*/
	
	//Calcule la distance nŽcessaire pour freiner en fonction d'une vitesse
	protected int calculeDistanceFreinage(int vitesse) {
		return vitesse * (10 / freinage);
	}
	
	//Calcule la vitesse lors d'un freinage en fonction d'une vitesse prŽcŽdente et d'une distance
	protected int calculeVitesseFreinage(int distance, int vitesse) {
		int res = calculeDistanceFreinage(vitesse);
		res -= distance;
		
		res *= (freinage / 10);
		
		return res;
	}
	
}
