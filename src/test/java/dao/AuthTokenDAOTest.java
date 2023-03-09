package dao;

import model.AuthToken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class AuthTokenDAOTest {
    private Database db;
    private AuthToken bestAuthToken;
    private AuthTokenDAO eDao;

    @BeforeEach
    public void setUp() throws DataAccessException {

        db = new Database();
        bestAuthToken = new AuthToken("5fh6&k_ui", "john.smith123");

        Connection conn = db.getConnection();
        eDao = new AuthTokenDAO(conn);
        eDao.clear();
    }

    @AfterEach
    public void tearDown() {

        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {

        eDao.insert(bestAuthToken);
        AuthToken compareTest = eDao.find(bestAuthToken.getAuthtoken());
        assertNotNull(compareTest);
        assertEquals(bestAuthToken, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {

        eDao.insert(bestAuthToken);
        assertThrows(DataAccessException.class, () -> eDao.insert(bestAuthToken));
    }

    @Test
    public void findPass() throws DataAccessException {

        eDao.insert(bestAuthToken);
        AuthToken compareTest = eDao.find(bestAuthToken.getAuthtoken());
        assertNotNull(compareTest);
        assertEquals(bestAuthToken, compareTest);
    }

    @Test
    public void findFail() throws DataAccessException {

        assertNull(eDao.find(bestAuthToken.getAuthtoken()));
    }

    @Test
    public void clearPass() throws DataAccessException {

        eDao.insert(bestAuthToken);
        assertEquals(bestAuthToken, eDao.find(bestAuthToken.getAuthtoken()));
        eDao.clear();
        assertNull(eDao.find(bestAuthToken.getAuthtoken()));
    }

}
