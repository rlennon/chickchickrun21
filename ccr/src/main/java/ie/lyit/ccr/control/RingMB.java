package ie.lyit.ccr.control;

/**
 *
 * @author juarezjunior
 */
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import ie.lyit.ccr.dao.PetDAO;
import ie.lyit.ccr.model.entities.Pet;
import ie.lyit.ccr.model.entities.User;
import ie.lyit.ccr.util.CcrConstants;

/**
*
* @author juarezjunior
*
*/
@ManagedBean("ringMB")
@SessionScoped
public class RingMB implements Serializable {

    private static Logger logger = Logger.getLogger(RingMB.class.getName());
    private List<Pet> pets;

	public PetDAO getPetDAO() {
		return petDAO;
	}

	public void setPetDAO(PetDAO petDAO) {
		this.petDAO = petDAO;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}


    private Pet selectedPet;
    
    private PetDAO petDAO;
   
    private ServletContext servletContext;

    public RingMB() {
        super();
        pets = new ArrayList<Pet>();
        servletContext = (ServletContext) FacesContext
                .getCurrentInstance().getExternalContext().getContext();
        
        //user profile search
        //username (email) must be search key like juarez.barbosa@gmail.com, stands for the current authenticated username from Principal
        User searchKey = new User();
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        String currUserName = null; //httpServletRequest.getUserPrincipal().getName();
        searchKey.setUserName(currUserName);
        User searchResultUser = null; //session.get(User.class, searchKey);
        if (petDAO == null) {
            petDAO = new PetDAO();
        }
        if (searchResultUser != null) {
            pets = petDAO.findMyOwnPets(currUserName);
            for (Object obj : pets) {
                Pet currPet = (Pet) obj;
                currPet.buildPhotoImage();
            }
        }
//        ConvertUtils.register(new DateConverter(null), java.util.Date.class);
//        try {
//            BeanUtils.copyProperties(this, searchResultUser);
//        } catch (IllegalAccessException ex) {
//            logger.log(Level.SEVERE, null, ex);
//        } catch (InvocationTargetException ex) {
//           logger.log(Level.SEVERE, null, ex);
//        } finally {
//            
//        }

    }
    public String cancel() {
		return CcrConstants.MAIN;
	}


    /**
     * @return the pets
     */
    public List<Pet> getPets() {
        return pets;
    }

    /**
     * @param pets the pets to set
     */
    public void setPets(List<Pet> newPets) {
        this.pets = newPets;
    }

    /**
     * @return the selectedPet
     */
    public Pet getSelectedPet() {
        return selectedPet;
    }

    /**
     * @param selectedPet the selectedPet to set
     */
    public void setSelectedPet(Pet newSelectedPet) {
        this.selectedPet = newSelectedPet;
    }




}
