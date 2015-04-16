package es.isst.ca;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import es.isst.ca.dao.AlarmDAO;
import es.isst.ca.dao.AlarmDAOImpl;


public class RemoveAlarmaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String id = req.getParameter("id");
		AlarmDAO alarmdao = AlarmDAOImpl.getInstance();
		alarmdao.removeAlarm(Long.parseLong(id));
		resp.sendRedirect("/");
	}
}
