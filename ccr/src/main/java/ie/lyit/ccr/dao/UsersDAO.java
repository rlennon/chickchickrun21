package ie.lyit.ccr.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ie.lyit.ccr.model.entities.Courses;
import ie.lyit.ccr.model.entities.Users;
import ie.lyit.ccr.util.HibernateUtil;

/**
 *
 * @author juarezjunior
 */
public class UsersDAO {
	
	private SessionFactory sessionFactory = null;
	
	public UsersDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public boolean updateUser(Users toUpdate) {
		if (toUpdate != null) {
			boolean wasUpdated = this.updateUser(toUpdate);
			if (wasUpdated) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean createUser(Users newUser) {
		if (newUser != null) {
			
			Session session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			session.save(newUser);
			session.flush();
			txn.commit();
			return true;
		}
		return false;
	}

	public boolean deleteUser(Users toDelete) {
		if (toDelete != null) {

			return true;
		}
		return false;
	}

	public Users findUser(Users toFind) {
		if (toFind != null) {
			return null;
		}
		return null;
	}

	public Users findUserByUserName(String userName) {
		if (userName != null) {

			return null;
		}
		return null;
	}

	public List<Users> findAllUsers() {		
		
		Session session = sessionFactory.openSession();
		Transaction txn = session.beginTransaction();
		List<Users> users = session.createQuery("SELECT u FROM Users u", Users.class).getResultList();  	
		session.flush();
		txn.commit();		
		return users;

	}
	

}
