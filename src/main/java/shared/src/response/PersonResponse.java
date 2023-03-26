package response;

import json.JSONObject;
import model.Person;

/**
 * Response object returned from the PersonService class
 */
public class PersonResponse implements JSONObject {

    /**
     * array of Person objects found by the service
     */
    private Person[] data;
    /**
     * truth value to indicate if the action was successful
     */
    private boolean success;
    /**
     * message reporting what occurred
     */
    private String message;

    public Person[] getData() {
        return data;
    }

    public void setData(Person[] data) {
        this.data = data;
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
