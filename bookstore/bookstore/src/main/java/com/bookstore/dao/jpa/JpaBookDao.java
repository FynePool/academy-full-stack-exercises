package com.bookstore.dao.jpa;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.bookstore.dao.BookDao;
import com.bookstore.Author;
import com.bookstore.Book;

public class JpaBookDao implements BookDao{
    private final EntityManager em;

    private final String FIND_ALL = "SELECT * FROM Book;";
    private final String FIND_BY_ID = "SELECT * FROM Book WHERE id = :book_id;";
    private final String FIND_BY_TITLE = "SELECT * FROM Book WHERE title = :book_title;";
    private final String FIND_BY_AUTHOR = "SELECT * FROM Book WHERE author_id = :book_auhtor;";

    public JpaBookDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Book> findAll() {
        em.getTransaction().begin();
        TypedQuery<Book> q = em.createQuery(FIND_ALL,Book.class);
        List<Book> results = q.getResultList();
        em.getTransaction().commit();
        em.close();
        return results;
    }

    @Override
    public Optional<Book> findById(long id) {
        Optional<Book> result = Optional.empty();
        em.getTransaction().begin();
        TypedQuery<Book> q = em.createQuery(FIND_BY_ID, Book.class);
        q.setParameter("book_id", id);
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
    public List<Book> findByTitle(String title) {
        em.getTransaction().begin();
        TypedQuery<Book> q = em.createQuery(FIND_BY_TITLE,Book.class);
        q.setParameter("book_title", title);
        List<Book> results = q.getResultList();
        for (Book res : results) {
            System.out.println(res);
        }
        em.getTransaction().commit();
        em.close();
        return results;
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        em.getTransaction().begin();
        TypedQuery<Book> q = em.createQuery(FIND_BY_AUTHOR,Book.class);
        q.setParameter("book_author", author.getId());
        List<Book> results = q.getResultList();
        for (Book res : results) {
            System.out.println(res);
        }
        em.getTransaction().commit();
        em.close();
        return results;
    }

    @Override
    public Book saveOrUpdate(Book book) {
        EntityTransaction transaction = em.getTransaction();
        boolean isActive = transaction.isActive();
        
        if (!isActive)
            transaction.begin();

        Book result = em.merge(book);

        if (!isActive)
            transaction.commit();

        return result;
    }

    
    
}