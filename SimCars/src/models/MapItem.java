package models;

public class MapItem {
	protected TypeSegment type;
	protected boolean isStand;
	protected boolean isLigneArrivee;
	
	public MapItem() {
		this.type = TypeSegment.TYPE_NONE;
		this.isLigneArrivee = false;
		this.isStand = false;
	}

	public TypeSegment getType() {
		return type;
	}

	public void setType(TypeSegment type) {
		this.type = type;
	}

	public boolean isStand() {
		return isStand;
	}

	public void setStand(boolean isStand) {
		this.isStand = isStand;
	}

	public boolean isLigneArrivee() {
		return isLigneArrivee;
	}

	public void setLigneArrivee(boolean isLigneArrivee) {
		this.isLigneArrivee = isLigneArrivee;
	}
}
