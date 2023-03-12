package dao;

import model.Event;

import java.sql.*;
import java.util.ArrayList;

/**
 * Interfaces with the Event table in the database
 */
public class EventDAO {

    /**
     * database connection
     */
    private final Connection conn;

    /**
     * Creates an EventDAO object
     *
     * @param conn the database connection
     */
    public EventDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Inserts the data from an Event object into the database
     *
     * @param event the Event object
     * @throws DataAccessException
     */
    public void insert(Event event) throws DataAccessException {

        String sql = "INSERT INTO Event (eventID, associatedUsername, personID, latitude, longitude, " +
                "country, city, eventType, year) VALUES(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, event.getEventID());
            stmt.setString(2, event.getAssociatedUsername());
            stmt.setString(3, event.getPersonID());
            stmt.setFloat(4, event.getLatitude());
            stmt.setFloat(5, event.getLongitude());
            stmt.setString(6, event.getCountry());
            stmt.setString(7, event.getCity());
            stmt.setString(8, event.getEventType());
            stmt.setInt(9, event.getYear());

            stmt.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting an event into the database");
        }
    }

    /**
     * Finds the database entry associated with the eventID
     *
     * @param eventID the event's identification
     * @return an Event object containing the found data
     * @throws DataAccessException
     */
    public Event find(String eventID) throws DataAccessException {

        Event event;
        ResultSet rs;
        String sql = "SELECT * FROM Event WHERE eventID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, eventID);
            rs = stmt.executeQuery();
            if (rs.next()) {

                event = new Event(rs.getString("eventID"), rs.getString("associatedUsername"),
                        rs.getString("personID"), rs.getFloat("latitude"), rs.getFloat("longitude"),
                        rs.getString("country"), rs.getString("city"), rs.getString("eventType"),
                        rs.getInt("year"));
                return event;
            } else {

                return null;
            }
        } catch (SQLException e) {

            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an event in the database");
        }

    }

    /**
     * Clears the Event table from the database
     *
     * @throws DataAccessException
     */
    public void clear() throws DataAccessException {

        String sql = "DELETE FROM Event";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the event table");
        }
    }

    /**
     * Finds all the events associated with a certain user
     *
     * @param username handle used to identify the user
     * @return an array of Event objects
     */
    public Event[] findUserEvents(String username) throws DataAccessException {

        ArrayList<Event> ListOfEvents = new ArrayList<>();
        Event event;
        ResultSet rs;
        String sql = "SELECT * FROM Event WHERE associatedUsername = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while (rs.next()) {

                event = new Event(rs.getString("eventID"), rs.getString("associatedUsername"),
                        rs.getString("personID"), rs.getFloat("latitude"), rs.getFloat("longitude"),
                        rs.getString("country"), rs.getString("city"), rs.getString("eventType"),
                        rs.getInt("year"));
                ListOfEvents.add(event);
            }

            Event[] events = new Event[ListOfEvents.size()];
            events = ListOfEvents.toArray(events);
            return events;
        } catch (SQLException e) {

            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an event in the database");
        }
    }

    /**
     * Removes all the events associated with a certain user
     *
     * @param username handle used to identify the user
     */
    public void clearUserEvents(String username) throws DataAccessException {

        String sql = "DELETE FROM Event WHERE associatedUsername = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an event in the database");
        }
    }
}
