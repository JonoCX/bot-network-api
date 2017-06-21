package uk.ac.ncl.botnetwork.repositories;

import org.springframework.stereotype.Repository;
import uk.ac.ncl.botnetwork.AbstractHibernateRepository;
import uk.ac.ncl.botnetwork.domain.Tweet;

/**
 *
 * Repository for the Tweet database entity.
 *
 * @author Jonathan Carlton
 */
@Repository
public class TweetRepository extends AbstractHibernateRepository<Tweet, String>
{
    /*
        No implementation, yet. Probably don't need anything specific.
     */
}
