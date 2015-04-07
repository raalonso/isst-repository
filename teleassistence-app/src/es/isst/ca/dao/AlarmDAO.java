package es.isst.ca.dao;

import java.util.List;

import es.isst.ca.model.Alarm;

public interface AlarmDAO {
	
	public void addDistressAlarm(String originator, Long timestamp,	Integer severity, List<Number> location);
<<<<<<< HEAD
	
=======

	public List<Alarm> listAlarms();
>>>>>>> c90b5c0a11f11ec0c71b0fa9b1550f199b24a377
	public List<Alarm> listAlarms(String originator);
	public List<Alarm> listAttendedAlarms(String originator);
	public List<Alarm> listUnattendedAlarms(String originator);

	public void clearAlarm(Long id);
<<<<<<< HEAD
=======

>>>>>>> c90b5c0a11f11ec0c71b0fa9b1550f199b24a377
	
	
}
