package es.isst.ca;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.isst.ca.dao.CaDAO;
import es.isst.ca.dao.CaDAOImpl;
import es.isst.ca.model.Usuario;

public class InfoUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws IOException {
		String id = req.getParameter("id");

		CaDAO dao = CaDAOImpl.getInstance();
			
		Usuario usuario = dao.getUsuarioById(Long.parseLong(id));
		
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
