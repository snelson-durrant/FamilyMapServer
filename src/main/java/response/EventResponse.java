package response;

import model.Event;

public class EventResponse {

    private Event[] data;
    private boolean success;
    private String message;

    public EventResponse(Event[] data, boolean success, String message) {
        this.data = data;
        this.success = success;
        this.message = message;
    }

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
