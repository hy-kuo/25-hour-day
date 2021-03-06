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

import ss.finalproject.twentyfivehourday.mvc.control.CommentController;
import ss.finalproject.twentyfivehourday.mvc.control.ResourceController;
import ss.finalproject.twentyfivehourday.mvc.model.domain.Activity;
@SuppressWarnings("serial")
public class ActivityDao extends ResourceController<Activity>{
	private static final Log log = LogFactory.getLog(CommentController.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (log.isDebugEnabled()) {
			log.debug("Getting/listing domain object(s)");
		}	
			String pathInfo = req.getPathInfo();
			if(pathInfo!=null){
				pathInfo = pathInfo.substring(1);
				Long id =Long.parseLong(pathInfo); 
				Activity act = ObjectifyService.begin().get(Activity.class, id);
				setModel(req,act);
			}else{
			// list domain objects
			List<Activity> acts =  ObjectifyService.begin().query(Activity.class) .order("-stamp").list();
			if(acts.size()>=20)
			acts = acts.subList(0, 20);
			
			setModel(req, acts);
			}					
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		

		
		Activity act = getModel(req);
		act.setStamp(System.currentTimeMillis());
		act.setScore(0);
		
		ObjectifyService.begin().put(act); // id will be populated automatically
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			String pathInfo = req.getPathInfo().substring(1);
			Long id =Long.parseLong(pathInfo); 

			String body = getRequestBody(req);
			int  index =Integer.valueOf(body);
			Activity act = ObjectifyService.begin().get(Activity.class, id);
			int oldvalue = act.getCharacteristicValueByIndex(index);
			act.setCharacteristicByIndex(index, oldvalue+1);
			ObjectifyService.begin().put(act); // id will be populated automatically
			setModel(req, act);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
	if (log.isDebugEnabled()) {
	log.debug("Dleting a domain object");
	}
	String pathInfo = req.getPathInfo();
	String id = pathInfo.substring(1);
	List<Activity> acts = ObjectifyService.begin().query(Activity.class) .order("-stamp").filter("founder =", id).list();
	setModel(req, acts);
	}
}
