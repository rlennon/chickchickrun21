package ie.lyit.ccr.model.entities;

import java.io.InputStream;
import java.io.Serializable;

/**
 *
 * @author juarezjunior
 */

public class Veterinary implements Serializable {

	public Veterinary() {
		super();
	}

	private Long id;

	private Long ownerId;

	private String name;

	private String address;

	private String telephone;

	private String email;

	private String webSite;

	private String userName;

	private InputStream[] photos;

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
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
