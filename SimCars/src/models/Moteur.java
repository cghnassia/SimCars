package models;

public class Moteur {
	
	protected TypeMoteur type;
	protected int vitesseMax;
	protected double acceleration;
	protected double consommation;
	
	public Moteur(TypeMoteur pType) {
		this.type = pType;
		if(pType == TypeMoteur.TYPE_ESSENCE) {
			this.vitesseMax = ConfigMoteur.MOTEUR_ESSENCE_VITESSE_MAX;
			this.acceleration = ConfigMoteur.MOTEUR_ESSENCE_ACCELERATION;
			this.consommation = ConfigMoteur.MOTEUR_ESSENCE_CONSOMMATION;
		}
		else {
			this.vitesseMax = ConfigMoteur.MOTEUR_ELECTRIQUE_VITESSE_MAX;
			this.acceleration = ConfigMoteur.MOTEUR_ELECTRIQUE_ACCELERATION;
			this.consommation = ConfigMoteur.MOTEUR_ELECTRIQUE_CONSOMMATION;
		}
	}
	
	public TypeMoteur getType() {
		return this.type;
	}
	
	public int getVitesseMax() {
		return this.vitesseMax;
	}
	
	public double getAccelerationPotentielle(int vitesse) {
		
		double res;
		
		if(vitesse == this.vitesseMax) {
			res = 0;
		}
		else {
			res = this.acceleration * Math.pow(this.vitesseMax - vitesse, 2) / Math.pow(this.vitesseMax, 2);
			if(res + vitesse > vitesseMax) {
				res = vitesseMax - vitesse;
			}
		}
		
		//System.out.println("getAcceleration potentielle : " + res);

		return res;
	}
}
