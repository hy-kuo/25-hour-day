package ss.finalproject.twentyfivehourday.mvc.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.googlecode.objectify.ObjectifyService;

import ss.finalproject.twentyfivehourday.mvc.model.domain.User;
import ss.finalproject.twentyfivehourday.service.json.JsonService;

@SuppressWarnings("serial")
public class LoginController extends ResourceController<User> {
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	HttpSession hs=req.getSession();
    	if(hs.getAttribute("login") != null && hs != null){
    		String id = (String)hs.getAttribute("login") ;
    		User u = ObjectifyService.begin().get(User.class, id);
    		resp.setHeader("Cache-Control", "no-cache");
    		resp.setCharacterEncoding("UTF-8");
    		resp.getWriter().print(JsonService.serialize(u));
    		
    	}else{
    		
    		resp.setStatus(400);
    	}
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		String body = getRequestBody(req);
		User trylogin =JsonService.deserialize(body, User.class);
        try{
        	User u  = ObjectifyService.begin().get(User.class, trylogin.getId());
            if(u != null && u.getPassword().equals(trylogin.getPassword())){
            	
                //if pw is matched
                 req.getSession().setAttribute("login", u.getId());
                 resp.setStatus(200);              
                 }else{
         			forward(req, resp, "/view/404-not-found-view");

                 }
        	
        }catch(Exception e){
        	
			forward(req, resp, "/view/404-not-found-view");
			
        }

        
    } 
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        	req.getSession().invalidate();
    }

}
