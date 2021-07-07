package ie.lyit.ccr.control.listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import ie.lyit.ccr.util.CcrConstants;

/**
 * 
 *
 * @author juarezjunior
 */
public class CcrHttpSessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute(CcrConstants.SHOW_WELCOME_NOTIFICATION_BAR, "true");       
    }

    public void sessionDestroyed(HttpSessionEvent se) {
    	se.getSession().removeAttribute(CcrConstants.SHOW_WELCOME_NOTIFICATION_BAR);   
    }
}
