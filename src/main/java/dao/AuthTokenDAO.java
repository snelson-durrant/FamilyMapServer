package dao;

import model.AuthToken;
import model.Person;

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
    public void insert(AuthToken authtoken) throws DataAccessException {

        String sql = "INSERT INTO Authtoken (authtoken, username) VALUES(?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authtoken.getAuthtoken());
            stmt.setString(2, authtoken.getUsername());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting an authtoken into the database");
        }

    }

    /**
     * Finds the database entry associated with the username
     * @param authtoken unique string used for authentication
     * @return an AuthToken object containing the found data
     * @throws DataAccessException
     */
    public AuthToken find(String authtoken) throws DataAccessException {

        AuthToken authToken;
        ResultSet rs;
        String sql = "SELECT * FROM Authtoken WHERE authtoken = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authtoken);
            rs = stmt.executeQuery();
            if (rs.next()) {
                authToken = new AuthToken(rs.getString("authtoken"), rs.getString("username"));
                return authToken;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an authtoken in the database");
        }

    }

    /**
     * Clears the AuthToken table from the database
     * @throws DataAccessException
     */
    public void clear() throws DataAccessException {

        String sql = "DELETE FROM Authtoken";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the authtoken table");
        }

    }

}
