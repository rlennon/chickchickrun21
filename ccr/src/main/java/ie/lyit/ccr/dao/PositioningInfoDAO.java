
package ie.lyit.ccr.dao;

import java.util.List;

import ie.lyit.ccr.model.entities.PositioningInfo;
import ie.lyit.ccr.util.PetHelpDataStoreSingleton;

/**
 *
 * @author juarezjunior
 */
public class PositioningInfoDAO {

	private static PetHelpDataStoreSingleton singleton = PetHelpDataStoreSingleton.getInstance();

	public PositioningInfoDAO() {

	}

	public boolean updatePositioningInfo(PositioningInfo toUpdate) {
		if (toUpdate != null) {
			boolean wasUpdated = this.updatePositioningInfo(toUpdate);
			if (wasUpdated) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean createPositioningInfo(PositioningInfo newPositioningInfo) {
		if (newPositioningInfo != null) {

			return true;
		}
		return false;
	}

	public boolean deletePositioningInfo(PositioningInfo toDelete) {
		if (toDelete != null) {

			return true;
		}
		return false;
	}

	public PositioningInfo findPositioningInfo(PositioningInfo toFind) {
		if (toFind != null) {
			return null;
		}
		return null;
	}

	public PositioningInfo findPositioningInfoById(String id) {
		if (id != null) {
			return null;
		}
		return null;
	}

	public List<PositioningInfo> findAllPositioningInfos() {
		return null;
	}

}
