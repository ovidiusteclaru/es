package playground.clients.enrollment.domain;

import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Document
{
   public enum DocType
   {
      enrollment, denial
   }

   private static final Logger LOG = LoggerFactory.getLogger( Document.class );

   private final UUID uuid;

   private final String message;

   private final DocType type;

   private final Client client;


   @JsonCreator
   public Document( @JsonProperty("type") final DocType type, @JsonProperty("message") final String message,
         @JsonProperty("client") final Client client )
   {
      uuid = UUID.randomUUID();
      this.type = type;
      this.message = message;
      this.client = client;
   }


   public UUID getUuid()
   {
      return uuid;
   }


   public String getMessage()
   {
      return message;
   }


   public DocType getType()
   {
      return type;
   }


   public Client getClient()
   {
      return client;
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
      final Document other = ( Document ) o;
      return Objects.equals( this.uuid, other.getUuid() );
   }


   @Override
   public int hashCode()
   {
      return Objects.hash( this.uuid );
   }


   @Override
   public String toString()
   {
      return MoreObjects.toStringHelper( getClass() ).add( "id", this.uuid )
            .add( "type", this.type )
            .add( "message", this.message )
            .add( "client", this.client )
            .toString();
   }


   public void sign()
   {
      LOG.info( "Document id {} signed.", this.getUuid() );
   }
}
