package service;

import dao.*;
import model.AuthToken;
import model.Person;
import model.User;
import response.PersonResponse;

/**
 * Returns all family members of the current user
 */
public class PersonService {

    /**
     * Return all family member of the current user
     *
     * @param authtoken string given for authentication
     * @return the associated response object
     */
    public PersonResponse person(String authtoken) {

        Database db = new Database();
        PersonResponse response = new PersonResponse();

        try {

            db.openConnection();
            AuthTokenDAO authDAO = new AuthTokenDAO(db.getConnection());
            UserDAO userDAO = new UserDAO(db.getConnection());
            PersonDAO personDAO = new PersonDAO(db.getConnection());

            AuthToken providedAuthtoken = authDAO.find(authtoken);
            if (providedAuthtoken != null) {

                User user = userDAO.find(providedAuthtoken.getUsername());
                if (user != null) {

                    Person[] foundPersons = personDAO.findUserPeople(user.getUsername());
                    response.setData(foundPersons);
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
