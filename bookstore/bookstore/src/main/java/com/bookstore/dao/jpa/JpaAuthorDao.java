package com.bookstore.dao.jpa;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.bookstore.dao.AuthorDao;
import com.bookstore.Author;

public class JpaAuthorDao implements AuthorDao{
    private final EntityManager em;

    private final String FIND_ALL = "SELECT * FROM Author;";
    private final String FIND_BY_ID = "SELECT * FROM Author where id = :author_id";

    public JpaAuthorDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery(FIND_ALL, Author.class);
        return query.getResultList();
    }

    @Override
    public Optional<Author> findById(long id) {
        em.getTransaction().begin();
        TypedQuery<Author> q = em.createQuery(FIND_BY_ID, Author.class);
        q.setParameter("author", id);
        Optional<Author> result = Optional.of(q.getSingleResult());
        return (result.isPresent()) ? result : Optional.empty();
    }

    @Override
    public Author saveOrUpdate(Author author) {
        EntityTransaction transaction = em.getTransaction();
        boolean isActive = transaction.isActive();

        if (!isActive)
            transaction.begin();

        Author result = em.merge(author);
        
        if (!isActive)
            transaction.commit();

        return result;
    }
    
}