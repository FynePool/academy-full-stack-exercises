package com.bookstore.dao.jdbc;

import java.sql.*;
import java.util.*;
import com.bookstore.*;
import com.bookstore.dao.*;

public class JdbcAuthorDao implements AuthorDao {

    private Connection conn;
    
    private final String FIND_BY_ID = "SELECT * FROM author WHERE id = ?";
    private final String FIND_ALL = "SELECT * FROM author";
    private final String SAVE_AUTHOR = "INSERT INTO author(first_name, last_name) VALUES(?, ?)";;
    private final String UPDATE_AUTHOR = "UPDATE author SET first_name = ?, last_name = ? WHERE id = ?";

    public JdbcAuthorDao(Connection conn) {
        this.conn = conn;
    }

    private void setIdFindAuthor(PreparedStatement idStatement, long id) throws SQLException {
        idStatement.setLong(1, id);
    }

    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();

        try (PreparedStatement findAllStatement = conn.prepareStatement(FIND_ALL);
                ResultSet rs = findAllStatement.executeQuery()) {
            while (rs.next()) {
                long id = rs.getLong("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                authors.add(new Author(id, firstName, lastName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public Optional<Author> findById(long id) {
        Optional<Author> author = Optional.empty();

        try (PreparedStatement idStatement = conn.prepareStatement(FIND_BY_ID);) {
            setIdFindAuthor(idStatement, id);
            try (ResultSet rs = idStatement.executeQuery()) {
                while (rs.next()) {
                    long _id = rs.getLong("id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    author = Optional.of(new Author(_id, firstName, lastName));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        return author;
    }

    @Override
    public Author saveOrUpdate(Author author) {
        Optional<Author> authOpt = findById(author.getId());
        try (PreparedStatement saveStatement = conn.prepareStatement(SAVE_AUTHOR);
                PreparedStatement updateStatement = conn.prepareStatement(UPDATE_AUTHOR);) {
            conn.setAutoCommit(false);
            if (authOpt.isEmpty()) {
                saveStatement.setString(1, author.getFirstName());
                saveStatement.setString(2, author.getLastName());
                saveStatement.executeUpdate();
            } else {
                updateStatement.setString(1, author.getFirstName());
                updateStatement.setString(2, author.getLastName());
                updateStatement.setLong(3, author.getId());
                updateStatement.executeUpdate();
            }
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

}