package ie.lyit.ccr.dao;

import java.util.List;

import ie.lyit.ccr.model.entities.Trainer;
import ie.lyit.ccr.util.PetHelpDataStoreSingleton;

/**
 *
 * @author juarezjunior
 *
 */
public class TrainerDAO {

	private static PetHelpDataStoreSingleton singleton = PetHelpDataStoreSingleton.getInstance();

	public TrainerDAO() {

	}

	public boolean updateTrainer(Trainer toUpdate) {
		if (toUpdate != null) {
			boolean wasUpdated = this.updateTrainer(toUpdate);
			if (wasUpdated) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean createTrainer(Trainer newTrainer) {
		if (newTrainer != null) {

			return true;
		}
		return false;
	}

	public boolean deleteTrainer(Trainer toDelete) {
		if (toDelete != null) {

			return true;
		}
		return false;
	}

	public Trainer findTrainer(Trainer toFind) {
		if (toFind != null) {
			return null;

		}
		return null;
	}

	public Trainer findTrainerById(String id) {
		if (id != null) {

			return null;
		}
		return null;
	}

	public List<Trainer> findAllTrainers() {
		return null;
	}

}
