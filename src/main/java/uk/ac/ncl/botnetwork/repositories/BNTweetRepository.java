package uk.ac.ncl.botnetwork.repositories;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import uk.ac.ncl.botnetwork.AbstractHibernateRepository;
import uk.ac.ncl.botnetwork.domain.Tweet;

/**
 * Repository for the Tweet database entity.
 *
 * @author Jonathan Carlton
 */
@Repository
public class BNTweetRepository extends AbstractHibernateRepository<Tweet, String>
{
    public Tweet getRandomTweet() {
        Query query = getSession().createQuery(
                "from Tweet order by RANDOM() limit 1"
        );
        return (Tweet) query.list().get(0);
    }
}
