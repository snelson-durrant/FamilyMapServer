package model;

import java.util.Objects;

/**
 * Model class for Event data
 */
public class Event {

    /**
     * unique string used to identify the event
     */
    private String eventID;
    /**
     * handle of the user associated with the event
     */
    private String associatedUsername;
    /**
     * unique string used to identify the person
     */
    private String personID;
    /**
     * latitude measurement of event location
     */
    private Float latitude;
    /**
     * longitude measurement of event location
     */
    private Float longitude;
    /**
     * country of event location
     */
    private String country;
    /**
     * city of event location
     */
    private String city;
    /**
     * type of event
     */
    private String eventType;
    /**
     * year in which the event occurred
     */
    private Integer year;

    /**
     * Creates an Event object
     *
     * @param eventID            unique string used to identify the event
     * @param associatedUsername handle of the user associated with the event
     * @param personID           unique string used to identify the person
     * @param latitude           latitude measurement of event location
     * @param longitude          longitude measurement of event location
     * @param country            country of event location
     * @param city               city of event location
     * @param eventType          type of event
     * @param year               year in which the event occurred
     */
    public Event(String eventID, String associatedUsername, String personID, Float latitude, Float longitude,
                 String country, String city, String eventType, Integer year) {

        this.eventID = eventID;
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * Overrides the equals() function to be true only when all private variables are the same
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(eventID, event.eventID) && Objects.equals(associatedUsername, event.associatedUsername) && Objects.equals(personID, event.personID) && Objects.equals(latitude, event.latitude) && Objects.equals(longitude, event.longitude) && Objects.equals(country, event.country) && Objects.equals(city, event.city) && Objects.equals(eventType, event.eventType) && Objects.equals(year, event.year);
    }
}
