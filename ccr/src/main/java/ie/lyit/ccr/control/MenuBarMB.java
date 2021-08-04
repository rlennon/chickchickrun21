package ie.lyit.ccr.control;

import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ie.lyit.ccr.util.CcrConstants;

/**
 *
 * @author juarezjunior
 */
@ManagedBean("menuBarMB")
@SessionScoped
public class MenuBarMB implements Serializable {

	private static Logger logger = Logger.getLogger(MenuBarMB.class.getName());
	private ResourceBundle bundle;

	public MenuBarMB() {
		super();
		bundle = ResourceBundle.getBundle(CcrConstants.RESOURCE_BUNDLE_BASE_NAME,
				FacesContext.getCurrentInstance().getViewRoot().getLocale());
	}

	public String cancel() {
		return CcrConstants.MAIN;
	}

	public String logout() {
	
		addMessage("Logging Off...");
		HttpSession httpSession = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(false);

		if (httpSession != null) {
			try {

				HttpServletResponse httpResponse = (HttpServletResponse) FacesContext
						.getCurrentInstance().getExternalContext()
						.getResponse();
				httpSession.invalidate();
				HttpServletRequest httpRequest = (HttpServletRequest) FacesContext
						.getCurrentInstance().getExternalContext().getRequest();
				String contextRoot = httpRequest.getContextPath();
				httpResponse.sendRedirect(contextRoot);
			} catch (IOException ex) {
				logger.log(Level.SEVERE,
						ex.getLocalizedMessage(), ex);
			}
		}

		return null;
	}

	public String firstAccessOk() {
		HttpSession httpSession = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(false);
		httpSession.setAttribute(
				CcrConstants.SHOW_WELCOME_NOTIFICATION_BAR, "false");

		return CcrConstants.MAIN;
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
