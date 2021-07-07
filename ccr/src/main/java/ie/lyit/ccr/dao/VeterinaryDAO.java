
package ie.lyit.ccr.dao;

import java.util.List;

import ie.lyit.ccr.model.entities.Veterinary;
import ie.lyit.ccr.util.PetHelpDataStoreSingleton;

/**
 *
 * @author juarezjunior
 */
public class VeterinaryDAO {

	private static PetHelpDataStoreSingleton singleton = PetHelpDataStoreSingleton.getInstance();

	public VeterinaryDAO() {

	}

	public boolean updateVeterinary(Veterinary toUpdate) {
		if (toUpdate != null) {
			boolean wasUpdated = this.updateVeterinary(toUpdate);
			if (wasUpdated) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean createVeterinary(Veterinary newVeterinary) {
		if (newVeterinary != null) {

			return true;
		}
		return false;
	}

	public boolean deleteVeterinary(Veterinary toDelete) {
		if (toDelete != null) {

			return true;
		}
		return false;
	}

	public Veterinary findVeterinary(Veterinary toFind) {
		if (toFind != null) {
			return null;
		}
		return null;
	}

	public Veterinary findVeterinaryById(String id) {
		if (id != null) {
			return null;
		}
		return null;
	}

	public List<Veterinary> findAllVeterinaries() {
		return null;
	}

}
