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

import ie.lyit.ccr.dao.TrainerDAO;
import ie.lyit.ccr.model.entities.Trainer;


/**
 * 
 * @author juarezjunior
 * 
 */
@Path("/trainer")
public class TrainerResource {
	
	private TrainerDAO dao;

	public TrainerResource() {
		super();
		dao = new TrainerDAO();
	}
	
	@GET
	@Path("/{id}/trainer/findTrainerById")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Trainer findTrainerById(@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new TrainerDAO();
		}
		return dao.findTrainerById(id);
	}

	@POST
	@Path("/{id}/trainer/trainerBulkPost")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Set<Trainer> trainerBulkPost(Set<Trainer> trainers,
			@PathParam("id") String id) {
		if (this.dao == null) {
			dao = new TrainerDAO();
		}
		// return dao.trainerBulkPost(trainers, id);
		return null;
	}

	@POST
	@Path("/trainer/findTrainer")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public Trainer findTrainer(Trainer toFind) {
		if (this.dao == null) {
			dao = new TrainerDAO();
		}
		return dao.findTrainer(toFind);
	}

	@POST
	@Path("/trainer/findAllTrainers")
	// @Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public List<Trainer> findAllTrainers() {
		if (this.dao == null) {
			dao = new TrainerDAO();
		}
		return dao.findAllTrainers();
	}

	@POST
	@Path("/{id}/trainer/createTrainer")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean createTrainer(Trainer trainer, @PathParam("id") String id) {
		if (this.dao == null) {
			dao = new TrainerDAO();
		}
		return dao.createTrainer(trainer);
	}

	@POST
	@Path("/{id}/trainer/deleteTrainer")
	@Consumes(PetHelpMediaType.JSON_UTF8)
	@Produces(PetHelpMediaType.JSON_UTF8)
	public boolean deleteTrainer(Trainer toDelete) {
		if (this.dao == null) {
			dao = new TrainerDAO();
		}
		return dao.deleteTrainer(toDelete);
	}

}
