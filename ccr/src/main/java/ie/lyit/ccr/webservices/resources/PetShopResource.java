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

import ie.lyit.ccr.dao.PetShopDAO;
import ie.lyit.ccr.model.entities.PetShop;


/**
 * 
 * @author juarezjunior
 * 
 */
@Path("/petshop")
public class PetShopResource {
	
	private PetShopDAO dao;

	public PetShopResource() {
		super();
		dao = new PetShopDAO();
	}
	
	@GET
	@Path("/{id}/petshop/findPetShopById")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public PetShop findPetShopById(@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new PetShopDAO();
		}
		return dao.findPetShopById(id);
	}

	@POST
	@Path("/{id}/petshop/ petshopBulkPost")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Set<PetShop> petshopBulkPost(Set<PetShop> petshops,
			@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new PetShopDAO();
		}
		// return dao.petshopBulkPost(hotels, id);
		return null;
	}

	@POST
	@Path("/petshop/findPetShop")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public PetShop findPetShop(PetShop toFind) {
		if (this.dao == null) {
			dao = new PetShopDAO();
		}
		return dao.findPetShop(toFind);
	}

	@POST
	@Path("/petshop/findAllPetShops")
	// @Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public List<PetShop> findAllPetShops() {
		if (this.dao == null) {
			dao = new PetShopDAO();
		}
		return dao.findAllPetShops();
	}

	@POST
	@Path("/{id}/petshop/createPetShop")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean createPetShop(PetShop petshop, @PathParam("id") String id) {
		if (this.dao == null) {
			dao = new PetShopDAO();
		}
		return dao.createPetShop(petshop);
	}

	@POST
	@Path("/{id}/petshop/deletePetShop")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean deletePetShop(PetShop toDelete) {
		if (this.dao == null) {
			dao = new PetShopDAO();
		}
		return dao.deletePetShop(toDelete);
	}
}
