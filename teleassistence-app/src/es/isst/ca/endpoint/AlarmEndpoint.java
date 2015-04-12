package es.isst.ca.endpoint;

import es.isst.ca.dao.AlarmDAO;
import es.isst.ca.dao.AlarmDAOImpl;
import es.isst.ca.dao.EMFService;
import es.isst.ca.model.Alarm;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "teleassistence", namespace = @ApiNamespace(ownerDomain = "isst.es", ownerName = "isst.es", packagePath = "ca.model"))
public class AlarmEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listAlarms", path = "alarms", httpMethod = HttpMethod.GET)
	public CollectionResponse<Alarm> listAlarms(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Alarm> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Alarm as Alarm");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Alarm>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Alarm obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Alarm> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getAlarm", path = "trigger/{id}", httpMethod = HttpMethod.GET)
	public Alarm getAlarm(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Alarm alarm = null;
		try {
			alarm = mgr.find(Alarm.class, id);
		} finally {
			mgr.close();
		}
		return alarm;
	}

	/**
	 * This method gets all the alarm entities for an originator. It uses HTTP GET method.
	 *
	 * @param originator the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listOriginatorAlarms", path = "alarms/{originator}", httpMethod = HttpMethod.GET)
	public CollectionResponse<Alarm> listOriginatorAlarms(
			@Named("originator") String originator,
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Alarm> execute = null;
		System.out.println("List Alarms from originator " + originator);
		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Alarm as Alarm where originator = :originator");
			query.setParameter("originator", originator);
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Alarm>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Alarm obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Alarm> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}
	
	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param alarm the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertAlarm", path = "alarm", httpMethod = HttpMethod.POST)
	public Alarm insertAlarm(Alarm alarm) {
		
		AlarmDAO alarm_dao = AlarmDAOImpl.getInstance();
		alarm_dao.addAlarm(alarm);
		
		
//		EntityManager mgr = getEntityManager();
//		try {
////			if (containsAlarm(alarm)) {
////				throw new EntityExistsException("Object already exists");
////			}
//			mgr.persist(alarm);
//		} finally {
//			mgr.close();
//		}
		return alarm;
	}

//	/**
//	 * This method is used for updating an existing entity. If the entity does not
//	 * exist in the datastore, an exception is thrown.
//	 * It uses HTTP PUT method.
//	 *
//	 * @param alarm the entity to be updated.
//	 * @return The updated entity.
//	 */
//	@ApiMethod(name = "updateAlarm")
//	public Alarm updateAlarm(Alarm alarm) {
//		EntityManager mgr = getEntityManager();
//		try {
//			if (!containsAlarm(alarm)) {
//				throw new EntityNotFoundException("Object does not exist");
//			}
//			mgr.persist(alarm);
//		} finally {
//			mgr.close();
//		}
//		return alarm;
//	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeAlarm")
	public void removeAlarm(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Alarm alarm = mgr.find(Alarm.class, id);
			mgr.remove(alarm);
		} finally {
			mgr.close();
		}
	}

	@SuppressWarnings("unused")
	private boolean containsAlarm(Alarm alarm) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Alarm item = mgr.find(Alarm.class, alarm.getId());
			if (item == null) {
				contains = false;
			}
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static EntityManager getEntityManager() {
		return EMFService.get().createEntityManager();
	}

}
