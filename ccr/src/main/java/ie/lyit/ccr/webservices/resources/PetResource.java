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

import ie.lyit.ccr.dao.PetDAO;
import ie.lyit.ccr.model.entities.Pet;


/**
 * 
 * @author juarezjunior
 * 
 */
@Path("/pet")
public class PetResource {
	
	private PetDAO dao;

	public PetResource() {
		super();
		dao = new PetDAO();
	}
	
	@GET
	@Path("/{id}/pet/findPetById")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Pet findPetById(@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new PetDAO();
		}
		return dao.findPetById(id);
	}

	@POST
	@Path("/{id}/pet/petBulkPost")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Set<Pet> kennelBulkPost(Set<Pet> pets,
			@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new PetDAO();
		}
		// return dao.petBulkPost(pets, id);
		return null;
	}

	@POST
	@Path("/pet/findPet")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Pet findPet(Pet toFind) {
		if (this.dao == null) {
			dao = new PetDAO();
		}
		return dao.findPet(toFind);
	}

	@POST
	@Path("/pet/findAllPets")
	// @Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public List<Pet> findAllPets() {
		if (this.dao == null) {
			dao = new PetDAO();
		}
		return dao.findAllPets();
	}

	@POST
	@Path("/{id}/pet/createPet")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean createPet(Pet pet, @PathParam("id") String id) {
		if (this.dao == null) {
			dao = new PetDAO();
		}
		return dao.createPet(pet);
	}

	@POST
	@Path("/{id}/pet/deletePet")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean deletePet(Pet toDelete) {
		if (this.dao == null) {
			dao = new PetDAO();
		}
		return dao.deletePet(toDelete);
	}

}
