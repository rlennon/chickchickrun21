package ie.lyit.ccr.model.entities;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import ie.lyit.ccr.util.Image;

/**
 *
 * @author juarezjunior
 */

public class Course implements Serializable {

	private static long serialVersionUID = 1L;

	private Long id;

	private Long ownerId;

	private byte[] photoBytes;

	private String photoName;

	private String photoContentType;

	private String name;

	private String breed;

	private String gender;

	private int age;

	private Boolean pedigree;

	private String userName;
	private Boolean mating;

	private String certType;

	private String comments;

	private StreamedContent image;

	public Course() {
		super();
	}

	public Course(String newName, String newUserName, String newPhoto, String newPhotoContentType) {
		this.name = newName;
		this.userName = newUserName;
		this.photoName = newPhoto;
		this.photoContentType = newPhotoContentType;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public void buildPhotoImage() {
		InputStream is = new ByteArrayInputStream(photoBytes);
		this.image = new DefaultStreamedContent(is, photoContentType, photoName);
	}

	public Image buildCustomImage() {
		Image customImage = new Image();
		customImage.setContent(photoBytes);
		customImage.setContentType(photoContentType);
		customImage.setId(String.valueOf(this.getId()));
		customImage.setName(photoName);
		return customImage;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static void setSerialVersionUID(long aSerialVersionUID) {
		serialVersionUID = aSerialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Boolean getPedigree() {
		return pedigree;
	}

	public void setPedigree(Boolean pedigree) {
		this.pedigree = pedigree;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getMating() {
		return mating;
	}

	public void setMating(Boolean mating) {
		this.mating = mating;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public byte[] getPhotoBytes() {
		return photoBytes;
	}

	public void setPhotoBytes(byte[] photoBytes) {
		this.photoBytes = photoBytes;
	}

	public String getPhotoContentType() {
		return photoContentType;
	}

	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}

	public StreamedContent getImage() {
		FacesContext context = FacesContext.getCurrentInstance();
		return image;
	}

	public void setImage(StreamedContent image) {
		this.image = image;
	}
}
