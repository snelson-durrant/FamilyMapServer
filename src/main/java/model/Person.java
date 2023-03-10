package model;

import java.util.Objects;

/**
 * Model class for Person data
 */
public class Person {

    /**
     * unique string used to identify the person
     */
    private String personID;
    /**
     * handle of the user associated with the person
     */
    private String associatedUsername;
    /**
     * first name of the person
     */
    private String firstName;
    /**
     * last name of the person
     */
    private String lastName;
    /**
     * gender of the person
     */
    private String gender;
    /**
     * unique string used to identify the father
     */
    private String fatherID;
    /**
     * unique string used to identify the mother
     */
    private String motherID;
    /**
     * unique string used to identify the spouse
     */
    private String spouseID;

    /**
     * Creates a Person object
     * @param personID unique string used to identify the person
     * @param associatedUsername handle of the user associated with the person
     * @param firstName first name of the person
     * @param lastName last name of the person
     * @param gender gender of the person
     * @param fatherID unique string used to identify the father
     * @param motherID unique string used to identify the mother
     * @param spouseID unique string used to identify the spouse
     */
    public Person(String personID, String associatedUsername, String firstName, String lastName,
                  String gender, String fatherID, String motherID, String spouseID) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }

    public Person(String personID, String associatedUsername, String firstName, String lastName,
                  String gender) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = null;
        this.motherID = null;
        this.spouseID = null;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
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

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
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
        Person person = (Person) o;
        return Objects.equals(personID, person.personID) && Objects.equals(associatedUsername, person.associatedUsername) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(gender, person.gender) && Objects.equals(fatherID, person.fatherID) && Objects.equals(motherID, person.motherID) && Objects.equals(spouseID, person.spouseID);
    }

}
