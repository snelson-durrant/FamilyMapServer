package model;

// class used to interpret JSON strings for random generation
public class Location {

    private String country;
    private String city;
    private float latitude;
    private float longitude;

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

}
