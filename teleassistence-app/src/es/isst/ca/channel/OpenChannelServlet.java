package es.isst.ca.channel;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;

@SuppressWarnings("serial")
public class OpenChannelServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ChannelService channelService = ChannelServiceFactory.getChannelService();
		// Server-side identifier fir this channel
		// PLEASE NOTE: this value should be different for EACH user
		//
		String operatorID = "OPERATOR1";
		String token = channelService.createChannel(operatorID);
		// The Channel API returns a token that should be sent to the client
		// This token looks like this: channel-4rw5e-1

		// Insert token into the HTML template that is returned to the operator
		//
		// Create a group manager for some templates, all of which are at or below the indicated directory
		StringTemplateGroup group = new StringTemplateGroup("xhtml", "WEB-INF/templates/channel");
		StringTemplate hello = group.getInstanceOf("index");
		hello.setAttribute("token", token);
		resp.getWriter().write(hello.toString());
		
	}
	
}
