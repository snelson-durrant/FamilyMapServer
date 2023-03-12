package service;

import dao.*;
import model.AuthToken;
import model.Event;
import model.User;
import response.EventResponse;

/**
 * Returns all events for all family members of the user
 */
public class EventService {

    /**
     * Return all events for all family members of the user
     *
     * @param authtoken string given for authentication
     * @return the associated response object
     */
    public EventResponse event(String authtoken) {

        Database db = new Database();
        EventResponse response = new EventResponse();

        try {

            db.openConnection();
            AuthTokenDAO authDAO = new AuthTokenDAO(db.getConnection());
            UserDAO userDAO = new UserDAO(db.getConnection());
            EventDAO eventDAO = new EventDAO(db.getConnection());

            AuthToken providedAuthtoken = authDAO.find(authtoken);
            if (providedAuthtoken != null) {

                User user = userDAO.find(providedAuthtoken.getUsername());
                if (user != null) {

                    Event[] foundEvents = eventDAO.findUserEvents(user.getUsername());
                    response.setData(foundEvents);
                    response.setSuccess(true);

                    db.closeConnection(true);

                    return response;
                } else {

                    response.setMessage("Error: Invalid authtoken.");
                    response.setSuccess(false);

                    db.closeConnection(false);

                    return response;
                }
            } else {

                response.setMessage("Error: Invalid authtoken.");
                response.setSuccess(false);

                db.closeConnection(false);

                return response;
            }
        } catch (DataAccessException e) {

            db.closeConnection(false);

            response.setMessage("Error: Internal server error.");
            response.setSuccess(false);
            return response;
        }
    }
}
