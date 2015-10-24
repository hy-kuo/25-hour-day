package ss.finalproject.twentyfivehourday.mvc.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ss.finalproject.twentyfivehourday.mvc.ModelAwareServlet;
import ss.finalproject.twentyfivehourday.mvc.model.domain.Activity;
import ss.finalproject.twentyfivehourday.service.json.JsonService;
@SuppressWarnings("serial")

public class RecommendationJsonView extends ModelAwareServlet<Activity>  {
	private static final Log log = LogFactory.getLog(RecommendationJsonView.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		if(log.isDebugEnabled()) {
			log.debug("Responsing 200 OK");
		}
		Object act = getModel(req); // m can be a Mail object or a list of Mail objects
		resp.setContentType("application/json");
		resp.setHeader("Cache-Control", "no-cache"); // make sure no intermediate node caches the result
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print(JsonService.serialize(act));
	}
	
}
