package ie.lyit.ccr.control;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.compass.core.Compass;
import org.compass.core.CompassException;
import org.compass.core.CompassSession;
import org.compass.core.CompassTransaction;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.Mongo;

import ie.lyit.ccr.dao.UserRolesDAO;
import ie.lyit.ccr.model.entities.UserRoles;
import ie.lyit.ccr.util.CcrConstants;

/**
 *
 * @author juarezjunior
 *
 */
@ManagedBean("userRolesMB")
@SessionScoped
public class UserRolesMB implements Serializable {

	private static Logger logger = Logger
			.getLogger(UserRolesMB.class.getName());
	private Mongo mongoObj;
	private Datastore morphiaDS;
	private Morphia morphia;
	private Compass compass;
	private ServletContext servletContext;

	private Long id;
	private String userName;
	private String roleName;
	private CompassSession compassSession;

	private UserRolesDAO userRolesDAO;

	public UserRolesMB() {
		super();
		servletContext = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();

		compass = (Compass) servletContext
				.getAttribute(CcrConstants.COMPASS_CONNECTION);

		userRolesDAO = new UserRolesDAO();
	}

	public String saveUserRoles() {

		logger.log(Level.INFO, "saveUserRoles method called...");

		UserRoles newUserRole = new UserRoles();
		// TODO - implement association...
		newUserRole.setUserName("juarez.barbosa@gmail.com");

		boolean created = new UserRolesDAO().createUserRoles(newUserRole);

		compassSession = compass.openSession();
		CompassTransaction tx = null;
		try {
			tx = compassSession.beginTransaction();
			compassSession.save(newUserRole);
			tx.commit();
		} catch (CompassException ce) {
			ce.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}

		// final check for compass
		if (!created || !tx.wasCommitted() || tx.wasRolledBack()) {
			logger.severe("ERROR: Entity NOT PERSISTED due to MongoDB-Morphia or Compass issue!");
		}
		tx = null;

		return CcrConstants.MAIN;
	}

	@Override
	protected void finalize() throws Throwable {

		if (compassSession != null && (!compassSession.isClosed())) {
			compassSession.close();
			compassSession = null;
		}
		return;
	}

	@PreDestroy
	public void releaseResources() {
		if (this.compassSession != null && (!compassSession.isClosed())) {
			compassSession.close();
			compassSession = null;
		}
		return;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public String cancel() {
		return CcrConstants.MAIN;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
