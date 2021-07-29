package ie.lyit.ccr.control.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author juarezjunior
 */
public class CcrServletContextListener implements ServletContextListener {

	private ServletContext sc;

	public void contextInitialized(ServletContextEvent sce) {
		sc = sce.getServletContext();
	}

	public void contextDestroyed(ServletContextEvent sce) {
		sc = sce.getServletContext();

	}
}
