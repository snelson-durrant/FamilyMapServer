package dao;

import model.User;

import java.sql.*;

/**
 * Interfaces with the User table in the database
 */
public class UserDAO {

    /**
     * database connection
     */
    private final Connection conn;

    /**
     * Creates a UserDAO object
     * @param conn the database connection
     */
    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Inserts the data from a User object into the database
     * @param user the User object
     * @throws DataAccessException
     */
    public void insert(User user) throws DataAccessException { }

    /**
     * Finds the database entry associated with the username
     * @param username the user's identification
     * @return a User object containing the found data
     * @throws DataAccessException
     */
    public User find(String username) throws DataAccessException {
        return null;
    }

    /**
     * Clears the User table from the database
     * @throws DataAccessException
     */
    public void clear() throws DataAccessException { }

}
