package ie.lyit.ccr.control;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
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
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.compass.core.Compass;
import org.compass.core.CompassException;
import org.compass.core.CompassSession;
import org.compass.core.CompassTransaction;

import ie.lyit.ccr.dao.PetDAO;
import ie.lyit.ccr.dao.UserDAO;
import ie.lyit.ccr.model.entities.Pet;
import ie.lyit.ccr.model.entities.User;
import ie.lyit.ccr.util.CcrConstants;

/**
 *
 * @author juarezjunior
 *
 */
@ManagedBean("userMB")
@SessionScoped
public class UserMB implements Serializable {

	private static Logger logger = Logger.getLogger(UserMB.class.getName());

	private Compass compass;
	private ServletContext servletContext;
	private String name;
	private boolean isBlocked;
	private boolean isNew;
	private String surName;
	private String email;
	private String password;
	private String passwordConfirmation;
	private String userName;
	private String gender;
	private Date birthDate;
	private String facebookHandle;
	private String twitterHandle;
	private String googlePlusHandle;
	private byte[] photo;
	private HashMap<String, Pet> myPets;
	private List<Pet> myCollectionPets;
	private PetDAO petDAO;
	private UserDAO userDAO;
	private CompassSession compassSession;

	public UserMB() {
		super();
		servletContext = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();

		compass = (Compass) servletContext
				.getAttribute(CcrConstants.COMPASS_CONNECTION);
		myPets = new HashMap();
		if (petDAO == null) {
			petDAO = new PetDAO();
		}
		if (userDAO == null) {
			userDAO = new UserDAO();
		}
	}

	public String cancel() {
		return CcrConstants.MAIN;
	}

	public String loadMyPets() {
		// runs functionality but disregards return String (forward)
		// destination...returning myPets.xhtml instead...
		this.loadUserProfile();
		return CcrConstants.MY_PETS;
	}



	public String loadUserProfile() {
		// load user profile information
		CompassSession session = compass.openSession();
		// user profile search
		// username (email) must be search key like juarez.barbosa@gmail.com
		User searchKey = new User();
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		String currUserName = httpServletRequest.getUserPrincipal().getName();
		searchKey.setUserName(currUserName);
		User searchResultUser = session.get(User.class, searchKey);
		if (petDAO == null) {
			petDAO = new PetDAO();
		}
		if (searchResultUser != null) {
			myCollectionPets = petDAO.findMyOwnPets(currUserName);
			for (Object obj : myCollectionPets) {
				Pet currPet = (Pet) obj;
				// build photos
				currPet.buildPhotoImage();
				myPets.put(String.valueOf(currPet.getId()), currPet);
			}
		}
		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
		try {
			BeanUtils.copyProperties(this, searchResultUser);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(UserMB.class.getName())
					.log(Level.SEVERE, null, ex);
		} catch (InvocationTargetException ex) {
			Logger.getLogger(UserMB.class.getName())
					.log(Level.SEVERE, null, ex);
		} finally {
			session.commit();
			session.close();
		}
		this.password = searchResultUser.getPassword();
		return CcrConstants.EDIT_USER;
	}

	public String saveUser() {
		logger.log(Level.INFO, "saveUser method called...");
		User newUser = new User();
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		newUser.setUserName(httpServletRequest.getUserPrincipal().getName());
		try {
			BeanUtils.copyProperties(newUser, this);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(UserMB.class.getName())
					.log(Level.SEVERE, null, ex);
		} catch (InvocationTargetException ex) {
			Logger.getLogger(UserMB.class.getName())
					.log(Level.SEVERE, null, ex);
		}
		if (userDAO == null) {
			userDAO = new UserDAO();
		}
		boolean created = userDAO.createUser(newUser);
		compassSession = compass.openSession();
		CompassTransaction tx = null;
		try {
			tx = compassSession.beginTransaction();
			compassSession.save(newUser);
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
	 * @return the surName
	 */
	public String getSurName() {
		return surName;
	}

	/**
	 * @param surName
	 *            the surName to set
	 */
	public void setSurName(String surName) {
		this.surName = surName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the genre
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param genre
	 *            the genre to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the facebookHandle
	 */
	public String getFacebookHandle() {
		return facebookHandle;
	}

	/**
	 * @param facebookHandle
	 *            the facebookHandle to set
	 */
	public void setFacebookHandle(String facebookHandle) {
		this.facebookHandle = facebookHandle;
	}

	/**
	 * @return the twitterHandle
	 */
	public String getTwitterHandle() {
		return twitterHandle;
	}

	/**
	 * @param twitterHandle
	 *            the twitterHandle to set
	 */
	public void setTwitterHandle(String twitterHandle) {
		this.twitterHandle = twitterHandle;
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
	 * @return the isBlocked
	 */
	public boolean isIsBlocked() {
		return isBlocked;
	}

	/**
	 * @param isBlocked
	 *            the isBlocked to set
	 */
	public void setIsBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	/**
	 * @return the isNew
	 */
	public boolean isIsNew() {
		return isNew;
	}

	/**
	 * @param isNew
	 *            the isNew to set
	 */
	public void setIsNew(boolean isNew) {
		this.isNew = isNew;
	}

	/**
	 * @return the photo
	 */
	public byte[] getPhoto() {
		return photo;
	}

	/**
	 * @param photo
	 *            the photo to set
	 */
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate
	 *            the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the passwordConfirmation
	 */
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	/**
	 * @param passwordConfirmation
	 *            the passwordConfirmation to set
	 */
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	/**
	 * @return the googlePlusHandle
	 */
	public String getGooglePlusHandle() {
		return googlePlusHandle;
	}

	/**
	 * @param googlePlusHandle
	 *            the googlePlusHandle to set
	 */
	public void setGooglePlusHandle(String googlePlusHandle) {
		this.googlePlusHandle = googlePlusHandle;
	}

	/**
	 * @return the myPets
	 */
	public HashMap<String, Pet> getMyPets() {
		return myPets;
	}

	/**
	 * @param myPets
	 *            the myPets to set
	 */
	public void setMyPets(HashMap<String, Pet> myPets) {
		this.myPets = myPets;
	}

	/**
	 * @return the myCollectionPets
	 */
	public List<Pet> getMyCollectionPets() {
		return myCollectionPets;
	}

	/**
	 * @param myCollectionPets
	 *            the myCollectionPets to set
	 */
	public void setMyCollectionPets(List<Pet> myCollectionPets) {
		this.myCollectionPets = myCollectionPets;
	}
}
