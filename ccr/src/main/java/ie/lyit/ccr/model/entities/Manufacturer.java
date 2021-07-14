package ie.lyit.ccr.model.entities;

import java.io.Serializable;

import java.io.InputStream;

/**
 *
 * @author juarezjunior
 */

public class Manufacturer implements Serializable {

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static void setSerialVersionUID(long aSerialVersionUID) {
		serialVersionUID = aSerialVersionUID;
	}

	private Long id;

	private String name;

	private Long ownerID;

	private String address;

	private String telephone;

	private String email;

	private String webSite;

	private InputStream[] photos;

	private static long serialVersionUID = 1L;

	public Manufacturer() {
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

}
