package ie.lyit.ccr.control;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.compass.core.Compass;
import org.compass.core.CompassSession;

import ie.lyit.ccr.dao.PetDAO;
import ie.lyit.ccr.model.entities.Pet;
import ie.lyit.ccr.util.CcrConstants;
import ie.lyit.ccr.util.Image;

/**
 *
 * @author juarezjunior
 */
public class ImageServlet extends HttpServlet {
    
    private static final int DEFAULT_BUFFER_SIZE = 1024000; // 100KB.
    private static final Logger logger = Logger.getLogger(ImageServlet.class.getName());
    
    private Compass compass;
    private CompassSession compassSession;
    private ServletContext servletContext;
    private static PetDAO petDAO;
    private HashMap<String, Pet> myPets;
    private List<Pet> myCollectionPets;
    
    @Override
    public void init()
            throws ServletException {
        servletContext = this.getServletContext();        
        compass = (Compass) servletContext.getAttribute(CcrConstants.COMPASS_CONNECTION);
        if (petDAO == null) {
            petDAO = new PetDAO();
        }
        myPets = new HashMap();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.myCollectionPets = petDAO.findMyOwnPets(request.getUserPrincipal().getName());
        for (Object obj : myCollectionPets) {
            Pet currPet = (Pet) obj;
            myPets.put(String.valueOf(currPet.getId()), currPet);
        }
        // Get ID from request.
        String imageId = request.getParameter("id");
        // Check if ID is supplied to the request.
        if (imageId == null) {
            // Do your thing if the ID is not supplied to the request.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }
        // Lookup Image by ImageId (Pet ID) in myPets retrieved from database...
        //this is #{pet.id} in editProfile.xhtml and MUST BE CHECKED against myPetsCollection, NOT myPets
        Long currentID = new Long(imageId.trim());
        Image image = null;
        for (Pet currPet : myCollectionPets) {
            if (currPet.getId().equals(currentID)) {
                image = currPet.buildCustomImage();
            }            
        }

        // Check if image is actually retrieved from database.
        if (image == null) {
            // Do your thing if the image does not exist in database.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }
        // Init servlet response.
        response.reset();
        response.setBufferSize(DEFAULT_BUFFER_SIZE);
        response.setContentType(image.getContentType());
        response.setContentLength(image.getContent().length);
        response.setHeader("Content-Disposition", "inline; filename=\"" + image.getName() + "\"");
        // Prepare streams.
        BufferedOutputStream output = null;
        try {
            // Open streams.
            output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);
            // Write file contents to response.
            output.write(image.getContent());
        } finally {
            // Gently close streams.
            close(output);
        }
    }
    
    private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

    /**
     * @return the myCollectionPets
     */
    public List<Pet> getMyCollectionPets() {
        return myCollectionPets;
    }

    /**
     * @param myCollectionPets the myCollectionPets to set
     */
    public void setMyCollectionPets(List<Pet> myCollectionPets) {
        this.myCollectionPets = myCollectionPets;
    }
}
