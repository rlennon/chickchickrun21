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

import ie.lyit.ccr.dao.MuralDAO;
import ie.lyit.ccr.model.entities.Mural;


/**
 * 
 * @author juarezjunior
 * 
 */
@Path("/mural")
public class MuralResource {
	
	private MuralDAO dao;

	public MuralResource() {
		super();
		dao = new MuralDAO();
	}
	
	@GET
	@Path("/{id}/mural/findMuralById")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Mural findMuralById(@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new MuralDAO();
		}
		return dao.findMuralById(id);
	}

	@POST
	@Path("/{id}/mural/muralBulkPost")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Set<Mural> muralBulkPost(Set<Mural> murals,
			@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new MuralDAO();
		}
		// return dao.muralBulkPost(murals, id);
		return null;
	}

	@POST
	@Path("/mural/findMural")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Mural findMural(Mural toFind) {
		if (this.dao == null) {
			dao = new MuralDAO();
		}
		return dao.findMural(toFind);
	}

	@POST
	@Path("/mural/findAllMurals")
	// @Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public List<Mural> findAllMurals() {
		if (this.dao == null) {
			dao = new MuralDAO();
		}
		return dao.findAllMurals();
	}

	@POST
	@Path("/{id}/mural/createMural")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean createMural(Mural mural, @PathParam("id") String id) {
		if (this.dao == null) {
			dao = new MuralDAO();
		}
		return dao.createMural(mural);
	}

	@POST
	@Path("/{id}/mural/deleteMural")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean deleteMural(Mural toDelete) {
		if (this.dao == null) {
			dao = new MuralDAO();
		}
		return dao.deleteMural(toDelete);
	}

}
