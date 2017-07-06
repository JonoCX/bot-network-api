package uk.ac.ncl.botnetwork.repositories;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import uk.ac.ncl.botnetwork.AbstractHibernateRepository;
import uk.ac.ncl.botnetwork.domain.Config;

import java.util.List;

/**
 * Repository for perform actions on the user entities stored in
 * the database.
 *
 * @author Callum McClean
 */
@Repository
public class ConfigRepository extends AbstractHibernateRepository<Config, Long>
{
    public Config findById(Long id) {
        Query query = getSession().createQuery(
                "from Config order where configId = :id"
        ).setParameter("id", id);
        return (Config) query.list().get(0);
    }

    public List<Config> findNotComplete() {
        Query query = getSession().createQuery(
                "from Config where complete = false"
        );
        return query.list();
    }



}