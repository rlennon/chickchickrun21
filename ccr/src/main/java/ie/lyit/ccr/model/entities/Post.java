package ie.lyit.ccr.model.entities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author juarezjunior
 */

public class Post implements Serializable {

	public Post() {
		super();
	}

	private Long id;

	private Long ownerId;

	private String text;

	private Date date;

	private static long serialVersionUID = 1L;

	private String userName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static void setSerialVersionUID(long serialVersionUID) {
		Post.serialVersionUID = serialVersionUID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
