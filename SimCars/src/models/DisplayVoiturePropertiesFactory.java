package models;

import java.awt.Point;

public class DisplayVoiturePropertiesFactory {
	
	protected static int POSITION_VOITURE_ELECTRIQUE = 40;
	protected static int POSITION_VOITURE_ESSENCE = 50;
	protected static int POSITION_VOITURE_HYBRIDE = 60;
	
	private void DispayVoiturePropertiesFactory() {
		//classe statique !
	}
	
	/*public static void init() {
		
	}*/
	
	public static DisplayVoitureProperties getDisplayVoitureProperties(TypeSegment type, TypeVoiture voiture, Direction direction, int avancement) {
		DisplayVoitureProperties res = null;
		
		///**********ATTENTION*****************//
		switch(type) {
			case TYPE_STRAIGHT_HORIZONTAL:
				res = DisplayVoiturePropertiesFactory.getDisplayVoiturePropertiesStraightHorizontal(voiture, direction, avancement);
				break;
			case TYPE_STRAIGHT_VERTICAL:
				res =DisplayVoiturePropertiesFactory.getDisplayVoiturePropertiesStraightVertical(voiture, direction, avancement);
				break;
			case TYPE_TURN_TOP_TO_RIGHT:
				res = DisplayVoiturePropertiesFactory.getDisplayVoiturePropertiesToptoRight(voiture, direction, avancement);
				break;
			case TYPE_TURN_TOP_TO_LEFT:
				res = DisplayVoiturePropertiesFactory.getDisplayVoiturePropertiesToptoLeft(voiture, direction, avancement);
				break;
			case TYPE_TURN_BOTTOM_TO_RIGHT:
				res = DisplayVoiturePropertiesFactory.getDisplayVoiturePropertiesBottomToRight(voiture, direction, avancement);
				break;
			case TYPE_TURN_BOTTOM_TO_LEFT:
				res = DisplayVoiturePropertiesFactory.getDisplayVoiturePropertiesBottomToLeft(voiture, direction, avancement);
				break;
			case TYPE_HARD_HORIZONTAL:
				res = DisplayVoiturePropertiesFactory.getDisplayVoiturePropertiesHardHorizontal(voiture, direction, avancement);
				break;
			case TYPE_HARD_VERTICAL:
				res = DisplayVoiturePropertiesFactory.getDisplayVoiturePropertiesHardVertical(voiture, direction, avancement);
				break;
			default :
				break;
			}
		
		return res;

		}
	
		public static DisplayVoitureProperties getDisplayVoiturePropertiesStraightHorizontal(TypeVoiture voiture, Direction direction, int avancement) {
			Point position = new Point();
			double rotation;
			
			
			switch(voiture) {
				case VOITURE_ESSENCE:
					position.y = POSITION_VOITURE_ESSENCE;
					break;
				case VOITURE_ELECTRIQUE:
					position.y = POSITION_VOITURE_ELECTRIQUE;
					break;
				case VOITURE_HYBRIDE:
					position.y = POSITION_VOITURE_HYBRIDE;
					break;
					
			}
			
			if(direction.getDestination() == TypeDirection.RIGHT) {
				position.x = avancement;
				position.y = 100 - position.y;
				rotation = 0;
			}
			else { //direction.getDestination() == TypeDirection.LEFT
				position.x = 100 - avancement;
				rotation = Math.PI;
			}

			return new DisplayVoitureProperties(rotation, position);
		}
		
		public static DisplayVoitureProperties getDisplayVoiturePropertiesStraightVertical(TypeVoiture voiture, Direction direction, int avancement) {
			Point position = new Point();
			double rotation;
			
			
			switch(voiture) {
				case VOITURE_ESSENCE:
					position.x = POSITION_VOITURE_ESSENCE;
					break;
				case VOITURE_ELECTRIQUE:
					position.x = POSITION_VOITURE_ELECTRIQUE;
				break;
				case VOITURE_HYBRIDE:
					position.x = POSITION_VOITURE_HYBRIDE;
				break;
				
			}
			
			if(direction.getDestination() == TypeDirection.BOTTOM) {
				position.y = avancement;
				rotation = Math.PI / 2;
			}
			else { //direction.getDestination() == TypeDirection.TOP
				position.x = 100 - position.x;
				position.y = 100 - avancement;
				rotation = -Math.PI / 2;
			}

			return new DisplayVoitureProperties(rotation, position);
		}
		
		public static DisplayVoitureProperties getDisplayVoiturePropertiesToptoRight(TypeVoiture voiture, Direction direction, int avancement) {
			Point p = new Point();
			double angle;
			double angleVoiture;
			int rayon = 0;
			
			switch(voiture) {
				case VOITURE_ESSENCE:
					rayon = 100 - POSITION_VOITURE_ESSENCE;
					break;
				case VOITURE_ELECTRIQUE:
					rayon = 100 - POSITION_VOITURE_ELECTRIQUE;
					break;
				case VOITURE_HYBRIDE:
					rayon = 100 - POSITION_VOITURE_HYBRIDE;
					break;
					
			}
			
			angle = ((double) avancement / 100) * (Math.PI / 2);
			
			if(direction.getDestination() == TypeDirection.RIGHT) {
				p.x = 100 - (int) (Math.cos(angle) * rayon);
				p.y = (int) (Math.sin(angle) * rayon);
				angleVoiture = -Math.PI / 2  - angle;
			}
			else {	// direction.getDestination() == TypeDirection.TOP
				p.x = 100 - (int) (Math.sin(angle) * rayon);
				p.y = (int) (Math.cos(angle) * rayon);
				angleVoiture = angle;
			}
			
			return new DisplayVoitureProperties((int) (angleVoiture), p);
			
		}
		
		public static DisplayVoitureProperties getDisplayVoiturePropertiesToptoLeft(TypeVoiture voiture, Direction direction, int avancement) {
			Point p = new Point();
			double angle;
			double angleVoiture;
			int rayon = 0;
			
			switch(voiture) {
				case VOITURE_ESSENCE:
					rayon = 100 - POSITION_VOITURE_ESSENCE;
					break;
				case VOITURE_ELECTRIQUE:
					rayon = 100 - POSITION_VOITURE_ELECTRIQUE;
					break;
				case VOITURE_HYBRIDE:
					rayon = 100 - POSITION_VOITURE_HYBRIDE;
					break;
				
			}
			
			angle = ((double) avancement / 100) * (Math.PI / 2);
			
			if(direction.getDestination() == TypeDirection.LEFT) {
				p.x = (int) (Math.cos(angle) * rayon);
				p.y = (int) (Math.sin(angle) * rayon);
				angleVoiture = -Math.PI / 2  - angle;
			}
			else {	// direction.getDestination() == TypeDirection.TOP
				p.x = (int) (Math.sin(angle) * rayon);
				p.y = (int) (Math.cos(angle) * rayon);
				angleVoiture = angle;
			}
			
			return new DisplayVoitureProperties(angleVoiture, p);
			
		}
		
		public static DisplayVoitureProperties getDisplayVoiturePropertiesBottomToRight(TypeVoiture voiture, Direction direction, int avancement) {
			Point p = new Point();
			double angle;
			double angleVoiture;
			int rayon = 0;
			
			switch(voiture) {
				case VOITURE_ESSENCE:
					rayon = 100 - POSITION_VOITURE_ESSENCE;
					break;
				case VOITURE_ELECTRIQUE:
					rayon = 100 - POSITION_VOITURE_ELECTRIQUE;
					break;
				case VOITURE_HYBRIDE:
					rayon = 100 - POSITION_VOITURE_HYBRIDE;
					break;	
			}
			
			angle = ((double) avancement / 100) * (Math.PI / 2);
			
			if(direction.getDestination() == TypeDirection.RIGHT) {
				p.x = 100 - (int) (Math.cos(angle) * rayon);
				p.y = 100 - (int) (Math.sin(angle) * rayon);
				angleVoiture = -Math.PI / 2  - angle;
			}
			else {	// direction.getDestination() == TypeDirection.BOTTOM
				p.x = 100 - (int) (Math.sin(angle) * rayon);
				p.y = 100 - (int) (Math.cos(angle) * rayon);
				angleVoiture = angle;
			}
			
			return new DisplayVoitureProperties(angleVoiture, p);
		}
		
		public static DisplayVoitureProperties getDisplayVoiturePropertiesBottomToLeft(TypeVoiture voiture, Direction direction, int avancement) {
			Point p = new Point();
			double angle;
			double angleVoiture;
			int rayon = 0;
			
			switch(voiture) {
				case VOITURE_ESSENCE:
					rayon = 100 - POSITION_VOITURE_ESSENCE;
					break;
				case VOITURE_ELECTRIQUE:
					rayon = 100 - POSITION_VOITURE_ELECTRIQUE;
					break;
				case VOITURE_HYBRIDE:
					rayon = 100 - POSITION_VOITURE_HYBRIDE;
					break;	
			}
			
			angle = ((double) avancement / 100) * (Math.PI / 2);
			
			if(direction.getDestination() == TypeDirection.LEFT) {
				p.x = (int) (Math.cos(angle) * rayon);
				p.y = 100 - (int) (Math.sin(angle) * rayon);
				angleVoiture = angle;
			}
			else { //direction.getDestination() == TypeDirection.BOTTOM
				p.x = (int) (Math.sin(angle) * rayon);
				p.y = 100 - (int) (Math.cos(angle) * rayon);
				angleVoiture = angle;
			}
			
			return new DisplayVoitureProperties(angleVoiture, p);
			
		}
		
		public static DisplayVoitureProperties getDisplayVoiturePropertiesHardHorizontal(TypeVoiture voiture, Direction direction, int avancement) {
			Point position = new Point();
			double rotation;
			
			
			switch(voiture) {
				case VOITURE_ESSENCE:
					position.y = POSITION_VOITURE_ESSENCE;
					break;
				case VOITURE_ELECTRIQUE:
					position.y = POSITION_VOITURE_ELECTRIQUE;
					break;
				case VOITURE_HYBRIDE:
					position.y = POSITION_VOITURE_HYBRIDE;
					break;
					
			}
			
			if(direction.getDestination() == TypeDirection.RIGHT) {
				position.x = avancement;
				position.y = 100 - position.y;
				rotation = 0;
			}
			else { //direction.getDestination() == TypeDirection.LEFT
				position.x = 100 - avancement;
				rotation = Math.PI;
			}

			return new DisplayVoitureProperties(rotation, position);
		}
		
		public static DisplayVoitureProperties getDisplayVoiturePropertiesHardVertical(TypeVoiture voiture, Direction direction, int avancement) {
			Point position = new Point();
			double rotation;
			
			
			switch(voiture) {
				case VOITURE_ESSENCE:
						position.x = POSITION_VOITURE_ESSENCE;
						break;
				case VOITURE_ELECTRIQUE:
						position.x = POSITION_VOITURE_ELECTRIQUE;
					break;
				case VOITURE_HYBRIDE:
						position.x = POSITION_VOITURE_HYBRIDE;
						break;
			}
			
			if(direction.getDestination() == TypeDirection.BOTTOM) {
				position.y = avancement;
				rotation = Math.PI / 2;
			}
			else { //direction.getDestination() == TypeDirection.TOP
				position.x = 100 - position.x;
				position.y = 100 - avancement;
				rotation = -Math.PI / 2;
			}

			return new DisplayVoitureProperties(rotation, position);
		}
	}

