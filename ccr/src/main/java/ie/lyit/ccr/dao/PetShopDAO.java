
package ie.lyit.ccr.dao;

import java.util.List;

import ie.lyit.ccr.model.entities.PetShop;
import ie.lyit.ccr.util.PetHelpDataStoreSingleton;

/**
 *
 * @author juarezjunior
 */
public class PetShopDAO {

	private static PetHelpDataStoreSingleton singleton = PetHelpDataStoreSingleton.getInstance();

	public PetShopDAO() {

	}

	public boolean updatePetShop(PetShop toUpdate) {
		if (toUpdate != null) {
			boolean wasUpdated = this.updatePetShop(toUpdate);
			if (wasUpdated) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean createPetShop(PetShop newPetShop) {
		if (newPetShop != null) {

			return true;
		}
		return false;
	}

	public boolean deletePetShop(PetShop toDelete) {
		if (toDelete != null) {

			return true;
		}
		return false;
	}

	public PetShop findPetShop(PetShop toFind) {
		if (toFind != null) {
			return null;
		}
		return null;
	}

	public PetShop findPetShopById(String id) {
		if (id != null) {
			return null;
		}
		return null;
	}

	public List<PetShop> findAllPetShops() {
		return null;
	}

}
