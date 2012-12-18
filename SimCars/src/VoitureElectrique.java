
public class VoitureElectrique extends Voiture {
	
	
	protected double niveauBatterie;
	protected double niveauBatterieMax;
	
	public VoitureElectrique(Course pCourse)  {
		this.course = pCourse;
		this.type = Voiture.TYPE_VOITURE_ELECTRIQUE;
		this.cVitesse = 0;
		this.habilite = ConfigVoiture.VOITURE_ELECTRIQUE_HABILITE;
		this.niveauBatterieMax = ConfigVoiture.NIVEAU_ELECTRIQUE_BATTERIE_MAX;
		this.freinage = ConfigVoiture.ELECTRIQUE_FREINAGE;
		this.vitesseRechargement = ConfigVoiture.VITESSE_RECHARGEMENT_ELECTRIQUE;
		this.moteur = new Moteur(TypeMoteur.TYPE_ELECTRIQUE);
		this.niveauBatterie = this.niveauBatterieMax;
	}
	
	public boolean hasToFill() {
		boolean res = false;
		if(this.hasToFill) {
			res = true;
		}
		else if (this.niveauBatterie < ConfigVoiture.NIVEAU_BATTERIE_MIN) {
			res = true;
			this.hasToFill = true;
		}
		
		return res;
	}
	
	protected void updateConsommation(int distance) {
		//System.out.println("distance : " + distance);
		this.niveauBatterie -= (ConfigMoteur.MOTEUR_ELECTRIQUE_CONSOMMATION * distance) * (this.cVitesse / this.moteur.getVitesseMax());	
		
		if(this.niveauBatterie < ConfigVoiture.NIVEAU_BATTERIE_MIN) {
			this.hasToFill = true;
		}
	}
	
	protected void recharger() {
		
	}
	
	/*protected int getAutonomie() {
		return (int) (this.niveauBatterie / (double) ConfigMoteur.MOTEUR_ELECTRIQUE_CONSOMMATION);
	}*/
}
