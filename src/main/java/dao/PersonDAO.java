package dao;

import json.Decoder;
import json.LocationArray;
import json.NameArray;
import model.Event;
import model.Person;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 * Interfaces with the Person table in the database
 */
public class PersonDAO {

    /**
     * database connection
     */
    private final Connection conn;

    private NameArray maleNames;
    private NameArray femaleNames;
    private NameArray lastNames;
    private LocationArray locations;

    /**
     * Creates a PersonDAO object
     * @param conn the database connection
     */
    public PersonDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Inserts the data from a Person object into the database
     * @param person the Person object
     * @throws DataAccessException
     */
    public void insert(Person person) throws DataAccessException {

        String sql = "INSERT INTO Person (personID, associatedUsername, firstName, lastName, " +
                "gender, fatherID, motherID, spouseID) VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getAssociatedUsername());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFatherID());
            stmt.setString(7, person.getMotherID());
            stmt.setString(8, person.getSpouseID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting a person into the database");
        }

    }

    /**
     * Finds the database entry associated with the personID
     * @param personID the person's identification
     * @return an AuthToken object containing the found data
     * @throws DataAccessException
     */
    public Person find(String personID) throws DataAccessException {

        Person person;
        ResultSet rs;
        String sql = "SELECT * FROM Person WHERE personID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, personID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                person = new Person(rs.getString("personID"), rs.getString("associatedUsername"),
                        rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"),
                        rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID"));
                return person;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding a person in the database");
        }

    }

    /**
     * Clears the Person table from the database
     * @throws DataAccessException
     */
    public void clear() throws DataAccessException {

        String sql = "DELETE FROM Person";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing a table in database");
        }

    }

    /**
     * Finds all the people associated with a certain user
     * @param username handle used to identify the user
     * @return an array of People objects
     */
    public Person[] findUserPeople(String username) throws DataAccessException {

        ArrayList<Person> ListOfPeople = new ArrayList<>();
        Person person;
        ResultSet rs;
        String sql = "SELECT * FROM Person WHERE associatedUsername = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while (rs.next()) {
                person = new Person(rs.getString("personID"), rs.getString("associatedUsername"),
                        rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"),
                        rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID"));
                ListOfPeople.add(person);
            }
            Person[] people = new Person[ListOfPeople.size()];
            people = ListOfPeople.toArray(people);
            return people;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding a person in the database");
        }

    }

    /**
     * Removes all the people associated with a certain user
     * @param username handle used to identify the user
     */
    public void clearUserPeople(String username) throws DataAccessException {

        String sql = "DELETE FROM Person WHERE associatedUsername = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding a person in the database");
        }

    }

    public void dataGeneration(User user, int gensLeft) throws DataAccessException {

        this.maleNames = Decoder.decodeNameArray("json/mnames.json");
        this.femaleNames = Decoder.decodeNameArray("json/fnames.json");
        this.lastNames = Decoder.decodeNameArray("json/snames.json");
        this.locations = Decoder.decodeLocationArray("json/locations.json");

        EventDAO eventDAO = new EventDAO(conn);

        Person person = new Person(user.getPersonID(), user.getUsername(), user.getFirstName(),
                user.getLastName(), user.getGender());

        String eventID = UUID.randomUUID().toString();
        Random randomGen = new Random();
        int birthInt = randomGen.nextInt(730);
        int birthYear = 2000;

        Event birthEvent = new Event(eventID, user.getUsername(), user.getPersonID(), locations.getLocation(birthInt).getLatitude(),
                locations.getLocation(birthInt).getLongitude(), locations.getLocation(birthInt).getCountry(),
                locations.getLocation(birthInt).getCity(), "birth", birthYear);
        eventDAO.insert(birthEvent);

        if (gensLeft > 0) {
            String fatherID = UUID.randomUUID().toString();
            String motherID = UUID.randomUUID().toString();
            person.setFatherID(fatherID);
            person.setMotherID(motherID);

            String fatherEventID = UUID.randomUUID().toString();
            String motherEventID = UUID.randomUUID().toString();
            int marrInt = randomGen.nextInt(730);
            int marrYear = randomGen.nextInt(32);
            marrYear = marrYear + 13;

            // TODO FIX OFFSET

            Event fatherMarrEvent = new Event(fatherEventID, user.getUsername(), fatherID, locations.getLocation(marrInt).getLatitude(),
                    locations.getLocation(marrInt).getLongitude(), locations.getLocation(marrInt).getCountry(),
                    locations.getLocation(marrInt).getCity(), "marriage", birthYear + marrYear);
            Event motherMarrEvent = new Event(motherEventID, user.getUsername(), motherID, locations.getLocation(marrInt).getLatitude(),
                    locations.getLocation(marrInt).getLongitude(), locations.getLocation(marrInt).getCountry(),
                    locations.getLocation(marrInt).getCity(), "marriage", birthYear + marrYear);
            eventDAO.insert(fatherMarrEvent);
            eventDAO.insert(motherMarrEvent);

            recDataGeneration(user, gensLeft - 1, "m", fatherID, motherID, birthYear);
            recDataGeneration(user, gensLeft - 1, "f", motherID, fatherID, birthYear);
        }

        this.insert(person);

    }

    private void recDataGeneration (User user, int gensLeft, String gender, String thisID, String spouseID, int birthYear) throws DataAccessException {

        Person person;
        Random randomGen = new Random();
        if (gender.equals("m")) {
            int maleInt = randomGen.nextInt(140);
            int lastInt = randomGen.nextInt(150);
            person = new Person(thisID, user.getUsername(), maleNames.getName(maleInt), lastNames.getName(lastInt), "m");
            person.setSpouseID(spouseID);
        } else {
            int femaleInt = randomGen.nextInt(140);
            int lastInt = randomGen.nextInt(150);
            person = new Person(thisID, user.getUsername(), femaleNames.getName(femaleInt), lastNames.getName(lastInt), "f");
            person.setSpouseID(spouseID);
        }
        // generate birth and death events

        if (gensLeft > 0) {
            String fatherID = UUID.randomUUID().toString();
            String motherID = UUID.randomUUID().toString();
            person.setFatherID(fatherID);
            person.setMotherID(motherID);
            // and marriage?
            recDataGeneration(user, gensLeft - 1, "m", fatherID, motherID, 0);
            recDataGeneration(user, gensLeft - 1, "f", motherID, fatherID, 0);
        }

        this.insert(person);

    }

}
