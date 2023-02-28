package dao;

import model.Event;
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
    public void insert(User user) throws DataAccessException {

        String sql = "INSERT INTO Users (Username, Password, Email, FirstName, LastName, " +
                "Gender, PersonID) VALUES(?,?,?,?,?,?,?)";
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
            throw new DataAccessException("Error encountered while inserting an event into the database");
        }

    }

    /**
     * Finds the database entry associated with the username
     * @param username the user's identification
     * @return a User object containing the found data
     * @throws DataAccessException
     */
    public User find(String username) throws DataAccessException {

        User user;
        ResultSet rs;
        String sql = "SELECT * FROM Users WHERE Username = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("Username"), rs.getString("Password"),
                        rs.getString("Email"), rs.getString("FirstName"), rs.getString("LastName"),
                        rs.getString("Gender"), rs.getString("PersonID"));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an event in the database");
        }

    }

    /**
     * Clears the User table from the database
     * @throws DataAccessException
     */
    public void clear() throws DataAccessException {

        String sql = "DELETE FROM Users";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing a table in database");
        }

    }

}
