package models;

public abstract class Voiture {
	protected int type;
	protected int cVitesse;
	protected Moteur moteur;
	protected int habilite;
	protected double freinage;
	protected int nbTours;
	protected int vitesseRechargement;
	
	protected CourseModel course;
	protected int iSegment; 
	protected Position cPosition;
	protected int vitesseMaxSegment;
	protected boolean hasToFill;
	protected boolean hasFinished;
	protected boolean isFilling;
	protected int cDureeRechargement;
	
	public static int TYPE_VOITURE_ESSENCE = 1;
	public static int TYPE_VOITURE_ELECTRIQUE = 2;
	public static int TYPE_VOITURE_HYBRIDE = 3;
		
	public abstract boolean hasToFill();
	
	public void init() {
		this.cPosition = new Position(this.course.getCircuit().getSegmentDepart());
		this.vitesseMaxSegment = this.cPosition.getSegment().getVitesseMaxEffective(this.habilite);
		this.hasToFill = false;
		this.hasFinished = false;
		this.isFilling = false;
		
		CalculeArretStand();
	}
	
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
		afficher();
		if(this.isFilling) {
			this.cVitesse = 0;
			recharger();
		}
		else if (this.hasFinished) {
			this.cVitesse = 0;
		}
		else {
			int distanceParcourue  = (int) (((double) this.cVitesse) * ConfigGlobal.FPS_RATE);
			if(distanceParcourue == 0) {
				distanceParcourue = 1;
			}
			
			//System.out.println("distance parcourue : " + distanceParcourue);
			updatePosition(distanceParcourue);
			updateVitesse(distanceParcourue);
			updateConsommation(distanceParcourue);
		}
	}
	
	protected void updateVitesse(int distance) {
		//on regarde si on doit freiner
		if(this.isFreinage()) {
			//System.out.println("On doit freiner");
			//si oui on applique le freinage sur la distance parcourue entre deux appels (A MODIFIER)
			this.cVitesse = calculeVitesseFreinage(distance, this.cVitesse);
			if(this.cVitesse < 0) {
				this.cVitesse = 0;
			}
		}
		else {
			//on applique la vitesse potentielle maximum juste grâce au moteur
			if(this.cVitesse < this.vitesseMaxSegment) {
				System.out.println("ACCELERATION POTENTIELLE : " +  this.moteur.getAccelerationPotentielle(this.cVitesse) * (distance + 1));
				this.cVitesse += this.moteur.getAccelerationPotentielle(this.cVitesse) * (distance + 1) + 1;
				if(this.cVitesse >= this.vitesseMaxSegment) {
					this.cVitesse = this.vitesseMaxSegment;
				}
				if(this.cVitesse > this.moteur.getVitesseMax()) {
					this.cVitesse = this.moteur.getVitesseMax();
				}
			}
			
		}
		
		//System.out.println("vitesse : " + cVitesse);
		
	}
	
	protected void updatePosition(int distance) {
		try {
			cPosition.update(distance);
		}
		catch (DepassementSegmentException e) {
			this.iSegment = (this.iSegment + 1) % this.course.getCircuit().getLongueur();
			try {
				this.cPosition.setPosition(this.course.getCircuit().getSegmentAt(this.iSegment), 0);
			}
			catch(DepassementSegmentException e2) {
				System.out.println("Erreur lors de changement de section");
			}
			
			//System.out.println("Tour " + nbTours + " - Segment : " + iSegment + " - Stand : " + this.cPosition.getSegment().isStand());
			
			this.vitesseMaxSegment = this.cPosition.getSegment().getVitesseMaxEffective(this.habilite);
			if(this.course.getCircuit().isLigneDarrivee(this.iSegment)) {
				//System.out.println("On a fait un tour !");
				this.nbTours++;
				if(this.nbTours >= this.course.getCircuit().getNbTours() ) {
					this.hasFinished = true;
					this.course.hasFinished(this);
					//System.out.print("Course finie !");
				}
			}
			if (! hasFinished && this.course.getCircuit().getSegmentAt(iSegment).isStand ){
				if(hasToFill) {
					//System.out.println("Remplissage en cours");
					this.hasToFill = false;
					this.isFilling = true;
				}	
				else {
					CalculeArretStand();
				}
			}
			if (! hasFinished && ! isFilling){
				updatePosition(e.depassement);
			}
		}
	}
	
	protected abstract void updateConsommation(int distance);
	
	protected boolean isFreinage() {
		boolean res;
		Segment nextSegment = this.course.getCircuit().getSegmentAt(this.iSegment + 1);
		int distanceAvantSegment = Position.AVANCEMENT_MAX - this.cPosition.getAvancement();
		int distanceNecessaire;
		
		//si la voiture doit s'arrêter sur le prochain segment
		if(nextSegment.isStand() && hasToFill()) {
			distanceNecessaire = calculeDistanceFreinage(this.cVitesse);
		}
		else {
			//System.out.println("calcule distance Freinage : " + calculeDistanceFreinage(this.cVitesse));
			distanceNecessaire = calculeDistanceFreinage(this.cVitesse) - calculeDistanceFreinage(nextSegment.getVitesseMaxEffective(habilite));
		}
		if(distanceNecessaire >= distanceAvantSegment) {
			res = true;
		}
		else {
			res = false;
		}
		
		//pour que les autres fonctions n'ai pas à refaire le calcul
		
		return res;
	}
	
	protected abstract void recharger();
	
	protected abstract void afficher();
	
	protected abstract int getAutonomie();
	
	//Calcule la distance nécessaire pour freiner en fonction d'une vitesse
	protected int calculeDistanceFreinage(int vitesse) {
		return (int) (vitesse * (10 / freinage));
	}
	
	//Calcule la vitesse lors d'un freinage en fonction d'une vitesse précédente et d'une distance
	protected int calculeVitesseFreinage(int distance, int vitesse) {
		int res = calculeDistanceFreinage(vitesse);
		res -= distance;
		
		res = (int) ((freinage / 10) * res);
		
		//System.out.println("Freinage avec vitesse de : " + vitesse + " après distance de " + distance + " donne : " + res);
		
		return res;		
	}
	
	protected void CalculeArretStand() {
		int prochainStand1 = this.course.getCircuit().getNextStand(this.iSegment + 1);
		int prochainStand2 = this.course.getCircuit().getNextStand(prochainStand1 + 1);
		int distance = ConfigCircuit.LONGUEUR_SEGMENT * (((prochainStand2 - this.iSegment) + this.course.getCircuit().getLongueur()) % this.course.getCircuit().getLongueur()) * ConfigCircuit.NB_CASES_HEIGHT;
		if(getAutonomie() > distance) {
			this.hasToFill = true;
		}
		//voir si il peut atteindre le stand d'après...
	}
	
}
