package ie.lyit.ccr.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ie.lyit.ccr.model.entities.Course;
import ie.lyit.ccr.util.HibernateUtil;

import javax.persistence.EntityManager;

/**
 *
 * @author juarezjunior
 */
public class CourseDAO {

	private SessionFactory sessionFactory;
	private EntityManager entityManager;

	public CourseDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public boolean updateCourse(Course toUpdate) {
		if (toUpdate == null) {
			return false;
		}
		entityManager.getTransaction().begin();
		entityManager.merge(toUpdate);
		entityManager.getTransaction().commit();
		return true;
	}

	public boolean createCourse(Course newCourse) {
		if (newCourse == null) {
			return false;
		}
		Session session = sessionFactory.getCurrentSession();
		Serializable id = session.save(newCourse);
		if (id == null) {
			return false;
		}
		return true;
	}

	public boolean deleteCourse(Course toDelete) {
		throw new RuntimeException("not implemented");
	}

	public Course findCourse(Course toFind) {
		throw new RuntimeException("not implemented");
	}

	public Course findCourseById(Long id) {
		return entityManager.find(Course.class, new Long(id));
	}

	public List<Course> findAllCourses() {
		List<Course> courses = entityManager.createQuery("SELECT * from courses")
				.getResultList();
		return courses;
	}

	public List<Course> findMyOwnCourses(String userName) {
		throw new RuntimeException("not implemented");
	}
}
