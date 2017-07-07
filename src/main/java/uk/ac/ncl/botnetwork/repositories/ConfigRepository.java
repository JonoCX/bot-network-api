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
 * @see Config
 */
@Repository
public class ConfigRepository extends AbstractHibernateRepository<Config, Long>
{
    /**
     * Find a {@link Config} with a given id.
     * @param id    the id to search for
     * @return      the corresponding config object
     * @see Config
     */
    public Config findByConfigId(Long id) {
        Query query = getSession().createQuery(
                "from Config order where configId = :id"
        ).setParameter("id", id);
        return (Config) query.list().get(0);
    }

    /**
     * Find all of the {@link Config}'s that are yet to be ran.
     * @return list of all non-ran configurations
     * @see Config
     * @see List
     */
    @SuppressWarnings("unchecked")
    public List<Config> findNotComplete() {
        Query query = getSession().createQuery(
                "from Config where complete = false"
        );
        return query.list();
    }



}