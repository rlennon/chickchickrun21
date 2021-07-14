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
import javax.enterprise.context.SessionScoped;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.commons.beanutils.BeanUtils;
import org.compass.core.Compass;
import org.compass.core.CompassException;
import org.compass.core.CompassSession;
import org.compass.core.CompassTransaction;

import ie.lyit.ccr.dao.KennelDAO;
import ie.lyit.ccr.model.entities.Kennel;
import ie.lyit.ccr.util.CcrConstants;

/**
 *
 * @author juarezjunior
 *
 */
@ManagedBean("kennelMB")
@SessionScoped
public class KennelMB implements Serializable {

	private static Logger logger = Logger.getLogger(KennelMB.class.getName());

	private Compass compass;
	private CompassSession compassSession;
	private ServletContext servletContext;
	private KennelDAO kennelDAO;
	private List<Kennel> allKennels = new ArrayList<Kennel>();

	private Long id;
	private String name;
	private Long ownerID;
	private InputStream[] photos;
	private String address;
	private String telephone;
	private String email;
	private String webSite;

	public KennelMB() {
		super();
		servletContext = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
		compass = (Compass) servletContext
				.getAttribute(CcrConstants.COMPASS_CONNECTION);
		compassSession = compass.openSession();
		kennelDAO = new KennelDAO();
	}

	public String cancel() {
		return CcrConstants.MAIN;
	}

	public String findKennelById(String id) {
		Kennel returned = kennelDAO.findKennelById(id);
		try {
			BeanUtils.copyProperties(this, returned);
		} catch (IllegalAccessException ce) {
			logger.log(Level.SEVERE, ce.getMessage(), ce);
		} catch (InvocationTargetException ce) {
			logger.log(Level.SEVERE, ce.getMessage(), ce);
		}
		return CcrConstants.MAIN;
	}

	public String findAllKennels() {
		this.allKennels = kennelDAO.findAllKennels();
		return CcrConstants.MAIN;
	}

	public String findKennel(Kennel toFind) {
		Kennel returned = kennelDAO.findKennel(toFind);
		try {
			BeanUtils.copyProperties(this, returned);
		} catch (IllegalAccessException ce) {
			logger.log(Level.SEVERE, ce.getMessage(), ce);
		} catch (InvocationTargetException ce) {
			logger.log(Level.SEVERE, ce.getMessage(), ce);
		}
		return CcrConstants.MAIN;
	}

	public String deleteKennel(Kennel toDelete) {
		boolean wasDeleted = kennelDAO.deleteKennel(toDelete);
		return CcrConstants.MAIN;
	}

	public String updateKennel(Kennel toUpdate) {
		boolean wasUpdated = kennelDAO.updateKennel(toUpdate);
		return CcrConstants.MAIN;
	}

	public String createKennel() {
		logger.log(Level.INFO, "createKennel method called...");

		// TODO - reconfirm all attributes
		// new record, auto assigned id, do not set it...
		Kennel newKennel = new Kennel();
		newKennel.setName(this.name);
		newKennel.setEmail(this.email);
		newKennel.setAddress(this.address);
		newKennel.setOwnerID(this.ownerID);
		newKennel.setPhotos(this.photos);
		newKennel.setTelephone(this.telephone);
		newKennel.setWebSite(this.webSite);

		boolean created = kennelDAO.createKennel(newKennel);
		logger.log(Level.INFO, "New Kennel saved in MongoDB. Commited!");

		CompassTransaction tx = null;

		try {
			tx = compassSession.beginTransaction();
			compassSession.save(newKennel);
			tx.commit();
			logger.log(Level.INFO, "New Kennel saved in Compass. Commited!");
		} catch (CompassException ce) {
			logger.log(Level.SEVERE, ce.getMessage(), ce);
			if (tx != null) {
				tx.rollback();
				logger.log(Level.INFO,
						"New Kennel NOT saved in Compass. Rolled Back!");
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
