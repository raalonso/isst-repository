package es.upm.dit.isst.todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.todo.dao.TodoDAO;
import es.upm.dit.isst.todo.dao.TodoDAOImpl;
import es.upm.dit.isst.todo.model.Todo;

public class ExpirationServlet extends HttpServlet {

	private static final long serialVersionUID = 2L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		TodoDAO dao = TodoDAOImpl.getInstance();
		List<String> users = dao.getUsers();
		for (String user : users) {
			List<Todo> todos = dao.getTodos(user);
			todos = filterExpiresIn2Weeks(todos);
			if (todos.size() > 0) {
				try {
					Properties props = new Properties();
					Session session = Session.getDefaultInstance(props, null);
					Message msg = new MimeMessage(session);
					msg.setFrom(new InternetAddress("noreply@raalonsogarcia-isst-2015.appspotmail.com", "My Todo App 2015"));
					msg.addRecipient(Message.RecipientType.TO,
							new InternetAddress(user + "@gmail.com", user));
					msg.setSubject("You have TODO's that will expired in less than 2 weeks");
					String msgBody = "You have " + todos.size() + " TODO's that will expire in less than 2 weeks: " + System.getProperty("line.separator");
					for (Todo todo : todos) {
						msgBody += "\t" + todo.getLongDescription() + System.getProperty("line.separator");
					}
					msgBody += System.getProperty("line.separator") + "Kind Regards," + System.getProperty("line.separator") + "The TODO team!";
					msg.setText(msgBody);
					Transport.send(msg);

				} catch(Exception e) {e.printStackTrace();}
			}
		}
	}

	private List<Todo> filterExpiresIn2Weeks(List<Todo> todos) {
		List<Todo> expires = new ArrayList<Todo>();
		int days = 14;
		Date filter = ExpirationServlet.addDays(new Date(), days);
		for (Todo todo : todos) {
			Date deadline = todo.getDeadline();
			if (deadline.before(filter)) expires.add(todo);
		}
		return expires;
	}
	
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); // minus number would decrement the days
        return cal.getTime();
    }
	
}
