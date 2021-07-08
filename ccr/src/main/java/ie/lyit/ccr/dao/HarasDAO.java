package ie.lyit.ccr.dao;

import java.util.List;

import ie.lyit.ccr.model.entities.Haras;
import ie.lyit.ccr.util.PetHelpDataStoreSingleton;

/**
 * 
 * @author juarezjunior
 */
public class HarasDAO {

	private static PetHelpDataStoreSingleton singleton = PetHelpDataStoreSingleton.getInstance();

	public HarasDAO() {

	}

	public boolean updateHaras(Haras toUpdate) {
		if (toUpdate != null) {
			boolean wasUpdated = this.updateHaras(toUpdate);
			if (wasUpdated) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean createHaras(Haras newHaras) {
		if (newHaras != null) {
			return true;
		}
		return false;
	}

	public boolean deleteHaras(Haras toDelete) {
		if (toDelete != null) {

			return true;
		}
		return false;
	}

	public Haras findHaras(Haras toFind) {
		if (toFind != null) {
			return null;
		}
		return null;
	}

	public Haras findHarasById(String id) {
		if (id != null) {
			return null;
		}
		return null;
	}

	public List<Haras> findAllHaras() {
		return null;
	}

}
