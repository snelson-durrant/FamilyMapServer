package service;

import dao.*;
import model.*;
import request.LoadRequest;
import response.TableModResponse;

/**
 * Clears the database and loads in new data
 */
public class LoadService {

    /**
     * Clear the database and load in new data
     * @param loadRequest data to load
     * @return the associated response object
     */
    public TableModResponse load(LoadRequest loadRequest) {

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

            int userCount = 0;
            int eventCount = 0;
            int personCount = 0;
            for (User user : loadRequest.getUsers()) {
                userDAO.insert(user);
                userCount++;
            }
            for (Event event : loadRequest.getEvents()) {
                eventDAO.insert(event);
                eventCount++;
            }
            for (Person person : loadRequest.getPersons()) {
                personDAO.insert(person);
                personCount++;
            }

            db.closeConnection(true);

            TableModResponse response = new TableModResponse();
            response.setMessage("Successfully added " + userCount + " users, " + personCount +
                    " persons, and " + eventCount + " events to the database.");
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
