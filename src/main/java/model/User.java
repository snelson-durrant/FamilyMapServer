package model;

import java.util.Objects;

/**
 * Model class for User data
 */
public class User {

        /**
         * handle used to identify the user
         */
        private String username;
        /**
         * password used by the user to log in
         */
        private String password;
        /**
         * email account of the user
         */
        private String email;
        /**
         * first name of the user
         */
        private String firstName;
        /**
         * last name of the user
         */
        private String lastName;
        /**
         * gender of the user
         */
        private String gender;
        /**
         * unique string used to identify the person linked to the user
         */
        private String personID;

        /**
         * Creates a User object
         * @param username handle used to identify the user
         * @param password password used by the user to log in
         * @param email email account of the user
         * @param firstName first name of the user
         * @param lastName last name of the user
         * @param gender gender of the user
         * @param personID unique string used to identify the person linked to the user
         */
        public User(String username, String password, String email, String firstName,
                    String lastName, String gender, String personID) {
                this.username = username;
                this.password = password;
                this.email = email;
                this.firstName = firstName;
                this.lastName = lastName;
                this.gender = gender;
                this.personID = personID;
        }

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

        public String getPersonID() {
                return personID;
        }

        public void setPersonID(String personID) {
                this.personID = personID;
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
                User user = (User) o;
                return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(gender, user.gender) && Objects.equals(personID, user.personID);
        }

}
