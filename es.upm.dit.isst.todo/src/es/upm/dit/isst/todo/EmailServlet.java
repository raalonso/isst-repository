package es.upm.dit.isst.todo;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;

import es.upm.dit.isst.todo.dao.TodoDAO;
import es.upm.dit.isst.todo.dao.TodoDAOImpl;

public class EmailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Properties props = new Properties();

		Session email = Session.getDefaultInstance(props, null);

		try {
			MimeMessage message = new MimeMessage(email, req.getInputStream());
			String summary = message.getSubject();
			String text = getText(message);
			
			// Added for exercise
			int index_date = text.indexOf("Date: ");
			int index_description = text.indexOf("Description: ");
			int index_url = text.indexOf("URL: ");
			
			Date deadline;
			String description;
			String url;
			if ((index_date == 0) && (index_description != -1) && (index_url != -1)) {
				String input_deadline = text.substring(index_date + 6, index_description - 1);
				
				description = text.substring(index_description + 13, index_url - 1);
				
				url = text.substring(index_url + 5);
				
				DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				try {
					deadline = format.parse(input_deadline);
				} catch (ParseException e) {
					deadline = new Date();
				}			
			}
			else {
				description = text;
				deadline = new Date();
				url = "";
			}
			
			
			Address[] addresses = message.getFrom();
			InternetAddress emailAddress = new InternetAddress(addresses[0].toString());
			User user = new User(emailAddress.getAddress(), "gmail.com");
			
			TodoDAO dao = TodoDAOImpl.getInstance();
			dao.add(user.getNickname(), summary, description, deadline, url);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean textIsHtml = false;

	/**
	 * Return the primary text content of the message.
	 */

	private String getText(Part p) throws
	MessagingException, IOException {
		if (p.isMimeType("text/*")) {
			String s = (String)p.getContent();
			textIsHtml = p.isMimeType("text/html");
			return s;
		}

		if (p.isMimeType("multipart/alternative")) {
			// prefer html text over plain text
			Multipart mp = (Multipart)p.getContent();
			String text = null;
			for (int i = 0; i < mp.getCount(); i++) {
				Part bp = mp.getBodyPart(i);
				if (bp.isMimeType("text/plain")) {
					if (text == null)
						text = getText(bp);
					continue;
				} else if (bp.isMimeType("text/html")) {
					String s = getText(bp);
					if (s != null)
						return s;
				} else {
					return getText(bp);
				}
			}
			return text;
		} else if (p.isMimeType("multipart/*")) {
			Multipart mp = (Multipart)p.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				String s = getText(mp.getBodyPart(i));
				if (s != null)
					return s;
			}
		}

		return null;
	}


} 