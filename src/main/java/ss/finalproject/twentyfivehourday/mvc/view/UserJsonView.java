package ss.finalproject.twentyfivehourday.mvc.view;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ss.finalproject.twentyfivehourday.mvc.ModelAwareServlet;
import ss.finalproject.twentyfivehourday.mvc.model.domain.User;
@SuppressWarnings("serial")
public class UserJsonView extends ModelAwareServlet<User>{
	private static final Log log = LogFactory.getLog(UserJsonView.class);
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(log.isDebugEnabled()) {
			log.debug("Responsing 201 Created");
		}
		User u = (User) getModel(req);
		resp.setStatus(201);
		
		resp.setHeader("Location", req.getAttribute("javax.servlet.forward.request_uri")
				+ "/" + u.getId() );
	}

}
