package es.isst.ca;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import es.isst.ca.dao.CaDAO;
import es.isst.ca.dao.CaDAOImpl;
public class RemoveUsuarioServlet extends HttpServlet {
 private static final long serialVersionUID = 1L;
 public void doGet(HttpServletRequest req, HttpServletResponse resp)
 throws IOException {
 String id = req.getParameter("id");
 CaDAO dao = CaDAOImpl.getInstance();
 dao.removeUsuario(Long.parseLong(id));
 resp.sendRedirect("/");
 }
}
