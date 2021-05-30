package playground.clients.enrollment.domain;

import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
final public class Client
{
   private final UUID id;

   private final String name;

   //TODO: add more client relevant fields


   @JsonCreator
   public Client(  @JsonProperty("name") final String name )
   {
      this.id = UUID.randomUUID();
      this.name = name;
   }


   public String getName()
   {
      return name;
   }


   public UUID getId()
   {
      return id;
   }


   @Override
   public boolean equals( final Object o )
   {
      if ( this == o )
      {
         return true;
      }
      if ( o == null || getClass() != o.getClass() )
      {
         return false;
      }
      final Client other = ( Client ) o;
      return Objects.equals( this.name, other.getName() ) && Objects.equals( this.id, other.getId() );
   }


   @Override
   public int hashCode()
   {
      return Objects.hash( this.getName(), this.getId() );
   }


   @Override
   public String toString()
   {
      return MoreObjects.toStringHelper( getClass() ).add( "name", this.name ).add( "id", this.id ).toString();
   }

}
