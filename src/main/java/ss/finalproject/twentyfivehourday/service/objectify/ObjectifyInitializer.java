package ss.finalproject.twentyfivehourday.service.objectify;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ss.finalproject.twentyfivehourday.mvc.model.domain.Activity;
import ss.finalproject.twentyfivehourday.mvc.model.domain.Comment;
import ss.finalproject.twentyfivehourday.mvc.model.domain.User;


import com.googlecode.objectify.ObjectifyService;

/**
 * Objectify needs every persistable object to be registered. This class
 * registers all domain objects at the time when the {@link ServletContext} is
 * initialized.
 */
public final class ObjectifyInitializer implements ServletContextListener {
	@Override
	public void contextInitialized(final ServletContextEvent sce) {
		ObjectifyService.register(User.class);
		ObjectifyService.register(Activity.class);
		ObjectifyService.register(Comment.class);

	}

	@Override
	public void contextDestroyed(final ServletContextEvent sce) {
		// do nothing
	}
}
