
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
		boolean res = false;
		if(this.hasToFill) {
			res = true;
		}
		else if (this.niveauBatterie < ConfigVoiture.NIVEAU_RESERVOIR_MIN && this.niveauReservoir < ConfigVoiture.NIVEAU_BATTERIE_MIN) {
			res = true;
			this.hasToFill = true;
		}
		
		return res; 
	}
	
	public void switchMoteur(TypeMoteur pMoteur) {
		this.moteur = new Moteur(pMoteur);
	}
	
	public void updateConsommation(int distance) {
		if(this.moteur.getType() == TypeMoteur.TYPE_ESSENCE) {
			this.niveauReservoir -= (ConfigMoteur.MOTEUR_ESSENCE_CONSOMMATION * distance) * (this.cVitesse / this.moteur.getVitesseMax());
			loadBatterie(distance);
			
			if(this.niveauReservoir < ConfigVoiture.NIVEAU_RESERVOIR_MIN && this.niveauBatterie > ConfigVoiture.NIVEAU_RESERVOIR_MIN) {
				switchMoteur(TypeMoteur.TYPE_ELECTRIQUE);
			}
		}
		else { //this.moteur.getType() == TypeMoteur.TYPE_ELECTRIQUE
			this.niveauBatterie -= (ConfigMoteur.MOTEUR_ELECTRIQUE_CONSOMMATION * distance) * (this.cVitesse / this.moteur.getVitesseMax());
		
			if(this.niveauBatterie < ConfigVoiture.NIVEAU_BATTERIE_MIN && this.niveauReservoir > ConfigVoiture.NIVEAU_RESERVOIR_MIN) {
				switchMoteur(TypeMoteur.TYPE_ESSENCE);
			}
		}
		
		if(this.niveauReservoir < ConfigVoiture.NIVEAU_RESERVOIR_MIN && this.niveauBatterie < ConfigVoiture.NIVEAU_BATTERIE_MIN) {
			this.hasToFill = true;
		}
		
		//mettre ˆ jour la batterie
		//changer de moteur si nŽcessaire
		//alerter qu'il faut s'arreter si plus beaucoup de carbueant
	}
	
	//chargement de la batterie lorsque la voiture utilise le moteur essence
	protected void loadBatterie(int distance) {
		this.niveauBatterie += (ConfigMoteur.HYBRIDE_RECHARGE_BATTERIE * distance);
		
		if(this.niveauBatterie > ConfigVoiture.NIVEAU_HYBRIDE_BATTERIE_MAX) {
			this.niveauBatterie = ConfigVoiture.NIVEAU_HYBRIDE_BATTERIE_MAX;
		}
	}

}
