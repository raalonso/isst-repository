package es.isst.ca.model;

import java.util.List;

public class DistressAlarm extends Alarm {

	private static final long serialVersionUID = -2076301762770244976L;

	public static final String EVENT_NAME = "Distress alarm";
	public static final Integer EVENT_TYPE = 1;
	
	public static Alarm create(String originator,	Long timestamp, Integer severity, List<Number> location) {
		Alarm alarm = new Alarm(DistressAlarm.EVENT_NAME, DistressAlarm.EVENT_TYPE, originator, timestamp, severity, location);
		return alarm;
	}
	
	public DistressAlarm(String originator,	Long timestamp, Integer severity, List<Number> location) {
		super(DistressAlarm.EVENT_NAME, DistressAlarm.EVENT_TYPE, originator, timestamp, severity, location);
	}
	
}
