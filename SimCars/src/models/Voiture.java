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
	protected double timeCourse;
	
	public void init() {
		this.cPosition = new Position(this.course.getCircuit().getSegmentDepart());
		this.vitesseMaxSegment = this.cPosition.getSegment().getVitesseMaxEffective(this.habilite);
		this.hasToFill = false;
		this.hasFinished = false;
		this.isFilling = false;
		this.classement = 0;
		this.timeCourse = 0;
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
	
	public boolean hasFinished() {
		return this.hasFinished;
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
	
	public double getTimeCourse() {
		return this.timeCourse;
	}
	
	public void setTimeCourse(double pTimeCourse) {
		this.timeCourse = pTimeCourse;
	}
	
	public void update() {
		//afficher();
		if(this.isFilling) {
			this.cVitesse = 0;
			recharger();
		}
		else if(! this.hasFinished){
			double distanceParcourue  =  this.cVitesse * ConfigGlobal.FPS_RATE;
			if(distanceParcourue == 0) {
				distanceParcourue = 0.1;
			}
			
			//System.out.println("distance parcourue : " + distanceParcourue);
			updateVitesse(distanceParcourue);
			updateConsommation(distanceParcourue);
			updatePosition(distanceParcourue);
		}
	}
	
	protected void updateVitesse(double distance) {
		if(isFreinage()) {
			this.cVitesse = calculeVitesseFreinage(distance);
		}
		else {
			if(this.cVitesse < this.vitesseMaxSegment) {
				for(int i = 0; i < distance; i++) {
					this.cVitesse += this.moteur.getAccelerationPotentielle(this.cVitesse);
					if(this.cVitesse >= this.vitesseMaxSegment) {
						this.cVitesse = this.vitesseMaxSegment;
						break;
					}
					if(this.cVitesse > this.moteur.getVitesseMax()) {
						this.cVitesse = this.moteur.getVitesseMax();
						break;
					}
				}
			}
		}
	}
	
	
	protected void updatePosition(double distance) {
		double total = distance;
		double temp;
		do {
			temp = ConfigCircuit.LONGUEUR_SEGMENT - this.cPosition.getAvancement();
			if(total < temp) {
				cPosition.update(total);
				total = 0;
			}
			else {
				total -= temp;
				this.iSegment = (this.iSegment + 1) % this.course.getCircuit().getLongueur();
				this.cPosition.setPosition(this.course.getCircuit().getSegmentAt(this.iSegment), 0);
				this.vitesseMaxSegment = this.cPosition.getSegment().getVitesseMaxEffective(this.habilite);
				updateDirection();
				calculeArretStand();
				
				if(this.course.getCircuit().isLigneDarrivee(this.iSegment)) {
					this.nbTours++;
					if(this.nbTours >= this.course.getCircuit().getNbTours() ) {
						this.cVitesse = 0;
						this.hasFinished = true;
						this.hasToFill = false;
						this.isFilling = false;
						this.course.hasFinished(this);
					}
				}
			}
			
			if(this.hasToFill && ! this.hasFinished && this.course.getCircuit().getSegmentAt(iSegment).isStand && this.cPosition.getAvancement() >= 50) {
				this.hasToFill = false;
				this.isFilling = true;
				total = 0;
			}
		}
		while(total > 0);
	}
	
	protected void updateDirection() {
	
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
	}
	
	protected abstract void updateConsommation(double distance);
	
	protected boolean isFreinage() {
		
		boolean res = false;
		boolean check = false;
		int vitesse;
		Segment segment;
		for(int i = 1; i < this.course.getCircuit().getLongueur(); i++) {
			segment = this.course.getCircuit().getSegmentAt(this.iSegment + i);
			
			if(segment.isStand() && hasToFill()) {
				check = true;
				vitesse = 0;
			}
			else {
				vitesse = segment.getVitesseMaxEffective(this.habilite);
				if(this.cVitesse > vitesse) {
					check = true;
				}
				else {
					check = false;
				}
			}
				
			if(check) {
				int distance = i * ConfigCircuit.LONGUEUR_SEGMENT - (int) this.cPosition.getAvancement();
				if(distance <= calculeDistanceFreinage(vitesse)) {
					//System.out.println("distance jusqu'au segment = " + distance + " - distance de freinage nŽcessaire = " + calculeDistanceFreinage(vitesse));
					res = true;
					break;
				}
			}
		}
		
		
		return res;
	}
	
	protected abstract void recharger();
	
	protected abstract void afficher();
	
	protected abstract int getAutonomie();
	
	//Calcule la distance nŽcessaire pour arriver ˆ ˆ une certaine vitesse ˆ partir de la vitesse actuelle;
	protected double calculeDistanceFreinage( double vitesseFinale) {
		double res = 0;
		
		if(this.cVitesse > vitesseFinale) {
			double distanceSuperieure = this.cVitesse / this.freinage;
			double distanceInferieure = vitesseFinale / this.freinage;
			res = distanceSuperieure - distanceInferieure;
		}
		
		return res;
	}
	
	//Calcule la nouvelle vitesse lorque la voiture freine en fonction de la vitesse actuelle
	protected double calculeVitesseFreinage(double distance) {
			double res;
			double distanceRes = this.cVitesse / this.freinage;
			distanceRes = distanceRes - distance;
			
			res = distanceRes * this.freinage;
			
			if(res < 0) {
				res = 0;
			}
			
			return res;
	}
	
	protected void calculeArretStand() {
		if(! (this.course.getCircuit().getSegmentAt(this.iSegment).isStand() && this.hasToFill)) {
			int prochainStand = this.course.getCircuit().getNextStand(this.iSegment + 1);
			prochainStand = this.course.getCircuit().getNextStand(prochainStand + 1);
			
			int distanceStand = (int) ((((prochainStand - this.iSegment) + this.course.getCircuit().getLongueur()) % (this.course.getCircuit().getLongueur()) * ConfigCircuit.LONGUEUR_SEGMENT) + (ConfigCircuit.LONGUEUR_SEGMENT / 2) - (ConfigCircuit.LONGUEUR_SEGMENT - this.cPosition.getAvancement()));
			int distanceFin = (int) (((this.course.getCircuit().getLongueur() * this.course.getCircuit().getNbTours()) - (this.nbTours * this.course.getCircuit().getLongueur() + this.iSegment + this.cPosition.getAvancement())) * ConfigCircuit.LONGUEUR_SEGMENT);
			
			if(getAutonomie() < distanceFin && getAutonomie() < distanceStand) {
				this.hasToFill = true;
			}
			else {
				this.hasToFill = false;
			}
		}
	}
	
	public int getDistanceParcourue() {
		return (int) ((this.nbTours * this.course.getCircuit().getLongueur() + this.iSegment) * ConfigCircuit.LONGUEUR_SEGMENT + cPosition.avancement);
	}
	
}
