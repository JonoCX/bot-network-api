package uk.ac.ncl.botnetwork.repositories;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import uk.ac.ncl.botnetwork.AbstractHibernateRepository;
import uk.ac.ncl.botnetwork.domain.Config;
import uk.ac.ncl.botnetwork.domain.Connection;
import uk.ac.ncl.botnetwork.domain.User;

import java.util.List;

/**
 * Repository for performing actions on the connections
 * stored in the database.
 *
 * These connections are stored in an origin to destination
 * basis.
 *
 * @author Jonathan Carlton
 * @see Connection
 */
@Repository
public class ConnectionRepository extends AbstractHibernateRepository<Connection, Long>
{
    /**
     * Given a user, query the database to find all {@link Connection}'s for
     * a given {@link User}.
     * <pre>
     *     <code>
     *         select distinct c from Connection c where c.origin = :user or c.destination = :user
     *     </code>
     * </pre>
     * @param user      the given user to find all connections for.
     * @return          a list of connections corresponding to the user parameter
     * @see List
     * @see Connection
     * @see User
     */
    @SuppressWarnings("unchecked")
    public List<Connection> findAllConnectionsFor(User user) {
        Query query = getSession().createQuery(
                "select distinct c from Connection c where c.origin = :user or c.destination = :user"
        ).setParameter("user", user);
        return query.list();
    }

    /**
     * Given a user, query the database to find all users that
     * follower the user.
     * <pre>
     *     <code>
     *         select c.origin from Connection c where c.destination = :user
     *     </code>
     * </pre>
     * @param user  the given user
     * @return      a list of all the users followers
     * @see List
     * @see User
     */
    @SuppressWarnings("unchecked")
    public List<User> findAllFollowers(User user) {
        Query query = getSession().createQuery(
                "select c.origin from Connection c where c.destination = :user"
        ).setParameter("user", user);
        return query.list();
    }

    /**
     * Given a user, query the database to find users that the
     * user follows.
     * <pre>
     *     <code>
     *         select c.destination from Connection c where c.origin = :user
     *     </code>
     * </pre>
     * @param user  the given user
     * @return      a list of users that the user follows
     * @see List
     * @see User
     */
    @SuppressWarnings("unchecked")
    public List<User> findAllFollowing(User user) {
        Query query = getSession().createQuery(
                "select c.destination from Connection c where c.origin = :user"
        ).setParameter("user", user);
        return query.list();
    }

    /**
     * Given a user, query the database to find all users that follower
     * the user and return their IDs.
     * <pre>
     *     <code>
     *         select c.origin.twitterId from Connection c where c.destination = :user
     *     </code>
     * </pre>
     * @param user the given user
     * @return  a list of twitter ids ({@link Long})
     * @see List
     * @see User
     */
    @SuppressWarnings("unchecked")
    public List<Long> findAllFollowersIDs(User user) {
        Query query = getSession().createQuery(
                "select c.origin.twitterId from Connection c where c.destination = :user"
        ).setParameter("user", user);
        return query.list();
    }

    /**
     * Given a {@link Config}, find all {@link Connection}'s that
     * are part of a given {@link Config}.
     * <pre>
     *     <code>
     *         select * from Connection where config = :c
     *     </code>
     * </pre>
     * @param c the given configuration
     * @return  a list of connections corresponding to the configuration
     * @see List
     * @see Connection
     * @see Config
     */
    @SuppressWarnings("unchecked")
    public List<Connection> getByConfig(Config c) {
        Query query = getSession().createQuery(
                "from Connection where config = :config"
        ).setParameter("config", c);
        return query.list();
    }

    /**
     * Given a {@link User}, query the database to find all users
     * that the user followers and return their twitter ids.
     * <pre>
     *     <code>
     *         selection c.destination.twitterId from Connection c where c.origin = :user
     *     </code>
     * </pre>
     * @param user  the given user
     * @return      a list of {@link Long}'s for twitter ids.
     * @see List
     * @see Long
     * @see User
     */
    @SuppressWarnings("unchecked")
    public List<Long> findAllFollowingIDs(User user) {
        Query query = getSession().createQuery(
                "select c.destination.twitterId from Connection c where c.origin = :user"
        ).setParameter("user", user);
        return query.list();
    }

    /**
     * Updates connections that are stored in the database.
     * @param updated   the updated connections
     * @return  a list of the connections
     * @see Connection
     */
    public List<Connection> updateConnections(List<Connection> updated) {
        return (List<Connection>) this.save(updated);
    }

    /**
     * Update a single connection in the database.
     * @param update    the new connection
     * @return          the saved connection.
     * @see Connection
     */
    public Connection updateConnection(Connection update) {
        return this.save(update);
    }
}
