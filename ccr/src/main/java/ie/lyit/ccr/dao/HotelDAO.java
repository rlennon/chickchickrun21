
package ie.lyit.ccr.dao;

import java.util.List;

import ie.lyit.ccr.model.entities.Hotel;
import ie.lyit.ccr.util.PetHelpDataStoreSingleton;

/**
 *
 * @author juarezjunior
 */
public class HotelDAO {

	private static PetHelpDataStoreSingleton singleton = PetHelpDataStoreSingleton.getInstance();

	public HotelDAO() {

	}

	public boolean updateHotel(Hotel toUpdate) {
		if (toUpdate != null) {
			boolean wasUpdated = this.updateHotel(toUpdate);
			if (wasUpdated) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean createHotel(Hotel newHotel) {
		if (newHotel != null) {

			return true;
		}
		return false;
	}

	public boolean deleteHotel(Hotel toDelete) {
		if (toDelete != null) {

			return true;
		}
		return false;
	}

	public Hotel findHotel(Hotel toFind) {
		if (toFind != null) {

		}
		return null;
	}

	public Hotel findHotelById(String id) {
		if (id != null) {
			return null;
		}
		return null;
	}

	public List<Hotel> findAllHotels() {
		return null;
	}

}
