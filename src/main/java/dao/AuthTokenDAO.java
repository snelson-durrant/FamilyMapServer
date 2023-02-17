package dao;

import model.AuthToken;

import java.sql.*;

public class AuthTokenDAO {

    private final Connection conn;

    public AuthTokenDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(AuthToken authtoken) throws DataAccessException { }

    public AuthToken find(String username) throws DataAccessException {
        return null;
    }

    public void clear() throws DataAccessException { }

}
