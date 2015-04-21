package es.isst.ca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.isst.ca.dao.AlarmDAO;
import es.isst.ca.dao.AlarmDAOImpl;
import es.isst.ca.model.Alarm;




public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}
	private String getAddress(List<Number> location) throws IOException, JSONException{
		Number latitude = location.get(0);	
		String lat = latitude.toString();
		Number longitude = location.get(1);	
		String lng = longitude.toString();
		String latlng = ""+lat+","+lng+"";
		System.out.println(latlng);
		String url = "http://maps.googleapis.com/maps/api/geocode/json?latlng=" + latlng + "&sensor=false";
		JSONObject json = readJsonFromUrl(url);
		//System.out.println("json:"+json);
		JSONArray results = json.getJSONArray("results");
		JSONObject rec = results.getJSONObject(0);
		String formatted_address = rec.getString("formatted_address");
		return formatted_address;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws IOException {
		
		AlarmDAO alarmdao = AlarmDAOImpl.getInstance();

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		String url = userService.createLoginURL(req.getRequestURI());
		String urlLinktext = "Login";
		List<Alarm> alarms = new ArrayList<Alarm>();		
		
		if (user != null){
			url = userService.createLogoutURL(req.getRequestURI());
			urlLinktext = "Logout";
			
			alarms = alarmdao.listUnattendedAlarms();
			
			for(int i=0; i<alarms.size(); i++) {
				
				SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
				Date date = new Date(alarms.get(i).getTimestamp()*1000);
			    String strDate = sdfDate.format(date);
				alarms.get(i).setDate(strDate); 
				//System.out.println(strDate+"eyyy");
			}
			
			Comparator<Alarm> comparador = new Comparator<Alarm>() {
				public int compare(Alarm a, Alarm b) {
					int resultado = Integer.compare(a.getSeverity(), b.getSeverity());
					if (resultado != 0) {
						return resultado;
					} 

					resultado = (int) Long.compare(a.getTimestamp(), b.getTimestamp());
					if (resultado != 0) {
						return resultado;
					} else {
						return -1;
					}
				}
			};
			
			Collections.sort(alarms, comparador);
			
			if (alarms.size() != 0) {
				for (int i = 0; i < alarms.size(); i++) {
					List<Number> location = alarms.get(i).getLocation();
					try {
						String address = getAddress(location);
						alarms.get(i).setAddress(address);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}
		
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("alarms", new ArrayList<Alarm>(alarms));
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("urlLinktext", urlLinktext);
		
		RequestDispatcher view = req.getRequestDispatcher("Home.jsp");
		try {
			view.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
