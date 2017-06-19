package uk.ac.ncl.repositories;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import uk.ac.ncl.AbstractHibernateRepository;
import uk.ac.ncl.domain.User;

import java.util.List;

/**
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
