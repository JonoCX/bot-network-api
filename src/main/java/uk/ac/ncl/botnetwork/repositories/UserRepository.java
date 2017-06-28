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
 * @author Jonathan Carlton
 */
@Repository
public class UserRepository extends AbstractHibernateRepository<User, Long>
{
    public User findByScreenName(String screenName) {
        Query query = getSession().createQuery(
                "from User order where screenName = :screenName"
        ).setParameter("screenName", screenName);
        return (User) query.list().get(0);
    }

    @SuppressWarnings("unchecked")
    public List<User> getByConfig(Config c) {
        Query query = getSession().createQuery(
                "from User where config = :config"
        ).setParameter("config", c);
        return query.list();
    }
}
