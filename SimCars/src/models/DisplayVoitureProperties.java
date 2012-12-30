package models;

import java.awt.Point;

//permet de connaitre la position d'une voiture sur un segment
public class DisplayVoitureProperties {

	/**
	 * @param args
	 */
	protected double rotation;
	protected Point position;
	
	public DisplayVoitureProperties(double pRotation, Point pPosition) {
		this.rotation = pRotation;
		this.position = pPosition;
	}
	
	public double getRotation() {
		return this.rotation;
	}

	public Point getPosition() {
		return this.position;
	}
	
}
