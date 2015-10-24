package ss.finalproject.twentyfivehourday.mvc.model.business.persistence;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.objectify.ObjectifyService;

import ss.finalproject.twentyfivehourday.mvc.ModelAwareServlet;
import ss.finalproject.twentyfivehourday.mvc.control.CommentController;
import ss.finalproject.twentyfivehourday.mvc.control.ResourceController;
import ss.finalproject.twentyfivehourday.mvc.model.domain.Comment;

@SuppressWarnings("serial")
public class CommentDao extends ResourceController<Comment>{
	private static final Log log = LogFactory.getLog(CommentController.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (log.isDebugEnabled()) {
			log.debug("Getting/listing domain object(s)");
		}
			String title = URLDecoder.decode(req.getPathInfo().substring(1),
				"UTF-8");		
			// list domain objects
		Long id =Long.parseLong(title);
		List<Comment> cs =  ObjectifyService.begin().query(Comment.class) .order("-stamp").filter("activity =", id).list();
		
			setModel(req, cs);
					
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		String activity = req.getPathInfo();
		
		if(activity != null){
		activity = activity.substring(1);
		Long activityId = Long.parseLong(activity);
		
		Comment c = getModel(req);
		c.setStamp(System.currentTimeMillis());
		c.setActivity(activityId);
		ObjectifyService.begin().put(c); // id will be populated automatically
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (log.isDebugEnabled()) {
			log.debug("Dleting a domain object");
		}
		String pathInfo = req.getPathInfo();
		Long id = new Long(pathInfo.substring(1));
		ObjectifyService.begin().delete(Comment.class, id);
	}

}
