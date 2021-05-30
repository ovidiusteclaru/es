
package playground.clients.enrollment.api;

import playground.clients.enrollment.ClientRepository;
import playground.clients.enrollment.ReputationService;
import com.google.inject.AbstractModule;

public class EnrollmentModule extends AbstractModule
{

   @Override
   protected void configure()
   {
      ClientRepository clients = new ClientRepository();
      ReputationService reputationService = new ReputationService();
      bind( EnrollmentApi.class ).to( EnrollmentResource.class );
      bind( ClientRepository.class ).toInstance( clients );
      bind( ReputationService.class ).toInstance( reputationService );
   }

}
