package uk.ac.ncl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * Sourced from Twitter-API (part of the vaza dengue project)
 * code originally written by Diego.
 *
 * @author Jonathan Carlton
 */
public class AbstractHibernateRepository<T, V extends Serializable> implements PagingAndSortingRepository<T, V>
{
    @Autowired private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    protected Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected List<T> getUniqueObjects(List<T> objects) {
        Set<T> set = new LinkedHashSet<>(objects);
        return new ArrayList<>(set);
    }

    public void flush() {
        this.getSession().flush();
    }

    public void evict(T t) {
        this.getSession().evict(t);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <S extends T> S save(S entity) {
        entity = (S) getSession().merge(entity);
        getSession().saveOrUpdate(entity);
        return entity;
    }

    @Override
    public T findOne(V v) {
        return getSession().get(getEntityClass(), v);
    }

    @Override
    public boolean exists(V v) {
        return this.findOne(v) != null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterable<T> findAll() {
        Criteria criteria = getSession().createCriteria(getEntityClass());
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return getUniqueObjects(criteria.list());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterable<T> findAll(Iterable<V> iterable) {
        List<V> idList = new ArrayList<>();
        for (V v : iterable) {
            idList.add(v);
        }

        Criteria criteria = getSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.in("id", idList));
        return getUniqueObjects(criteria.list());
    }

    @Override
    public long count() {
        return (Long) getSession()
                .createCriteria(getEntityClass())
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

    @Override
    public void delete(V v) {
        this.delete(this.findOne(v));
    }

    @Override
    public void delete(T k) {
        getSession().delete(k);
    }

    @Override
    public void delete(Iterable<? extends T> iterable) {
        for (T t : iterable) {
            this.delete(t);
        }
    }

    @Override
    public void deleteAll() {
        String name = getEntityClass().getSimpleName();
        getSession().createQuery("delete from " + name).executeUpdate();
    }

    @Override
    public <S extends T> Iterable<S> save(Iterable<S> iterable) {
        for (S s : iterable) {
            this.save(s);
        }
        return iterable;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterable<T> findAll(Sort sort) {
        CriteriaBuilder builder = new CriteriaBuilder(getSession(), getEntityClass());
        builder.addSort(sort);

        Criteria criteria = builder.getCriteria();
        return getUniqueObjects(criteria.list());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Page<T> findAll(Pageable pageable) {
        CriteriaBuilder builder = new CriteriaBuilder(getSession(), getEntityClass());
        if (pageable.getSort() != null) {
            builder.addSort(pageable.getSort());
        }

        Criteria criteria = builder.getCriteria();
        criteria.setFirstResult(pageable.getOffset());
        criteria.setMaxResults(pageable.getPageSize());

        List<T> content = getUniqueObjects(criteria.list());

        PageImpl<T> page = new PageImpl<>(content, pageable, this.count());
        return page;
    }
}
