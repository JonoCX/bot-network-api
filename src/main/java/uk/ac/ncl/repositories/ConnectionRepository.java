package uk.ac.ncl.repositories;

import org.hibernate.query.Query;
import uk.ac.ncl.AbstractHibernateRepository;
import uk.ac.ncl.domain.Connection;
import uk.ac.ncl.domain.User;

import java.util.List;

/**
 * @author Jonathan Carlton
 */
public class ConnectionRepository extends AbstractHibernateRepository<Connection, Long>
{
    @SuppressWarnings("unchecked")
    public List<Connection> findAllConnectionsFor(User user) {
        Query query = getSession().createQuery(
                "select distinct c from Connection c where c.origin = :user or c.destination = :user"
        ).setParameter("user", user);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<Connection> findAllFollowers(User user) {
        Query query = getSession().createQuery(
                "select c.origin from Connection c where c.destination = :user"
        ).setParameter("user", user);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<Connection> findAllFollowing(User user) {
        Query query = getSession().createQuery(
                "select c.destination from Connection c where c.origin = :user"
        ).setParameter("user", user);
        return query.list();
    }

    /**
     * Updates connections that are stored in the database.
     * @return the updated connections
     */
    public List<Connection> updateConnections(List<Connection> updated) {
        return (List<Connection>) this.save(updated);
    }

    /**
     * Update a single connection in the database.
     * @param update    the new connection
     * @return          the saved connection.
     */
    public Connection updateConnection(Connection update) {
        return this.save(update);
    }
}
