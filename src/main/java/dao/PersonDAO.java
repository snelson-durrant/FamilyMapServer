package dao;

import model.Person;

import java.sql.*;

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
    public void insert(Person person) throws DataAccessException { }

    /**
     * Finds the database entry associated with the personID
     * @param personID the person's identification
     * @return an AuthToken object containing the found data
     * @throws DataAccessException
     */
    public Person find(String personID) throws DataAccessException {
        return null;
    }

    /**
     * Clears the Person table from the database
     * @throws DataAccessException
     */
    public void clear() throws DataAccessException { }

}
