package ie.lyit.ccr.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import ie.lyit.ccr.dao.CertificateDAO;
import ie.lyit.ccr.model.entities.Courses;
import ie.lyit.ccr.util.CcrConstants;

/**
 *
 * @author juarezjunior
 */
@ManagedBean("certMB")
@SessionScoped
public class CertificateMB implements Serializable {

	private static final Logger logger = Logger.getLogger(CertificateMB.class.getName());

	private ServletContext servletContext;

	private String certType;

	private String comments;
	private byte[] photoBytes;
	private UploadedFile uploaded;
	private InputStream photoInputStream;
	private String photoName;
	private String photoContentType;

	private CertificateDAO courseDAO;

	public CertificateMB() {
		super();
		servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

		if (courseDAO == null) {
			courseDAO = new CertificateDAO();
		}
	}

	public String cancel() {
		return CcrConstants.MAIN;
	}

	public String loadMyCertificates() {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
//		String currUserName = httpServletRequest.getUserPrincipal().getName();
//		myCollectionCourses = null;
//		myCollectionCourses = courseDAO.findMyOwnCourses(currUserName);
//		for (Object obj : myCollectionCourses) {
//			Course currCourse = (Course) obj;
//			// build photos
//			currCourse.buildPhotoImage();
//		}
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
			Logger.getLogger(CertificateMB.class.getName()).log(Level.SEVERE, null, ex);
		}
		Messages.addInfo("certificateForm:coursePhoto", "Upload OK");
	}

	public String deleteCourse(Courses course) {
		logger.log(Level.INFO, "deleteCourse method called...");
		if (courseDAO == null) {
			courseDAO = new CertificateDAO();
		}
		boolean isDeleted = courseDAO.deleteCourse(course);
		logger.log(Level.INFO, "Course deleted?: " + isDeleted);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Course Deleted",
				"Course Deleted: " + isDeleted);
		FacesContext.getCurrentInstance().addMessage(null, message);
		// reloads and updates course list for userName...
		this.loadMyCertificates();
		return CcrConstants.MAIN;
	}

	public String saveCourse() {

		logger.log(Level.INFO, "saveCourse method called...");

		// Course newCourse = new Course();

//		try {
//			BeanUtils.copyProperties(newCourse, this);
//		} catch (IllegalAccessException ex) {
//			logger.log(Level.SEVERE, null, ex);
//		} catch (InvocationTargetException ex) {
//			logger.log(Level.SEVERE, null, ex);
//		}
//		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
//				.getExternalContext().getRequest();
//		String currentUser = httpServletRequest.getUserPrincipal().getName();
//		newCourse.setUserName(currentUser);
//
//		boolean created = false;
//		if (courseDAO == null) {
//			courseDAO = new CourseDAO();
//		}
//		created = courseDAO.createCourse(newCourse);

		Messages.addInfo("certificateForm:coursePanelGrid", "Certificate created succesfully!");
		return CcrConstants.ADD_PET;
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

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public CertificateDAO getCertificateDAO() {
		return courseDAO;
	}

	public void setCertificateDAO(CertificateDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	public static Logger getLogger() {
		return logger;
	}
}
