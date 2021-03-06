package ss.finalproject.twentyfivehourday.mvc.model.business.persistence;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.objectify.ObjectifyService;

import ss.finalproject.twentyfivehourday.mvc.control.ResourceController;
import ss.finalproject.twentyfivehourday.mvc.model.domain.Activity;
import ss.finalproject.twentyfivehourday.mvc.model.domain.User;

@SuppressWarnings("serial")
public class UserDao extends ResourceController<User> {
	private static final Log log = LogFactory.getLog(UserDao.class);
	

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 //create a new comment
			if (log.isDebugEnabled()) {
				log.debug("Creating a domain object");
			}
			User u = getModel(req);
			
			ObjectifyService.begin().put(u);
			
		}
	

}
