package ie.lyit.ccr.control.filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

/***
 * 
 * @author juarezjunior
 *
 */
@Priority(Priorities.HEADER_DECORATOR)
public class GZIPContentEncodingFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		responseContext.getHeaders().add("Content-Encoding", "gzip");
	}
}
