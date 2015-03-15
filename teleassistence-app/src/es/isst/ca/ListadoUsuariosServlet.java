package es.isst.ca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.isst.ca.dao.CaDAO;
import es.isst.ca.dao.CaDAOImpl;
import es.isst.ca.model.Usuario;

public class ListadoUsuariosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws IOException {

		CaDAO dao = CaDAOImpl.getInstance();

		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		usuarios = dao.listUsuarios();
		
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
