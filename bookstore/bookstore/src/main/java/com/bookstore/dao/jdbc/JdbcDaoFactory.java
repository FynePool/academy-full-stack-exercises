package com.bookstore.dao.jdbc;

import java.sql.*;
import com.bookstore.dao.*;

public class JdbcDaoFactory implements DaoFactory{


    private Connection conn;

    public JdbcDaoFactory(String URL, String USER, String PASSWORD) {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AuthorDao getAuthorDao()  {
        return new JdbcAuthorDao(conn);
    }

    @Override
    public BookDao getBookDao()  {
        try {
            return new JdbcBookDao(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PublisherDao getPublisherDao(){
        return new JdbcPublisherDao(conn);
    }

    @Override
    public void close() throws Exception {
        conn.close();        
    }

}