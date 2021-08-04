package ie.lyit.ccr.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import ie.lyit.ccr.model.entities.Courses;
import ie.lyit.ccr.util.HibernateUtil;

/**
 *
 * @author juarezjunior
 */
public class CertificateDAO {

	
	private SessionFactory sessionFactory = null;

	public CertificateDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public boolean updateCourse(Courses toUpdate) {
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

	public boolean createCourse(Courses newCourse) {
		if (newCourse != null) {

			return true;
		}
		return false;
	}

	public boolean deleteCourse(Courses toDelete) {
		if (toDelete != null) {
			return true;
		}
		return false;
	}

	public Courses findCourse(Courses toFind) {
		if (toFind != null) {
			return null;
		}
		return null;
	}

	public Courses findCourseById(String id) {
		if (id != null) {
			return null;
		}
		return null;
	}

	public List<Courses> findAllCourses() {
		return null;
	}

	public List<Courses> findMyOwnCourses(String userName) {

		if (userName != null) {
			return null;
		}
		return null;
	}
}
