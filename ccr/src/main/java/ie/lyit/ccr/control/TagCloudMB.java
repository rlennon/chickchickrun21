package ie.lyit.ccr.control;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;

//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;

import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudModel;

import ie.lyit.ccr.util.CcrConstants;

/**
*
* @author juarezjunior
*
*/
@ManagedBean ("tagCloudMB")
@SessionScoped
public class TagCloudMB implements Serializable {

    private static Logger logger = Logger.getLogger(TagCloudMB.class.getName());

    private TagCloudModel model;

    public TagCloudMB() {
        model = new DefaultTagCloudModel();
        model.addTag(new DefaultTagCloudItem("DevOps", "#", 1));
        model.addTag(new DefaultTagCloudItem("Terraform", "#", 3));
        model.addTag(new DefaultTagCloudItem("Java", "#", 2));
        model.addTag(new DefaultTagCloudItem("Python", "#", 2));
        model.addTag(new DefaultTagCloudItem("Ansible", "#", 4));
        model.addTag(new DefaultTagCloudItem("Kubernetes", "#", 2));
        model.addTag(new DefaultTagCloudItem("Scrum", "#", 5));
        model.addTag(new DefaultTagCloudItem("PowerShell", "#", 3));
        model.addTag(new DefaultTagCloudItem("Javascript", "#", 4));
        model.addTag(new DefaultTagCloudItem("YAML", "#", 1));
        model.addTag(new DefaultTagCloudItem("Visual Studio Code", "#", 1));
        model.addTag(new DefaultTagCloudItem("PyCharm", "#", 3));
        model.addTag(new DefaultTagCloudItem("Azure", "#", 3));
        model.addTag(new DefaultTagCloudItem("AWS", "#", 5));
        model.addTag(new DefaultTagCloudItem("GCP", "#", 5));
        model.addTag(new DefaultTagCloudItem("Chef", "#", 2));
        model.addTag(new DefaultTagCloudItem("Docker", "#", 5));
        model.addTag(new DefaultTagCloudItem("OCI", "#", 3));
        model.addTag(new DefaultTagCloudItem("Jenkins", "#", 4));
        model.addTag(new DefaultTagCloudItem("Junit", "#", 1));
    }

    public String cancel() {
		return CcrConstants.MAIN;
	}

    public TagCloudModel getModel() {
        return model;
    }
}
