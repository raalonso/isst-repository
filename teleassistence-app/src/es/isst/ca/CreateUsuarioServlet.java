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

		UserDAO userdao = UserDAOImpl.getInstance();
		userdao.addUsuario(apellido1 + " " + apellido2, nombre, nacimiento, dni, sexo, telefono,
				movil, domicilio, cp, localidad, provincia, datos);
        
		resp.sendRedirect("/");
		
	}

	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
}