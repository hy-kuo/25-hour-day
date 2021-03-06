package ss.finalproject.twentyfivehourday.mvc.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ss.finalproject.twentyfivehourday.mvc.model.domain.Activity;
import ss.finalproject.twentyfivehourday.service.json.JsonService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("serial")
public class ActivityController extends ResourceController<Activity>{
	private static final Log log = LogFactory
			.getLog(ActivityController.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		// invoke business logics
		if(log.isDebugEnabled()) {
			log.debug("Invoking business logics");
		}
		include(req, resp, "/model/business/activity-dao");
		
		// dispatch to view
		if(log.isDebugEnabled()) {
			log.debug("Dispatching to view");
		}

		if(!req.getHeader("Accept").contains("application/json")) {
			forward(req, resp, "/view/406-not-acceptable-view");
			return;
		}
		forward(req, resp, "/view/activity-json-view");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// setup model in request
		try {
			if(log.isDebugEnabled()) {
				log.debug("Setting up model in request");
			}
			String body = getRequestBody(req);
			Activity act = JsonService.deserialize(body, Activity.class);
			setModel(req, act);
		} catch(Exception e) {
			forward(req, resp, "/view/400-bad-request-view");
				if(log.isInfoEnabled()) {
				log.info("Bad request: " + e.getMessage());
			}
			return;
		}
		
		// invoke business logics
		if(log.isDebugEnabled()) {
			log.debug("Invoking business logics");
		}
		include(req, resp, "/model/business/activity-dao");
		
		// dispatch to view
		if(log.isDebugEnabled()) {
			log.debug("Dispatching to view");
		}
		forward(req, resp, "/view/activity-json-view");
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// setup model in request
		
		int index;
		try {
			if(log.isDebugEnabled()) {
				log.debug("Setting up model in request");
			}
			String id = req.getPathInfo().substring(1);

		} catch(Exception e) {
			forward(req, resp, "/view/400-bad-request-view");
			if(log.isInfoEnabled()) {
				log.info("Bad request: " + e.getMessage());
			}
			return;
		}
		
		// invoke business logics
		if(log.isDebugEnabled()) {
			log.debug("Invoking business logics");
		}
		include(req, resp, "/model/business/activity-dao");
		
		// dispatch to view
		if(log.isDebugEnabled()) {
			log.debug("Dispatching to view");
		}
		forward(req, resp, "/view/activity-json-view");
	}	

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// invoke business logics
		if(log.isDebugEnabled()) {
			log.debug("Invoking business logics");
		}
		include(req, resp, "/model/business/activity-dao");
		
		// dispatch to view
		if(log.isDebugEnabled()) {
			log.debug("Dispatching to view");
		}
		forward(req, resp, "/view/activity-json-view");
	}
}
