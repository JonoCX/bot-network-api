package uk.ac.ncl.botnetwork.repositories;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import uk.ac.ncl.botnetwork.AbstractHibernateRepository;
import uk.ac.ncl.botnetwork.domain.Config;
import uk.ac.ncl.botnetwork.domain.GeneratedTweet;

import java.util.List;

/**
 * Repository for performing actions on the generated
 * tweets.
 *
 * @author Jonathan Carlton
 * @see GeneratedTweet
 */
@Repository
public class BNTweetRepository extends AbstractHibernateRepository<GeneratedTweet, String>
{
    /**
     * Fetch a random tweet from the database.
     * <pre>
     *     <code>
     *         select * from GeneratedTweet order by random()
     *     </code>
     * </pre>
     * @return  the randomly selected tweet
     * @see GeneratedTweet
     */
    public GeneratedTweet getRandomTweet() {
        Query query = getSession().createQuery(
                "from GeneratedTweet order by RANDOM()"
        );
        return (GeneratedTweet) query.list().get(0);
    }

    /**
     * Override {@link AbstractHibernateRepository#findAll()} method to
     * fix issues.
     * @return  all of the stored entities.
     * @see GeneratedTweet
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<GeneratedTweet> findAll() {
        Query query = getSession().createQuery(
                "from GeneratedTweet");
        return (List<GeneratedTweet>) query.list();
    }

    /**
     * Given a {@link Config}, find all generated tweets that
     * correspond to that object.
     * <pre>
     *     <code>
     *         select * from GeneratedTweet where config = :c
     *     </code>
     * </pre>
     * @param c the given object
     * @return  a list of {@link GeneratedTweet}'s corresponding
     * to the parameter.
     * @see List
     * @see GeneratedTweet
     * @see Config
     */
    @SuppressWarnings("unchecked")
    public List<GeneratedTweet> getByConfig(Config c) {
        Query query = getSession().createQuery(
                "from GeneratedTweet where config = :config"
        ).setParameter("config", c);
        return query.list();
    }
}
