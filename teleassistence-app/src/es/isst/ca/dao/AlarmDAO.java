package es.isst.ca.dao;

import java.util.List;

import es.isst.ca.model.Alarm;

public interface AlarmDAO {
	
	public void addDistressAlarm(String originator, Long timestamp,	Integer severity, List<Number> location);
	
	public List<Alarm> listAlarms(String originator);

}
