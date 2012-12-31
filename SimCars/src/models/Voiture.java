package models;

public abstract class Voiture {
	protected TypeVoiture type;
	protected double cVitesse;
	protected Moteur moteur;
	protected int habilite;
	protected double freinage;
	protected int nbTours;
	protected int classement;
	
	protected CourseModel course;
	protected int iSegment; 
	protected Position cPosition;
	protected int vitesseMaxSegment;
	protected boolean hasToFill;
	protected boolean hasFinished;
	protected boolean isFilling;
	protected int cDureeRechargement;
	
	public void init() {
		this.cPosition = new Position(this.course.getCircuit().getSegmentDepart());
		this.vitesseMaxSegment = this.cPosition.getSegment().getVitesseMaxEffective(this.habilite);
		this.hasToFill = false;
		this.hasFinished = false;
		this.isFilling = false;
		this.classement = 0;
	}
	
	public TypeVoiture getType() {
		return this.type;	
	}
	
	public void setId(TypeVoiture pType) {
		this.type = pType;
	}
	
	public double getCVitesse() {
		return this.cVitesse;
	}
	
	public void setCVitesse(int pCVitesse) {
		this.cVitesse = pCVitesse;
	}
	
	public Position getPosition() {
		return this.cPosition;
	}
	
	public boolean hasToFill() {
		return this.hasToFill;
	}
	
	public boolean isFilling() {
		return this.isFilling;
	}
	
	public int getNbTours() {
		return this.nbTours;
	}
	
	public int getNbToursTotal() {
		return this.course.getCircuit().getNbTours();
	}
	
	public int getClassement() {
		return this.classement;
	}
	
	public void setClassement(int pClassement) {
		this.classement = pClassement;
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
			updateVitesse(distanceParcourue);
			updateConsommation(distanceParcourue);
			updatePosition(distanceParcourue);
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
				//System.out.println("ACCELERATION POTENTIELLE : " +  this.moteur.getAccelerationPotentielle(this.cVitesse) * (distance + 1));
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
			if (this.hasToFill && ! this.hasFinished && this.course.getCircuit().getSegmentAt(iSegment).isStand && this.cPosition.getAvancement() >= 50) {
				this.hasToFill = false;
				this.isFilling = true;
			}
		else {
			cPosition.update(distance);
			}
		}
		catch (DepassementSegmentException e) {
			this.iSegment = (this.iSegment + 1) % this.course.getCircuit().getLongueur();
			
			TypeSegment typeSegment = this.course.getCircuit().getSegmentAt(this.iSegment).getType();
			Direction direction = this.cPosition.getDirection();
			switch(typeSegment) {
				case TYPE_STRAIGHT_HORIZONTAL: 
				case TYPE_HARD_HORIZONTAL:
					if(direction.getDestination() == TypeDirection.LEFT) {
						direction.setOrigine(TypeDirection.RIGHT);
					}
					else {	//direction.getDestination() == TypeDirection.RIGHT
						direction.setOrigine(TypeDirection.LEFT);
					}
					break;
				case TYPE_STRAIGHT_VERTICAL:
				case TYPE_HARD_VERTICAL:
					if(direction.getDestination() == TypeDirection.TOP) {
						direction.setOrigine(TypeDirection.BOTTOM);
					}
					else {	//direction.getDestination() == TypeDirection.BOTTOM
						direction.setOrigine(TypeDirection.TOP);
					}
					break;
				case TYPE_TURN_TOP_TO_LEFT:
					if(direction.getDestination() == TypeDirection.BOTTOM) {
						direction.setOrigine(TypeDirection.TOP);
						direction.setDestination(TypeDirection.LEFT);
					}	
					else {	//direction.getDestination() == TypeDirection.RIGHT
						direction.setOrigine(TypeDirection.LEFT);
						direction.setDestination(TypeDirection.TOP);
					}
					break;
				case TYPE_TURN_TOP_TO_RIGHT:
					if(direction.getDestination() == TypeDirection.BOTTOM) {
						direction.setOrigine(TypeDirection.TOP);
						direction.setDestination(TypeDirection.RIGHT);
					}	
					else {	//direction.getDestination() == TypeDirection.LEFT
						direction.setOrigine(TypeDirection.RIGHT);
						direction.setDestination(TypeDirection.TOP);
					}
					break;
				case TYPE_TURN_BOTTOM_TO_LEFT:
					if(direction.getDestination() == TypeDirection.RIGHT) {
						direction.setOrigine(TypeDirection.LEFT);
						direction.setDestination(TypeDirection.BOTTOM);
					}	
					else {	//direction.getDestination() == TypeDirection.TOP
						direction.setOrigine(TypeDirection.BOTTOM);
						direction.setDestination(TypeDirection.LEFT);
					}
					break;
				case TYPE_TURN_BOTTOM_TO_RIGHT:
					if(direction.getDestination() == TypeDirection.BOTTOM) {
						direction.setOrigine(TypeDirection.BOTTOM);
						direction.setDestination(TypeDirection.RIGHT);
					}	
					else {	//direction.getDestination() == TypeDirection.LEFT
						direction.setOrigine(TypeDirection.RIGHT);
						direction.setDestination(TypeDirection.BOTTOM);
					}
					break;
				case TYPE_NONE:
					break;
			}
			
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
			else {
				CalculeArretStand();

			}
			if (! hasFinished && ! isFilling) {
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
		if((nextSegment.isStand() || this.course.getCircuit().getSegmentAt(this.iSegment).isStand()) && hasToFill()) {
			distanceNecessaire = calculeDistanceFreinage(this.cVitesse);
			distanceAvantSegment = ConfigCircuit.LONGUEUR_SEGMENT / 2;
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
		
		return res;
	}
	
	protected abstract void recharger();
	
	protected abstract void afficher();
	
	protected abstract int getAutonomie();
	
	//Calcule la distance nécessaire pour freiner en fonction d'une vitesse
	protected int calculeDistanceFreinage(double vitesse) {
		return (int) (vitesse * (10 / freinage));
	}
	
	//Calcule la vitesse lors d'un freinage en fonction d'une vitesse précédente et d'une distance
	protected int calculeVitesseFreinage(int distance, double vitesse) {
		int res = calculeDistanceFreinage(vitesse);
		res -= distance;
		
		res = (int) ((freinage / 10) * res);
		
		//System.out.println("Freinage avec vitesse de : " + vitesse + " après distance de " + distance + " donne : " + res);
		
		return res;		
	}
	
	protected void CalculeArretStand() {
		int prochainStand1 = this.course.getCircuit().getNextStand(this.iSegment + 1);
		int prochainStand2 = this.course.getCircuit().getNextStand(prochainStand1 + 1);
		int finCircuit = this.course.getCircuit().getLongueur() * (this.course.getCircuit().getNbTours() - this.nbTours - 1) + this.course.getCircuit().getLongueur() - this.iSegment ;
		
		int distanceStand = (((prochainStand2 - this.iSegment) + this.course.getCircuit().getLongueur()) * ConfigCircuit.LONGUEUR_SEGMENT) % (this.course.getCircuit().getLongueur() * ConfigCircuit.LONGUEUR_SEGMENT);
		int distanceFin = (((finCircuit - this.iSegment) + this.course.getCircuit().getLongueur()) * ConfigCircuit.LONGUEUR_SEGMENT) % (this.course.getCircuit().getLongueur() * ConfigCircuit.LONGUEUR_SEGMENT);
		
		if(getAutonomie() < distanceFin && getAutonomie() < distanceStand) {
			this.hasToFill = true;
		}
		else {
			//this.hasToFill = false;
		}
		
		if(this.type == TypeVoiture.VOITURE_HYBRIDE) {
			//System.out.println("autonomie : " + getAutonomie() + " distance au stand d'après : " + distance + " hasToFill : " + this.hasToFill + " isFilling : " + this.isFilling);
		}
		//voir si il peut atteindre le stand d'après...
	}
	
	public int getDistanceParcourue() {
		return (this.nbTours * this.course.getCircuit().getLongueur() + this.iSegment) * ConfigCircuit.LONGUEUR_SEGMENT + cPosition.avancement;
	}
	
}
