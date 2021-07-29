package ie.lyit.ccr.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
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

import ie.lyit.ccr.dao.CourseDAO;
import ie.lyit.ccr.model.entities.Course;
import ie.lyit.ccr.util.CcrConstants;

/**
 *
 * @author juarezjunior
 */
@ManagedBean("certMB")
@SessionScoped
public class CertificateMB implements Serializable {

	private static final Logger logger = Logger
			.getLogger(CertificateMB.class.getName());

	private Compass compass;
	private CompassSession compassSession;
	private ServletContext servletContext;
	private Long id;
	private String name;
	private String breed;
	private int age;
	private Boolean pedigree;
	private Boolean mating;
	private String userName;
	private String petType;
	private String gender;
	private String comments;
	private byte[] photoBytes;
	private UploadedFile uploaded;
	private InputStream photoInputStream;
	private String photoName;
	private String photoContentType;
	private List<Course> myPets;
	private int[] ages = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
			14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30 };
	private Course selectedPet;
	private List<Course> myCollectionPets;
	private CourseDAO petDAO;

	public CertificateMB() {
		super();
		servletContext = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();

		compass = (Compass) servletContext
				.getAttribute(CcrConstants.COMPASS_CONNECTION);
		if (petDAO == null) {
			petDAO = new CourseDAO();
		}
	}

	public String cancel() {
		return CcrConstants.MAIN;
	}

	public String loadMyPets() {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		String currUserName = httpServletRequest.getUserPrincipal().getName();
		myCollectionPets = null;
		myCollectionPets = petDAO.findMyOwnPets(currUserName);
		for (Object obj : myCollectionPets) {
			Course currPet = (Course) obj;
			// build photos
			currPet.buildPhotoImage();
		}
		return CcrConstants.MY_CERTIFICATES;
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
			Logger.getLogger(CertificateMB.class.getName()).log(Level.SEVERE, null, ex);
		}
		Messages.addInfo("petForm:petPhoto", "Upload OK");
	}

	public String deletePet(Course pet) {
		logger.log(Level.INFO, "deletePet method called...");
		if (petDAO == null) {
			petDAO = new CourseDAO();
		}
		boolean isDeleted = petDAO.deletePet(pet);
		logger.log(Level.INFO, "Course deleted?: " + isDeleted);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Course Deleted", "Course Deleted: " + isDeleted);
		FacesContext.getCurrentInstance().addMessage(null, message);
		// reloads and updates pet list for userName...
		this.loadMyPets();
		return CcrConstants.MAIN;
	}



	public String savePet() {

		logger.log(Level.INFO, "savePet method called...");

		Course newPet = new Course();

		try {
			BeanUtils.copyProperties(newPet, this);
		} catch (IllegalAccessException ex) {
			logger.log(Level.SEVERE, null, ex);
		} catch (InvocationTargetException ex) {
			logger.log(Level.SEVERE, null, ex);
		}
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		String currentUser = httpServletRequest.getUserPrincipal().getName();
		newPet.setUserName(currentUser);

		boolean created = false;
		if (petDAO == null) {
			petDAO = new CourseDAO();
		}
		created = petDAO.createPet(newPet);

		compassSession = compass.openSession();
		CompassTransaction tx = null;
		try {
			tx = compassSession.beginTransaction();
			compassSession.save(newPet);
			tx.commit();
		} catch (CompassException ce) {
			ce.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		//final check for compass
		if(!created || !tx.wasCommitted() || tx.wasRolledBack()){
			logger.severe("ERROR: Entity NOT PERSISTED due to MongoDB-Morphia or Compass issue!");
		}
		tx = null;
		Messages.addInfo("petForm:petPanelGrid", "Course created succesfully!");
		return CcrConstants.ADD_PET;
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
	 * @return the mating
	 */
	public Boolean getMating() {
		return mating;
	}

	/**
	 * @param mating
	 *            the mating to set
	 */
	public void setMating(Boolean mating) {
		this.mating = mating;
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
	 * @return the petType
	 */
	public String getPetType() {
		return petType;
	}

	/**
	 * @param petType
	 *            the petType to set
	 */
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
	 * @return the breed
	 */
	public String getBreed() {
		return breed;
	}

	/**
	 * @param breed
	 *            the breed to set
	 */
	public void setBreed(String breed) {
		this.breed = breed;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the pedigree
	 */
	public Boolean getPedigree() {
		return pedigree;
	}

	/**
	 * @param pedigree
	 *            the pedigree to set
	 */
	public void setPedigree(Boolean pedigree) {
		this.pedigree = pedigree;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the uploaded
	 */
	public UploadedFile getUploaded() {
		return uploaded;
	}

	/**
	 * @param uploaded
	 *            the uploaded to set
	 */
	public void setUploaded(UploadedFile uploaded) {
		this.uploaded = uploaded;
	}

	/**
	 * @return the photoBytes
	 */
	public byte[] getPhotoBytes() {
		return photoBytes;
	}

	/**
	 * @param photoBytes
	 *            the photoBytes to set
	 */
	public void setPhotoBytes(byte[] photoBytes) {
		this.photoBytes = photoBytes;
	}

	/**
	 * @return the photoContentType
	 */
	public String getPhotoContentType() {
		return photoContentType;
	}

	/**
	 * @param photoContentType
	 *            the photoContentType to set
	 */
	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}

	/**
	 * @return the photoName
	 */
	public String getPhotoName() {
		return photoName;
	}

	/**
	 * @param photoName
	 *            the photoName to set
	 */
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	/**
	 * @return the myPets
	 */
	public List<Course> getMyPets() {
		return myPets;
	}

	/**
	 * @param myPets
	 *            the myPets to set
	 */
	public void setMyPets(List<Course> myPets) {
		this.myPets = myPets;
	}

	/**
	 * @return the photoInputStream
	 */
	public InputStream getPhotoInputStream() {
		return photoInputStream;
	}

	/**
	 * @param photoInputStream
	 *            the photoInputStream to set
	 */
	public void setPhotoInputStream(InputStream photoInputStream) {
		this.photoInputStream = photoInputStream;
	}

	/**
	 * @return the ages
	 */
	public int[] getAges() {
		return ages;
	}

	/**
	 * @param ages
	 *            the ages to set
	 */
	public void setAges(int[] ages) {
		this.setAges(ages);
	}

	/**
	 * @return the selectedPet
	 */
	public Course getSelectedPet() {
		return selectedPet;
	}

	/**
	 * @param selectedPet
	 *            the selectedPet to set
	 */
	public void setSelectedPet(Course selectedPet) {
		this.selectedPet = selectedPet;
	}

	/**
	 * @return the myCollectionPets
	 */
	public List<Course> getMyCollectionPets() {
		return myCollectionPets;
	}

	/**
	 * @param myCollectionPets
	 *            the myCollectionPets to set
	 */
	public void setMyCollectionPets(List<Course> myCollectionPets) {
		this.myCollectionPets = myCollectionPets;
	}

	public Compass getCompass() {
		return compass;
	}

	public void setCompass(Compass compass) {
		this.compass = compass;
	}

	public CompassSession getCompassSession() {
		return compassSession;
	}

	public void setCompassSession(CompassSession compassSession) {
		this.compassSession = compassSession;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public CourseDAO getPetDAO() {
		return petDAO;
	}

	public void setPetDAO(CourseDAO petDAO) {
		this.petDAO = petDAO;
	}

	public static Logger getLogger() {
		return logger;
	}
}
