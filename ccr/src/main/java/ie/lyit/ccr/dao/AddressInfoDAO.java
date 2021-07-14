package ie.lyit.ccr.dao;

import java.util.List;

import ie.lyit.ccr.model.entities.AddressInfo;
import ie.lyit.ccr.util.PetHelpDataStoreSingleton;

/**
 *
 * @author juarezjunior
 * 
 */
public class AddressInfoDAO {

	private static PetHelpDataStoreSingleton singleton = PetHelpDataStoreSingleton.getInstance();

	public AddressInfoDAO() {

	}

	public boolean updateAddressInfo(AddressInfo toUpdate) {
		if (toUpdate != null) {
			boolean wasUpdated = this.updateAddressInfo(toUpdate);
			if (wasUpdated) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean createAddressInfo(AddressInfo newAddressInfo) {
		if (newAddressInfo != null) {
			newAddressInfo.setId(new Long(System.currentTimeMillis()));

			return true;
		}
		return false;
	}

	public boolean deleteAddressInfo(AddressInfo toDelete) {
		if (toDelete != null) {

			return true;
		}
		return false;
	}

	public AddressInfo findAddressInfo(AddressInfo toFind) {
		if (toFind != null) {

			return null;

		}
		return null;
	}

	public AddressInfo findAddressInfoById(String id) {
		if (id != null) {
			return null;
		}
		return null;
	}

	public List<AddressInfo> findAllAddressInfos() {
		return null;
	}

}
