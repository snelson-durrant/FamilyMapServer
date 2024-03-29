package request;

import json.JSONObject;

/**
 * Request object given to the RegisterService Class
 */
public class RegisterRequest implements JSONObject {

    /**
     * handle of the user to be registered
     */
    private String username;
    /**
     * password of the user to be registered
     */
    private String password;
    /**
     * email account of the user to be registered
     */
    private String email;
    /**
     * first name of the user to be registered
     */
    private String firstName;
    /**
     * last name of the user to be registered
     */
    private String lastName;
    /**
     * gender of the user to be registered
     */
    private String gender;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
