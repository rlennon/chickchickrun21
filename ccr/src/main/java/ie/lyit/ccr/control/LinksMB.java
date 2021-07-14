
package ie.lyit.ccr.control;


import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ie.lyit.ccr.util.CcrConstants;

/**
 *
 * @author juarezjunior
 */
@ManagedBean ("linksMB")
@SessionScoped
public class LinksMB implements Serializable {

    private static Logger logger = Logger.getLogger(LinksMB.class.getName());

    public void navigate(){
    logger.log(Level.INFO,"linksMB.navigate()...");
      addMessage("navigate...");
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String cancel() {
		return CcrConstants.MAIN;
	}

}
