package ie.lyit.ccr.dao;

import java.util.List;

import ie.lyit.ccr.model.entities.Course;
import ie.lyit.ccr.util.PetHelpDataStoreSingleton;

/**
 *
 * @author juarezjunior
 */
public class CourseDAO {

	private static PetHelpDataStoreSingleton singleton = PetHelpDataStoreSingleton.getInstance();

	public CourseDAO() {

	}

	public boolean updatePet(Course toUpdate) {
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

	public boolean createPet(Course newPet) {
		if (newPet != null) {

			return true;
		}
		return false;
	}

	public boolean deletePet(Course toDelete) {
		if (toDelete != null) {
			return true;
		}
		return false;
	}

	public Course findPet(Course toFind) {
		if (toFind != null) {
			return null;
		}
		return null;
	}

	public Course findPetById(String id) {
		if (id != null) {
			return null;
		}
		return null;
	}

	public List<Course> findAllPets() {
		return null;
	}

	public List<Course> findMyOwnPets(String userName) {

		if (userName != null) {
			return null;
		}
		return null;
	}
}
