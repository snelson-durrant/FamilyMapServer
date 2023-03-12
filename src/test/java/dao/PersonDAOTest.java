package dao;

import model.Person;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDAOTest {
    private Database db;
    private Person bestPerson;
    private Person secondPerson;
    private User bestUser;
    private Person[] personArray;
    private PersonDAO eDao;
    private UserDAO userDao;
    private EventDAO eventDao;

    @BeforeEach
    public void setUp() throws DataAccessException {

        db = new Database();
        bestPerson = new Person("5fh6&k_ui", "john.smith123", "John",
                "Smith", "m", "h7ydk1980", "fr$th78_g", "_jdf789yt");
        secondPerson = new Person("7h5_&fjk890", "john.smith123", "Nelson",
                "Durrant", "m", "asdk78sf", "sfdkj7810", "h_kfdsjfk");
        bestUser = new User("mockuser", "123456", "email@email.com", "John",
                "Smith", "m", "654321");
        personArray = new Person[2];
        personArray[0] = bestPerson;
        personArray[1] = secondPerson;

        Connection conn = db.getConnection();
        eDao = new PersonDAO(conn);
        eDao.clear();
        userDao = new UserDAO(conn);
        userDao.clear();
        eventDao = new EventDAO(conn);
        eventDao.clear();
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
        assertEquals(0, compareTest.length);
    }

    @Test
    public void clearUserPeoplePass() throws DataAccessException {

        eDao.insert(bestPerson);
        eDao.insert(secondPerson);
        eDao.clearUserPeople(bestPerson.getAssociatedUsername());
        Person[] compareTest = eDao.findUserPeople(bestPerson.getAssociatedUsername());
        assertEquals(0, compareTest.length);
    }

    @Test
    public void clearUserPeopleFail() throws DataAccessException {

        eDao.insert(bestPerson);
        eDao.insert(secondPerson);
        eDao.clearUserPeople("unassociatedUsername");
        Person[] compareTest = eDao.findUserPeople(bestPerson.getAssociatedUsername());
        assertEquals(personArray.length, compareTest.length);
    }

    @Test
    public void dataGenerationPass() throws DataAccessException {

        userDao.insert(bestUser);
        eDao.dataGeneration(bestUser, 4);
        assertEquals(31, eDao.findUserPeople(bestUser.getUsername()).length);
        assertEquals(91, eventDao.findUserEvents(bestUser.getUsername()).length);
    }

    @Test
    public void dataGenerationFail() throws DataAccessException {

        assertThrows(DataAccessException.class, () -> eDao.dataGeneration(bestUser, 4));
        userDao.insert(bestUser);
        assertThrows(DataAccessException.class, () -> eDao.dataGeneration(bestUser, -1));
        assertEquals(0, eDao.findUserPeople(bestUser.getUsername()).length);
        assertEquals(0, eventDao.findUserEvents(bestUser.getUsername()).length);
    }

}
