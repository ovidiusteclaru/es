package playground.clients.enrollment.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientIdentity
{
   private final String name;

   private final float income;


   @JsonCreator
   public ClientIdentity( @JsonProperty("name") final String name, @JsonProperty("income") final float income )
   {
      this.name = name;
      this.income = income;
   }


   public float getIncome()
   {
      return income;
   }


   public String getName()
   {
      return name;
   }


   public Client toClient()
   {
      return new Client( this.name );
   }
}
