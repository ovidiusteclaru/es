package playground.clients.enrollment;

import playground.clients.enrollment.domain.ClientIdentity;

public class ReputationService
{
   public ClientReputationType checkReputation( final ClientIdentity clientIdentity )
   {
      return ClientReputationType.getReputation( incomeToReputation( clientIdentity.getIncome() ) );
   }


   private int incomeToReputation( final float income )
   {
      if ( income > 10_000 )
         return 8;
      else if ( income > 1_000 )
         return 64;
      else
         return 128;
   }
}
