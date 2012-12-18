import java.util.ArrayList;



public class Circuit {
	protected String nom;
	protected String description;
	protected int nbTours;
	protected ArrayList<Segment> segments;
	protected MapCircuit map;
	
	public Circuit(int pNbTours) {
		this.nbTours = pNbTours;
		this.segments = new ArrayList<Segment>();
	}
	
	public Segment getSegmentAt(int i) {
		return this.segments.get(i % this.segments.size());
	}
	
	public void setSegment(int i, Segment pSegment) {
		this.segments.set(i, pSegment);
	}
	
	public void addSegment(Segment pSegment) {
		this.segments.add(pSegment);
	}
	
	public void removeSegmentAt(int i) {
		this.segments.remove(i);
	}
	
	public int getNbTours() {
		return this.nbTours;
	}
	
	public void setNbTours(int pNbTours) {
		this.nbTours = pNbTours;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public void setNom(String pNom) {
		this.nom = pNom;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String pDescription) {
		this.description = pDescription;
	}
	
	public Segment getSegmentDepart() {
		return this.segments.get(0);
	}
	
	public boolean isLigneDarrivee(int i) {
		return (i % segments.size()) == 0;
	}
}
