package response;

/**
 * Response object returned from the PersonIDService class
 */
public class PersonIDResponse {

    /**
     * handle of the user associated with the referenced person
     */
    private String associatedUsername;
    /**
     * unique string used to identify the referenced person
     */
    private String personID;
    /**
     * first name of the referenced person
     */
    private String firstName;
    /**
     * last name of the referenced person
     */
    private String lastName;
    /**
     * gender of the referenced person
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

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
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
