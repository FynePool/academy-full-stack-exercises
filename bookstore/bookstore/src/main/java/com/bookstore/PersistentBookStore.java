package com.bookstore;

import java.util.List;
import com.bookstore.dao.*;

public class PersistentBookStore {
    public static void main(String[] args) {
        try ( DaoFactory daoFactory = DaoFactoryCreator.getDaoFactory(); ){

            PublisherDao publisherDao = daoFactory.getPublisherDao();
            List<Publisher> publishers = publisherDao.findAll();
            for (Publisher publisher : publishers) {
                System.out.println(publisher);
            }

            AuthorDao authorDao = daoFactory.getAuthorDao();
            List<Author> authors = authorDao.findAll();
            for (Author author : authors) {
                System.out.println(author);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
