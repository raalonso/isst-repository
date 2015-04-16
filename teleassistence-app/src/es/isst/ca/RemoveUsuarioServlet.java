package es.isst.ca;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import es.isst.ca.dao.UserDAO;
import es.isst.ca.dao.UserDAOImpl;

public class RemoveUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String id = req.getParameter("id");
		UserDAO userdao = UserDAOImpl.getInstance();
		userdao.removeUsuario(Long.parseLong(id));
		resp.sendRedirect("/");
	}
}
