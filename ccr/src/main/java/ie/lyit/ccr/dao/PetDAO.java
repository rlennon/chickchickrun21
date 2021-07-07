package ie.lyit.ccr.dao;

import java.util.List;

import ie.lyit.ccr.model.entities.Pet;
import ie.lyit.ccr.util.PetHelpDataStoreSingleton;

/**
 *
 * @author juarezjunior
 */
public class PetDAO {

	private static PetHelpDataStoreSingleton singleton = PetHelpDataStoreSingleton.getInstance();

	public PetDAO() {

	}

	public boolean updatePet(Pet toUpdate) {
		if (toUpdate != null) {
			boolean wasUpdated = this.updatePet(toUpdate);
			if (wasUpdated) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean createPet(Pet newPet) {
		if (newPet != null) {

			return true;
		}
		return false;
	}

	public boolean deletePet(Pet toDelete) {
		if (toDelete != null) {
			return true;
		}
		return false;
	}

	public Pet findPet(Pet toFind) {
		if (toFind != null) {
			return null;
		}
		return null;
	}

	public Pet findPetById(String id) {
		if (id != null) {
			return null;
		}
		return null;
	}

	public List<Pet> findAllPets() {
		return null;
	}

	public List<Pet> findMyOwnPets(String userName) {

		if (userName != null) {
			return null;
		}
		return null;
	}
}
