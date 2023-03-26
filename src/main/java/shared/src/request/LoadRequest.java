package request;

import json.JSONObject;
import model.*;

/**
 * Request object given to the LoadService Class
 */
public class LoadRequest implements JSONObject {

    /**
     * array of User objects to be loaded
     */
    User[] users;
    /**
     * array of Person objects to be loaded
     */
    Person[] persons;
    /**
     * array of Event objects to be loaded
     */
    Event[] events;

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Person[] getPersons() {
        return persons;
    }

    public void setPersons(Person[] persons) {
        this.persons = persons;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }

}
