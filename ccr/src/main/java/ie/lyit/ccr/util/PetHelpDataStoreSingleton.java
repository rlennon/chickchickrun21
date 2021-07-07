package ie.lyit.ccr.util;

import javax.servlet.ServletContext;

/**
 *
 * @author juarezjunior
 */
public class PetHelpDataStoreSingleton {

	private volatile static PetHelpDataStoreSingleton uniqueInstance;
	private static ServletContext servletContext;

	private PetHelpDataStoreSingleton() {
		super();
		// TODO - cosmos db connection
	}

	public static PetHelpDataStoreSingleton getInstance() {
		if (uniqueInstance == null) {
			synchronized (PetHelpDataStoreSingleton.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new PetHelpDataStoreSingleton();
				}
			}
		}
		return uniqueInstance;
	}

	public static PetHelpDataStoreSingleton getInstance(ServletContext sc) {
		if (uniqueInstance == null) {
			synchronized (PetHelpDataStoreSingleton.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new PetHelpDataStoreSingleton();
				}
			}
		}
		servletContext = sc;
		return uniqueInstance;
	}

}
