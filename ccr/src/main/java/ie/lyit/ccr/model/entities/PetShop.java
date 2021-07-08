package ie.lyit.ccr.model.entities;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import ie.lyit.ccr.util.Image;

/**
 *
 * @author juarezjunior
 */

public class PetShop implements Serializable {

	private Long id;

	private String name;

	private Long ownerID;

	private String address;

	private String telephone;

	private String email;

	private String webSite;

	private byte[] photoBytes;

	private String photoName;

	private String photoContentType;

	private String userName;

	private static long serialVersionUID = 1L;

	private StreamedContent image;

	public PetShop() {
		super();
	}

	public PetShop(String newName, String newUserName, String newPhoto, String newPhotoContentType) {
		this.name = newName;
		this.userName = newUserName;
		this.photoName = newPhoto;
		this.photoContentType = newPhotoContentType;
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

	public StreamedContent getImage() {
		return image;
	}

	public void setImage(StreamedContent image) {
		this.image = image;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static void setSerialVersionUID(long aSerialVersionUID) {
		serialVersionUID = aSerialVersionUID;
	}

	public byte[] getPhotoBytes() {
		return photoBytes;
	}

	public void setPhotoBytes(byte[] photoBytes) {
		this.photoBytes = photoBytes;
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

	public Long getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(Long ownerID) {
		this.ownerID = ownerID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
