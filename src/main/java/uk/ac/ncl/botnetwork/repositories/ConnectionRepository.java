package uk.ac.ncl.botnetwork.repositories;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import uk.ac.ncl.botnetwork.AbstractHibernateRepository;
import uk.ac.ncl.botnetwork.domain.Config;
import uk.ac.ncl.botnetwork.domain.Connection;
import uk.ac.ncl.botnetwork.domain.User;

import java.util.List;

/**
 * @author Jonathan Carlton
 */
@Repository
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
    public List<User> findAllFollowers(User user) {
        Query query = getSession().createQuery(
                "select c.origin from Connection c where c.destination = :user"
        ).setParameter("user", user);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<User> findAllFollowing(User user) {
        Query query = getSession().createQuery(
                "select c.destination from Connection c where c.origin = :user"
        ).setParameter("user", user);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<Long> findAllFollowersIDs(User user) {
        Query query = getSession().createQuery(
                "select c.origin.twitterId from Connection c where c.destination = :user"
        ).setParameter("user", user);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<Connection> getByConfig(Config c) {
        Query query = getSession().createQuery(
                "from Connection where config = :config"
        ).setParameter("config", c);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<Long> findAllFollowingIDs(User user) {
        Query query = getSession().createQuery(
                "select c.destination.twitterId from Connection c where c.origin = :user"
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
