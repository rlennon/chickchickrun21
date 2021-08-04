package ie.lyit.ccr.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;

import ie.lyit.ccr.model.entities.UserCourses;

/**
 *
 * @author aks
 *
 */
@ManagedBean("userCoursesMB")
@SessionScoped
public class UserCoursesMB implements Serializable {

	private static Logger logger = Logger.getLogger(UserCoursesMB.class.getName());

	private List<UserCourses> userCourses;

	public UserCoursesMB() {
		super();
		init();
	}

	public void init() {
		userCourses = new ArrayList<UserCourses>();
		userCourses.add(new UserCourses("Java Development", "Development", "Java Web Development Course"));
		userCourses.add(new UserCourses("Agile", "Development", "Agile Training"));
	}

	public List<UserCourses> getUserCourses() {
		return userCourses;
	}
}
