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

import ie.lyit.ccr.dao.VeterinaryDAO;
import ie.lyit.ccr.model.entities.Veterinary;


/**
 * 
 * @author juarezjunior
 * 
 */
@Path("/veterinary")
public class VeterinaryResource {
	
	private VeterinaryDAO dao;

	public VeterinaryResource() {
		super();
		dao = new VeterinaryDAO();
	}
	
	@GET
	@Path("/{id}/veterinary/findVeterinaryById")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Veterinary findVeterinaryById(@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new VeterinaryDAO();
		}
		return dao.findVeterinaryById(id);
	}

	@POST
	@Path("/{id}/veterinary/veterinaryBulkPost")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Set<Veterinary> veterinaryBulkPost(Set<Veterinary> veterinaries,
			@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new VeterinaryDAO();
		}
		// return dao.veterinaryBulkPost(veterinaries, id);
		return null;
	}

	@POST
	@Path("/veterinary/findVeterinary")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Veterinary findVeterinary(Veterinary toFind) {
		if (this.dao == null) {
			dao = new VeterinaryDAO();
		}
		return dao.findVeterinary(toFind);
	}

	@POST
	@Path("/veterinary/findAllVeterinaries")
	// @Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public List<Veterinary> findAllVeterinaries() {
		if (this.dao == null) {
			dao = new VeterinaryDAO();
		}
		return dao.findAllVeterinaries();
	}

	@POST
	@Path("/{id}/veterinary/createVeterinary")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean createVeterinary(Veterinary veterinary, @PathParam("id") String id) {
		if (this.dao == null) {
			dao = new VeterinaryDAO();
		}
		return dao.createVeterinary(veterinary);
	}

	@POST
	@Path("/{id}/veterinary/deleteVeterinary")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean deleteVeterinary(Veterinary toDelete) {
		if (this.dao == null) {
			dao = new VeterinaryDAO();
		}
		return dao.deleteVeterinary(toDelete);
	}

}
