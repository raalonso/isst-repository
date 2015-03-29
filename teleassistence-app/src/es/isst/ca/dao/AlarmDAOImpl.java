package es.isst.ca.dao;

import java.util.List;

import javax.persistence.EntityManager;

import es.isst.ca.model.Alarm;
import es.isst.ca.model.DistressAlarm;

public class AlarmDAOImpl implements AlarmDAO {

	private static AlarmDAOImpl instance;
	
	public AlarmDAOImpl() {
		super();
	}

	public static AlarmDAOImpl getInstance() {
		if (instance == null)
			instance = new AlarmDAOImpl();
		return instance;
	}
	
	@Override
	public void addDistressAlarm(String originator, Long timestamp,
			Integer severity, List<Number> location) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Alarm alarm = DistressAlarm.create(originator, timestamp, severity, location);
			em.persist(alarm);
			em.close();
		}
	}

	@Override
	public List<Alarm> listAlarms(String originator) {
		// TODO Auto-generated method stub
		return null;
	}

}
