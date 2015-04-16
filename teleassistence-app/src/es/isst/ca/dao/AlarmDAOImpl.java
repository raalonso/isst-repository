package es.isst.ca.dao;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.isst.ca.model.Alarm;
import es.isst.ca.model.Alarma;
import es.isst.ca.model.DistressAlarm;
import es.isst.ca.model.Usuario;

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
	public void addAlarm(Alarm alarm) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			em.persist(alarm);
			em.close();
		}
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
	public List<Alarm> listUnattendedAlarms() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Alarm t where t.attended = false");
		//q.setParameter("originator", originator);
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

	@Override
	public Alarm getAlarmById(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		Alarm alarm = em.find(Alarm.class, id);
		return alarm;
	}
	
	@Override
	public void removeAlarm(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Alarma alarma = em.find(Alarma.class, id);
			em.remove(alarma);
		} finally {
			em.close();
		}
	}
	
}
