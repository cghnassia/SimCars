
public abstract class Voiture {
	protected int type;
	protected int cVitesse;
	protected Moteur moteur;
	protected int habilite;
	protected int freinage;
	
	protected Circuit circuit;
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
		
		//Pour mettre � jour cPosition.getSegment().getVitesseMaxEffective(habilite);
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
	
	public void updateVitesse() {
		//on regarde si on doit freiner
		boolean freinage = true;
		if(isFreinage()) {
			//si oui on applique le freinage sur la distance parcourue entre deux appels (� d�finir)
			this.cVitesse -= calculeVitesseFreinage(10, this.cVitesse);
		}
		else {
			//on applique la vitesse potentielle maximum juste gr�ce au moteur
			if(this.cVitesse < this.vitesseMaxSegment) {
				this.cVitesse = this.moteur.getVitessePotentielle(this.cVitesse);
				if(this.cVitesse >= this.vitesseMaxSegment) {
					this.cVitesse = this.vitesseMaxSegment;
				}
			}
			
		}
		
	}
	
	public boolean isFreinage() {
		boolean res;
		if(isFreinage) {
			res = true;
		}
		else {
			Segment nextSegment = this.circuit.getSegmentAt(this.iSegment + 1);
		
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
	
	//Calcule la distance n�cessaire pour freiner en fonction d'une vitesse
	public int calculeDistanceFreinage(int vitesse) {
		return vitesse * (10 / freinage);
	}
	
	//Calcule la vitesse lors d'un freinage en fonction d'une vitesse pr�c�dente et d'une distance
	public int calculeVitesseFreinage(int distance, int vitesse) {
		int res = calculeDistanceFreinage(vitesse);
		res -= distance;
		
		res *= (freinage / 10);
		
		return res;
	}
	
}