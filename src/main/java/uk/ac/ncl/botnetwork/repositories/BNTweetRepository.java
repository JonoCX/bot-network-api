package uk.ac.ncl.botnetwork.repositories;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import uk.ac.ncl.botnetwork.AbstractHibernateRepository;
import uk.ac.ncl.botnetwork.domain.GeneratedTweet;

import java.util.List;

/**
 * Repository for the GeneratedTweet database entity.
 *
 * @author Jonathan Carlton
 */
@Repository
public class BNTweetRepository extends AbstractHibernateRepository<GeneratedTweet, String>
{
    public GeneratedTweet getRandomTweet() {
        Query query = getSession().createQuery(
                "from GeneratedTweet order by RANDOM() limit 1"
        );
        return (GeneratedTweet) query.list().get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<GeneratedTweet> findAll() {
        Query query = getSession().createQuery(
                "from GeneratedTweet");
        return (List<GeneratedTweet>) query.list();
    }
}
