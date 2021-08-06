package ie.lyit.ccr.control;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.omnifaces.util.Messages;

import ie.lyit.ccr.dao.UsersDAO;
import ie.lyit.ccr.model.entities.Users;
import ie.lyit.ccr.util.CcrConstants;

/**
 *
 * @author juarezjunior
 *
 */
@ManagedBean("userMB")
@SessionScoped
public class UsersMB implements Serializable {

	private static Logger logger = Logger.getLogger(UsersMB.class.getName());
	private ServletContext servletContext;
	private Integer id;
	private String email;

	private String passwordHash;
	private Timestamp createdAt;
	private Timestamp emailVerifiedAt;

	private List<Users> users;
	private UsersDAO usersDAO;

	public UsersMB() {
		super();
		servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

		if (usersDAO == null) {
			usersDAO = new UsersDAO();
		}

		init();

	}

	public void init() {
		users = usersDAO.findAllUsers();
	}

	public String cancel() {
		return CcrConstants.MAIN;
	}

	public String loadUsers() {
		users = usersDAO.findAllUsers();
		return CcrConstants.USERS_DETAILS;
	}

	public String saveUser() {

		logger.log(Level.INFO, "saveUser method called...");
		Users newUser = new Users();
		newUser.setEmail(email);
		String hashedPw = hashPassword(passwordHash);
		newUser.setPasswordHash(hashedPw);

		newUser.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		newUser.setEmailVerifiedAt(new Timestamp(System.currentTimeMillis()));

		if (usersDAO == null) {
			usersDAO = new UsersDAO();
		}
		
		usersDAO.createUser(newUser);

		Messages.addInfo("certificateForm:coursePanelGrid", "User added succesfully!");
		return CcrConstants.ADD_USER;

	}

	private String hashPassword(String passwordToHash) {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] hashedPassword = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
		return new String(hashedPassword);
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getEmailVerifiedAt() {
		return emailVerifiedAt;
	}

	public void setEmailVerifiedAt(Timestamp emailVerifiedAt) {
		this.emailVerifiedAt = emailVerifiedAt;
	}

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

}
