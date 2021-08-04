package ie.lyit.ccr.control;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.compass.core.Compass;
import org.compass.core.CompassSession;

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

    
    @Override
    public void init()
            throws ServletException {
        servletContext = this.getServletContext();        
   
        }
      
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    

}
