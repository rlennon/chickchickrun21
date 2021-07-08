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

import org.compass.core.Compass;
import org.compass.core.CompassException;
import org.compass.core.CompassSession;
import org.compass.core.CompassTransaction;

import ie.lyit.ccr.dao.PositioningInfoDAO;
import ie.lyit.ccr.model.entities.PositioningInfo;
import ie.lyit.ccr.util.CcrConstants;

/**
 *
 * @author juarezjunior
 */
@ManagedBean("positioningInfoMB")
@SessionScoped
public class PositioningInfoMB implements Serializable {

	private static Logger logger = Logger.getLogger(PositioningInfoMB.class
			.getName());

	private Compass compass;
	private ServletContext servletContext;

	private Long id;
	private String latitude;
	private String longitude;
	private String altitude;

	private PositioningInfoDAO positioningInfoDAO;
	private CompassSession compassSession;

	public PositioningInfoMB() {
		super();
		servletContext = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();

		compass = (Compass) servletContext
				.getAttribute(CcrConstants.COMPASS_CONNECTION);

		positioningInfoDAO = new PositioningInfoDAO();
	}

	public String cancel() {
		return CcrConstants.MAIN;
	}

	public String savePositioningInfo() {

		logger.log(Level.INFO, "savePositioningInfo method called...");

		PositioningInfo newPositInfo = new PositioningInfo();
		// TODO - implement association...
		newPositInfo.setUserName("juarez.barbosa@gmail.com");

		boolean created = this.positioningInfoDAO
				.createPositioningInfo(newPositInfo);

		compassSession = compass.openSession();
		CompassTransaction tx = null;
		try {
			tx = compassSession.beginTransaction();
			compassSession.save(newPositInfo);
			tx.commit();
		} catch (CompassException ce) {
			ce.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		// final check for compass
		if (!created || !tx.wasCommitted() || tx.wasRolledBack()) {
			logger.severe("ERROR: Entity NOT PERSISTED due to MongoDB-Morphia or Compass issue!");
		}
		tx = null;
		return CcrConstants.MAIN;
	}

	@Override
	protected void finalize() throws Throwable {

		if (compassSession != null && (!compassSession.isClosed())) {
			compassSession.close();
			compassSession = null;
		}
		return;
	}

	@PreDestroy
	public void releaseResources() {
		if (this.compassSession != null && (!compassSession.isClosed())) {
			compassSession.close();
			compassSession = null;
		}
		return;
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

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the altitude
	 */
	public String getAltitude() {
		return altitude;
	}

	/**
	 * @param altitude
	 *            the altitude to set
	 */
	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

}
