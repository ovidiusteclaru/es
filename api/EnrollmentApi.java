
package playground.clients.enrollment.api;

import javax.ws.rs.core.Response;

import playground.clients.enrollment.domain.ClientIdentity;
import playground.clients.enrollment.domain.Document;

public interface EnrollmentApi
{

   /**
    * Performs a full Client check.
    *
    * @param clientIdentity the client identity
    * @return
    */
   Response clientCheck( final ClientIdentity clientIdentity );


   Response generateDoc( final ClientIdentity clientIdentity );


   Response signDoc( final Document doc );

}
