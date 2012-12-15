
public class DepassementSegmentException extends Exception {
	
	protected double depassement;
	
	private static final long serialVersionUID = 42L;
	
	public DepassementSegmentException(double pDepassement) {
		super("DepassementSegmentException avec depassement de " + pDepassement);
		this.depassement = pDepassement;
	}
	
	public double getDepassement() {
		return this.depassement;
	}
}
