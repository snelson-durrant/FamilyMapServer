package response;

import model.Event;

/**
 * Response object returned from the EventService class
 */
public class EventResponse {

    /**
     * array of Event objects found by the service
     */
    private Event[] data;
    /**
     * truth value to indicate if the action was successful
     */
    private boolean success;
    /**
     * message reporting what occurred
     */
    private String message;

    public Event[] getData() {
        return data;
    }

    public void setData(Event[] data) {
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
