package es.isst.ca.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.isst.ca.model.Event;
import es.isst.ca.model.GlucoseMeter;
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
	public void addGlucoseMeter(String originator, Long timestamp,
			Double glucoseVal) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Event glucose = GlucoseMeter.create(originator, timestamp, glucoseVal);
			em.persist(glucose);
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
	public List<GlucoseMeter> listGlucoseMeters(String originator) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select t from Event t where t.type = 302 and t.originator = :originator");
		q.setParameter("originator", originator);
		List<Event> events = q.getResultList();
		
		List<GlucoseMeter> glucoseVals = new ArrayList();
		
		if (events.size() != 0) {
			
			for (int i = 0; i < events.size(); i++) {
				glucoseVals.add(i, new GlucoseMeter(originator, 
						events.get(i).getTimestamp(), (double) events.get(i).getData().get(0)));
			}
		} 
		
		return glucoseVals;
	}

	@Override
	public Location getUserLocation(String originator) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select t from Event t where t.type = 102 and t.originator = :originator");
		q.setParameter("originator", originator);
		List<Event> events = q.getResultList();
		
		Location location;
		
		if (events.size() != 0) {
			Event event = events.get(0);
			location = new Location(originator, event.getTimestamp(),
					(double) event.getData().get(0), (double) event.getData().get(1));
			
			for (int i = 0; i < events.size(); i++) {
				if (location.getTimestamp() < events.get(i).getTimestamp()) {
					location = new Location(originator, events.get(i).getTimestamp(),
							(double) events.get(i).getData().get(0), 
							(double) events.get(i).getData().get(1));
				}
			}
		} else {
			location = new Location(originator, (long) 0,
					40.416944, -3.703611);
		}
		return location;
	}
}
