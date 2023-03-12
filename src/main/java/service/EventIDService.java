package service;

import dao.*;
import model.AuthToken;
import model.Event;
import model.User;
import response.EventIDResponse;

/**
 * Returns the single Event object with the specified ID
 */
public class EventIDService {

    /**
     * Return a single Event object with the specified ID
     *
     * @param authtoken string given for authentication
     * @param eventID   unique string used to identify the event
     * @return the associated response object
     */
    public EventIDResponse eventID(String authtoken, String eventID) {

        Database db = new Database();
        EventIDResponse response = new EventIDResponse();

        try {

            db.openConnection();
            AuthTokenDAO authDAO = new AuthTokenDAO(db.getConnection());
            UserDAO userDAO = new UserDAO(db.getConnection());
            EventDAO eventDAO = new EventDAO(db.getConnection());

            AuthToken providedAuthtoken = authDAO.find(authtoken);
            if (providedAuthtoken != null) {

                User user = userDAO.find(authDAO.find(authtoken).getUsername());
                if (user != null) {

                    Event foundEvent = eventDAO.find(eventID);
                    if (foundEvent != null) {

                        if (user.getUsername().equals(foundEvent.getAssociatedUsername())) {

                            response.setAssociatedUsername(foundEvent.getAssociatedUsername());
                            response.setEventID(foundEvent.getEventID());
                            response.setPersonID(foundEvent.getPersonID());
                            response.setLatitude(foundEvent.getLatitude());
                            response.setLongitude(foundEvent.getLongitude());
                            response.setCountry(foundEvent.getCountry());
                            response.setCity(foundEvent.getCity());
                            response.setEventType(foundEvent.getEventType());
                            response.setYear(foundEvent.getYear());
                            response.setSuccess(true);

                            db.closeConnection(true);

                            return response;
                        } else {

                            response.setMessage("Error: User can't access info.");
                            response.setSuccess(false);

                            db.closeConnection(false);

                            return response;
                        }
                    }

                    response.setMessage("Error: Event does not exist.");
                    response.setSuccess(false);

                    db.closeConnection(false);

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
