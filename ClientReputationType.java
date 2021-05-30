package playground.clients.enrollment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum ClientReputationType
{
   NO_RISK( "Candidate with no risk)" ),
   MEDIUM_RISK( "Candidate with medium risk, but enrollment still possible" ),
   HIGH_RISK( "Risky candidate, enrollment not acceptable" );

   private final String description;


   @JsonCreator
   private ClientReputationType( @JsonProperty("description") final String description )
   {
      this.description = description;
   }


   public static ClientReputationType getReputation( final int reputation )
   {
      if ( reputation >= 0 && reputation <= 20 )
         return NO_RISK;
      if ( reputation >= 21 && reputation <= 99 )
         return MEDIUM_RISK;

      return HIGH_RISK;
   }


   public String getDescription()
   {
      return this.description;
   }

}
