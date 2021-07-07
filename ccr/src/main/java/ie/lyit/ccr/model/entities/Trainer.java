package ie.lyit.ccr.model.entities;

import java.io.InputStream;
import java.io.Serializable;

/**
 *
 * @author juarezjunior
 */

public class Trainer implements Serializable {

	private Long id;

	private String name;

	private Long ownerID;

	private String address;

	private String telephone;

	private String email;

	private String webSite;

	private InputStream[] photos;

	private String userName;

	public Trainer() {
		super();
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

	public InputStream[] getPhotos() {
		return photos;
	}

	public void setPhotos(InputStream[] photos) {
		this.photos = photos;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
