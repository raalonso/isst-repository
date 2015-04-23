package es.isst.ca;

import java.io.IOException;
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
import es.isst.ca.model.Alarm;
import es.isst.ca.model.Event;
import es.isst.ca.model.Location;

public class CreateAlarmServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		System.out.println("Creating new DUMMY alarm ");
		
//		AlarmDAO alarm_dao = AlarmDAOImpl.getInstance();
//		
//		Location location = new Location("IMSI0123456789", timestamp,  40.416944, -3.703611);
//		alarm_dao.addDistressAlarm("IMSI0123456789", timestamp, 1, location.getData());
//        
//		alarm_dao.clearAlarm(4996180836614144L);
//		
//		List<Alarm> alarms = alarm_dao.listAlarms();
//		System.out.println("Alarms " + alarms);
//		List<Alarm> originator_alarms = alarm_dao.listAlarms("IMSI0123456789");
//		System.out.println("Alarms for originator " + originator_alarms);
//		List<Alarm> unattended_originator_alarms = alarm_dao.listUnattendedAlarms("IMSI0123456789");
//		System.out.println("Unattended Alarms for originator " + unattended_originator_alarms);
//		List<Alarm> attended_originator_alarms = alarm_dao.listAttendedAlarms("IMSI0123456789");
//		System.out.println("Attended Alarms for originator " + attended_originator_alarms);
		
		
		long timestamp = System.currentTimeMillis();
		EventDAO dao2 = EventDAOImpl.getInstance();
        
        //dao2.addLocation("IMSI0123456711", timestamp, 40.714224, -3.961452);
        dao2.addGlucoseMeter("IMSI0123456711", timestamp, (double) 50); 
        //dao2.addAcceleration("IMSI023456711", timestamp, 10.0, 12.0, 20.0);
        
        List<Event> events = dao2.listEvents("IMSI0123456711");
        System.out.println("Events " + events);
        
        List<Location> locations = dao2.listUserLocations("IMSI0123456711");
        System.out.println("Events " + locations);
		
		resp.sendRedirect("/");
	}
	
}

