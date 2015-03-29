package es.isst.ca.model;

import java.util.Arrays;

public class Location extends Event {

	private static final long serialVersionUID = -2189277890756417769L;

	public static final String EVENT_NAME = "User location";
	public static final Integer EVENT_TYPE = 102;
	
	private Long latitude;
	private Long longitude;
	
	public static Event create(String originator, Long timestamp, Long latitude,
			Long longitude) {
		Event location = new Event(Location.EVENT_NAME, originator, timestamp, Location.EVENT_TYPE, Arrays.asList((Number) new Long(latitude), (Number) new Long(longitude)));
		return location;
	}
	
	public Location(String originator, Long timestamp,
			Long latitude, Long longitude) {
		super(Location.EVENT_NAME, originator, timestamp, Location.EVENT_TYPE, Arrays.asList((Number) new Long(latitude), (Number) new Long(longitude)));
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getLatitude() {
		return latitude;
	}

	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}

	public Long getLongitude() {
		return longitude;
	}

	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}
	
}
