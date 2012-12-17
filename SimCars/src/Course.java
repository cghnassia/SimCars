
public class Course {
	protected Circuit circuit;
	
	protected VoitureElectrique voitureElectrique;
	protected VoitureEssence voitureEssence;
	protected VoitureHybride voitureHybride;
	
	public Course() {
		this.circuit = null;
		this.voitureElectrique = null;
		this.voitureEssence = null;
		this.voitureHybride = null;
	}
	
	public Course(Circuit pCircuit, VoitureElectrique pVoitureElectrique, VoitureEssence pVoitureEssence, VoitureHybride pVoitureHybride) {
		this.circuit = pCircuit;
		this.voitureElectrique = pVoitureElectrique;
		this.voitureEssence = pVoitureEssence;
		this.voitureHybride = pVoitureHybride;
	}
	
	public Circuit getCircuit() {
		return this.circuit;
	}
	
	public void setCircuit(Circuit pCircuit) {
		this.circuit = pCircuit;
	}
	
	public VoitureElectrique getVoitureElectrique() {
		return this.voitureElectrique;
	}
	
	public void setVoitureElectrique(VoitureElectrique pVoitureElectrique) {
		this.voitureElectrique = pVoitureElectrique;
	}
	
	public VoitureEssence getVoitureEssence() {
		return this.voitureEssence;
	}
	
	public void setVoitureEssence(VoitureEssence pVoitureEssence) {
		this.voitureEssence = pVoitureEssence;
	}
	
	public VoitureHybride getVoitureHybride() {
		return this.voitureHybride;
	}
	
	public void setVoitureHybride(VoitureHybride pVoitureHybride) {
		this.voitureHybride = pVoitureHybride;
	}
	
}
