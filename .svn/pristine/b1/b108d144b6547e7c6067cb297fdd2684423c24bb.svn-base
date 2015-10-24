package ss.finalproject.twentyfivehourday.mvc.control;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ss.finalproject.twentyfivehourday.mvc.model.domain.Activity;

@SuppressWarnings("serial")
public class RecommendationController extends ResourceController<Activity>{
	private static final Log log = LogFactory
			.getLog(RecommendationController.class);
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
	IOException {
		// invoke business logics
		if(log.isDebugEnabled()) {
			log.debug("Invoking business logics");
		}
		include(req, resp, "/model/business/recommendation-dao");
		
		// dispatch to view
		if(log.isDebugEnabled()) {
			log.debug("Dispatching to view");
		}

		if(!req.getHeader("Accept").contains("application/json")) {
			forward(req, resp, "/view/406-not-acceptable-view");
			return;
		}
		forward(req, resp, "/view/recommendation-json-view");
	}
}
