
package ie.lyit.ccr.dao;

import java.util.List;

import ie.lyit.ccr.model.entities.Mural;
import ie.lyit.ccr.util.PetHelpDataStoreSingleton;

/**
 *
 * @author juarezjunior
 */
public class MuralDAO {

	private static PetHelpDataStoreSingleton singleton = PetHelpDataStoreSingleton.getInstance();

	public MuralDAO() {

	}

	public boolean updateMural(Mural toUpdate) {
		if (toUpdate != null) {
			boolean wasUpdated = this.updateMural(toUpdate);
			if (wasUpdated) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean createMural(Mural newMural) {
		if (newMural != null) {

			return true;
		}
		return false;
	}

	public boolean deleteMural(Mural toDelete) {
		if (toDelete != null) {

			return true;
		}
		return false;
	}

	public Mural findMural(Mural toFind) {
		if (toFind != null) {
			return null;
		}
		return null;
	}

	public Mural findMuralById(String id) {
		if (id != null) {
			return null;
		}
		return null;
	}

	public List<Mural> findAllMurals() {
		return null;
	}

}
