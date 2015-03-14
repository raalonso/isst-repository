package es.upm.dit.isst.todo;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.todo.dao.TodoDAO;
import es.upm.dit.isst.todo.dao.TodoDAOImpl;

public class CreateTodoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		System.out.println("Creating new todo ");
		User user = (User) req.getAttribute("user");
		if (user == null) {
			UserService userService = UserServiceFactory.getUserService();
			user = userService.getCurrentUser();
		}

		String summary = checkNull(req.getParameter("summary"));
		String longDescription = checkNull(req.getParameter("description"));
		String input_deadline = checkNull(req.getParameter("deadline"));
		
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date deadline;
		try {
			deadline = format.parse(input_deadline);
		} catch (ParseException e) {
			deadline = new Date();
		}
		
		String url = checkNull(req.getParameter("url"));

		TodoDAO dao = TodoDAOImpl.getInstance();
		dao.add(user.getNickname(), summary, longDescription, deadline, url);

		resp.sendRedirect("/");
	}

	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
} 