package dao;

import model.AuthToken;

import java.sql.*;

/**
 * Interfaces with the AuthToken table in the database
 */
public class AuthTokenDAO {

    /**
     * database connection
     */
    private final Connection conn;

    /**
     * Creates an AuthTokenDAO object
     * @param conn the database connection
     */
    public AuthTokenDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Inserts the data from an AuthToken object into the database
     * @param authtoken the AuthToken object
     * @throws DataAccessException
     */
    public void insert(AuthToken authtoken) throws DataAccessException { }

    /**
     * Finds the database entry associated with the username
     * @param authtoken unique string used for authentication
     * @return an AuthToken object containing the found data
     * @throws DataAccessException
     */
    public AuthToken find(String authtoken) throws DataAccessException {
        return null;
    }

    /**
     * Clears the AuthToken table from the database
     * @throws DataAccessException
     */
    public void clear() throws DataAccessException { }

}
