package ss.finalproject.twentyfivehourday.mvc.control;

import java.io.IOException;

import ss.finalproject.twentyfivehourday.mvc.model.domain.User;
import ss.finalproject.twentyfivehourday.service.json.JsonService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.objectify.ObjectifyService;

@SuppressWarnings("serial")
public class UserController extends ResourceController<User>{
	private static final Log log = LogFactory.getLog(User.class);
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			if(log.isDebugEnabled()) {
				log.debug("Setting up model in request");
			}
			String body = getRequestBody(req);
			User u =JsonService.deserialize(body, User.class);
			setModel(req, u);
			
			}catch(Exception e){
				forward(req, resp, "/view/400-bad-request-view");
				if(log.isInfoEnabled()) {
					log.info("Bad request: " + e.getMessage());
				}
				return;
			}
		
		//invoke business logics
		if(log.isDebugEnabled()){
			log.debug("Invoking business logics");
		}
		User u = getModel(req);
		User x = null;
		
		try{
			
		 x	=ObjectifyService.begin().get(User.class, u.getId());
			
		}catch(Exception e){
			x = null;
		}
		
		if(x!=null){
			
			forward(req, resp, "/view/400-bad-request-view");
			return;
			
		}
		include(req, resp, "/model/business/user-dao");
		//dispatch to view
		if(log.isDebugEnabled()){
			log.debug("Dispatching to view");
		}
		
		forward(req, resp, "/view/user-json-view");
		
	}

}
