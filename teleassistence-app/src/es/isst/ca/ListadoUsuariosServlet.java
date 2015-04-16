package es.isst.ca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.isst.ca.dao.UserDAO;
import es.isst.ca.dao.UserDAOImpl;
import es.isst.ca.model.Usuario;

public class ListadoUsuariosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws IOException {

		UserDAO userdao = UserDAOImpl.getInstance();

		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		usuarios = userdao.listUsuarios();
		
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
