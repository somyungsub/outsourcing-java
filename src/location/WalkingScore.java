package location;

public class WalkingScore extends Location {
	private double walkingScore;

	// constructor
	public WalkingScore() {
        super();
	}
	
	// constructor
	public WalkingScore(double walkingScore) {
        this.walkingScore = walkingScore;
	}

	// constructor
	public WalkingScore(double latitude, double longitude) {
        super(latitude,longitude);
	}

	// constructor
	public WalkingScore(double latitude, double longitude, double walkingScore) {
        super(latitude,longitude);
        this.walkingScore = walkingScore;
	}

	// to return walkingScore
	public double getWalkingScore() {
        return 0;
	}

	// to set walkingScore
	public void setWalkingScore(double walkingScore) {

	}

	// to set latitude, longitude, and walkingScore simultaneously
	public void insertData(double latitude, double longitude, double walkingScore) {

	}
}