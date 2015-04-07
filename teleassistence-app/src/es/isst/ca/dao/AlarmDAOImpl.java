package es.isst.ca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	public List<Alarm> listAlarms() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Alarm t");
		List<Alarm> alarms = q.getResultList();
		return alarms;
	}

	@Override
	public List<Alarm> listAlarms(String originator) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select t from Alarm t where t.originator = :originator");
		q.setParameter("originator", originator);
		List<Alarm> alarms = q.getResultList();
		return alarms;
	}

	@Override
	public List<Alarm> listAttendedAlarms(String originator) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select t from Alarm t where t.originator = :originator and t.attended = true");
		q.setParameter("originator", originator);
		List<Alarm> alarms = q.getResultList();
		return alarms;
	}
	

	@Override
	public List<Alarm> listUnattendedAlarms(String originator) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select t from Alarm t where t.originator = :originator and t.attended = false");
		q.setParameter("originator", originator);
		List<Alarm> alarms = q.getResultList();
		return alarms;
	}

	@Override
	public void clearAlarm(Long id) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Alarm alarm = em.find(Alarm.class, id);
			alarm.setAttended(true);
			em.merge(alarm);
			em.close();
		}
	}

}
