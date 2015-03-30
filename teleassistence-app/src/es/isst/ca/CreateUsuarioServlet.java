package es.isst.ca;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.isst.ca.dao.AlarmDAO;
import es.isst.ca.dao.AlarmDAOImpl;
import es.isst.ca.dao.CaDAO;
import es.isst.ca.dao.CaDAOImpl;
import es.isst.ca.dao.EventDAO;
import es.isst.ca.dao.EventDAOImpl;

public class CreateUsuarioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		System.out.println("Creating new usuario ");
	
		String apellido1 = checkNull(req.getParameter("apellido1"));
		String apellido2 = checkNull(req.getParameter("apellido2"));
		String nombre = checkNull(req.getParameter("nombre"));
		String nacimiento = checkNull(req.getParameter("nacimiento"));
		String dni = checkNull(req.getParameter("dni"));
		String sexo = checkNull(req.getParameter("sexo"));
		String telefono = checkNull(req.getParameter("telefono"));
		String movil = checkNull(req.getParameter("movil"));
		String domicilio = checkNull(req.getParameter("domicilio"));
		String cp = checkNull(req.getParameter("cp"));
		String localidad = checkNull(req.getParameter("localidad"));
		String provincia = checkNull(req.getParameter("provincia"));
		String datos = checkNull(req.getParameter("datos"));

		CaDAO dao = CaDAOImpl.getInstance();
		dao.addUsuario(apellido1 + " " + apellido2, nombre, nacimiento, dni, sexo, telefono,
				movil, domicilio, cp, localidad, provincia, datos);
		
		EventDAO dao2 = EventDAOImpl.getInstance();
        
        dao2.addLocation("IMSI1234567890", new Long(1234567), new Long(12), new Long(24));
        dao2.addAcceleration("IMSI1234567890", new Long(1234567), new Double(12), new Double(12), new Double(12));
        
        AlarmDAO dao3 = AlarmDAOImpl.getInstance();
        
        dao3.addDistressAlarm("IMSI0123456789", new Long(1234567), new Integer(1), Arrays.asList((Number) new Long(10), (Number) new Long(20)));
        
		resp.sendRedirect("/");
		
	}

	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
}