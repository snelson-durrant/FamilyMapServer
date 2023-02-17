package dao;

import model.User;

import java.sql.*;

public class UserDAO {

    private final Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(User user) throws DataAccessException { }

    public User find(String username) throws DataAccessException {
        return null;
    }

    public void clear() throws DataAccessException { }

}
