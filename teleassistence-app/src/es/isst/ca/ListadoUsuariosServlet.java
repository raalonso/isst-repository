package es.isst.ca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.isst.ca.dao.EventDAO;
import es.isst.ca.dao.EventDAOImpl;
import es.isst.ca.dao.UserDAO;
import es.isst.ca.dao.UserDAOImpl;
import es.isst.ca.model.Location;
import es.isst.ca.model.Usuario;

public class ListadoUsuariosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws IOException {

		UserDAO userdao = UserDAOImpl.getInstance();
		EventDAO eventdao = EventDAOImpl.getInstance();
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = userdao.listUsuarios();
		
		
		if (usuarios.size() != 0) {
			for (int i = 0; i < usuarios.size(); i++) {
				Location location = eventdao.getUserLocation(usuarios.get(i)
						.getIMEI());

				long ts = System.currentTimeMillis();
				long diff = ts - location.getTimestamp();

				if (diff < 60) {
					usuarios.get(i).setConectado("success");
				} else {
					usuarios.get(i).setConectado("danger");
				}
			}
		}
		
		
		for(int i=0; i<usuarios.size(); i++) {
			System.out.println(usuarios.get(i).getConectado());
		}
		
		req.getSession().setAttribute("usuarios", new ArrayList<Usuario>(usuarios));
		
		RequestDispatcher view = req.getRequestDispatcher("Usuarios.jsp");
		try {
			view.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
