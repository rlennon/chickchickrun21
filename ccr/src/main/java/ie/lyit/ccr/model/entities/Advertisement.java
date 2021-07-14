package ie.lyit.ccr.model.entities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author juarezjunior
 */

public class Advertisement implements Serializable {

	public Advertisement() {
		super();
	}

	private Long id;

	private Long ownerId;

	private byte[] photo;

	private Date birthDate;

	private String fullAd;

	private String title;

	private Long expirationTime;

	private boolean enabled;

	private boolean reuse;

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getFullAd() {
		return fullAd;
	}

	public void setFullAd(String fullAd) {
		this.fullAd = fullAd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Long expirationTime) {
		this.expirationTime = expirationTime;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isReuse() {
		return reuse;
	}

	public void setReuse(boolean reuse) {
		this.reuse = reuse;
	}

	private static long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static void setSerialVersionUID(long aSerialVersionUID) {
		serialVersionUID = aSerialVersionUID;
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

}
