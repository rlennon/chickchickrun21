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

import ie.lyit.ccr.dao.ManufacturerDAO;
import ie.lyit.ccr.model.entities.Manufacturer;


/**
 * 
 * @author juarezjunior
 * 
 */
@Path("/manufacturer")
public class ManufacturerResource {
	
	private ManufacturerDAO dao;

	public ManufacturerResource() {
		super();
		dao = new ManufacturerDAO();
	}
	
	@GET
	@Path("/{id}/manufacturer/findManufacturerById")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Manufacturer findManufacturerById(@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new ManufacturerDAO();
		}
		return dao.findManufacturerById(id);
	}

	@POST
	@Path("/{id}/manufacturer/manufacturerBulkPost")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Set<Manufacturer> manufacturerBulkPost(Set<Manufacturer> mans,
			@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new ManufacturerDAO();
		}
		// return dao.manufacturerBulkPost(mans, id);
		return null;
	}

	@POST
	@Path("/manufacturer/findManufacturer")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Manufacturer findManufacturer(Manufacturer toFind) {
		if (this.dao == null) {
			dao = new ManufacturerDAO();
		}
		return dao.findManufacturer(toFind);
	}

	@POST
	@Path("/manufacturer/findAllManufacturers")
	// @Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public List<Manufacturer> findAllManufacturers() {
		if (this.dao == null) {
			dao = new ManufacturerDAO();
		}
		return dao.findAllManufacturers();
	}

	@POST
	@Path("/{id}/manufacturer/createManufacturer")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean createManufacturer(Manufacturer manufacturer, @PathParam("id") String id) {
		if (this.dao == null) {
			dao = new ManufacturerDAO();
		}
		return dao.createManufacturer(manufacturer);
	}

	@POST
	@Path("/{id}/manufacturer/deleteManufacturer")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean deleteManufacturer(Manufacturer toDelete) {
		if (this.dao == null) {
			dao = new ManufacturerDAO();
		}
		return dao.deleteManufacturer(toDelete);
	}

}
