package ie.lyit.ccr.model.entities;

import java.io.Serializable;

/**
 *
 * @author juarezjunior
 */

public class UserRoles implements Serializable {

	private Long id;

	private String userName;

	private String roleName;

	private static long serialVersionUID = 1L;

	public UserRoles() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
}
