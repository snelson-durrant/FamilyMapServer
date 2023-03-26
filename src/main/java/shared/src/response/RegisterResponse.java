package response;

import json.JSONObject;

/**
 * Response object returned from the RegisterService and LoginService classes
 */
public class RegisterResponse implements JSONObject {

    /**
     * string given to the user for authentication
     */
    private String authtoken;
    /**
     * handle of the referenced user
     */
    private String username;
    /**
     * unique string of the person linked to the referenced user
     */
    private String personID;
    /**
     * truth value to indicate if the action was successful
     */
    private boolean success;
    /**
     * message reporting what occurred
     */
    private String message;

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
