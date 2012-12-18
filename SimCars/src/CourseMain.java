import javax.swing.JFrame;


public class CourseMain extends JFrame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Course course = new Course();
		
		//construction du circuit
		Circuit circuit = new Circuit(3);
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, false, 1, 0));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_TURN_BOTTOM_TO_RIGHT, false, 0, 0));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_VERTICAL, false, 0, 1));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_VERTICAL, false, 0, 2));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_VERTICAL, false, 0, 3));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_TURN_TOP_TO_LEFT, false, 0, 4));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, false, 1, 4));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, false, 2, 4));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, false, 3, 4));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, false, 4, 4));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, true, 5, 4));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, false, 6, 4));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, false, 7, 4));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_TURN_BOTTOM_TO_LEFT, false, 8, 4));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_VERTICAL, false, 8, 3));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_VERTICAL, false, 8, 2));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_VERTICAL, false, 8, 1));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_TURN_BOTTOM_TO_LEFT, false, 8, 0));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, false, 7, 0));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, false, 6, 0));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, false, 5, 0));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, false, 4, 0));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, true, 3, 0));
		circuit.addSegment(SegmentFactory.getSegment(TypeSegment.TYPE_STRAIGHT_HORIZONTAL, false, 2, 0));
		
		VoitureElectrique voitureElectrique = new VoitureElectrique(course);
		VoitureEssence voitureEssence = new VoitureEssence(course);
		VoitureHybride voitureHybride = new VoitureHybride(course);
		
		course.init(circuit, voitureElectrique, voitureEssence, voitureHybride);
		
		//demarrer la course
		course.demarrer();
	}

}
