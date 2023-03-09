package dao;

import model.Event;
import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDAOTest {
    private Database db;
    private Person bestPerson;
    private Person secondPerson;
    private Person[] personArray;
    private PersonDAO eDao;

    @BeforeEach
    public void setUp() throws DataAccessException {

        db = new Database();
        bestPerson = new Person("5fh6&k_ui", "john.smith123", "John",
                "Smith", "m", "h7ydk1980", "fr$th78_g", "_jdf789yt");
        secondPerson = new Person("7h5_&fjk890", "john.smith123", "Nelson",
                "Durrant", "m", "asdk78sf", "sfdkj7810", "h_kfdsjfk");
        personArray = new Person[2];
        personArray[0] = bestPerson;
        personArray[1] = secondPerson;

        Connection conn = db.getConnection();
        eDao = new PersonDAO(conn);
        eDao.clear();
    }

    @AfterEach
    public void tearDown() {

        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {

        eDao.insert(bestPerson);
        Person compareTest = eDao.find(bestPerson.getPersonID());
        assertNotNull(compareTest);
        assertEquals(bestPerson, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {

        eDao.insert(bestPerson);
        assertThrows(DataAccessException.class, () -> eDao.insert(bestPerson));
    }

    @Test
    public void findPass() throws DataAccessException {

        eDao.insert(bestPerson);
        Person compareTest = eDao.find(bestPerson.getPersonID());
        assertNotNull(compareTest);
        assertEquals(bestPerson, compareTest);
    }

    @Test
    public void findFail() throws DataAccessException {

        assertNull(eDao.find(bestPerson.getPersonID()));
    }

    @Test
    public void clearPass() throws DataAccessException {

        eDao.insert(bestPerson);
        assertEquals(bestPerson, eDao.find(bestPerson.getPersonID()));
        eDao.clear();
        assertNull(eDao.find(bestPerson.getPersonID()));
    }

    @Test
    public void findUserPeoplePass() throws DataAccessException {

        eDao.insert(bestPerson);
        eDao.insert(secondPerson);
        Person[] compareTest = eDao.findUserPeople(bestPerson.getAssociatedUsername());
        assertNotNull(compareTest);
        assertEquals(personArray.length, compareTest.length);
    }

    @Test
    public void findUserPeopleFail() throws DataAccessException {

        eDao.insert(bestPerson);
        eDao.insert(secondPerson);
        Person[] compareTest = eDao.findUserPeople("unassociatedUsername");
        assertTrue(compareTest.length == 0);
    }

    @Test
    public void clearUserPeoplePass() throws DataAccessException {

        eDao.insert(bestPerson);
        eDao.insert(secondPerson);
        eDao.clearUserPeople(bestPerson.getAssociatedUsername());
        Person[] compareTest = eDao.findUserPeople(bestPerson.getAssociatedUsername());
        assertTrue(compareTest.length == 0);
    }

    @Test
    public void clearUserPeopleFail() throws DataAccessException {

        eDao.insert(bestPerson);
        eDao.insert(secondPerson);
        eDao.clearUserPeople("unassociatedUsername");
        Person[] compareTest = eDao.findUserPeople(bestPerson.getAssociatedUsername());
        assertEquals(personArray.length, compareTest.length);
    }

}
