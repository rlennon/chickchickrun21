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
import ie.lyit.ccr.model.entities.Kennel;


/**
 * 
 * @author juarezjunior
 * 
 */
@Path("/kennel")
public class KennelResource {

	private KennelDAO dao;

	public KennelResource() {
		super();
		dao = new KennelDAO();
	}

	@GET
	@Path("/{id}/kennel/findKennelById")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Kennel findKennelById(@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new KennelDAO();
		}
		return dao.findKennelById(id);
	}

	@POST
	@Path("/{id}/kennel/kennelBulkPost")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Set<Kennel> kennelBulkPost(Set<Kennel> kennels,
			@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new KennelDAO();
		}
		// return dao.kennelBulkPost(kennels, id);
		return null;
	}

	@POST
	@Path("/kennel/findKennel")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Kennel findKennel(Kennel toFind) {
		if (this.dao == null) {
			dao = new KennelDAO();
		}
		return dao.findKennel(toFind);
	}

	@POST
	@Path("/kennel/findAllKennels")
	// @Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public List<Kennel> findAllKennels() {
		if (this.dao == null) {
			dao = new KennelDAO();
		}
		return dao.findAllKennels();
	}

	@POST
	@Path("/{id}/kennel/createKennel")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean createKennel(Kennel kennel, @PathParam("id") String id) {
		if (this.dao == null) {
			dao = new KennelDAO();
		}
		return dao.createKennel(kennel);
	}

	@POST
	@Path("/{id}/kennel/deleteKennel")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean deleteKennel(Kennel toDelete) {
		if (this.dao == null) {
			dao = new KennelDAO();
		}
		return dao.deleteKennel(toDelete);
	}

}
