
public class VoitureEssence extends Voiture {
	
	protected double niveauReservoir;
	protected double niveauReservoirMax;
	
	public VoitureEssence(Course pCourse) {
		this.course = pCourse;
		this.type = Voiture.TYPE_VOITURE_ESSENCE;
		this.cVitesse = 0;
		this.habilite = ConfigVoiture.VOITURE_ESSENCE_HABILITE;
		this.niveauReservoirMax = ConfigVoiture.NIVEAU_ESSENCE_RESERVOIR_MAX;
		this.vitesseRechargement = ConfigVoiture.VITESSE_RECHARGEMENT_ESSENCE;
		this.freinage = ConfigVoiture.ESSENCE_FREINAGE;
		this.moteur = new Moteur(TypeMoteur.TYPE_ESSENCE);
		this.niveauReservoir = this.niveauReservoirMax;
	}
	
	public boolean hasToFill() {
		boolean res = false;
		if(this.hasToFill) {
			res = true;
		}
		else if (niveauReservoir < ConfigVoiture.NIVEAU_RESERVOIR_MIN) {
			res = true;
			this.hasToFill = true;
		}
		
		return res;
	}
	
	protected void updateConsommation(int distance) {
		System.out.println("distance : " + distance + " - conso : " + (ConfigMoteur.MOTEUR_ESSENCE_CONSOMMATION * distance) * ((double) this.cVitesse / this.moteur.getVitesseMax()));
		this.niveauReservoir -= (ConfigMoteur.MOTEUR_ESSENCE_CONSOMMATION * distance) * ((double) this.cVitesse / this.moteur.getVitesseMax());
	
		if(this.niveauReservoir < ConfigVoiture.NIVEAU_RESERVOIR_MIN) {
			this.hasToFill = true;
		}
		
		System.out.println("niveau Reservoir : " + niveauReservoir + "/" + niveauReservoirMax);
	}
	
	protected void recharger() {
		this.niveauReservoir += this.vitesseRechargement * ConfigGlobal.FPS_RATE;
		if(this.niveauReservoir >= ConfigVoiture.NIVEAU_ESSENCE_RESERVOIR_MAX) {
			this.niveauReservoir = ConfigVoiture.NIVEAU_ESSENCE_RESERVOIR_MAX;
			this.isFilling = false;
		}
	}
	
	/*protected int getAutonomie() {
		
	}*/
	
}
