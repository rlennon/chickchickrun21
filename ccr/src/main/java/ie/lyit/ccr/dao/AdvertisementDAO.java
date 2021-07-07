package ie.lyit.ccr.dao;

import java.util.List;

import ie.lyit.ccr.model.entities.Advertisement;
import ie.lyit.ccr.util.PetHelpDataStoreSingleton;

/**
*
* @author juarezjunior
*
*/
public class AdvertisementDAO {

	private static PetHelpDataStoreSingleton singleton = PetHelpDataStoreSingleton
			.getInstance();
	

	public AdvertisementDAO() {
		
	}

	public boolean updateAdvertisement(Advertisement toUpdate) {
		if (toUpdate != null) {
			boolean wasUpdated = this.updateAdvertisement(toUpdate);
			if (wasUpdated) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

    public boolean createAdverstisement(Advertisement newAdverstisement) {
        if (newAdverstisement != null) {
         
            return true;
        }
        return false;
    }

    public boolean deleteAdverstisement(Advertisement toDelete) {
        if (toDelete != null) {
           
            return true;
        }
        return false;
    }

    public Advertisement findAdverstisement(Advertisement toFind) {
        if (toFind != null) {
        	
           return null;
        }
        return null;
    }

	public Advertisement findAdverstisementById(String id) {
		if (id != null) {
			  return null;
		}
		return null;
	}

    public List<Advertisement> findAllAdverstisement() {
    	  return null;
    }

}
