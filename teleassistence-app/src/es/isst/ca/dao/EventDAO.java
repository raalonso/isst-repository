package es.isst.ca.dao;

import java.util.List;

import es.isst.ca.model.Event;

public interface EventDAO {

	public void addLocation(String originator, Long timestamp, Long latitude, Long longitude);
	public void addAcceleration(String originator, Long timestamp, Double acceleration_x_axis, Double acceleration_y_axis, Double acceleration_z_axis);
	
	public List<Event> listEvents(String originator);
	
}
