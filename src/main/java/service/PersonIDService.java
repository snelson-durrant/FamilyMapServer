package service;

import dao.*;
import model.AuthToken;
import model.Person;
import model.User;
import response.PersonIDResponse;

/**
 * Returns the single Person object with the specified ID
 */
public class PersonIDService {

    /**
     * Return a single Person object with the specified ID
     *
     * @param authtoken string given for authentication
     * @param personID  unique string used to identify the person
     * @return the associated response object
     */
    public PersonIDResponse personID(String authtoken, String personID) {
        Database db = new Database();
        PersonIDResponse response = new PersonIDResponse();

        try {

            db.openConnection();
            AuthTokenDAO authDAO = new AuthTokenDAO(db.getConnection());
            UserDAO userDAO = new UserDAO(db.getConnection());
            PersonDAO personDAO = new PersonDAO(db.getConnection());

            AuthToken providedAuthtoken = authDAO.find(authtoken);
            if (providedAuthtoken != null) {

                User user = userDAO.find(providedAuthtoken.getUsername());
                if (user != null) {

                    Person foundPerson = personDAO.find(personID);
                    if (foundPerson != null) {

                        if (user.getUsername().equals(foundPerson.getAssociatedUsername())) {

                            response.setAssociatedUsername(foundPerson.getAssociatedUsername());
                            response.setPersonID(foundPerson.getPersonID());
                            response.setFirstName(foundPerson.getFirstName());
                            response.setLastName(foundPerson.getLastName());
                            response.setGender(foundPerson.getGender());
                            response.setFatherID(foundPerson.getFatherID());
                            response.setMotherID(foundPerson.getMotherID());
                            response.setSpouseID(foundPerson.getSpouseID());
                            response.setSuccess(true);

                            db.closeConnection(true);

                            return response;
                        } else {

                            response.setMessage("Error: User can't access info.");
                            response.setSuccess(false);

                            db.closeConnection(true);

                            return response;
                        }
                    }

                    response.setMessage("Error: Person does not exist.");
                    response.setSuccess(false);

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
