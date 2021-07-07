package ie.lyit.ccr.model.entities;

import java.io.Serializable;

/**
 *
 * @author juarezjunior
 */

public class Mural implements Serializable {

	private Long id;

	private Long ownerId;

	private String userName;

	private Post[] posts;

	public Mural() {
		super();
	}

	private static long serialVersionUID = 1L;

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
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

	public Post[] getPosts() {
		return posts;
	}

	public void setPosts(Post[] posts) {
		this.posts = posts;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
