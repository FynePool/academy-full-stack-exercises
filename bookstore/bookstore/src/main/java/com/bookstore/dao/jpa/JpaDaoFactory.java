package com.bookstore.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import com.bookstore.dao.*;

public class JpaDaoFactory implements DaoFactory{
    private final EntityManager em;

    public JpaDaoFactory() {
    this.em = Persistence.createEntityManagerFactory("JPAExample")
                .createEntityManager();
    }

    @Override
    public BookDao getBookDao() {
        return new JpaBookDao(em);
    }

    @Override
    public AuthorDao getAuthorDao() {
        return new JpaAuthorDao(em);
    }

    @Override
    public PublisherDao getPublisherDao() {
        return new JpaPublisherDao(em);
    }

    @Override
    public void close() throws Exception {
        em.close();
    }
    
}