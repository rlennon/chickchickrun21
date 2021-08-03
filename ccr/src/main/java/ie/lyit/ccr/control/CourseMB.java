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
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import ie.lyit.ccr.dao.CourseDAO;
import ie.lyit.ccr.model.entities.Course;
import ie.lyit.ccr.util.CcrConstants;
import ie.lyit.ccr.util.HibernateUtil;

/**
 *
 * @author juarezjunior
 */
@ManagedBean("courseMB")
@SessionScoped
public class CourseMB implements Serializable {

	private static final Logger logger = Logger.getLogger(CourseMB.class.getName());

	private ServletContext servletContext;

	private Integer id;
	private String name;
	private java.sql.Timestamp createdAt;
	private java.sql.Timestamp updatedAt;
	private Integer skillId;
	private String description;

	private String certType;

	private String comments;
	private byte[] photoBytes;
	private UploadedFile uploaded;
	private InputStream photoInputStream;
	private String photoName;
	private String photoContentType;
	private List<Course> myPets;

	private Course selectedPet;
	private List<Course> myCollectionCourses;
	private CourseDAO courseDAO;

	public CourseMB() {
		super();
		servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

		if (courseDAO == null) {
			courseDAO = new CourseDAO();
		}
	}

	public String cancel() {
		return CcrConstants.MAIN;
	}

	public String loadMyCourses() {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
		String currUserName = httpServletRequest.getUserPrincipal().getName();
		myCollectionCourses = courseDAO.findMyOwnCourses(currUserName);
		for (Course course : myCollectionCourses) {
			// build photos
			course.buildPhotoImage();
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
			logger.log(Level.INFO, "File photoInputStream contents: " + uploaded.getInputstream().available());
			logger.log(Level.INFO, "File Content Type: " + uploaded.getContentType());
			logger.log(Level.INFO, "Read: " + read);
		} catch (IOException ex) {
			Logger.getLogger(CourseMB.class.getName()).log(Level.SEVERE, null, ex);
		}
		Messages.addInfo("courseForm:coursePhoto", "Upload OK");
	}

	public String deleteCourse(Course course) {
		logger.log(Level.INFO, "deleteCourse method called...");
		if (courseDAO == null) {
			courseDAO = new CourseDAO();
		}
		boolean isDeleted = courseDAO.deleteCourse(course);
		logger.log(Level.INFO, "Course deleted?: " + isDeleted);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Course Deleted",
				"Course Deleted: " + isDeleted);
		FacesContext.getCurrentInstance().addMessage(null, message);
		// reloads and updates course list for userName...
		this.loadMyCourses();
		return CcrConstants.MAIN;
	}

	public String saveCourse() {

		logger.log(Level.INFO, "saveCourse method called...");

		Course newCourse = new Course();

		try {
			BeanUtils.copyProperties(newCourse, this);
		} catch (IllegalAccessException ex) {
			logger.log(Level.SEVERE, null, ex);
		} catch (InvocationTargetException ex) {
			logger.log(Level.SEVERE, null, ex);
		}
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
		String currentUser = httpServletRequest.getUserPrincipal().getName();
		newCourse.setUserName(currentUser);

		boolean created = false;
		if (courseDAO == null) {
			courseDAO = new CourseDAO();
		}
		created = courseDAO.createCourse(newCourse);

		Messages.addInfo("courseForm:coursePanelGrid", "Course created succesfully!");
		return CcrConstants.ADD_PET;
	}

	@Override
	protected void finalize() throws Throwable {

		HibernateUtil.shutdown();
		return;
	}

	@PreDestroy
	public void releaseResources() {
		HibernateUtil.shutdown();
		return;
	}

	/**
	 * @return the certType
	 */
	public String getCertType() {
		return certType;
	}

	/**
	 * @param certType the certType to set
	 */
	public void setCertType(String certType) {
		this.certType = certType;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
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
	 * @param uploaded the uploaded to set
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
	 * @param photoBytes the photoBytes to set
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
	 * @param photoContentType the photoContentType to set
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
	 * @param photoName the photoName to set
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
	 * @param myPets the myPets to set
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
	 * @param photoInputStream the photoInputStream to set
	 */
	public void setPhotoInputStream(InputStream photoInputStream) {
		this.photoInputStream = photoInputStream;
	}

	/**
	 * @return the selectedPet
	 */
	public Course getSelectedPet() {
		return selectedPet;
	}

	/**
	 * @param selectedPet the selectedPet to set
	 */
	public void setSelectedPet(Course selectedPet) {
		this.selectedPet = selectedPet;
	}

	/**
	 * @return the myCollectionCourses
	 */
	public List<Course> getMyCollectionPets() {
		return myCollectionCourses;
	}

	/**
	 * @param myCollectionCourses the myCollectionCourses to set
	 */
	public void setMyCollectionPets(List<Course> myCollectionPets) {
		this.myCollectionCourses = myCollectionPets;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public CourseDAO getPetDAO() {
		return courseDAO;
	}

	public void setPetDAO(CourseDAO petDAO) {
		this.courseDAO = petDAO;
	}

	public static Logger getLogger() {
		return logger;
	}
}
