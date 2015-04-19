package es.isst.ca;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.isst.ca.dao.UserDAO;
import es.isst.ca.dao.UserDAOImpl;

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
		String IMEI = checkNull(req.getParameter("IMEI"));
		String papellido1 = checkNull(req.getParameter("papellido1"));
		String papellido2 = checkNull(req.getParameter("papellido2"));
		String pnombre = checkNull(req.getParameter("pnombre"));
		String pmovil = checkNull(req.getParameter("pmovil"));
		String persona = checkNull(req.getParameter("persona"));

		UserDAO userdao = UserDAOImpl.getInstance();
		userdao.addUsuario(apellido1 + " " + apellido2, nombre, nacimiento, dni, sexo, telefono,
				movil, domicilio, cp, localidad, provincia, datos, IMEI, persona, pnombre,
				papellido1 + " " + papellido2, pmovil);
        
		resp.sendRedirect("/");
		
	}

	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
}