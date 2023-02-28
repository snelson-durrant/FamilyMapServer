package dao;

import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

//We will use this to test that our insert method is working and failing in the right ways
public class PersonDAOTest {
    private Database db;
    private Person bestPerson;
    private PersonDAO eDao;

    @BeforeEach
    public void setUp() throws DataAccessException {

        db = new Database();
        bestPerson = new Person("5fh6&k_ui", "john.smith123", "John",
                "Smith", "m", "h7ydk1980", "fr$th78_g", "_jdf789yt");

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

}
