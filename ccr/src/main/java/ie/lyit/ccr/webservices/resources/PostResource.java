package ie.lyit.ccr.webservices.resources;

import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ie.lyit.ccr.dao.KennelDAO;
import ie.lyit.ccr.dao.PostDAO;
import ie.lyit.ccr.model.entities.Kennel;
import ie.lyit.ccr.model.entities.Post;


/**
 * 
 * @author juarezjunior
 * 
 */
@Path("/post")
public class PostResource {
	
	private PostDAO dao;

	public PostResource() {
		super();
		dao = new PostDAO();
	}
	
	@GET
	@Path("/{id}/post/findPostById")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Post findPostById(@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new PostDAO();
		}
		return dao.findPostById(id);
	}

	@POST
	@Path("/{id}/post/postBulkPost")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Set<Post> postBulkPost(Set<Post> posts,
			@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new PostDAO();
		}
		// return dao.postBulkPost(posts, id);
		return null;
	}

	@POST
	@Path("/post/findPost")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Post findPost(Post toFind) {
		if (this.dao == null) {
			dao = new PostDAO();
		}
		return dao.findPost(toFind);
	}

	@POST
	@Path("/post/findAllPosts")
	// @Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public List<Post> findAllPost() {
		if (this.dao == null) {
			dao = new PostDAO();
		}
		return dao.findAllPosts();
	}

	@POST
	@Path("/{id}/post/createPost")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean createPost(Post post, @PathParam("id") String id) {
		if (this.dao == null) {
			dao = new PostDAO();
		}
		return dao.createPost(post);
	}

	@POST
	@Path("/{id}/post/deletePost")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean deletePost(Post toDelete) {
		if (this.dao == null) {
			dao = new PostDAO();
		}
		return dao.deletePost(toDelete);
	}
}
