
public class VoitureElectrique extends Voiture {
	
	protected double niveauBatterie;
	
	public VoitureElectrique(Position pCPosition)  {
		this.type = Voiture.TYPE_VOITURE_ELECTRIQUE;
		this.cVitesse = 0;
		this.cPosition = pCPosition;
		this.habilite = ConfigVoiture.VOITURE_ELECTRIQUE_HABILITE;
	}
	
	public boolean hasToFill() {
		return this.niveauBatterie < ConfigVoiture.NIVEAU_BATTERIE_MIN;
	}
}
