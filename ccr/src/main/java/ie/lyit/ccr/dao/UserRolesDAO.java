package ie.lyit.ccr.dao;

import java.util.List;

import ie.lyit.ccr.model.entities.User;
import ie.lyit.ccr.model.entities.UserRoles;
import ie.lyit.ccr.util.PetHelpDataStoreSingleton;

/**
 *
 * @author juarezjunior
 */
public class UserRolesDAO {

	private static PetHelpDataStoreSingleton singleton = PetHelpDataStoreSingleton.getInstance();

	public UserRolesDAO() {

	}

	public boolean updateUserRoles(UserRoles toUpdate) {
		if (toUpdate != null) {
			boolean wasUpdated = this.updateUserRoles(toUpdate);
			if (wasUpdated) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean createUserRoles(UserRoles newUserRole) {
		if (newUserRole != null) {

			return true;
		}
		return false;
	}

	public boolean deleteUserRoles(UserRoles toDelete) {
		if (toDelete != null) {

			return true;
		}
		return false;
	}

	public UserRoles findUserRoles(UserRoles toFind) {
		if (toFind != null) {
			return null;
		}
		return null;
	}

	public UserRoles findUserRolesById(String id) {
		if (id != null) {

			return null;
		}
		return null;
	}

	public List<UserRoles> findAllUserRoles() {
		return null;
	}

}
