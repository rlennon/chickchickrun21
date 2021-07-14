package ie.lyit.ccr.control;

import java.io.Serializable;
import java.util.Date;
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

import ie.lyit.ccr.dao.PostDAO;
import ie.lyit.ccr.model.entities.Post;
import ie.lyit.ccr.util.CcrConstants;

/**
 *
 * @author juarezjunior
 */
@ManagedBean("postMB")
@SessionScoped
public class PostMB implements Serializable {

	private static Logger logger = Logger.getLogger(PostMB.class.getName());

	private Compass compass;
	private ServletContext servletContext;

	private Long id;
	private String text;
	private Date date;
	private String userName;

	private PostDAO postDAO;
	private CompassSession compassSession;

	public PostMB() {
		super();
		servletContext = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();

		compass = (Compass) servletContext
				.getAttribute(CcrConstants.COMPASS_CONNECTION);

		postDAO = new PostDAO();
	}

	public String cancel() {
		return CcrConstants.MAIN;
	}

	public String savePost() {

		logger.log(Level.INFO, "savePost method called...");

		Post newPost = new Post();
		// logger.severe("ERROR: Entity NOT PERSISTED due to MongoDB-Morphia or Compass issue!");
		newPost.setUserName("juarez.barbosa@gmail.com");


		boolean created = this.postDAO.createPost(newPost);

		this.compassSession = compass.openSession();
		CompassTransaction tx = null;
		try {
			tx = this.compassSession.beginTransaction();
			this.compassSession.save(newPost);
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
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
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

}
