package ie.lyit.ccr.control;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ie.lyit.ccr.dao.UserDAO;
import ie.lyit.ccr.dao.UserRolesDAO;
import ie.lyit.ccr.model.entities.User;
import ie.lyit.ccr.model.entities.UserRoles;
import ie.lyit.ccr.util.CcrConstants;

/**
 *
 * @author juarezjunior
 */
public class Registration extends HttpServlet {

	private Logger logger = Logger.getLogger(Registration.class.getName());
	private UserDAO userDAO;
	private UserRolesDAO userRolesDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		if (userDAO == null) {
			userDAO = new UserDAO();
		}
		if (userRolesDAO == null) {
			userRolesDAO = new UserRolesDAO();
		}
	}

	
	private User searchResultsUser;

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String userName = request.getParameter("username");
			String name = request.getParameter("name");
			String surName = request.getParameter("surname");
			String email = request.getParameter("email");
			String emailConfirmation = request
					.getParameter("emailConfirmation");
			String password = request.getParameter("password");
			String passwordConfirmation = request
					.getParameter("passwordConfirmation");
			String gender = request.getParameter("gender");
			String birthDate = request.getParameter("birthDate");

			if (!email.equalsIgnoreCase(emailConfirmation)) {
				throw new ServletException("E-mail does not match!");
			}
			if (!password.equalsIgnoreCase(passwordConfirmation)) {
				throw new ServletException("Password does not match!");
			}
			// null it so that it does not go plain to MongoDB...
			passwordConfirmation = null;
			emailConfirmation = null;

			User newUser = new User();
			// set @Id
			newUser.setUserName(userName);
			newUser.setName(name);
			newUser.setSurName(surName);
			newUser.setEmail(email);

			//password = new String(Utils.cryptPassword(password.toCharArray()));
			newUser.setPassword(password);

			newUser.setGender(gender);
			// TODO - define definitive solution
			java.sql.Date birth = new java.sql.Date(System.currentTimeMillis());
			newUser.setBirthDate(birth);

			// first, save User it to MongoDB database...
			if (userDAO == null) {
				userDAO = new UserDAO();
			}
			boolean userCreated = this.userDAO.createUser(newUser);

			// then, create and save UserRoles = user to MongoDB...
			UserRoles newRole = new UserRoles();
			newRole.setRoleName(CcrConstants.USER_ROLE);
			newRole.setUserName(userName);

			if (userRolesDAO == null) {
				userRolesDAO = new UserRolesDAO();
			}
			boolean userRoleCreated = this.userRolesDAO
					.createUserRoles(newRole);

			
			

//			if (!userRoleCreated || !tx.wasCommitted() || tx.wasRolledBack()) {
//				logger.severe(new StringBuilder(
//						"ERROR: USER REGISTRATION FAILED! ID:").append(email)
//						.toString());
//			}

	

			// TODO - define the URL after registration
			response.sendRedirect(request.getContextPath());

		} catch (Exception ex) {
			Logger.getLogger(Registration.class.getName()).log(Level.SEVERE,
					null, ex);
		}

	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
