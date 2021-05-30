package playground.clients.enrollment;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import playground.clients.enrollment.domain.Client;

public class ClientRepository
{
   private final Set<Client> clients;

   private static final Logger LOG = LoggerFactory.getLogger( ClientRepository.class );


   public ClientRepository()
   {
      this.clients = new HashSet<>();
   }


   public boolean add( final Client client )
   {
      final boolean added = clients.add( client );
      LOG.info( "Add client {} into repository returned {}", client, added );
      return added;
   }


   public boolean remove( final Client client )
   {
      final boolean removed = this.clients.remove( client );
      LOG.info( "Remove client {} from repository returned {}", client, removed );
      return removed;
   }


   public boolean find( final Client client )
   {
      final boolean exists = this.clients.contains( client );
      LOG.info( "Searching client {} in repository returned {}", client, exists );
      return exists;
   }

}
