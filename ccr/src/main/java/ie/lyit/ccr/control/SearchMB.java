package ie.lyit.ccr.control;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import ie.lyit.ccr.util.CcrConstants;

/**
 *
 * @author juarezjunior
 */
@ManagedBean("searchMB")
@RequestScoped
public class SearchMB implements Serializable {

 
    private ServletContext servletContext;
    private String searchKey;

    public SearchMB() {
        super();
        servletContext = (ServletContext) FacesContext
                .getCurrentInstance().getExternalContext().getContext();

 
    }

    public SearchMB(String searchKey) {
        super();
        this.searchKey = searchKey;
        servletContext = (ServletContext) FacesContext
                .getCurrentInstance().getExternalContext().getContext();

  
    }

    public String search() {

    	   return CcrConstants.SEARCH_RESULTS;

    }

    public String cancel() {
		return CcrConstants.MAIN;
	}

    public String search(String searchKey) {


        return CcrConstants.SEARCH_RESULTS;
    }

    /**
     * @return the searchKey
     */
    public String getSearchKey() {
        return searchKey;
    }

    /**
     * @param searchKey the searchKey to set
     */
    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }
}
