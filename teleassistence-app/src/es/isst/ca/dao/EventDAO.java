package es.isst.ca.dao;

import java.util.List;

import es.isst.ca.model.Event;
import es.isst.ca.model.Location;

public interface EventDAO {

	public void addLocation(String originator, Long timestamp, Double latitude, Double longitude);
	public void addAcceleration(String originator, Long timestamp, Double acceleration_x_axis, Double acceleration_y_axis, Double acceleration_z_axis);
	
	public List<Event> listEvents(String originator);
	
	public List<Location> listUserLocations(String originator);
	public Location getUserLocation(String originator);
	
	
}
