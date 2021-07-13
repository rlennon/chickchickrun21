package ie.lyit.ccr.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.compass.core.Compass;
import org.compass.core.CompassException;
import org.compass.core.CompassSession;
import org.compass.core.CompassTransaction;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import ie.lyit.ccr.dao.PetShopDAO;
import ie.lyit.ccr.model.entities.PetShop;
import ie.lyit.ccr.util.CcrConstants;

/**
 *
 * @author juarezjunior
 */
@ManagedBean("petShopMB")
@SessionScoped
public class PetShopMB implements Serializable {

	private static Logger logger = Logger.getLogger(PetShopMB.class.getName());

	private Compass compass;
	private CompassSession compassSession;
	private ServletContext servletContext;
	private String petType;
	private byte[] photoBytes;
	private UploadedFile uploaded;
	private InputStream photoInputStream;
	private String photoName;
	private String photoContentType;
	private Long id;
	private Long ownerID;
	private String name;
	private String address;
	private String telephone;
	private String email;
	private String webSite;
	private PetShopDAO petShopDAO;

	public PetShopMB() {
		super();
		servletContext = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();

		compass = (Compass) servletContext
				.getAttribute(CcrConstants.COMPASS_CONNECTION);
		if (petShopDAO == null) {
			petShopDAO = new PetShopDAO();
		}

	}

	public String cancel() {
		return CcrConstants.MAIN;
	}

	public void handleFileUpload(FileUploadEvent event) {
		uploaded = event.getFile();
		try {
			photoInputStream = uploaded.getInputstream();
			int available = getPhotoInputStream().available();
			photoBytes = new byte[available];
			int read = getPhotoInputStream().read(photoBytes, 0, available);
			this.photoContentType = uploaded.getContentType();
			this.photoName = getUploaded().getFileName();
			logger.log(Level.INFO, "File name: " + getUploaded().getFileName());
			logger.log(Level.INFO, "File photoInputStream contents: "
					+ uploaded.getInputstream().available());
			logger.log(Level.INFO,
					"File Content Type: " + uploaded.getContentType());
			logger.log(Level.INFO, "Read: " + read);
		} catch (IOException ex) {
			Logger.getLogger(PetShopMB.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		Messages.addInfo("petShopForm:petShopPhoto", "Upload OK");
	}

	public String savePetShop() {

		logger.log(Level.INFO, "savePetShop method called...");

		PetShop newPetShop = new PetShop();

		try {
			BeanUtils.copyProperties(newPetShop, this);
		} catch (IllegalAccessException ex) {
			logger.log(Level.SEVERE, null, ex);
		} catch (InvocationTargetException ex) {
			logger.log(Level.SEVERE, null, ex);
		}
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		String currentUser = httpServletRequest.getUserPrincipal().getName();
		newPetShop.setUserName(currentUser);

		boolean created = false;
		if (petShopDAO == null) {
			petShopDAO = new PetShopDAO();
		}
		created = petShopDAO.createPetShop(newPetShop);

		compassSession = compass.openSession();
		CompassTransaction tx = null;
		try {
			tx = compassSession.beginTransaction();
			compassSession.save(newPetShop);
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

		Messages.addInfo("petShopForm:petShopPanelGrid",
				"PetShop created succesfully!");
		return CcrConstants.ADD_PET_SHOP;
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

	public String getPetType() {
		return petType;
	}

	public void setPetType(String petType) {
		this.petType = petType;
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

	public CompassSession getCompassSession() {
		return compassSession;
	}

	public void setCompassSession(CompassSession compassSession) {
		this.compassSession = compassSession;
	}

	public PetShopDAO getPetShopDAO() {
		return petShopDAO;
	}

	public void setPetShopDAO(PetShopDAO petShopDAO) {
		this.petShopDAO = petShopDAO;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		PetShopMB.logger = logger;
	}

	public Compass getCompass() {
		return compass;
	}

	public void setCompass(Compass compass) {
		this.compass = compass;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public UploadedFile getUploaded() {
		return uploaded;
	}

	public void setUploaded(UploadedFile uploaded) {
		this.uploaded = uploaded;
	}

	public InputStream getPhotoInputStream() {
		return photoInputStream;
	}

	public void setPhotoInputStream(InputStream photoInputStream) {
		this.photoInputStream = photoInputStream;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getPhotoContentType() {
		return photoContentType;
	}

	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}

	public byte[] getPhotoBytes() {
		return photoBytes;
	}

	public void setPhotoBytes(byte[] photoBytes) {
		this.photoBytes = photoBytes;
	}

}