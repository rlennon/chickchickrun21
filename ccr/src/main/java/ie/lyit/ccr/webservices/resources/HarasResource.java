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

import ie.lyit.ccr.dao.HarasDAO;
import ie.lyit.ccr.model.entities.Haras;


/**
 * 
 * @author juarezjunior
 * 
 */
@Path("/haras")
public class HarasResource {

	private HarasDAO dao;

	public HarasResource() {
		super();
		dao = new HarasDAO();
	}

	@GET
	@Path("/{id}/haras/findHarasById")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Haras findHarasById(@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new HarasDAO();
		}
		return dao.findHarasById(id);
	}

	@POST
	@Path("/{id}/haras/harasBulkPost")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Set<Haras> harasBulkPost(Set<Haras> harases,
			@PathParam("id") String id) {
		// TODO - implement this one...
		if (this.dao == null) {
			dao = new HarasDAO();
		}
		// return dao.harasBulkPost(id, harases);
		return null;
	}

	@POST
	@Path("/haras/findHaras")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Haras findHaras(Haras toFind) {
		if (this.dao == null) {
			dao = new HarasDAO();
		}
		return dao.findHaras(toFind);
	}

	@POST
	@Path("/haras/findAllHaras")
	// @Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public List<Haras> findAllHaras() {
		if (this.dao == null) {
			dao = new HarasDAO();
		}
		return dao.findAllHaras();
	}

	@POST
	@Path("/{id}/haras/createHaras")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean createHaras(Haras haras, @PathParam("id") String id) {
		if (this.dao == null) {
			dao = new HarasDAO();
		}
		return dao.createHaras(haras);
	}

	@POST
	@Path("/{id}/haras/deleteHaras")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean deleteHaras(Haras toDelete) {
		if (this.dao == null) {
			dao = new HarasDAO();
		}
		return dao.deleteHaras(toDelete);
	}

}
