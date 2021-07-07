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

import ie.lyit.ccr.dao.MuralDAO;
import ie.lyit.ccr.model.entities.Mural;
import ie.lyit.ccr.model.entities.Post;
import ie.lyit.ccr.util.CcrConstants;

/**
 *
 * @author juarezjunior
 */
@ManagedBean("muralMB")
@SessionScoped
public class MuralMB implements Serializable {

	private static Logger logger = Logger.getLogger(MuralMB.class.getName());

	private Compass compass;
	private ServletContext servletContext;
	private Long id;
	private Long ownerID;
	private Post[] posts;
	private MuralDAO muralDAO;
	private CompassSession compassSession;

	public MuralMB() {
		super();
		servletContext = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();

		compass = (Compass) servletContext
				.getAttribute(CcrConstants.COMPASS_CONNECTION);
		muralDAO = new MuralDAO();
	}

	public String cancel() {
		return CcrConstants.MAIN;
	}

	public String saveMural() {

		logger.log(Level.INFO, "saveMural method called...");

		Mural newMural = new Mural();
		// TODO - implement association...
		newMural.setUserName("juarez.barbosa@gmail.com");

		boolean created = this.muralDAO.createMural(newMural);

		compassSession = compass.openSession();
		CompassTransaction tx = null;
		try {
			tx = compassSession.beginTransaction();
			compassSession.save(newMural);
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

	/**
	 * @return the ownerID
	 */
	public Long getOwnerID() {
		return ownerID;
	}

	/**
	 * @param ownerID
	 *            the ownerID to set
	 */
	public void setOwnerID(Long ownerID) {
		this.ownerID = ownerID;
	}

	/**
	 * @return the posts
	 */
	public Post[] getPosts() {
		return posts;
	}

	/**
	 * @param posts
	 *            the posts to set
	 */
	public void setPosts(Post[] posts) {
		this.posts = posts;
	}
}
