package ie.lyit.ccr.model.entities;

import java.io.Serializable;

import java.util.Date;

public class User implements Serializable {

	private String userName;

	private static long serialVersionUID = 1L;

	private boolean isBlocked;

	private boolean isNew;

	private String gender;
	// TODO - to solve having it in results...

	private String password;

	private byte[] photo;

	private Date birthDate;

	private String name;

	private String surName;

	private String email;

	private String facebookHandle;

	private String twitterHandle;

	private String googlePlusHandle;

	public User() {
		super();
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static void setSerialVersionUID(long aSerialVersionUID) {
		serialVersionUID = aSerialVersionUID;
	}

	public boolean isIsBlocked() {
		return isBlocked;
	}

	public void setIsBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public boolean isIsNew() {
		return isNew;
	}

	public void setIsNew(boolean isNew) {
		this.isNew = isNew;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFacebookHandle() {
		return facebookHandle;
	}

	public void setFacebookHandle(String facebookHandle) {
		this.facebookHandle = facebookHandle;
	}

	public String getTwitterHandle() {
		return twitterHandle;
	}

	public void setTwitterHandle(String twitterHandle) {
		this.twitterHandle = twitterHandle;
	}

	public String getGooglePlusHandle() {
		return googlePlusHandle;
	}

	public void setGooglePlusHandle(String googlePlusHandle) {
		this.googlePlusHandle = googlePlusHandle;
	}

}
