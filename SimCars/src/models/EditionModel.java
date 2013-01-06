package models;

public class EditionModel {
	protected MapItem[][] map;
	
	public EditionModel() {
		this.map = new MapItem[ConfigCircuit.NB_CASES_HEIGHT][ConfigCircuit.NB_CASES_WIDTH];
		
		for(int i = 0; i < ConfigCircuit.NB_CASES_HEIGHT; i++) {
			for(int j = 0; j < ConfigCircuit.NB_CASES_WIDTH; j++) {
				this.map[i][j] = new MapItem();
			}
		}
	}
	
	public boolean validateAll() {
		return true;
	}
	

	public boolean validateItem(int type, int ligne, int colonne) {
		
		boolean isSegment = true;
		TypeSegment typeSegment = TypeSegment.TYPE_NONE;
		boolean res = false;
		
		switch(type) {
			case TypeItem.SEGMENT_STRAIGHT_HORIZONTAL:
				res = validateSegmentHorizontal(ligne, colonne);
				typeSegment = TypeSegment.TYPE_STRAIGHT_HORIZONTAL;
				break;
			case TypeItem.SEGMENT_HARD_HORIZONTAL:
				res = validateSegmentHorizontal(ligne, colonne);
				typeSegment = TypeSegment.TYPE_HARD_HORIZONTAL;
				break;
			case TypeItem.SEGMENT_STRAIGHT_VERTICAL:
				res = validateSegmentVertical(ligne, colonne);
				typeSegment = TypeSegment.TYPE_STRAIGHT_VERTICAL;
				break;
			case TypeItem.SEGMENT_HARD_VERTICAL:
				res = validateSegmentVertical(ligne, colonne);
				typeSegment = TypeSegment.TYPE_STRAIGHT_VERTICAL;
				break;
			case TypeItem.SEGMENT_TOP_LEFT:
				res = validateSegmentTopLeft(ligne, colonne);
				typeSegment = TypeSegment.TYPE_TURN_TOP_TO_LEFT;
				break;
			case TypeItem.SEGMENT_TOP_RIGHT:
				res = validateSegmentTopRight(ligne, colonne);
				typeSegment = TypeSegment.TYPE_TURN_TOP_TO_RIGHT;
				break;
			case TypeItem.SEGMENT_BOTTOM_LEFT:
				res = validateSegmentBottomLeft(ligne, colonne);
				typeSegment = TypeSegment.TYPE_TURN_BOTTOM_TO_LEFT;
				break;
			case TypeItem.SEGMENT_BOTTOM_RIGHT:
				res = validateSegmentBottomRight(ligne, colonne);
				typeSegment = TypeSegment.TYPE_TURN_BOTTOM_TO_RIGHT;
				break;
			case TypeItem.SEGMENT_NONE:
				res = true;
				typeSegment = TypeSegment.TYPE_NONE;
				break;
			case TypeItem.ARRIVEE_HORIZONTAL:
			case TypeItem.ARRIVEE_VERTICAL:
				res = validateArrivee(ligne, colonne);
				isSegment = false;
				break;
			case TypeItem.STAND:
				res = validateStand(ligne, colonne);
				isSegment = false;
				break;		
				
		}
		
		if(res && isSegment) {
			map[ligne][colonne] = new MapItem();
			map[ligne][colonne].setType(typeSegment);
		}
		else if (res && (type == TypeItem.ARRIVEE_HORIZONTAL || type == TypeItem.ARRIVEE_VERTICAL)) {
			map[ligne][colonne].setLigneArrivee(true);
			//System.out.println("arrivee : " + ligne + " - " + colonne);
		}
		else if(res) { // STAND
			map[ligne][colonne].setStand(true);
		}
		
		return res;
	}
	
	protected boolean validateSegmentHorizontal(int ligne, int colonne) {
		
		boolean res = true;
		if(colonne == 0 || colonne >= ConfigCircuit.NB_CASES_WIDTH - 1) {
			res = false;
		}
		
		if(res){	//gauche
			MapItem itemLeft = map[ligne][colonne - 1];
			switch(itemLeft.getType()) {
				case TYPE_STRAIGHT_VERTICAL:
				case TYPE_HARD_VERTICAL:
				case TYPE_TURN_TOP_TO_LEFT:
				case TYPE_TURN_BOTTOM_TO_LEFT:
					res = false;
					break;
				default:
					res = true;
			}
		}
		
		if(res) {
			MapItem itemRight = map[ligne][colonne + 1];
			switch(itemRight.getType()) {
				case TYPE_STRAIGHT_VERTICAL:
				case TYPE_HARD_VERTICAL:
				case TYPE_TURN_TOP_TO_RIGHT:
				case TYPE_TURN_BOTTOM_TO_RIGHT:
					res = false;
					break;
				default:
					res = true;
			}
		}
		
		if(res && ligne > 0) {
			MapItem itemTop = map[ligne - 1][colonne];
			switch(itemTop.getType()) {
				case TYPE_STRAIGHT_VERTICAL:
				case TYPE_HARD_VERTICAL:
				case TYPE_TURN_BOTTOM_TO_LEFT:
				case TYPE_TURN_BOTTOM_TO_RIGHT:
					res = false;
					break;
				default:
					res = true;
			}
		}
			
		if(res && ligne < ConfigCircuit.NB_CASES_HEIGHT - 1) {
			MapItem itemBottom = map[ligne + 1][colonne];
			switch(itemBottom.getType()) {
				case TYPE_STRAIGHT_VERTICAL:
				case TYPE_HARD_VERTICAL:
				case TYPE_TURN_TOP_TO_LEFT:
				case TYPE_TURN_TOP_TO_RIGHT:
					res = false;
					break;
				default:
					res = true;
			}
		}
		
		
		return res;
	}
	
	
	protected boolean validateSegmentVertical(int ligne, int colonne) {
		
		boolean res = true;
		if(ligne == 0 || ligne >= ConfigCircuit.NB_CASES_HEIGHT - 1) {
			res = false;
		}
		
		if(res && colonne > 0){	//gauche
			MapItem itemLeft = map[ligne][colonne - 1];
			switch(itemLeft.getType()) {
				case TYPE_STRAIGHT_HORIZONTAL:
				case TYPE_HARD_HORIZONTAL:
				case TYPE_TURN_TOP_TO_RIGHT:
				case TYPE_TURN_BOTTOM_TO_RIGHT:
					res = false;
					break;
				default:
					res = true;
			}
		}
		
		if(res && colonne < ConfigCircuit.NB_CASES_WIDTH - 1) {
			MapItem itemRight = map[ligne][colonne + 1];
			switch(itemRight.getType()) {
				case TYPE_STRAIGHT_HORIZONTAL:
				case TYPE_HARD_HORIZONTAL:
				case TYPE_TURN_TOP_TO_LEFT:
				case TYPE_TURN_BOTTOM_TO_LEFT:
					res = false;
					break;
				default:
					res = true;
			}
		}
		
		if(res) {
			MapItem itemTop = map[ligne - 1][colonne];
			switch(itemTop.getType()) {
				case TYPE_STRAIGHT_HORIZONTAL:
				case TYPE_HARD_HORIZONTAL:
				case TYPE_TURN_TOP_TO_LEFT:
				case TYPE_TURN_TOP_TO_RIGHT:
					res = false;
					break;
				default:
					res = true;
			}
		}
			
		if(res) {
			MapItem itemBottom = map[ligne + 1][colonne];
			switch(itemBottom.getType()) {
				case TYPE_STRAIGHT_HORIZONTAL:
				case TYPE_HARD_HORIZONTAL:
				case TYPE_TURN_BOTTOM_TO_LEFT:
				case TYPE_TURN_BOTTOM_TO_RIGHT:
					res = false;
					break;
				default:
					res = true;
			}
		}
		
		
		return res;
	}
	
	protected boolean validateSegmentTopLeft(int ligne, int colonne) {
		
		boolean res = true;
		if(ligne == 0 || colonne == 0) {
			res = false;
		}
		
		if(res){	//gauche
			MapItem itemLeft = map[ligne][colonne - 1];
			switch(itemLeft.getType()) {
				case TYPE_STRAIGHT_VERTICAL:
				case TYPE_HARD_VERTICAL:
				case TYPE_TURN_TOP_TO_LEFT:
				case TYPE_TURN_BOTTOM_TO_LEFT:
					res = false;
					break;
				default:
					res = true;
			}
		}
		
		if(res && colonne < ConfigCircuit.NB_CASES_WIDTH - 1) {
			MapItem itemRight = map[ligne][colonne + 1];
			switch(itemRight.getType()) {
				case TYPE_STRAIGHT_HORIZONTAL:
				case TYPE_HARD_HORIZONTAL:
				case TYPE_TURN_TOP_TO_RIGHT:
				case TYPE_TURN_BOTTOM_TO_RIGHT:
					res = false;
					break;
				default:
					res = true;
			}
		}
		
		if(res) {
			MapItem itemTop = map[ligne - 1][colonne];
			switch(itemTop.getType()) {
				case TYPE_STRAIGHT_HORIZONTAL:
				case TYPE_HARD_HORIZONTAL:
				case TYPE_TURN_TOP_TO_LEFT:
				case TYPE_TURN_TOP_TO_RIGHT:
					res = false;
					break;
				default:
					res = true;
			}
		}
			
		if(res && ligne < ConfigCircuit.NB_CASES_HEIGHT - 1) {
			MapItem itemBottom = map[ligne + 1][colonne];
			switch(itemBottom.getType()) {
				case TYPE_STRAIGHT_VERTICAL:
				case TYPE_HARD_VERTICAL:
				case TYPE_TURN_TOP_TO_LEFT:
				case TYPE_TURN_TOP_TO_RIGHT:
					res = false;
					break;
				default:
					res = true;
			}
		}
		
		
		return res;
	}
	
	protected boolean validateSegmentTopRight(int ligne, int colonne) {
		
		boolean res = true;
		if(ligne == 0 || colonne >= ConfigCircuit.NB_CASES_WIDTH - 1) {
			res = false;
		}
		
		if(res && colonne > 0){	//gauche
			MapItem itemLeft = map[ligne][colonne - 1];
			switch(itemLeft.getType()) {
				case TYPE_STRAIGHT_HORIZONTAL:
				case TYPE_HARD_HORIZONTAL:
				case TYPE_TURN_TOP_TO_RIGHT:
				case TYPE_TURN_BOTTOM_TO_RIGHT:
					res = false;
					break;
				default:
					res = true;
			}
		}
		
		if(res) {
			MapItem itemRight = map[ligne][colonne + 1];
			switch(itemRight.getType()) {
				case TYPE_STRAIGHT_VERTICAL:
				case TYPE_HARD_VERTICAL:
				case TYPE_TURN_TOP_TO_RIGHT:
				case TYPE_TURN_BOTTOM_TO_RIGHT:
					res = false;
					break;
				default:
					res = true;
			}
		}
		
		if(res) {
			MapItem itemTop = map[ligne - 1][colonne];
			switch(itemTop.getType()) {
				case TYPE_STRAIGHT_HORIZONTAL:
				case TYPE_HARD_HORIZONTAL:
				case TYPE_TURN_TOP_TO_LEFT:
				case TYPE_TURN_TOP_TO_RIGHT:
					res = false;
					break;
				default:
					res = true;
			}
		}
			
		if(res && ligne < ConfigCircuit.NB_CASES_HEIGHT - 1) {
			MapItem itemBottom = map[ligne + 1][colonne];
			switch(itemBottom.getType()) {
				case TYPE_STRAIGHT_VERTICAL:
				case TYPE_HARD_VERTICAL:
				case TYPE_TURN_TOP_TO_LEFT:
				case TYPE_TURN_TOP_TO_RIGHT:
					res = false;
					break;
				default:
					res = true;
			}
		}
		
		
		return res;
	}
	
	protected boolean validateSegmentBottomLeft(int ligne, int colonne) {
		
		boolean res = true;
		if(ligne >= ConfigCircuit.NB_CASES_HEIGHT - 1 || colonne == 0) {
			res = false;
		}
		
		if(res){	//gauche
			MapItem itemLeft = map[ligne][colonne - 1];
			switch(itemLeft.getType()) {
				case TYPE_STRAIGHT_VERTICAL:
				case TYPE_HARD_VERTICAL:
				case TYPE_TURN_TOP_TO_LEFT:
				case TYPE_TURN_BOTTOM_TO_LEFT:
					res = false;
					break;
				default:
					res = true;
			}
		}
		
		if(res && colonne < ConfigCircuit.NB_CASES_HEIGHT - 1) {
			MapItem itemRight = map[ligne][colonne + 1];
			switch(itemRight.getType()) {
				case TYPE_STRAIGHT_HORIZONTAL:
				case TYPE_HARD_HORIZONTAL:
				case TYPE_TURN_TOP_TO_LEFT:
				case TYPE_TURN_BOTTOM_TO_LEFT:
					res = false;
					break;
				default:
					res = true;
			}
		}
		
		if(res && ligne > 0) {
			MapItem itemTop = map[ligne - 1][colonne];
			switch(itemTop.getType()) {
				case TYPE_STRAIGHT_VERTICAL:
				case TYPE_HARD_VERTICAL:
				case TYPE_TURN_BOTTOM_TO_LEFT:
				case TYPE_TURN_BOTTOM_TO_RIGHT:
					res = false;
					break;
				default:
					res = true;
			}
		}
			
		if(res) {
			MapItem itemBottom = map[ligne + 1][colonne];
			switch(itemBottom.getType()) {
				case TYPE_STRAIGHT_HORIZONTAL:
				case TYPE_HARD_VERTICAL:
				case TYPE_TURN_BOTTOM_TO_LEFT:
				case TYPE_TURN_BOTTOM_TO_RIGHT:
					res = false;
					break;
				default:
					res = true;
			}
		}
		
		
		return res;
	}
	
	protected boolean validateSegmentBottomRight(int ligne, int colonne) {
		
		boolean res = true;
		if(ligne >= ConfigCircuit.NB_CASES_HEIGHT - 1 || colonne >= ConfigCircuit.NB_CASES_WIDTH - 1) {
			res = false;
		}
		
		if(res && colonne > 0){	//gauche
			MapItem itemLeft = map[ligne][colonne - 1];
			switch(itemLeft.getType()) {
				case TYPE_STRAIGHT_HORIZONTAL:
				case TYPE_HARD_HORIZONTAL:
				case TYPE_TURN_TOP_TO_RIGHT:
				case TYPE_TURN_BOTTOM_TO_RIGHT:
					res = false;
					break;
				default:
					res = true;
			}
		}
		
		if(res) {
			MapItem itemRight = map[ligne][colonne + 1];
			switch(itemRight.getType()) {
				case TYPE_STRAIGHT_VERTICAL:
				case TYPE_HARD_VERTICAL:
				case TYPE_TURN_TOP_TO_RIGHT:
				case TYPE_TURN_BOTTOM_TO_RIGHT:
					res = false;
					break;
				default:
					res = true;
			}
		}
		
		if(res && ligne > 0) {
			MapItem itemTop = map[ligne - 1][colonne];
			switch(itemTop.getType()) {
				case TYPE_STRAIGHT_VERTICAL:
				case TYPE_HARD_VERTICAL:
				case TYPE_TURN_BOTTOM_TO_LEFT:
				case TYPE_TURN_BOTTOM_TO_RIGHT:
					res = false;
					break;
				default:
					res = true;
			}
		}
			
		if(res) {
			MapItem itemBottom = map[ligne + 1][colonne];
			switch(itemBottom.getType()) {
				case TYPE_STRAIGHT_HORIZONTAL:
				case TYPE_HARD_HORIZONTAL:
				case TYPE_TURN_BOTTOM_TO_LEFT:
				case TYPE_TURN_BOTTOM_TO_RIGHT:
					res = false;
					break;
				default:
					res = true;
			}
		}
		
		
		return res;
	}
	
	protected boolean validateArrivee(int ligne, int colonne) {
		boolean res = true;
		
		MapItem item = map[ligne][colonne];
		switch(item.getType()) {
			case TYPE_STRAIGHT_HORIZONTAL:
			case TYPE_HARD_HORIZONTAL:
			case TYPE_STRAIGHT_VERTICAL:
			case TYPE_HARD_VERTICAL:
				res = true;
				break;
			default:
				res = true;
		}
		
		if(res) {
			boolean trouve = false;
			for(int i = 0; i < ConfigCircuit.NB_CASES_HEIGHT; i++) {
				for(int j = 0; j < ConfigCircuit.NB_CASES_WIDTH; j++) {
					item = map[i][j];
					if(item.isLigneArrivee) {
						res = false;
						trouve = true;
						break;
					}
					if(trouve) {
						break;
					}
				}
			}
		}
		
		return res;
	}
	
	protected boolean validateStand(int ligne, int colonne) {
		boolean res = true;
		
		MapItem item = map[ligne][colonne];
		if(item.getType() == TypeSegment.TYPE_NONE) {
			res = false;
		}
		
		if(item.isStand()) {
			res = false;
		}
		
		return res;
	}
	
	public int getArriveeDirection() {
		int res = 10;
		
		boolean isArrivee = false;
		MapItem item = null;
		int i = 0, j = 0;
		for(i = 0; i < ConfigCircuit.NB_CASES_HEIGHT; i++) {
			for(j = 0; j < ConfigCircuit.NB_CASES_WIDTH; j++) {
				item = map[i][j];
				if(item.isLigneArrivee) {
					isArrivee = true;
					break;
				}
			}
			if(isArrivee) {
				break;
			}
		}
		
		if(isArrivee) {
			switch(item.getType()) {
				case TYPE_STRAIGHT_HORIZONTAL:
				case TYPE_HARD_HORIZONTAL:
					res = TypeItem.ARRIVEE_HORIZONTAL;;
					break;
				default:
					res = TypeItem.ARRIVEE_VERTICAL;
					break;
			}
		}
		
		return res;
	}
}
