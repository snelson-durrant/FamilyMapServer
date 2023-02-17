package dao;

import model.Person;

import java.sql.*;

public class PersonDAO {

    private final Connection conn;

    public PersonDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(Person person) throws DataAccessException { }

    public Person find(String personID) throws DataAccessException {
        return null;
    }

    public void clear() throws DataAccessException { }

}
