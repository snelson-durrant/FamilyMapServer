package model;

// class used to interpret JSON strings for random generation
public class LocationArray {

    private final Location[] data;

    public LocationArray() {
        data = new Location[750];
    }

    public Location getLocation(int i) {
        return data[i];
    }

}
