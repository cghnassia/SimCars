package models;
import java.awt.Point;
import java.util.ArrayList;


public class MapCircuit {
	
	TypeSegment[][] tab;
	Point start;
	
	public MapCircuit() {
		this.tab = new TypeSegment[ConfigCircuit.NB_CASES_HEIGHT][ConfigCircuit.NB_CASES_WIDTH];
		start = new Point(0,0);
	}
	
	//créer une map vierge
	public void init() {
		for(int i = 0; i < ConfigCircuit.NB_CASES_HEIGHT; i++) {
			for(int j = 0; j < ConfigCircuit.NB_CASES_WIDTH; i++) {
				tab[i][j] = TypeSegment.TYPE_NONE;
			}
		}
	}
	
	public TypeSegment getSegmentAt(Point p) {
		return tab[p.y][p.x];
	}
	
	public void setSegmentAt(Point p, TypeSegment type) {
		this.tab[p.x][p.y] = type;
	}
	
	public ArrayList<Segment> toArray() {
		ArrayList<Segment> res = new ArrayList<Segment>(); 
		
		return res;
	}
}
