package es.isst.ca;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.isst.ca.dao.AlarmDAO;
import es.isst.ca.dao.AlarmDAOImpl;
import es.isst.ca.dao.UserDAO;
import es.isst.ca.dao.UserDAOImpl;
import es.isst.ca.model.Alarm;
import es.isst.ca.model.Usuario;

public class AtenderAlarmaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws IOException {
		String id = req.getParameter("id");

		AlarmDAO alarmdao = AlarmDAOImpl.getInstance();
		UserDAO userdao = UserDAOImpl.getInstance();
			
		Alarm alarm = alarmdao.getAlarmById(Long.parseLong(id));
		Usuario usuario = userdao.getUsuarioByIMEI(alarm.getOriginator());
		
		req.getSession().setAttribute("usuario", usuario);
		req.getSession().setAttribute("alarm", alarm);
		RequestDispatcher view = req.getRequestDispatcher("Atender.jsp");
		try {
			view.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
