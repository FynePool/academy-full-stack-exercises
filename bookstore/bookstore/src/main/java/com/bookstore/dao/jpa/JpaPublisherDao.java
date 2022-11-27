package com.bookstore.dao.jpa;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import com.bookstore.dao.PublisherDao;
import com.bookstore.Publisher;

public class JpaPublisherDao implements PublisherDao {
    private final EntityManager em;
    
    private final String FIND_ALL = "SELECT * FROM publisher;";
    private final String FIND_BY_ID = "SELECT * FROM publisher WHERE id = :publisher_id";

    public JpaPublisherDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Publisher> findAll() {
        em.getTransaction().begin();
        TypedQuery<Publisher> q = em.createQuery(FIND_ALL,Publisher.class);
        List<Publisher> results = q.getResultList();
        em.getTransaction().commit();
        em.close();
        return results;
    }

    @Override
    public Optional<Publisher> findById(long id) {
        Optional<Publisher> result = Optional.empty();
        em.getTransaction().begin();
        TypedQuery<Publisher> q = em.createQuery(FIND_BY_ID, Publisher.class);
        q.setParameter("publisher_id", id);
        try {
            result = Optional.of(q.getSingleResult());
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            em.getTransaction().commit();
            em.close();
        }
        return result;
    }

    @Override
    public Publisher saveOrUpdate(Publisher publisher) {
        EntityTransaction transaction = em.getTransaction();
        boolean isActive = transaction.isActive();

        if (!isActive)
            transaction.begin();

        Publisher result = em.merge(publisher);

        if (!isActive)
            transaction.commit();

        return result;
    }
    
}