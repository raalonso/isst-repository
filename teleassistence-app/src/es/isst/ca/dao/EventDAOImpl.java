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
	public void addLocation(String originator, Long timestamp, Double latitude,
			Double longitude) {
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
				.createQuery("select t from Event t where t.originator = :originator");
		q.setParameter("originator", originator);
		List<Event> events = q.getResultList();
		return events;
	}

	@Override
	public List<Location> listUserLocations(String originator) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select t from Event t where t.type = 102 and t.originator = :originator");
		q.setParameter("originator", originator);
		List<Location> events = q.getResultList();
		return events;
	}

	@Override
	public Double[] getUserLocation(String originator) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select t from Event t where t.type = 102 and t.originator = :originator");
		q.setParameter("originator", originator);
		List<Event> events = q.getResultList();
		
		Double[] latlon = { (double) 0, (double) 0 };
		
		if (events.size() != 0) {
			Event event = events.get(0);

			for (int i = 0; i < events.size(); i++) {
				if (event.getTimestamp() < events.get(i).getTimestamp()) {
					latlon[0] = (Double) events.get(i).getData().get(0);
					latlon[1] = (Double) events.get(i).getData().get(1);
				}
			}
		} else {
			latlon[0] = 40.416944;
			latlon[1] = -3.703611;
		}
		return latlon;
	}
	

}
