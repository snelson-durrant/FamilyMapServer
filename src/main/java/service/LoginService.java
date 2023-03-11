package service;

import dao.*;
import model.AuthToken;
import model.User;
import request.LoginRequest;
import response.RegisterResponse;

import java.util.UUID;

/**
 * Logs in the user and returns an authtoken
 */
public class LoginService {

    /**
     * Log in the user and return an authtoken
     * @param loginRequest login credentials
     * @return the associated response object
     */
    public RegisterResponse login(LoginRequest loginRequest) {

        Database db = new Database();
        RegisterResponse response = new RegisterResponse();

        try {

            db.openConnection();
            AuthTokenDAO authDAO = new AuthTokenDAO(db.getConnection());
            UserDAO userDAO = new UserDAO(db.getConnection());

            User user = userDAO.find(loginRequest.getUsername());
            String sessionAuthtoken;

            if (user != null && loginRequest.getPassword().equals(user.getPassword())) {

                sessionAuthtoken = UUID.randomUUID().toString();
                AuthToken sessionObject = new AuthToken(sessionAuthtoken, user.getUsername());
                authDAO.insert(sessionObject);
            } else {

                response.setMessage("Error: Invalid login information.");
                response.setSuccess(false);

                db.closeConnection(true);

                return response;
            }

            db.closeConnection(true);

            response.setAuthtoken(sessionAuthtoken);
            response.setUsername(user.getUsername());
            response.setPersonID(user.getPersonID());
            response.setSuccess(true);
            return response;
        }
        catch (DataAccessException e) {

            db.closeConnection(false);

            response.setMessage("Error: Internal server error.");
            response.setSuccess(false);
            return response;
        }
    }
}
