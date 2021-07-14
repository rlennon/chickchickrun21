package ie.lyit.ccr.dao;

import java.util.List;

import ie.lyit.ccr.model.entities.User;
import ie.lyit.ccr.util.PetHelpDataStoreSingleton;

/**
 *
 * @author juarezjunior
 */
public class UserDAO {

	private static PetHelpDataStoreSingleton singleton = PetHelpDataStoreSingleton.getInstance();

	public UserDAO() {

	}

	public boolean updateUser(User toUpdate) {
		if (toUpdate != null) {
			boolean wasUpdated = this.updateUser(toUpdate);
			if (wasUpdated) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean createUser(User newUser) {
		if (newUser != null) {

			return true;
		}
		return false;
	}

	public boolean deleteUser(User toDelete) {
		if (toDelete != null) {

			return true;
		}
		return false;
	}

	public User findUser(User toFind) {
		if (toFind != null) {
			return null;
		}
		return null;
	}

	public User findUserByUserName(String userName) {
		if (userName != null) {

			return null;
		}
		return null;
	}

	public List<User> findAllUsers() {
		return null;
	}
}
