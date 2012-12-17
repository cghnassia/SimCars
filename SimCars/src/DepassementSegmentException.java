
public class DepassementSegmentException extends Exception {
	
	protected int depassement;
	
	private static final long serialVersionUID = 42L;
	
	public DepassementSegmentException(int pDepassement) {
		super("DepassementSegmentException avec depassement de " + pDepassement);
		this.depassement = pDepassement;
	}
	
	public double getDepassement() {
		return this.depassement;
	}
}
