package es.isst.ca.model;

import java.util.Arrays;
import java.util.List;

public class GlucoseMeter extends Event{

	private static final long serialVersionUID = 1819131874084625501L;
	
	public static final String EVENT_NAME = "Glucose Meter";
	public static final Integer EVENT_TYPE = 302;

	private Double glucose;

	public static Event create(String originator, Long timestamp,
			Double glucoseVal) {
		Event glucose = new Event(GlucoseMeter.EVENT_NAME, originator,
				timestamp, GlucoseMeter.EVENT_TYPE,
				Arrays.asList((Number) glucoseVal), "mg/dl");
		return glucose;
	}

	public GlucoseMeter(String originator, Long timestamp,
			Double glucoseVal) {
		super(GlucoseMeter.EVENT_NAME, originator, timestamp, GlucoseMeter.EVENT_TYPE, Arrays.asList((Number) new Double(glucoseVal)), "mg/dl");
		this.glucose = glucoseVal;
		this.data = Arrays.asList((Number) this.glucose);
	}
	
	public List<Number> asList() {
		return Arrays.asList((Number) this.glucose);
	}
	
	public double getGlucose() {
		return (Double) this.getData().get(0);
	}
	
	public void setGlucose(Double glucoseVal) {
		this.glucose = glucoseVal;
	}
	
	public Long getTimestamp() {
		return (Long) this.timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}
