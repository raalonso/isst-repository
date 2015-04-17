package es.isst.ca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.isst.ca.dao.UserDAO;
import es.isst.ca.dao.UserDAOImpl;
import es.isst.ca.model.Usuario;
import es.isst.ca.dao.AlarmDAO;
import es.isst.ca.dao.AlarmDAOImpl;
import es.isst.ca.model.Alarm;

public class InfoUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws IOException {
		String id = req.getParameter("id");

		UserDAO userdao = UserDAOImpl.getInstance();
		AlarmDAO alarmdao = AlarmDAOImpl.getInstance();
			
		Usuario usuario = userdao.getUsuarioById(Long.parseLong(id));
		List<Alarm> alarms = new ArrayList<Alarm>();
		
		alarms = alarmdao.listAttendedAlarms(usuario.getIMEI());
		Comparator<Alarm> comparador = new Comparator<Alarm>() {
			public int compare(Alarm a, Alarm b) {
				int resultado = (int) Long.compare(a.getTimestamp(), b.getTimestamp());
				if (resultado != 0) {
					return resultado;
				} else {
					return -1;
				}
			}
		};
		
		Collections.sort(alarms, comparador);
		
		req.getSession().setAttribute("alarms", new ArrayList<Alarm>(alarms));
		req.getSession().setAttribute("usuario", usuario);
		
		RequestDispatcher view = req.getRequestDispatcher("Info.jsp");
		try {
			view.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
