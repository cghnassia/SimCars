package models;

public class VoitureElectrique extends Voiture {
	
	
	protected double niveauBatterie;
	protected double niveauBatterieMax;
	protected double vitesseRechargement;
	
	public VoitureElectrique(CourseModel pCourse)  {
		this.course = pCourse;
		this.type = TypeVoiture.VOITURE_ELECTRIQUE;
		this.cVitesse = 0;
		this.habilite = ConfigVoiture.VOITURE_ELECTRIQUE_HABILITE;
		this.niveauBatterieMax = ConfigVoiture.NIVEAU_ELECTRIQUE_BATTERIE_MAX;
		this.freinage = ConfigVoiture.ELECTRIQUE_FREINAGE;
		this.vitesseRechargement = ConfigVoiture.VITESSE_RECHARGEMENT_ELECTRIQUE;
		this.moteur = new Moteur(TypeMoteur.TYPE_ELECTRIQUE);
		this.niveauBatterie = this.niveauBatterieMax;
	}
	
	public double getNiveauBatterie() {
		return this.niveauBatterie;
	}
	
	public double getNiveauBatterieMax() {
		return this.niveauBatterieMax;
	}
	
	protected void updateConsommation(int distance) {
		//System.out.println("distance : " + distance);
		this.niveauBatterie -= (ConfigMoteur.MOTEUR_ELECTRIQUE_CONSOMMATION * distance) * (this.cVitesse / this.moteur.getVitesseMax());	
	}
	
	protected void recharger() {
		this.niveauBatterie += this.vitesseRechargement * ConfigGlobal.FPS_RATE;
		if(this.niveauBatterie >= ConfigVoiture.NIVEAU_ESSENCE_RESERVOIR_MAX) {
			this.niveauBatterie = ConfigVoiture.NIVEAU_ESSENCE_RESERVOIR_MAX;
			this.isFilling = false;
		}
	}
	
	protected void afficher() {
		
	}
	
	protected int getAutonomie() {
		return (int) (this.niveauBatterie / ConfigMoteur.MOTEUR_ELECTRIQUE_CONSOMMATION);
	}
	
	/*protected int getAutonomie() {
		return (int) (this.niveauBatterie / (double) ConfigMoteur.MOTEUR_ELECTRIQUE_CONSOMMATION);
	}*/
}
