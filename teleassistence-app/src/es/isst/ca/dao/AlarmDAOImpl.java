package es.isst.ca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.isst.ca.model.Alarm;
<<<<<<< HEAD
=======
import es.isst.ca.model.Alarma;
>>>>>>> c90b5c0a11f11ec0c71b0fa9b1550f199b24a377
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
<<<<<<< HEAD
=======
	
	@Override
	public List<Alarm> listAlarms() {
		EntityManager em = EMFService.get().createEntityManager();
		// read the existing entries
		Query q = em.createQuery("select t from Alarm t");
		List<Alarm> alarms = q.getResultList();
		return alarms;
	}
>>>>>>> c90b5c0a11f11ec0c71b0fa9b1550f199b24a377

	@Override
	public List<Alarm> listAlarms(String originator) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select t from Alarm t");
				//.createQuery("select t from Alarm t where t.originator = :originator");
		q.setParameter("originator", originator);
		List<Alarm> alarms = q.getResultList();
		return alarms;
	}

	@Override
	public List<Alarm> listAttendedAlarms(String originator) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select t from Alarm t where t.originator = :originator");
		q.setParameter("originator", originator);
		List<Alarm> alarms = q.getResultList();
		return alarms;
	}
	

	@Override
	public List<Alarm> listUnattendedAlarms(String originator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearAlarm(Long id) {
		// TODO Auto-generated method stub
		
	}

}
