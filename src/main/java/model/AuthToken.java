package model;

import java.util.Objects;

/**
 * Model class for AuthToken data
 */
public class AuthToken {

    /**
     * string given to the user for authentication
     */
    private String authtoken;
    /**
     * handle used to identify the user
     */
    private String username;

    /**
     * Creates a AuthToken Object
     * @param authtoken string given to the user for authentication
     * @param username handle used to identify the user
     */
    public AuthToken(String authtoken, String username) {
        this.authtoken = authtoken;
        this.username = username;
    }

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

    /**
     * Overrides the equals() function to be true only when all private variables are the same
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthToken authToken = (AuthToken) o;
        return Objects.equals(authtoken, authToken.authtoken) && Objects.equals(username, authToken.username);
    }

}
