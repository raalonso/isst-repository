package es.isst.ca;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.isst.ca.dao.EventDAO;
import es.isst.ca.dao.EventDAOImpl;
import es.isst.ca.dao.UserDAO;
import es.isst.ca.dao.UserDAOImpl;
import es.isst.ca.model.Event;
import es.isst.ca.model.GlucoseMeter;
import es.isst.ca.model.Location;
import es.isst.ca.model.Usuario;
import es.isst.ca.dao.AlarmDAO;
import es.isst.ca.dao.AlarmDAOImpl;
import es.isst.ca.model.Alarm;

public class InfoUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws IOException {
		String id = req.getParameter("id");

		UserDAO userdao = UserDAOImpl.getInstance();
		AlarmDAO alarmdao = AlarmDAOImpl.getInstance();
		EventDAO eventdao = EventDAOImpl.getInstance();
			
		Usuario usuario = userdao.getUsuarioById(Long.parseLong(id));
		List<Alarm> alarms = new ArrayList<Alarm>();
		Location latlon = eventdao.getUserLocation(usuario.getIMEI());
		List<GlucoseMeter> glucoseVals = eventdao.listGlucoseMeters(usuario.getIMEI());
		
		//System.out.println(latlon.getLatitude()+", "+latlon.getLongitude());
		Comparator<GlucoseMeter> comparadore = new Comparator<GlucoseMeter>() {
			public int compare(GlucoseMeter a, GlucoseMeter b) {

				int resultado = (int) Long.compare(a.getTimestamp(), b.getTimestamp());
				if (resultado != 0) {
					return resultado;
				} else {
					return -1;
				}
			}
		};
		
		Collections.sort(glucoseVals, Collections.reverseOrder(comparadore));
		
		List<GlucoseMeter> tenglucoses = new ArrayList<GlucoseMeter>();
		List<GlucoseMeter> glucosesld = new ArrayList<GlucoseMeter>();
		
		if ((glucoseVals.size() > 0) && (glucoseVals.size() < 10)) {
			for (int i = 0; i < glucoseVals.size(); i++) {
				tenglucoses.add(glucoseVals.get(i));
			}
		} else {
			for (int i = 0; i < 10; i++) {
				tenglucoses.add(glucoseVals.get(i));
			}
		}
		
		if (glucoseVals.size() > 0) {
			for (int i=0; i<glucoseVals.size(); i++) {
				long a = System.currentTimeMillis() - glucoseVals.get(i).getTimestamp();
				if (a < 86400000) {
					glucosesld.add(glucoseVals.get(i));
				}
			}
		}
		
		alarms = alarmdao.listAttendedAlarms(usuario.getIMEI());
		
		for(int i=0; i<glucoseVals.size(); i++) {
			
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
			Date date = new Date(tenglucoses.get(i).getTimestamp());
		    String strDate = sdfDate.format(date);
			glucoseVals.get(i).setDate(strDate); 
			//System.out.println(strDate+"eyyy");
		}
		
		Comparator<Alarm> comparadora = new Comparator<Alarm>() {
			public int compare(Alarm a, Alarm b) {
				int resultado = (int) Long.compare(a.getTimestamp(), b.getTimestamp());
				if (resultado != 0) {
					return resultado;
				} else {
					return -1;
				}
			}
		};
		
		Collections.sort(alarms, Collections.reverseOrder(comparadora));
		
		req.getSession().setAttribute("alarms", new ArrayList<Alarm>(alarms));
		req.getSession().setAttribute("glucosesld", new ArrayList<GlucoseMeter>(glucosesld));
		req.getSession().setAttribute("tenglucoses", new ArrayList<GlucoseMeter>(tenglucoses));
		req.getSession().setAttribute("usuario", usuario);
		req.getSession().setAttribute("latlon", latlon);
		RequestDispatcher view = req.getRequestDispatcher("Info.jsp");
		try {
			view.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
