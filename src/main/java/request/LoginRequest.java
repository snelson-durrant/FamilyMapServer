package request;

/**
 * Request object given to the LoginService Class
 */
public class LoginRequest {

    /**
     * handle of the user to be logged in
     */
    private String username;
    /**
     * password of the user to be logged in
     */
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
