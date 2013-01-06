package models;

public class VoitureHybride extends Voiture {
	
	protected double niveauBatterie;
	protected double niveauReservoir;
	protected double niveauBatterieMax;
	protected double niveauReservoirMax;
	
	protected double vitesseRechargementEssence;
	protected double vitesseRechargementElectrique;
	
	public VoitureHybride(CourseModel pCourse) {
		this.course = pCourse;
		this.type = TypeVoiture.VOITURE_HYBRIDE;
		this.cVitesse = 0;
		this.habilite = ConfigVoiture.VOITURE_HYRIDE_HABILITE;
		this.niveauReservoirMax = ConfigVoiture.NIVEAU_HYBRIDE_RESERVOIR_MAX;
		this.niveauBatterieMax = ConfigVoiture.NIVEAU_HYBRIDE_BATTERIE_MAX;
		this.freinage = ConfigVoiture.HYBRIDE_FREINAGE;
		this.moteur = new Moteur(TypeMoteur.TYPE_ELECTRIQUE);
		this.niveauReservoir = this.niveauReservoirMax;
		this.niveauBatterie = this.niveauBatterieMax;
		this.vitesseRechargementEssence = ConfigVoiture.VITESSE_RECHARGEMENT_ESSENCE;
		this.vitesseRechargementElectrique = ConfigVoiture.VITESSE_RECHARGEMENT_ELECTRIQUE;
	}
	
	public double getNiveauReservoir() {
		return this.niveauReservoir;
	}
	
	public double getNiveauBatterie() {
		return this.niveauBatterie;
	}
	
	public double getNiveauReservoirMax() {
		return this.niveauReservoirMax;
	}
	
	public double getNiveauBatterieMax() {
		return this.niveauBatterieMax;
	}
	
	public Moteur getMoteur() {
		return this.moteur;
	}
	
	protected void switchMoteur(TypeMoteur pMoteur) {
		this.moteur = new Moteur(pMoteur);
	}
	

	protected void updateConsommation(double distance) {
		if(this.moteur.getType() == TypeMoteur.TYPE_ESSENCE) {
			this.niveauReservoir -= (ConfigMoteur.MOTEUR_ESSENCE_CONSOMMATION * distance) * ((double) this.cVitesse / this.moteur.getVitesseMax());
			loadBatterie(distance);
			
			if(this.niveauReservoir < 0) {
				this.niveauReservoir = 0;
				switchMoteur(TypeMoteur.TYPE_ELECTRIQUE);
			}
		}
		else { //this.moteur.getType() == TypeMoteur.TYPE_ELECTRIQUE
			this.niveauBatterie -= (ConfigMoteur.MOTEUR_ELECTRIQUE_CONSOMMATION * distance) * ((double) this.cVitesse / this.moteur.getVitesseMax());
		
			if(this.niveauBatterie < 0) {
				this.niveauBatterie = 0;
				switchMoteur(TypeMoteur.TYPE_ESSENCE);
			}
		}
	}
	
	//chargement de la batterie lorsque la voiture utilise le moteur essence
	protected void loadBatterie(double distance) {
		this.niveauBatterie += (ConfigMoteur.HYBRIDE_RECHARGE_BATTERIE * distance);
		
		if(this.niveauBatterie > ConfigVoiture.NIVEAU_HYBRIDE_BATTERIE_MAX) {
			this.niveauBatterie = ConfigVoiture.NIVEAU_HYBRIDE_BATTERIE_MAX;
		}
	}
	
	protected void recharger() {
		this.niveauReservoir += this.vitesseRechargementEssence * ConfigGlobal.FPS_RATE;
		this.niveauBatterie += this.vitesseRechargementElectrique * ConfigGlobal.FPS_RATE;
		
		if(this.niveauReservoir >= ConfigVoiture.NIVEAU_HYBRIDE_RESERVOIR_MAX) {
			this.niveauReservoir = ConfigVoiture.NIVEAU_HYBRIDE_RESERVOIR_MAX;
		}
		
		if(this.niveauBatterie >= ConfigVoiture.NIVEAU_HYBRIDE_BATTERIE_MAX) {
			this.niveauBatterie = ConfigVoiture.NIVEAU_HYBRIDE_BATTERIE_MAX;
		}
		
		if(this.niveauReservoir == ConfigVoiture.NIVEAU_HYBRIDE_RESERVOIR_MAX && this.niveauBatterie == ConfigVoiture.NIVEAU_HYBRIDE_BATTERIE_MAX) {
			this.isFilling = false;
		}
	}
	
	protected void afficher() {
		String moteurString;
		if(this.moteur.getType() == TypeMoteur.TYPE_ESSENCE) {
			moteurString = "essence";
		}
		else {
			moteurString = "electrique";
		}
		/*System.out.println("***************VOITURE_HYBRIDE*******************");
		System.out.println("Moteur : " + moteurString + " - Vitesse : " + this.cVitesse);
		System.out.println("Carburant " + this.niveauReservoir + " - Batterie : " + this.niveauBatterie);
		System.out.println("hasToFill : " + this.hasToFill + " - isFilling : " + this.isFilling);
		System.out.println("isStand : " + this.course.getCircuit().getSegmentAt(iSegment).isStand + " - avancement : " + this.cPosition.getAvancement() + " - hasFinished : " + this.hasFinished);
		System.out.println("--------------------------------------------------");*/
	}
	
	protected int getAutonomie() {
		int res;
	
		res = (int) (this.niveauReservoir / ConfigMoteur.MOTEUR_ESSENCE_CONSOMMATION);
		res += (int) ((res * ConfigMoteur.HYBRIDE_RECHARGE_BATTERIE + this.niveauBatterie) /  ConfigMoteur.MOTEUR_ELECTRIQUE_CONSOMMATION);
		
		return res;
	}

}
