package ie.lyit.ccr.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import ie.lyit.ccr.model.entities.Course;
import ie.lyit.ccr.util.HibernateUtil;
import ie.lyit.ccr.util.PetHelpDataStoreSingleton;

/**
 *
 * @author juarezjunior
 */
public class CourseDAO {

	private static PetHelpDataStoreSingleton singleton = PetHelpDataStoreSingleton.getInstance();
	private SessionFactory sessionFactory = null;

	public CourseDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public boolean updateCourse(Course toUpdate) {
		if (toUpdate != null) {
			boolean wasUpdated = this.updateCourse(toUpdate);
			if (wasUpdated) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean createCourse(Course newCourse) {
		if (newCourse != null) {

			return true;
		}
		return false;
	}

	public boolean deleteCourse(Course toDelete) {
		if (toDelete != null) {
			return true;
		}
		return false;
	}

	public Course findCourse(Course toFind) {
		if (toFind != null) {
			return null;
		}
		return null;
	}

	public Course findCourseById(String id) {
		if (id != null) {
			return null;
		}
		return null;
	}

	public List<Course> findAllCourses() {
		return null;
	}

	public List<Course> findMyOwnCourses(String userName) {

		if (userName != null) {
			return null;
		}
		return null;
	}
}
