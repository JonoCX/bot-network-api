package uk.ac.ncl.botnetwork.repositories;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import uk.ac.ncl.botnetwork.AbstractHibernateRepository;
import uk.ac.ncl.botnetwork.domain.Config;

/**
 * Repository for perform actions on the user entities stored in
 * the database.
 *
 * @author Jonathan Carlton
 */
@Repository
public class ConfigRepository extends AbstractHibernateRepository<Config, Long>
{
    public Config findById(int id) {
        Query query = getSession().createQuery(
                "select from Config order where configId = :id"
        ).setParameter("id", id);
        return (Config) query.list().get(0);
    }

    public Long getMaxId() {
        Query query = getSession().createQuery(
                "FROM Config ORDER BY configId DESC LIMIT 1");

            return (Long) query.list().get(0);
    }
}