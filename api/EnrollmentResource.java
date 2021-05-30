
package playground.clients.enrollment.api;

import static playground.clients.enrollment.domain.Document.DocType.enrollment;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import playground.clients.enrollment.ClientRepository;
import playground.clients.enrollment.ClientReputationType;
import playground.clients.enrollment.ReputationService;
import playground.clients.enrollment.domain.ClientIdentity;
import playground.clients.enrollment.domain.Document;
import com.google.inject.Inject;

@Path("/playground/enrollment/clients")
public class EnrollmentResource implements EnrollmentApi
{

   private static final Logger LOG = LoggerFactory.getLogger( EnrollmentResource.class );

   @Context
   private UriInfo uriInfo;

   private final ClientRepository clients;

   private final ReputationService reputationService;


   @Inject
   public EnrollmentResource( final ClientRepository clients,
         final ReputationService reputationService )
   {
      this.clients = clients;
      this.reputationService = reputationService;
   }


   @Override
   @POST
   @Produces(MediaType.APPLICATION_JSON)
   @Path("check")
   public Response clientCheck( final ClientIdentity clientIdentity )
   {
      final ClientReputationType clientReputation = this.reputationService.checkReputation( clientIdentity );
      LOG.info( "Checked client identity {} resulted {}", clientIdentity, clientReputation );
      return Response.status( Response.Status.OK ).entity( clientReputation ).build();
   }


   @Override
   @POST
   @Produces(MediaType.APPLICATION_JSON)
   @Path("generate")
   public Response generateDoc( final ClientIdentity clientIdentity )
   {
      Document document;
      if ( this.reputationService.checkReputation( clientIdentity ) == ClientReputationType.HIGH_RISK )
      {
         document = new Document( Document.DocType.denial,
               "Not allowed to register client " + clientIdentity.getName(), clientIdentity.toClient() );
      }
      else
      {
         document = new Document( enrollment, "Welcome, " + clientIdentity.getName(), clientIdentity.toClient() );
      }
      LOG.info( "Created document {} ", document );
      return Response.status( Response.Status.OK ).entity( document ).build();
   }


   @Override
   @POST
   @Produces(MediaType.APPLICATION_JSON)
   @Path("sign")
   public Response signDoc( final Document document )
   {
      if ( document.getType() == enrollment )
      {
         this.clients.add( document.getClient() );
      }
      document.sign();
      return Response.status( Response.Status.OK ).entity( "Document signed." ).build();
   }

}
