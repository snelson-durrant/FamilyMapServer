package response;

/**
 * Response object returned from the ClearService, FillService, and LoadService classes
 */
public class ClearResponse {

    /**
     * message reporting what occurred
     */
    private String message;
    /**
     * truth value to indicate if the action was successful
     */
    private boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
