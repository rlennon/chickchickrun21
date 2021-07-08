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

import ie.lyit.ccr.dao.AdvertisementDAO;
import ie.lyit.ccr.model.entities.Advertisement;


/**
 * 
 * @author juarezjunior
 * 
 */
@Path("/advertisement")
public class AdvertisementResource {

	private AdvertisementDAO dao;

	public AdvertisementResource() {
		super();
		dao = new AdvertisementDAO();
	}

	@GET
	@Path("/{id}/advertisement/findAdverstisementById")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Advertisement findAdverstisementById(@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new AdvertisementDAO();
			
		}
		return dao.findAdverstisementById(id);
	}

	@POST
	@Path("/{id}/advertisement/advertisementBulkPost")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Set<Advertisement> advertisementBulkPost(Set<Advertisement> ads,
			@PathParam("id") String id) {
		// TODO - implement this one...
		if (this.dao == null) {
			dao = new AdvertisementDAO();
		}
		// return dao.advertisementBulkPost(id, ads);
		return null;
	}

	@POST
	@Path("/advertisement/findAdverstisement")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Advertisement findAdverstisement(Advertisement toFind) {
		if (this.dao == null) {
			dao = new AdvertisementDAO();
		}
		return dao.findAdverstisement(toFind);
	}

	@POST
	@Path("/advertisement/findAllAdverstisement")
	// @Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public List<Advertisement> findAllAdverstisement() {
		if (this.dao == null) {
			dao = new AdvertisementDAO();
		}
		return dao.findAllAdverstisement();
	}

	@POST
	@Path("/{id}/advertisement/createAdverstisement")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean createAdverstisement(Advertisement newAdverstisement,
			@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new AdvertisementDAO();
		}
		return dao.createAdverstisement(newAdverstisement);
	}

	@POST
	@Path("/{id}/advertisement/deleteAdverstisement")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean deleteAdverstisement(Advertisement toDelete) {
		if (this.dao == null) {
			dao = new AdvertisementDAO();
		}
		return dao.deleteAdverstisement(toDelete);
	}

}
