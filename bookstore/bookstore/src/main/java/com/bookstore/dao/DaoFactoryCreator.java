package com.bookstore.dao;

import com.bookstore.dao.jdbc.JdbcDaoFactory;
import com.bookstore.dao.jpa.JpaDaoFactory;

public class DaoFactoryCreator {

    private static final String URL = "jdbc:mysql://localhost/bookstore";
    private static final String USER = "root";
    private static final String PASSWORD = "citterio";
    private static final DaoFactory instance, instanceJDBC;

    static{
        instanceJDBC = new JdbcDaoFactory(URL, USER, PASSWORD);
        instance = new JpaDaoFactory();
    }

    public static DaoFactory getDaoFactory(){
        return instance;
    }

    public static DaoFactory getJdbcDaoFactory(){
        return instanceJDBC;
    }
}
