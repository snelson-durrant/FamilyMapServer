package dao;

import model.Event;
import model.Person;
import model.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * Interfaces with the Person table in the database
 */
public class PersonDAO {

    /**
     * database connection
     */
    private final Connection conn;

    /**
     * Creates a PersonDAO object
     * @param conn the database connection
     */
    public PersonDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Inserts the data from a Person object into the database
     * @param person the Person object
     * @throws DataAccessException
     */
    public void insert(Person person) throws DataAccessException {

        String sql = "INSERT INTO Person (personID, associatedUsername, firstName, lastName, " +
                "gender, fatherID, motherID, spouseID) VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getAssociatedUsername());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFatherID());
            stmt.setString(7, person.getMotherID());
            stmt.setString(8, person.getSpouseID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting an event into the database");
        }

    }

    /**
     * Finds the database entry associated with the personID
     * @param personID the person's identification
     * @return an AuthToken object containing the found data
     * @throws DataAccessException
     */
    public Person find(String personID) throws DataAccessException {

        Person person;
        ResultSet rs;
        String sql = "SELECT * FROM Person WHERE personID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, personID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                person = new Person(rs.getString("personID"), rs.getString("associatedUsername"),
                        rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"),
                        rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID"));
                return person;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an event in the database");
        }

    }

    /**
     * Clears the Person table from the database
     * @throws DataAccessException
     */
    public void clear() throws DataAccessException {

        String sql = "DELETE FROM Person";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing a table in database");
        }

    }

    /**
     * Finds all the people associated with a certain user
     * @param username handle used to identify the user
     * @return an array of People objects
     */
    public Person[] findUserPeople(String username) throws DataAccessException {

        ArrayList<Person> ListOfPeople = new ArrayList<>();
        Person person;
        ResultSet rs;
        String sql = "SELECT * FROM Person WHERE associatedUsername = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while (rs.next()) {
                person = new Person(rs.getString("personID"), rs.getString("associatedUsername"),
                        rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"),
                        rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID"));
                ListOfPeople.add(person);
            }
            Person[] people = new Person[ListOfPeople.size()];
            people = ListOfPeople.toArray(people);
            return people;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding a person in the database");
        }

    }

    /**
     * Removes all the people associated with a certain user
     * @param username handle used to identify the user
     */
    public void clearUserPeople(String username) throws DataAccessException {

        String sql = "DELETE FROM Person WHERE associatedUsername = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding a person in the database");
        }

    }

}
