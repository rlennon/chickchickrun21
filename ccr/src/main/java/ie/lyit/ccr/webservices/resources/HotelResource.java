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

import ie.lyit.ccr.dao.HotelDAO;
import ie.lyit.ccr.model.entities.Hotel;


/**
 * 
 * @author juarezjunior
 * 
 */
@Path("/hotel")
public class HotelResource {

	private HotelDAO dao;

	public HotelResource() {
		super();
		dao = new HotelDAO();
	}

	@GET
	@Path("/{id}/hotel/findHotelById")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Hotel findHotelById(@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new HotelDAO();
		}
		return dao.findHotelById(id);
	}

	@POST
	@Path("/{id}/hotel/hotelBulkPost")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Set<Hotel> hotelBulkPost(Set<Hotel> hotels,
			@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new HotelDAO();
		}
		// return dao.hotelBulkPost(hotels, id);
		return null;
	}

	@POST
	@Path("/hotel/findHotel")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Hotel findHotel(Hotel toFind) {
		if (this.dao == null) {
			dao = new HotelDAO();
		}
		return dao.findHotel(toFind);
	}

	@POST
	@Path("/hotel/findAllHotels")
	// @Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public List<Hotel> findAllHotel() {
		if (this.dao == null) {
			dao = new HotelDAO();
		}
		return dao.findAllHotels();
	}

	@POST
	@Path("/{id}/hotel/createHotel")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean createHotel(Hotel hotel, @PathParam("id") String id) {
		if (this.dao == null) {
			dao = new HotelDAO();
		}
		return dao.createHotel(hotel);
	}

	@POST
	@Path("/{id}/hotel/deleteHotel")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean deleteHotel(Hotel toDelete) {
		if (this.dao == null) {
			dao = new HotelDAO();
		}
		return dao.deleteHotel(toDelete);
	}

}
