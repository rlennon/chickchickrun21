package ie.lyit.ccr.control;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import ie.lyit.ccr.dao.UserRolesDAO;
import ie.lyit.ccr.model.entities.UserRoles;
import ie.lyit.ccr.util.CcrConstants;

/**
 *
 * @author juarezjunior
 *
 */
@ManagedBean("userRolesMB")
@SessionScoped
public class UserRolesMB implements Serializable {

	private static Logger logger = Logger
			.getLogger(UserRolesMB.class.getName());

	private ServletContext servletContext;

	private Long id;
	private String userName;
	private String roleName;


	private UserRolesDAO userRolesDAO;

	public UserRolesMB() {
		super();
		servletContext = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();



		userRolesDAO = new UserRolesDAO();
	}

	public String saveUserRoles() {

		logger.log(Level.INFO, "saveUserRoles method called...");
		UserRoles newUserRole = new UserRoles();	
		newUserRole.setUserName("l00162879@student.lyit.ie");
		boolean created = new UserRolesDAO().createUserRoles(newUserRole);
		return CcrConstants.MAIN;
	}

	@Override
	protected void finalize() throws Throwable {	
	}

	@PreDestroy
	public void releaseResources() {	
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public String cancel() {
		return CcrConstants.MAIN;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
