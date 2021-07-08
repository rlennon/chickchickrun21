
package ie.lyit.ccr.dao;

import java.util.List;

import ie.lyit.ccr.model.entities.Post;
import ie.lyit.ccr.util.PetHelpDataStoreSingleton;

/**
 *
 * @author juarezjunior
 */
public class PostDAO {

	private static PetHelpDataStoreSingleton singleton = PetHelpDataStoreSingleton.getInstance();

	public PostDAO() {

	}

	public boolean updatePost(Post toUpdate) {
		if (toUpdate != null) {
			boolean wasUpdated = this.updatePost(toUpdate);
			if (wasUpdated) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean createPost(Post newPost) {
		if (newPost != null) {

			return true;
		}
		return false;
	}

	public boolean deletePost(Post toDelete) {
		if (toDelete != null) {

			return true;
		}
		return false;
	}

	public Post findPost(Post toFind) {
		if (toFind != null) {
			return null;
		}
		return null;
	}

	public Post findPostById(String id) {
		if (id != null) {
			return null;
		}
		return null;
	}

	public List<Post> findAllPosts() {
		return null;
	}

}
