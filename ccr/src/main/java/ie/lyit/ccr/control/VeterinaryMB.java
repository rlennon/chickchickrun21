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

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import com.mongodb.Mongo;

import ie.lyit.ccr.dao.VeterinaryDAO;
import ie.lyit.ccr.model.entities.Veterinary;
import ie.lyit.ccr.util.CcrConstants;

/**
 *
 * @author juarezjunior
 */
@ManagedBean("veterinaryMB")
@SessionScoped
public class VeterinaryMB implements Serializable {

	private static Logger logger = Logger.getLogger(VeterinaryMB.class
			.getName());
	private Mongo mongoObj;
	private Datastore morphiaDS;
	private Morphia morphia;
	private Compass compass;
	private ServletContext servletContext;
	private UploadedFile uploaded;
	private InputStream photoInputStream;
	private String photoName;
	private String photoContentType;
	private Long id;
	private String name;
	private String address;
	private String telephone;
	private String email;
	private String webSite;
	private String userName;
	private InputStream[] photos;
	private byte[] photoBytes;
	private VeterinaryDAO veterinaryDAO;
	private CompassSession compassSession;

	public VeterinaryMB() {
		super();
		servletContext = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
		compass = (Compass) servletContext
				.getAttribute(CcrConstants.COMPASS_CONNECTION);
		if (veterinaryDAO == null) {
			veterinaryDAO = new VeterinaryDAO();
		}
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		VeterinaryMB.logger = logger;
	}

	public Mongo getMongoObj() {
		return mongoObj;
	}

	public void setMongoObj(Mongo mongoObj) {
		this.mongoObj = mongoObj;
	}

	public Datastore getMorphiaDS() {
		return morphiaDS;
	}

	public void setMorphiaDS(Datastore morphiaDS) {
		this.morphiaDS = morphiaDS;
	}

	public Morphia getMorphia() {
		return morphia;
	}

	public void setMorphia(Morphia morphia) {
		this.morphia = morphia;
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
			logger.log(Level.SEVERE, null, ex);
		}
		Messages.addInfo("veterinaryForm:veterinaryPhoto", "Upload OK");
	}

	public String saveVeterinary() {

		logger.log(Level.INFO, "saveVeterinary method called...");

		Veterinary newVet = new Veterinary();

		try {
			BeanUtils.copyProperties(newVet, this);
		} catch (IllegalAccessException ex) {
			logger.log(Level.SEVERE, null, ex);
		} catch (InvocationTargetException ex) {
			logger.log(Level.SEVERE, null, ex);
		}
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		String currentUser = httpServletRequest.getUserPrincipal().getName();
		newVet.setUserName(currentUser);

		boolean created = false;

		if (veterinaryDAO == null) {
			veterinaryDAO = new VeterinaryDAO();
		}
		created = veterinaryDAO.createVeterinary(newVet);

		compassSession = compass.openSession();
		CompassTransaction tx = null;
		try {
			tx = compassSession.beginTransaction();
			compassSession.save(newVet);
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

		Messages.addInfo("veterinaryForm:veterinaryPanelGrid",
				"Veterinary created succesfully!");
		return CcrConstants.ADD_VETERINARY;
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

	public String cancel() {
		return CcrConstants.MAIN;
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

}
