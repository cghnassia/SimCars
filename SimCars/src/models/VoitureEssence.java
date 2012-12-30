package models;

public class VoitureEssence extends Voiture {
	
	protected double niveauReservoir;
	protected double niveauReservoirMax;
	
	public VoitureEssence(CourseModel pCourse) {
		this.course = pCourse;
		this.type = TypeVoiture.VOITURE_ESSENCE;
		this.cVitesse = 0;
		this.habilite = ConfigVoiture.VOITURE_ESSENCE_HABILITE;
		this.niveauReservoirMax = ConfigVoiture.NIVEAU_ESSENCE_RESERVOIR_MAX;
		this.vitesseRechargement = ConfigVoiture.VITESSE_RECHARGEMENT_ESSENCE;
		this.freinage = ConfigVoiture.ESSENCE_FREINAGE;
		this.moteur = new Moteur(TypeMoteur.TYPE_ESSENCE);
		this.niveauReservoir = this.niveauReservoirMax;
	}
	
	public boolean hasToFill() {
		/*boolean res = false;
		if(this.hasToFill) {
			res = true;
		}
		else if (niveauReservoir < ConfigVoiture.NIVEAU_RESERVOIR_MIN) {
			res = true;
		}*/
		
		return this.hasToFill;
	}
	
	protected void updateConsommation(int distance) {
		//System.out.println("distance : " + distance + " - conso : " + (ConfigMoteur.MOTEUR_ESSENCE_CONSOMMATION * distance) * ((double) this.cVitesse / this.moteur.getVitesseMax()));
		this.niveauReservoir -= (ConfigMoteur.MOTEUR_ESSENCE_CONSOMMATION * distance) * ((double) this.cVitesse / this.moteur.getVitesseMax());
	
		/*if(this.niveauReservoir < ConfigVoiture.NIVEAU_RESERVOIR_MIN) {
			this.hasToFill = true;
		}*/
		
		//System.out.println("niveau Reservoir : " + niveauReservoir + "/" + niveauReservoirMax);
	}
	
	protected void recharger() {
		this.niveauReservoir += this.vitesseRechargement * ConfigGlobal.FPS_RATE;
		if(this.niveauReservoir >= ConfigVoiture.NIVEAU_ESSENCE_RESERVOIR_MAX) {
			this.niveauReservoir = ConfigVoiture.NIVEAU_ESSENCE_RESERVOIR_MAX;
			this.isFilling = false;
		}
	}
	
	protected void afficher() {
		/*System.out.println("--------Voiture essence----------------------");
		System.out.println("Position : " + this.iSegment + " ("  + this.cPosition.getAvancement() + "/100)" + " Stand : " + this.course.getCircuit().getSegmentAt(iSegment).isStand);
		System.out.println("Vitesse : " + this.cVitesse);
		System.out.println("Vitesse max sur le segment : " + this.course.getCircuit().getSegmentAt(this.iSegment).vitesseMax);
		System.out.println("Vitesse max effective sur le segment : " + this.vitesseMaxSegment);
		System.out.println("Habilité : " + this.habilite);
		System.out.println("Niveau de réservoir : " + this.niveauReservoir); 
		System.out.println("Doit faire le plein : " + this.hasToFill);
		System.out.println("Fait le plein : " + this.isFilling);
		System.out.println("--------------------------------------------\n");*/
	}
	
	protected int getAutonomie() {
		return (int) ((double) this.niveauReservoir / ConfigMoteur.MOTEUR_ESSENCE_CONSOMMATION);
	}
	
}
