package response;

import json.JSONObject;

/**
 * Response object returned from the EventIDService class
 */
public class EventIDResponse implements JSONObject {

    /**
     * handle of the user associated with referenced event
     */
    private String associatedUsername;
    /**
     * unique string used to identify referenced event
     */
    private String eventID;
    /**
     * unique string used to identify the linked person
     */
    private String personID;
    /**
     * latitude measurement of referenced event location
     */
    private float latitude;
    /**
     * longitude measurement of referenced event location
     */
    private float longitude;
    /**
     * country of referenced event location
     */
    private String country;
    /**
     * city of referenced event location
     */
    private String city;
    /**
     * type of referenced event
     */
    private String eventType;
    /**
     * year in which referenced event occurred
     */
    private int year;
    /**
     * truth value to indicate if the action was successful
     */
    private boolean success;
    /**
     * message reporting what occurred
     */
    private String message;

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
