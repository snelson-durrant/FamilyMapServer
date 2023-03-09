package service;

import response.TableModResponse;
import dao.*;

import javax.xml.crypto.Data;

/**
 * Deletes all data from the database
 */
public class ClearService {

    /**
     * Delete all data from the database
     * @return the associated response object
     */
    public TableModResponse clear() {

        Database db = new Database();

        try {
            db.openConnection();
            AuthTokenDAO authDAO = new AuthTokenDAO(db.getConnection());
            EventDAO eventDAO = new EventDAO(db.getConnection());
            PersonDAO personDAO = new PersonDAO(db.getConnection());
            UserDAO userDAO = new UserDAO(db.getConnection());

            authDAO.clear();
            eventDAO.clear();
            personDAO.clear();
            userDAO.clear();

            db.closeConnection(true);

            TableModResponse response = new TableModResponse();
            response.setMessage("Clear succeeded.");
            response.setSuccess(true);
            return response;
        }
        catch (DataAccessException e) {

            db.closeConnection(false);

            TableModResponse response = new TableModResponse();
            response.setMessage(e.getMessage());
            response.setSuccess(false);
            return response;
        }

    }

}
