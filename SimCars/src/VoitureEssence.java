
public class VoitureEssence extends Voiture {
	
	protected double niveauReservoir;
	protected double niveauReservoirMax;
	
	public VoitureEssence(Course pCourse, Position pCPosition) {
		this.course = pCourse;
		this.type = Voiture.TYPE_VOITURE_ESSENCE;
		this.cVitesse = 0;
		this.cPosition = pCPosition;
		this.habilite = ConfigVoiture.VOITURE_ESSENCE_HABILITE;
		this.niveauReservoirMax = ConfigVoiture.NIVEAU_ESSENCE_RESERVOIR_MAX;
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
		this.niveauReservoir -= (ConfigMoteur.MOTEUR_ESSENCE_CONSOMMATION * distance) * (this.cVitesse / this.moteur.getVitesseMax());
	
		if(this.niveauReservoir < ConfigVoiture.NIVEAU_RESERVOIR_MIN) {
			this.hasToFill = true;
		}
	}
	
	/*protected int getAutonomie() {
		
	}*/
	
}
