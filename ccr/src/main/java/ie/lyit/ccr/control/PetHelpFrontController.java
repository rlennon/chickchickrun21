package ie.lyit.ccr.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.compass.core.Compass;
import org.compass.core.CompassSession;
import org.compass.core.CompassTransaction;

import ie.lyit.ccr.dao.UserRolesDAO;
import ie.lyit.ccr.model.entities.User;
import ie.lyit.ccr.model.entities.UserRoles;
import ie.lyit.ccr.util.CcrConstants;

/**
 * 
 * @author juarezjunior
 */
public class PetHelpFrontController extends HttpServlet {

	private Logger logger = Logger.getLogger(PetHelpFrontController.class
			.getName());

	private Compass compass;
	private User searchResultsUser;
	private CompassSession session;
	private CompassTransaction tx;
	private UserRolesDAO userRolesDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		compass = (Compass) config.getServletContext().getAttribute(
				CcrConstants.COMPASS_CONNECTION);

		this.userRolesDAO = new UserRolesDAO();

	}

	// http://localhost:8080/pet-help/PetHelpFrontController?opTarget=1
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		session = null;
		session = compass.openSession();

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String opTarget = request.getParameter(CcrConstants.OP_TARGET);
		UserRoles testDataUserRoles = null;
		boolean roleCreated = false;

		// Sample MongoDB database population and bootstrap test data...
		if (opTarget != null) {

			int target = Integer.parseInt(opTarget.trim());

			switch (target) {
			// ADMINISTRATOR ROLE
			case 1:
				testDataUserRoles = null;
				testDataUserRoles = new UserRoles();
				testDataUserRoles.setRoleName("administrator");
				testDataUserRoles.setUserName("juarez.barbosa@gmail.com");
				roleCreated = this.userRolesDAO
						.createUserRoles(testDataUserRoles);
				tx = null;
				tx = session.beginTransaction();
				session.save(testDataUserRoles);
				session.commit();
				tx.commit();

				break;

			// USER ROLE
			case 2:
				testDataUserRoles = null;
				testDataUserRoles = new UserRoles();
				testDataUserRoles.setRoleName("user");
				testDataUserRoles.setUserName("juarez.barbosa@gmail.com");
				roleCreated = this.userRolesDAO
						.createUserRoles(testDataUserRoles);
				tx = null;
				tx = session.beginTransaction();
				session.save(testDataUserRoles);
				session.commit();
				tx.commit();

				break;

			// BUSINESS ROLE
			case 3:
				testDataUserRoles = null;
				testDataUserRoles = new UserRoles();
				testDataUserRoles.setRoleName("business");
				testDataUserRoles.setUserName("juarez.barbosa@gmail.com");
				roleCreated = this.userRolesDAO
						.createUserRoles(testDataUserRoles);
				tx = null;
				tx = session.beginTransaction();
				session.save(testDataUserRoles);
				session.commit();
				tx.commit();

				break;

			}

		}
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet PetHelpFrontController</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Servlet PetHelpFrontController at "
				+ request.getContextPath() + "</h1>");
		out.println("Role created: " + testDataUserRoles.getRoleName() + "-->"
				+ roleCreated);
		out.println("</body>");
		out.println("</html>");
		out.flush();
		out.close();

	}

	@Override
	protected void finalize() throws Throwable {
		if (session != null && session.isClosed()) {
			session.close();
		}
		return;
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

	@Override
	public String getServletInfo() {
		return "PetHelpFrontController component!";
	}
}
