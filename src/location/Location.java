package location;

public class Location {
	private double latitude;
	private double longitude;

	// constructor
	public Location() {
	}

	// constructor
	public Location(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	// to return latitude
	public double getLatitude() {
		return latitude;
	}

	// to set latitude 
	public void setLatitude(double latitude) {
		this.latitude=latitude;
	}

	// to return longitude
	public double getLongitude() {
		return longitude;
	}

	// to set longitude
	public void setLongitude(double longitude) {
		this.longitude=longitude;
	}

	// if otherLatitude and otherLongitude is same to latitude and longitude, return true.
	// otherwise, return false. 
	public boolean isSameLocation(double otherLatitude, double otherLongitude) {
        return otherLatitude == otherLongitude;
	}

	// to calculate the distance from a location(latitude, longitude) to a location(otherLatitude, otherLongitude).
	public double calculateDistance(double otherLatitude, double otherLongitude) {
        return 0;
	}
}