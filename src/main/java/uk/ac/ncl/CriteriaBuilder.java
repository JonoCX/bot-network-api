package uk.ac.ncl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Sort;

/**
 * Sourced from Twitter-API (part of the vaza dengue project)
 * code originally written by Diego.
 *
 * @author Jonathan Carlton
 */
public class CriteriaBuilder 
{
    private Criteria criteria;

    public CriteriaBuilder(Session session, Class<?> c) {
        this.criteria = session.createCriteria(c);
    }

    public void addCriterion(Criterion criterion) {
        this.criteria.add(criterion);
    }

    public void addSort(Sort sort) {
        for (Sort.Order order : sort) {
            if (order.isAscending()) {
                criteria.addOrder(Order.asc(order.getProperty()));
            } else {
                criteria.addOrder(Order.desc(order.getProperty()));
            }
        }
    }

    public Criteria getCriteria() {
        return criteria;
    }
}
