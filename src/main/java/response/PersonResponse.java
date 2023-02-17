package response;

import model.Person;

public class PersonResponse {

    private Person[] data;
    private boolean success;
    private String message;

    public PersonResponse(Person[] data, boolean success, String message) {
        this.data = data;
        this.success = success;
        this.message = message;
    }

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
