package uk.ac.ncl.botnetwork.repositories;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import uk.ac.ncl.botnetwork.AbstractHibernateRepository;
import uk.ac.ncl.botnetwork.domain.Config;
import uk.ac.ncl.botnetwork.domain.User;

import java.util.List;

/**
 * Repository for perform actions on the user entities stored in
 * the database.
 *
 * Requires the bot_network schema to be created.
 *
 * @author Jonathan Carlton
 * @see User
 */
@Repository
public class UserRepository extends AbstractHibernateRepository<User, Long>
{
    /**
     * Given a screen name, query the database and return the
     * entity that matches the scree name (a unique component
     * of a user).
     * <pre>
     *     <code>
     *         select * from User where screenname = :screenName
     *     </code>
     * </pre>
     * @param screenName    the screen name of the user
     * @return              the matching user object or null
     * if no match is found.
     * @see User
     */
    public User findByScreenName(String screenName) {
        Query query = getSession().createQuery(
                "from User order where screenName = :screenName"
        ).setParameter("screenName", screenName);
        return (User) query.list().get(0);
    }

    /**
     * Given a {@link Config} (provided by a configuration file)
     * find all of the {@link User}'s that correspond with that
     * {@link Config}.
     * <pre>
     *     <code>
     *         select * from User where config = :c
     *     </code>
     * </pre>
     * @param c the {@link Config} that you're searching for.
     * @return  List of users corresponding to the configuration.
     * @see List
     * @see User
     * @see Config
     */
    @SuppressWarnings("unchecked")
    public List<User> getByConfig(Config c) {
        Query query = getSession().createQuery(
                "from User where config = :config"
        ).setParameter("config", c);
        return query.list();
    }
}
