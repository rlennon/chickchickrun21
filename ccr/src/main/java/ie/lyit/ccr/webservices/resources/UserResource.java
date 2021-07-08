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

import ie.lyit.ccr.dao.UserDAO;
import ie.lyit.ccr.model.entities.User;


/**
 * 
 * @author juarezjunior
 * 
 */
@Path("/user")
public class UserResource {
	
	private UserDAO dao;

	public UserResource() {
		super();
		dao = new UserDAO();
	}
	
	@GET
	@Path("/{userName}/user/findUserById")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public User findUserById(@PathParam("userName") String userName) {
		if (this.dao == null) {
			dao = new UserDAO();
		}
		return dao.findUserByUserName(userName);
	}

	@POST
	@Path("/{id}/user/userBulkPost")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Set<User> userBulkPost(Set<User> users,
			@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new UserDAO();
		}
		// return dao.userBulkPost(users, id);
		return null;
	}

	@POST
	@Path("/user/findUser")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public User findUser(User toFind) {
		if (this.dao == null) {
			dao = new UserDAO();
		}
		return dao.findUser(toFind);
	}

	@POST
	@Path("/user/findAllUsers")
	// @Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public List<User> findAllUser() {
		if (this.dao == null) {
			dao = new UserDAO();
		}
		return dao.findAllUsers();
	}

	@POST
	@Path("/{id}/user/createUser")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean createUser(User user, @PathParam("id") String id) {
		if (this.dao == null) {
			dao = new UserDAO();
		}
		return dao.createUser(user);
	}

	@POST
	@Path("/{id}/user/deleteUser")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean deleteUser(User toDelete) {
		if (this.dao == null) {
			dao = new UserDAO();
		}
		return dao.deleteUser(toDelete);
	}

}
