package es.isst.ca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.isst.ca.dao.CaDAO;
import es.isst.ca.dao.CaDAOImpl;
import es.isst.ca.model.Alarma;

public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws IOException {

		CaDAO dao = CaDAOImpl.getInstance();

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		String url = userService.createLoginURL(req.getRequestURI());
		String urlLinktext = "Login";
		List<Alarma> alarmas = new ArrayList<Alarma>();

		if (user != null){
			url = userService.createLogoutURL(req.getRequestURI());
			urlLinktext = "Logout";
			
			alarmas = dao.getAlarmas(user.getNickname());
		}
		
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("alarmas", new ArrayList<Alarma>(alarmas));
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