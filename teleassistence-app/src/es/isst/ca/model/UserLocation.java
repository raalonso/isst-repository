package es.isst.ca.model;

import java.io.Serializable;

public class UserLocation implements Serializable {

	private static final long serialVersionUID = -309434184231112937L;
	
	protected Double latitude;
	protected Double longitude;
	
	public UserLocation() {
		super();
	}
	
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
