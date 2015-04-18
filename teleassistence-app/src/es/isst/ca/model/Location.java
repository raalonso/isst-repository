package es.isst.ca.model;

import java.util.Arrays;
import java.util.List;

public class Location extends Event {

	private static final long serialVersionUID = -2189277890756417769L;

	public static final String EVENT_NAME = "User location";
	public static final Integer EVENT_TYPE = 102;
	
	private Double latitude;
	private Double longitude;
	
	public static Event create(String originator, Long timestamp, Double latitude,
			Double longitude) {
		Event location = new Event(Location.EVENT_NAME, originator, timestamp, Location.EVENT_TYPE, Arrays.asList((Number) latitude, (Number) longitude), "");
		return location;
	}
	
	public Location(String originator, Long timestamp,
			Double latitude, Double longitude) {
		super(Location.EVENT_NAME, originator, timestamp, Location.EVENT_TYPE, Arrays.asList((Number) latitude, (Number) longitude), "");
		this.latitude = latitude;
		this.longitude = longitude;
		this.data = Arrays.asList((Number) this.latitude, (Number) this.longitude);
	}
	
	public List<Number> asList() {
		return Arrays.asList((Number) this.latitude, (Number) this.longitude);
	}
	
	public Double getLatitude() {
		return (Double) this.getData().get(0);
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return (Double) this.getData().get(1);
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public Long getTimestamp() {
		return (Long) this.timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}
