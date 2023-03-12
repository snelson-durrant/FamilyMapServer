package service;

import dao.*;
import model.AuthToken;
import model.User;
import request.RegisterRequest;
import response.RegisterResponse;

import java.util.UUID;

/**
 * Creates a new account, generates data, logs the user in, and returns an authtoken
 */
public class RegisterService {

    /**
     * Create a new account, generate data, log the user in, and return an authtoken
     *
     * @param registerRequest new login credentials
     * @return the associated response object
     */
    public RegisterResponse register(RegisterRequest registerRequest) {

        Database db = new Database();
        RegisterResponse response = new RegisterResponse();

        try {

            db.openConnection();
            AuthTokenDAO authDAO = new AuthTokenDAO(db.getConnection());
            UserDAO userDAO = new UserDAO(db.getConnection());

            User existingUser = userDAO.find(registerRequest.getUsername());

            if (existingUser == null) {

                String userPersonID = UUID.randomUUID().toString();
                User newUser = new User(registerRequest.getUsername(), registerRequest.getPassword(),
                        registerRequest.getEmail(), registerRequest.getFirstName(), registerRequest.getLastName(),
                        registerRequest.getGender(), userPersonID);
                userDAO.insert(newUser);

                String sessionAuthtoken = UUID.randomUUID().toString();
                AuthToken sessionObject = new AuthToken(sessionAuthtoken, newUser.getUsername());
                authDAO.insert(sessionObject);

                PersonDAO genPersonDAO = new PersonDAO(db.getConnection());
                genPersonDAO.dataGeneration(newUser, 4);

                response.setAuthtoken(sessionAuthtoken);
                response.setUsername(newUser.getUsername());
                response.setPersonID(newUser.getPersonID());
                response.setSuccess(true);

                db.closeConnection(true);

                return response;
            } else {

                response.setMessage("Error: User already exists.");
                response.setSuccess(false);

                db.closeConnection(true);

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
