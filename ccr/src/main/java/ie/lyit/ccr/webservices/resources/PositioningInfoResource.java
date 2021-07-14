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

import ie.lyit.ccr.dao.PositioningInfoDAO;
import ie.lyit.ccr.model.entities.PositioningInfo;


/**
 * 
 * @author juarezjunior
 * 
 */
@Path("/positioninginfo")
public class PositioningInfoResource {
	
	private PositioningInfoDAO dao;

	public PositioningInfoResource() {
		super();
		dao = new PositioningInfoDAO();
	}
	
	@GET
	@Path("/{id}/positioninginfo/findPositioningInfoById")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public PositioningInfo findPositioningInfoById(@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new PositioningInfoDAO();
		}
		return dao.findPositioningInfoById(id);
	}

	@POST
	@Path("/{id}/positioninginfo/positioningInfoBulkPost")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Set<PositioningInfo> positioningInfoBulkPost(Set<PositioningInfo> positioningInfos,
			@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new PositioningInfoDAO();
		}
		// return dao.positioningInfoBulkPost(positioningInfos, id);
		return null;
	}

	@POST
	@Path("/positioninginfo/findPositioningInfo")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public PositioningInfo findPositioningInfo(PositioningInfo toFind) {
		if (this.dao == null) {
			dao = new PositioningInfoDAO();
		}
		return dao.findPositioningInfo(toFind);
	}

	@POST
	@Path("/positioninginfo/findAllPositioningInfos")
	// @Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public List<PositioningInfo> findAllPositioningInfo() {
		if (this.dao == null) {
			dao = new PositioningInfoDAO();
		}
		return dao.findAllPositioningInfos();
	}

	@POST
	@Path("/{id}/positioninginfo/createPositioningInfo")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean createPositioningInfo(PositioningInfo positioningInfo, @PathParam("id") String id) {
		if (this.dao == null) {
			dao = new PositioningInfoDAO();
		}
		return dao.createPositioningInfo(positioningInfo);
	}

	@POST
	@Path("/{id}/positioninginfo/deletePositioningInfo")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean deletePositioningInfo(PositioningInfo toDelete) {
		if (this.dao == null) {
			dao = new PositioningInfoDAO();
		}
		return dao.deletePositioningInfo(toDelete);
	}

}
