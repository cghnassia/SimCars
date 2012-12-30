package models;

public class Direction {
	
	protected TypeDirection origine;
	protected TypeDirection destination;
	
	public Direction(TypeDirection pOrigine, TypeDirection pDestination) {
		this.origine = pOrigine;
		this.destination = pDestination;
	}
	
	public TypeDirection getOrigine() {
		return this.origine;
	}
	
	public void setOrigine(TypeDirection pOrigine) {
		this.origine = pOrigine;
	}
	
	public TypeDirection getDestination() {
		return this.destination;
	}
	
	public void setDestination(TypeDirection pDestination) {
		this.destination = pDestination;
	}
}
