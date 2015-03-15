package es.isst.ca;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.isst.ca.dao.CaDAO;
import es.isst.ca.dao.CaDAOImpl;

public class CreateAlarmaServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		System.out.println("Creating new alarma ");
		User user = (User) req.getAttribute("user");
		if (user == null) {
			UserService userService = UserServiceFactory.getUserService();
			user = userService.getCurrentUser();
		}
		String tipo = checkNull(req.getParameter("tipo"));
		String apellidos = checkNull(req.getParameter("apellidos"));
		String nombre = checkNull(req.getParameter("nombre"));

		CaDAO dao = CaDAOImpl.getInstance();
		dao.add(user.getNickname(), tipo, apellidos, nombre);

		resp.sendRedirect("/");
	}
	
	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
}

