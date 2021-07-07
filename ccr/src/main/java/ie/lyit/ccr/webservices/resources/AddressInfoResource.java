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

import ie.lyit.ccr.dao.AddressInfoDAO;
import ie.lyit.ccr.model.entities.AddressInfo;

/**
 * 
 * @author juarezjunior
 * 
 */
@Path("/addressinfo")
public class AddressInfoResource {

	private AddressInfoDAO dao;

	public AddressInfoResource() {
		super();
		dao = new AddressInfoDAO();
	}

	@GET
	@Path("/{id}/addresinfo/findAddressInfoById")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public AddressInfo findAddressInfoById(@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new AddressInfoDAO();
		}
		return dao.findAddressInfoById(id);

	}

	@POST
	@Path("/{userName}/addressinfo/addressInfoBulkPost")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Set<AddressInfo> addressInfoBulkPost(Set<AddressInfo> addresses,
			@PathParam("userName") String id) {
		if (this.dao == null) {
			dao = new AddressInfoDAO();
		}
		// TODO - implement this one...
		// return dao.addressInfoBulkPost(id, addresses);
		return null;
	}

	@POST
	@Path("/{userName}/addressinfo/findAddressInfo")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public AddressInfo findAddressInfo(AddressInfo toFind,@PathParam("userName") String id) {
		if (this.dao == null) {
			dao = new AddressInfoDAO();
		}
		return dao.findAddressInfo(toFind);
	}

	@POST
	@Path("/addressinfo/findAllAddressInfos")
	// @Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public List<AddressInfo> findAllAddressInfos() {
		if (this.dao == null) {
			dao = new AddressInfoDAO();
		}
		return dao.findAllAddressInfos();
	}

	@POST
	@Path("/{id}/addressinfo/createAddressInfo")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean createAddressInfo(AddressInfo newAddressInfo,
			@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new AddressInfoDAO();
		}
		return dao.createAddressInfo(newAddressInfo);
	}

	@POST
	@Path("/{id}/addressinfo/deleteAddressInfo")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean deleteAddressInfo(AddressInfo toDelete) {
		if (this.dao == null) {
			dao = new AddressInfoDAO();
		}
		return dao.deleteAddressInfo(toDelete);
	}

}
