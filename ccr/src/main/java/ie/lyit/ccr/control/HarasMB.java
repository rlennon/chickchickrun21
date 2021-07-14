package ie.lyit.ccr.control;

import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ApplicationScoped;

//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;
import javax.faces.event.NamedEvent;
import javax.servlet.ServletContext;

import org.apache.commons.beanutils.BeanUtils;
import org.compass.core.Compass;
import org.compass.core.CompassException;
import org.compass.core.CompassSession;
import org.compass.core.CompassTransaction;

import ie.lyit.ccr.dao.HarasDAO;
import ie.lyit.ccr.model.entities.Haras;
import ie.lyit.ccr.util.CcrConstants;

/**
 *
 * @author juarezjunior
 */
@ManagedBean("harasMB")
//@ManagedBean(name = "harasMB")
@javax.faces.bean.SessionScoped
@Dependent
public class HarasMB implements Serializable {

	private static Logger logger = Logger.getLogger(HarasMB.class.getName());

	private Compass compass;
	private CompassSession compassSession;
	private ServletContext servletContext;
	private HarasDAO harasDAO;
	private List<Haras> allHarases = new ArrayList<Haras>();

	private Long id;
	private String name;
	private Long ownerID;
	private InputStream[] photos;
	private String address;
	private String telephone;
	private String email;
	private String webSite;

	public HarasMB() {
		super();
		servletContext = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
		compass = (Compass) servletContext
				.getAttribute(CcrConstants.COMPASS_CONNECTION);
		compassSession = compass.openSession();
		harasDAO = new HarasDAO();
	}

	public String cancel() {
		return CcrConstants.MAIN;
	}

	public String findHarasById(String id) {
		Haras returned = harasDAO.findHarasById(id);
		try {
			BeanUtils.copyProperties(this, returned);
		} catch (IllegalAccessException ce) {
			logger.log(Level.SEVERE, ce.getMessage(), ce);
		} catch (InvocationTargetException ce) {
			logger.log(Level.SEVERE, ce.getMessage(), ce);
		}
		return CcrConstants.MAIN;
	}

	public String findAllHaras() {
		this.allHarases = harasDAO.findAllHaras();
		return CcrConstants.MAIN;
	}

	public String findHaras(Haras toFind) {
		Haras returned = harasDAO.findHaras(toFind);
		try {
			BeanUtils.copyProperties(this, returned);
		} catch (IllegalAccessException ce) {
			logger.log(Level.SEVERE, ce.getMessage(), ce);
		} catch (InvocationTargetException ce) {
			logger.log(Level.SEVERE, ce.getMessage(), ce);
		}
		return CcrConstants.MAIN;
	}

	public String deleteHaras(Haras toDelete) {
		boolean wasDeleted = harasDAO.deleteHaras(toDelete);
		return CcrConstants.MAIN;
	}

	public String updateHaras(Haras toUpdate) {
		boolean wasUpdated = harasDAO.updateHaras(toUpdate);
		return CcrConstants.MAIN;
	}

	public String createHaras() {
		logger.log(Level.INFO, "createHaras method called...");

		// new record, auto assigned id, do not set it...
		Haras newHaras = new Haras();
		newHaras.setName(this.name);
		newHaras.setEmail(this.email);
		newHaras.setAddress(this.address);
		newHaras.setOwnerID(this.ownerID);
		newHaras.setPhotos(this.photos);
		newHaras.setTelephone(this.telephone);
		newHaras.setWebSite(this.webSite);

		boolean created = harasDAO.createHaras(newHaras);
		logger.log(Level.INFO, "New Haras saved in MongoDB. Commited!");

		CompassTransaction tx = null;

		try {
			tx = compassSession.beginTransaction();
			compassSession.save(newHaras);
			tx.commit();
			logger.log(Level.INFO, "New Haras saved in Compass. Commited!");
		} catch (CompassException ce) {
			logger.log(Level.SEVERE, ce.getMessage(), ce);
			if (tx != null) {
				tx.rollback();
				logger.log(Level.INFO,
						"New Haras NOT saved in Compass. Rolled Back!");
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
	 * @return the compass
	 */
	public Compass getCompass() {
		return compass;
	}

	/**
	 * @param compass
	 *            the compass to set
	 */
	public void setCompass(Compass compass) {
		this.compass = compass;
	}

	/**
	 * @return the servletContext
	 */
	public ServletContext getServletContext() {
		return servletContext;
	}

	/**
	 * @param servletContext
	 *            the servletContext to set
	 */
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the photos
	 */
	public InputStream[] getPhotos() {
		return photos;
	}

	/**
	 * @param photos
	 *            the photos to set
	 */
	public void setPhotos(InputStream[] photos) {
		this.photos = photos;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the webSite
	 */
	public String getWebSite() {
		return webSite;
	}

	/**
	 * @param webSite
	 *            the webSite to set
	 */
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

}
