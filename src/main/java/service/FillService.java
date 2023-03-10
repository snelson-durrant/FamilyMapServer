package service;

import dao.*;
import model.Event;
import model.Person;
import model.User;
import response.TableModResponse;

import java.sql.SQLException;
import java.util.UUID;

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
            UserDAO userDAO = new UserDAO(db.getConnection());

            eventDAO.clearUserEvents(username);
            personDAO.clearUserPeople(username);

            TableModResponse response = new TableModResponse();

            // start of recursion
            User thisUser = userDAO.find(username);
            if (thisUser != null) {

                personDAO.dataGeneration(thisUser, numOfGens);

                int personCount = personDAO.findUserPeople(username).length;
                int eventCount = eventDAO.findUserEvents(username).length;

                response = new TableModResponse();
                response.setMessage("Successfully added " + personCount +
                        " persons and " + eventCount + " events to the database.");
                response.setSuccess(true);

            } else {

                response = new TableModResponse();
                response.setMessage("Error: Invalid username.");
                response.setSuccess(true);
            }

            db.closeConnection(true);
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
