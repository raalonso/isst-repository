package es.isst.ca.model;

import java.util.Arrays;

public class Acceleration extends Event {

	private static final long serialVersionUID = -4837662834640482701L;

	public static final String EVENT_NAME = "Acceleration";
	public static final Integer EVENT_TYPE = 103;
	
	private Double acceleration_x_axis;
	private Double acceleration_y_axis;
	private Double acceleration_z_axis;
	
	public static Event create(String originator, Long timestamp,
			Double acceleration_x_axis, Double acceleration_y_axis,	Double acceleration_z_axis) {
		Event acceleration = new Event(Acceleration.EVENT_NAME, originator, timestamp, Acceleration.EVENT_TYPE, Arrays.asList((Number) new Double(acceleration_x_axis), (Number) new Double(acceleration_y_axis), (Number) new Double(acceleration_z_axis)), "");
		return acceleration;
	}
	
	public Acceleration(String originator, Long timestamp,
			Double acceleration_x_axis,	Double acceleration_y_axis, Double acceleration_z_axis) {
		super(Acceleration.EVENT_NAME, originator, timestamp, Acceleration.EVENT_TYPE, Arrays.asList((Number) new Double(acceleration_x_axis), (Number) new Double(acceleration_y_axis), (Number) new Double(acceleration_z_axis)), "");
		this.acceleration_x_axis = acceleration_x_axis;
		this.acceleration_y_axis = acceleration_y_axis;
		this.acceleration_z_axis = acceleration_z_axis;
	}

	public Double getAcceleration_x_axis() {
		return acceleration_x_axis;
	}

	public void setAcceleration_x_axis(Double acceleration_x_axis) {
		this.acceleration_x_axis = acceleration_x_axis;
	}

	public Double getAcceleration_y_axis() {
		return acceleration_y_axis;
	}

	public void setAcceleration_y_axis(Double acceleration_y_axis) {
		this.acceleration_y_axis = acceleration_y_axis;
	}

	public Double getAcceleration_z_axis() {
		return acceleration_z_axis;
	}

	public void setAcceleration_z_axis(Double acceleration_z_axis) {
		this.acceleration_z_axis = acceleration_z_axis;
	}
	
}
