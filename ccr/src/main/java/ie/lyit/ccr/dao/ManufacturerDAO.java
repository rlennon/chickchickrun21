package ie.lyit.ccr.dao;

import java.util.List;

import ie.lyit.ccr.model.entities.Manufacturer;
import ie.lyit.ccr.util.PetHelpDataStoreSingleton;

/**
 *
 * @author juarezjunior
 */
public class ManufacturerDAO  {

	private static PetHelpDataStoreSingleton singleton = PetHelpDataStoreSingleton.getInstance();

	public ManufacturerDAO() {

	}

	public boolean updateManufacturer(Manufacturer toUpdate) {
		if (toUpdate != null) {
			boolean wasUpdated = this.updateManufacturer(toUpdate);
			if (wasUpdated) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean createManufacturer(Manufacturer newManufacturer) {
		if (newManufacturer != null) {

			return true;
		}
		return false;
	}

	public boolean deleteManufacturer(Manufacturer toDelete) {
		if (toDelete != null) {

			return true;
		}
		return false;
	}

	public Manufacturer findManufacturer(Manufacturer toFind) {
		if (toFind != null) {
			return null;
		}
		return null;
	}

	public Manufacturer findManufacturerById(String id) {
		if (id != null) {
			return null;
		}
		return null;
	}

	public List<Manufacturer> findAllManufacturers() {
		return null;
	}

}
