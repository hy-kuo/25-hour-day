package ss.finalproject.twentyfivehourday.mvc.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ss.finalproject.twentyfivehourday.mvc.ModelAwareServlet;
import ss.finalproject.twentyfivehourday.mvc.model.domain.Comment;
import ss.finalproject.twentyfivehourday.service.json.JsonService;

import ss.finalproject.twentyfivehourday.mvc.view.ActivityJsonView;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("serial")
public class CommentJsonView extends ModelAwareServlet<Comment> {
	private static final Log log = LogFactory.getLog(ActivityJsonView.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		if(log.isDebugEnabled()) {
			log.debug("Responsing 200 OK");
		}
		Object c = getModel(req); // m can be a Mail object or a list of Mail objects
		resp.setContentType("application/json");
		resp.setHeader("Cache-Control", "no-cache"); // make sure no intermediate node caches the result
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print(JsonService.serialize(c));
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(log.isDebugEnabled()) {
			log.debug("Responsing 201 Created");
		}
		Comment c = (Comment) getModel(req);
		resp.setStatus(201);
		resp.setHeader("Location", req.getAttribute("javax.servlet.forward.request_uri")+ "/" + c.getActivity());
		/* 
		 * Append id to the request URI of the controller rather than that of the current 
		 * view.
		 * The URI information of a forwarding servlet can be accessed by the request attributes
		 * javax.servlet.forward.[request_uri|context_path|servlet_path|path_info|query_string] in
		 * the forwared servlet. 
		 */
	}

	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(log.isDebugEnabled()) {
			log.debug("Responsing 204 No Content");
		}
		resp.setStatus(204);
	}

}
