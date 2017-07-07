package uk.ac.ncl.botnetwork;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Sort;

/**
 * A builder for the creation of criterion that can be applied
 * to the queries being executed on the database.
 *
 * @author Jonathan Carlton
 */
public class CriteriaBuilder 
{
    private Criteria criteria;

    public CriteriaBuilder(Session session, Class<?> c) {
        this.criteria = session.createCriteria(c);
    }

    /**
     * Add a {@link Criterion} to the internal {@link Criteria}.
     * @param criterion the criterion to be added.
     */
    public void addCriterion(Criterion criterion) {
        this.criteria.add(criterion);
    }

    /**
     * Specifically add {@link Sort} to the {@link Criteria} to determine
     * the order of query results.
     * @param sort the sort that is going to be added to the criteria
     */
    public void addSort(Sort sort) {
        for (Sort.Order order : sort) {
            if (order.isAscending()) {
                criteria.addOrder(Order.asc(order.getProperty()));
            } else {
                criteria.addOrder(Order.desc(order.getProperty()));
            }
        }
    }

    /**
     * Get the internal {@link Criteria}
     * @return  the criteria
     */
    public Criteria getCriteria() {
        return criteria;
    }
}
