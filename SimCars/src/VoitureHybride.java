
public class VoitureHybride extends Voiture {
	
	protected double niveauBatterie;
	protected double niveauReservoir;
	protected double niveauBatterieMax;
	protected double niveauReservoirMax;
	
	public VoitureHybride(Course pCourse, Position pCPosition) {
		this.course = pCourse;
		this.type = Voiture.TYPE_VOITURE_HYBRIDE;
		this.cVitesse = 0;
		this.cPosition = pCPosition;
		this.habilite = ConfigVoiture.VOITURE_HYRIDE_HABILITE;
		this.niveauReservoirMax = ConfigVoiture.NIVEAU_HYBRIDE_RESERVOIR_MAX;
		this.niveauBatterieMax = ConfigVoiture.NIVEAU_HYBRIDE_BATTERIE_MAX;
	}
	
	public boolean hasToFill() {
		return this.niveauBatterie < ConfigVoiture.NIVEAU_RESERVOIR_MIN && this.niveauReservoir < ConfigVoiture.NIVEAU_BATTERIE_MIN;
	}
	
	public void switchMoteur() {
		if(moteur.getType() == TypeMoteur.TYPE_ELECTRIQUE) {
			this.moteur = new Moteur(TypeMoteur.TYPE_ESSENCE);
		}
		else {
			this.moteur = new Moteur(TypeMoteur.TYPE_ELECTRIQUE);
		}
	}
	
	public void updateConsommation(int distance) {
		if(this.moteur.getType() == TypeMoteur.TYPE_ESSENCE) {
			this.niveauReservoir -= (ConfigMoteur.MOTEUR_ESSENCE_CONSOMMATION * distance) * (this.cVitesse / this.moteur.getVitesseMax());
			this.niveauBatterie += (ConfigMoteur.HYBRIDE_RECHARGE_BATTERIE * distance);
			
			if(this.niveauBatterie > ConfigVoiture.NIVEAU_HYBRIDE_BATTERIE_MAX) {
				this.niveauBatterie = ConfigVoiture.NIVEAU_HYBRIDE_BATTERIE_MAX;
			}
		}
		else {
			this.niveauBatterie -= (ConfigMoteur.MOTEUR_ELECTRIQUE_CONSOMMATION * distance) * (this.cVitesse / this.moteur.getVitesseMax());
		}
		
		//mettre ˆ jour la batterie
		//alerter qu'il faut s'arreter si plus beaucoup de carbueant
	}

}
