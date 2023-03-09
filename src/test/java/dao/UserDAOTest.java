package dao;

import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {
    private Database db;
    private User bestUser;
    private UserDAO eDao;

    @BeforeEach
    public void setUp() throws DataAccessException {

        db = new Database();
        bestUser = new User("john.smith123", "p4ssw0rd", "js@gmail.com",
                "John", "Smith", "m", "5fh6&k_ui");

        Connection conn = db.getConnection();
        eDao = new UserDAO(conn);
        eDao.clear();
    }

    @AfterEach
    public void tearDown() {

        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {

        eDao.insert(bestUser);
        User compareTest = eDao.find(bestUser.getUsername());
        assertNotNull(compareTest);
        assertEquals(bestUser, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {

        eDao.insert(bestUser);
        assertThrows(DataAccessException.class, () -> eDao.insert(bestUser));
    }

    @Test
    public void findPass() throws DataAccessException {

        eDao.insert(bestUser);
        User compareTest = eDao.find(bestUser.getUsername());
        assertNotNull(compareTest);
        assertEquals(bestUser, compareTest);
    }

    @Test
    public void findFail() throws DataAccessException {

        assertNull(eDao.find(bestUser.getUsername()));
    }

    @Test
    public void clearPass() throws DataAccessException {

        eDao.insert(bestUser);
        assertEquals(bestUser, eDao.find(bestUser.getUsername()));
        eDao.clear();
        assertNull(eDao.find(bestUser.getUsername()));
    }

}
