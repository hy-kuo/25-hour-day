package ss.finalproject.twentyfivehourday.mvc.model.business.persistence;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.objectify.ObjectifyService;

import ss.finalproject.twentyfivehourday.mvc.control.RecommendationController;
import ss.finalproject.twentyfivehourday.mvc.control.ResourceController;
import ss.finalproject.twentyfivehourday.mvc.model.domain.Activity;
import ss.finalproject.twentyfivehourday.mvc.model.domain.User;
@SuppressWarnings("serial")
public class RecommendationDao extends ResourceController<Activity>{
	private static final Log log = LogFactory
			.getLog(RecommendationController.class);
	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
	IOException {
		
		if (log.isDebugEnabled()) {
			log.debug("Getting/listing domain object(s)");
		}
			String id = req.getPathInfo();
			id = id.substring(1);
			User u = ObjectifyService.begin().get(User.class, id);
			
			// list domain objects
			List<Activity> acts =  ObjectifyService.begin().query(Activity.class).list() ;
			int [] preference = u.getPreference();
			
			acts= ObjectifyService.begin().query(Activity.class).list() ;
			
			for(int i=0 ; i<acts.size(); i++){
				Activity act =acts.get(i);
				act.setScore(0);
			}// init score 
			
			int preferValue = 0;
			int h = 0, m = 0, s = 0;
			int deltatime = 0;
			double score;
			Date current = new Date();
			
			for(int i=0 ; i<acts.size(); i++){
				Activity act =acts.get(i);
					Date timestamp = new Date(act.getStamp());
					
					//hour difference
					h = Math.abs(current.getHours()-timestamp.getHours());
					if(h > 12) h = 24-h;
					//minute difference
					m = current.getMinutes()-timestamp.getMinutes();
					if(m < 0){
						if(h>0){
							h--;
							m = 60+m;
						}
						else m = 0-m;
					}
					//second difference
					s = current.getSeconds()-timestamp.getSeconds();
					if(s < 0){						
						if( m==0 ){
							if(h > 0){
								h--;
								m = 59;
								s = 60+s;
							}
							else s=0-s;
						}
						else {
							m--;
							s = 60+s;
						}					
					}
					deltatime= h*3600 + m*60 + s;
					
					for(int j=0; j<5; j++){
					if(preference[j] > 0){
						preferValue =(int)act.getScore();
						preferValue +=act.getCharacteristicValueByIndex(j);						
						}
					}
				score= Math.log((double)preferValue/deltatime);		
				act.setScore(score);				
				}
			   Collections.sort(acts,
				        new Comparator<Activity>() {
				            public int compare(Activity a1, Activity a2) {
				                return (int)(a2.getScore()-a1.getScore());
				            }
				        });
			
			   acts= acts.subList(0, 5); 
			//if(acts.size()>=10)
			//acts = acts.subList(0, 10);
			setModel(req, acts);

	}

}
