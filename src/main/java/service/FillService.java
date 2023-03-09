package service;

import dao.*;
import model.Event;
import model.Person;
import model.User;
import response.TableModResponse;

/**
 * Populates the server's database with generated data
 */
public class FillService {

    /**
     * Populate the database with generated data
     * @return the associated response object
     * @param username handle used to identify the user
     * @param numOfGens the number of generations to generate
     */
    public TableModResponse fill(String username, int numOfGens) {

        Database db = new Database();

        try {
            db.openConnection();
            EventDAO eventDAO = new EventDAO(db.getConnection());
            PersonDAO personDAO = new PersonDAO(db.getConnection());

            eventDAO.clearUserEvents(username);
            personDAO.clearUserPeople(username);

            int eventCount = 0;
            int personCount = 0;

            // TODO: ADD RECURSION HERE

            db.closeConnection(true);

            TableModResponse response = new TableModResponse();
            response.setMessage("Successfully added " + personCount +
                    " persons and " + eventCount + " events to the database.");
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
