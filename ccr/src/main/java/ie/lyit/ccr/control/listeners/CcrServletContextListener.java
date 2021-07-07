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
		
	
//		sc.setAttribute(CcrConstants.MONGO_CLIENT,
//				this.mongoDBConnection.getMongoClient());
//		sc.setAttribute(CcrConstants.MORPHIA_DATA_STORE, this.morphiaDataStore);
//		sc.setAttribute(CcrConstants.MORPHIA, this.morphia);
		
		// sc.setAttribute(CcrConstants.COMPASS_CONNECTION, this.compass);
		// TODO - bootstraping pethelp - remember to create first admin user
		// profile information using teste login page url and create 3 user roles...
	}

	public void contextDestroyed(ServletContextEvent sce) {
		/*if (morphiaDataStore != null) {
			sc.removeAttribute(CcrConstants.MORPHIA_DATA_STORE);
			morphiaDataStore = null;
		}
		if (this.mongoDBConnection != null) {
			this.mongoDBConnection.getMongoClient().close();
			sc.removeAttribute(CcrConstants.MONGO_CLIENT);
			this.mongoDBConnection.close();
			this.mongoDBConnection = null;
		}
		if(compass != null){
			compass.close();
			sc.removeAttribute(CcrConstants.COMPASS_CONNECTION);
			compass = null;
		}
		morphia = null;*/
		sc = null;
	}
}
