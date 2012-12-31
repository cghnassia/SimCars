package models;

public class Position {
	protected Segment cSegment;
	protected int avancement;
	protected Direction direction; //true = normal, false = inverse
	
	public static final int AVANCEMENT_MAX = 100;
	
	public Position() {
		this.cSegment = null;
		this.avancement = 0;
		this.direction = new Direction(TypeDirection.RIGHT, TypeDirection.LEFT);
	}
	
	public Position(Segment pCSegment) {
		this.cSegment = pCSegment;
		this.avancement = 0;
		
		switch(this.cSegment.getType()) {
			case TYPE_STRAIGHT_HORIZONTAL:
			case TYPE_HARD_HORIZONTAL:
				this.direction = new Direction(TypeDirection.RIGHT, TypeDirection.LEFT);
				break;
			case TYPE_STRAIGHT_VERTICAL:
			case TYPE_HARD_VERTICAL:
				this.direction = new Direction(TypeDirection.TOP, TypeDirection.BOTTOM);
				break;
			case TYPE_TURN_TOP_TO_LEFT:
				this.direction = new Direction(TypeDirection.TOP, TypeDirection.LEFT);
				break;
			case TYPE_TURN_TOP_TO_RIGHT:
				this.direction = new Direction(TypeDirection.RIGHT,TypeDirection.TOP);
				break;
			case TYPE_TURN_BOTTOM_TO_LEFT:
				this.direction = new Direction(TypeDirection.BOTTOM, TypeDirection.LEFT);
				break;
			case TYPE_TURN_BOTTOM_TO_RIGHT:
				this.direction = new Direction(TypeDirection.RIGHT, TypeDirection.BOTTOM);
				break;
			case TYPE_NONE:
				this.direction = null;
				break;
		}
	}
	
	public Position(Segment pCSegment, int pAvancement, Direction pDirection) {
		this.cSegment = pCSegment;
		this.avancement = pAvancement;
		this.direction = pDirection;
	}
	
	public Segment getSegment() {
		return this.cSegment;	
	}
	
	public void setSegment(Segment pSegment) {
		this.cSegment = pSegment;
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public int getAvancement() {
		return this.avancement;
	}
	
	public void setPosition(Segment pCSegment, int pAvancement) throws DepassementSegmentException {
		
		this.cSegment = pCSegment;
		this.avancement = pAvancement;
		
		if(pAvancement > Position.AVANCEMENT_MAX) {
			this.avancement = Position.AVANCEMENT_MAX;
			throw new DepassementSegmentException(Position.AVANCEMENT_MAX - this.avancement);
		}
	}
	
	public void update(double distance) throws DepassementSegmentException {
		this.avancement += distance;
		if(this.avancement > Position.AVANCEMENT_MAX) {
			this.avancement = Position.AVANCEMENT_MAX;
			throw new DepassementSegmentException(Position.AVANCEMENT_MAX - this.avancement);
		}
	}

}
