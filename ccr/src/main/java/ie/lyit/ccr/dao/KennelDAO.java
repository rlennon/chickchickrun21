package ie.lyit.ccr.dao;

import java.util.List;

import ie.lyit.ccr.model.entities.Hotel;
import ie.lyit.ccr.model.entities.Kennel;
import ie.lyit.ccr.util.PetHelpDataStoreSingleton;

/**
 *
 * @author juarezjunior
 */
public class KennelDAO {

	private static PetHelpDataStoreSingleton singleton = PetHelpDataStoreSingleton.getInstance();

	public KennelDAO() {

	}

	public boolean updateKennel(Kennel toUpdate) {
		if (toUpdate != null) {
			boolean wasUpdated = this.updateKennel(toUpdate);
			if (wasUpdated) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean createKennel(Kennel newKennel) {
		if (newKennel != null) {

			return true;
		}
		return false;
	}

	public boolean deleteKennel(Kennel toDelete) {
		if (toDelete != null) {

			return true;
		}
		return false;
	}

	public Kennel findKennel(Kennel toFind) {
		if (toFind != null) {
			return null;
		}
		return null;
	}

	public Kennel findKennelById(String id) {
		if (id != null) {
			return null;
		}
		return null;
	}

	public List<Kennel> findAllKennels() {
		return null;
	}
}
