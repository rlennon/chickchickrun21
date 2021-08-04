package ie.lyit.ccr.control;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ie.lyit.ccr.dao.UsersDAO;
import ie.lyit.ccr.dao.UserRolesDAO;
import ie.lyit.ccr.model.entities.Users;
import ie.lyit.ccr.model.entities.UserRoles;
import ie.lyit.ccr.util.CcrConstants;

/**
 *
 * @author juarezjunior
 */
public class Registration extends HttpServlet {

	private Logger logger = Logger.getLogger(Registration.class.getName());
	private UsersDAO userDAO;
	private UserRolesDAO userRolesDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		if (userDAO == null) {
			userDAO = new UsersDAO();
		}
		if (userRolesDAO == null) {
			userRolesDAO = new UserRolesDAO();
		}
	}

	
	private Users searchResultsUser;

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
	
			passwordConfirmation = null;
			emailConfirmation = null;
			Users newUser = new Users();			
			newUser.setEmail(email);			
			newUser.setPasswordHash(password);	
			if (userDAO == null) {
				userDAO = new UsersDAO();
			}
			boolean userCreated = this.userDAO.createUser(newUser);

			UserRoles newRole = new UserRoles();
			newRole.setRoleName(CcrConstants.USER_ROLE);
			newRole.setUserName(userName);

			if (userRolesDAO == null) {
				userRolesDAO = new UserRolesDAO();
			}
			boolean userRoleCreated = this.userRolesDAO
					.createUserRoles(newRole);
	
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
