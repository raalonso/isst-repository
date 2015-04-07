package es.isst.ca;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.isst.ca.dao.AlarmDAO;
import es.isst.ca.dao.AlarmDAOImpl;
import es.isst.ca.dao.EventDAO;
import es.isst.ca.dao.EventDAOImpl;
import es.isst.ca.model.Event;
import es.isst.ca.model.Location;

public class CreateAlarmServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		System.out.println("Creating new DUMMY alarm ");

		long timestamp = System.currentTimeMillis() / 1000L;
		
		AlarmDAO alarm_dao = AlarmDAOImpl.getInstance();
		alarm_dao.addDistressAlarm("IMSI0123456789", new Long(timestamp), new Integer(1), Arrays.asList((Number) new Long(10), (Number) new Long(20)));
        

		EventDAO dao2 = EventDAOImpl.getInstance();
        
        dao2.addLocation("IMSI1234567890", timestamp, 40.416944, -3.703611);
        dao2.addAcceleration("IMSI1234567890", timestamp, 10.0, 12.0, 20.0);
        
        List<Event> events = dao2.listEvents("IMSI1234567890");
        System.out.println("Events " + events);
        
        List<Location> events = dao2.listUserLocations("IMSI1234567890");
        System.out.println("Events " + events);
		
		resp.sendRedirect("/");
	}
	
}

