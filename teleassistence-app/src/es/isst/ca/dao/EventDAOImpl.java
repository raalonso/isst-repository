package es.isst.ca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.isst.ca.model.Event;
import es.isst.ca.model.Location;
import es.isst.ca.model.Acceleration;

public class EventDAOImpl implements EventDAO {

	private static EventDAOImpl instance;
	
	public EventDAOImpl() {
		super();
	}

	public static EventDAOImpl getInstance() {
		if (instance == null)
			instance = new EventDAOImpl();
		return instance;
	}

	@Override
	public void addLocation(String originator, Long timestamp, Long latitude,
			Long longitude) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Event location = Location.create(originator, timestamp, latitude, longitude);
			em.persist(location);
			em.close();
		}

	}

	@Override
	public void addAcceleration(String originator, Long timestamp,
			Double acceleration_x_axis, Double acceleration_y_axis,	Double acceleration_z_axis) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Event event = Acceleration.create(originator, timestamp, acceleration_x_axis, acceleration_y_axis, acceleration_z_axis);
			em.persist(event);
			em.close();
		}
	}

	@Override
	public List<Event> listEvents(String originator) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select t from Location t where t.originator = :originator");
		q.setParameter("userId", originator);
		List<Event> events = q.getResultList();
		return events;
	}

}
