package uk.ac.ncl.botnetwork.repositories;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import uk.ac.ncl.botnetwork.AbstractHibernateRepository;
import uk.ac.ncl.botnetwork.domain.User;

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
}
