package model;

public class LocationArray {

    private Location[] data;

    public LocationArray() {
        data = new Location[750];
    }

    public Location getLocation(int i) {
        return data[i];
    }

}
