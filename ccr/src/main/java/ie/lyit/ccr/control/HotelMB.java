/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

import ie.lyit.ccr.dao.HotelDAO;
import ie.lyit.ccr.model.entities.Hotel;
import ie.lyit.ccr.util.CcrConstants;

/**
 *
 * @author juarezjunior
 */
@ManagedBean("hotelMB")
//@ManagedBean(name = "hotelMB")
@SessionScoped
public class HotelMB implements Serializable {

	private static Logger logger = Logger.getLogger(PetMB.class.getName());

	private Compass compass;
	private CompassSession compassSession;
	private ServletContext servletContext;
	private HotelDAO hotelDAO;
	private List<Hotel> allHotels = new ArrayList<Hotel>();

	private Long id;
	private String name;
	private Long ownerID;
	private String address;
	private String telephone;
	private String email;
	private String webSite;
	private InputStream[] photos;
	private String userName;

	public HotelMB() {
		super();
		servletContext = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
		compass = (Compass) servletContext
				.getAttribute(CcrConstants.COMPASS_CONNECTION);
		compassSession = compass.openSession();
		hotelDAO = new HotelDAO();
	}

	public String cancel() {
		return CcrConstants.MAIN;
	}

	public String findHotelById(String id) {
		Hotel returned = hotelDAO.findHotelById(id);
		try {
			BeanUtils.copyProperties(this, returned);
		} catch (IllegalAccessException ce) {
			logger.log(Level.SEVERE, ce.getMessage(), ce);
		} catch (InvocationTargetException ce) {
			logger.log(Level.SEVERE, ce.getMessage(), ce);
		}
		return CcrConstants.MAIN;
	}

	public String findAllHotels() {
		this.allHotels = hotelDAO.findAllHotels();
		return CcrConstants.MAIN;
	}

	public String findHotel(Hotel toFind) {
		Hotel returned = hotelDAO.findHotel(toFind);
		try {
			BeanUtils.copyProperties(this, returned);
		} catch (IllegalAccessException ce) {
			logger.log(Level.SEVERE, ce.getMessage(), ce);
		} catch (InvocationTargetException ce) {
			logger.log(Level.SEVERE, ce.getMessage(), ce);
		}
		return CcrConstants.MAIN;
	}

	public String deleteHotel(Hotel toDelete) {
		boolean wasDeleted = hotelDAO.deleteHotel(toDelete);
		return CcrConstants.MAIN;
	}

	public String updateHotel(Hotel toUpdate) {
		boolean wasUpdated = hotelDAO.updateHotel(toUpdate);
		return CcrConstants.MAIN;
	}

	public String createHotel() {
		logger.log(Level.INFO, "createHotel method called...");

		// TODO - reconfirm all attributes...
		// new record, auto assigned id, do not set it...
		Hotel newHotel = new Hotel();
		newHotel.setName(this.name);
		newHotel.setEmail(this.email);
		newHotel.setAddress(this.address);
		newHotel.setOwnerID(this.ownerID);
		newHotel.setPhotos(this.photos);
		newHotel.setTelephone(this.telephone);
		newHotel.setWebSite(this.webSite);

		boolean created = hotelDAO.createHotel(newHotel);
		logger.log(Level.INFO, "New Hotel saved in MongoDB. Commited!");

		CompassTransaction tx = null;

		try {
			tx = compassSession.beginTransaction();
			compassSession.save(newHotel);
			tx.commit();
			logger.log(Level.INFO, "New Hotel saved in Compass. Commited!");
		} catch (CompassException ce) {
			logger.log(Level.SEVERE, ce.getMessage(), ce);
			if (tx != null) {
				tx.rollback();
				logger.log(Level.INFO,
						"New Hotel NOT saved in Compass. Rolled Back!");
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
