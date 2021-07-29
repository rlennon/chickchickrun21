package ie.lyit.ccr.control;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.compass.core.Compass;
import org.compass.core.CompassException;
import org.compass.core.CompassSession;
import org.compass.core.CompassTransaction;

//import ie.lyit.ccr.dao.CourseDAO;
import ie.lyit.ccr.dao.UserDAO;
import ie.lyit.ccr.model.entities.UserCourses;
import ie.lyit.ccr.util.CcrConstants;

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

	public List<UserCourses> getuserCourses() {
		return userCourses;
	}
}
