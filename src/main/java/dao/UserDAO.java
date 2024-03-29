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
     *
     * @param conn the database connection
     */
    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Inserts the data from a User object into the database
     *
     * @param user the User object
     * @throws DataAccessException
     */
    public void insert(User user) throws DataAccessException {

        String sql = "INSERT INTO User (username, password, email, firstName, lastName, " +
                "gender, personID) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setString(6, user.getGender());
            stmt.setString(7, user.getPersonID());

            stmt.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting a user into the database");
        }
    }

    /**
     * Finds the database entry associated with the username
     *
     * @param username the user's identification
     * @return a User object containing the found data
     * @throws DataAccessException
     */
    public User find(String username) throws DataAccessException {

        User user;
        ResultSet rs;
        String sql = "SELECT * FROM User WHERE username = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (rs.next()) {

                user = new User(rs.getString("username"), rs.getString("password"),
                        rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("gender"), rs.getString("personID"));
                return user;
            } else {

                return null;
            }
        } catch (SQLException e) {

            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding a user in the database");
        }

    }

    /**
     * Clears the User table from the database
     *
     * @throws DataAccessException
     */
    public void clear() throws DataAccessException {

        String sql = "DELETE FROM User";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing a table in database");
        }
    }
}
