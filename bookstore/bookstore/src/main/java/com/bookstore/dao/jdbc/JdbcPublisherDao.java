package com.bookstore.dao.jdbc;

import java.sql.*;
import java.util.*;
import com.bookstore.dao.*;
import com.bookstore.*;

public class JdbcPublisherDao implements PublisherDao {

    private Connection conn;

    private final String FIND_BY_ID = "SELECT * FROM publisher WHERE id = ?";
    private final String FIND_ALL = "SELECT * FROM publisher";
    private final String SAVE_PUBLISHER = "INSERT INTO publisher(name) VALUES(?)";;
    private final String UPDATE_PUBLISHER = "UPDATE author SET name = ? WHERE id = ?";

    public JdbcPublisherDao(Connection conn) {
        this.conn = conn;
    }

    private void setIdFindPublisher(PreparedStatement idStatement, long id) throws SQLException {
        idStatement.setLong(1, id);
    }

    @Override
    public List<Publisher> findAll() {
        List<Publisher> publishers = new ArrayList<>();

        try (PreparedStatement findAllStatement = conn.prepareStatement(FIND_ALL);
                ResultSet rs = findAllStatement.executeQuery()) {
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                publishers.add(new Publisher(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return publishers;
    }

    @Override
    public Optional<Publisher> findById(long id) {
        Optional<Publisher> publisher = Optional.empty();

        try (PreparedStatement idStatement = conn.prepareStatement(FIND_BY_ID)) {
            setIdFindPublisher(idStatement, id);
            try (ResultSet rs = idStatement.executeQuery()) {
                while (rs.next()) {
                    long _id = rs.getLong("id");
                    String name = rs.getString("name");
                    publisher = Optional.of(new Publisher(_id, name));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return publisher;
    }

    @Override
    public Publisher saveOrUpdate(Publisher publisher) {
        Optional<Publisher> publisherOpt = findById(publisher.getId());
        try (PreparedStatement updateStatement = conn.prepareStatement(UPDATE_PUBLISHER);
                PreparedStatement saveStatement = conn.prepareStatement(SAVE_PUBLISHER);) {
            conn.setAutoCommit(false);
            if (publisherOpt.isEmpty()) {
                saveStatement.setString(1, publisher.getName());
                saveStatement.executeUpdate();
            } else {
                updateStatement.setString(1, publisher.getName());
                updateStatement.setLong(2, publisherOpt.get().getId());
                updateStatement.executeUpdate();
            }
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publisher;
    }

}
