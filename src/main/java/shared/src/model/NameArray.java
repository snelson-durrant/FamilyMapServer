package model;

// class used to interpret JSON strings for random generation
public class NameArray {

    private final String[] data;

    public NameArray() {
        data = new String[160];
    }

    public String getName(int i) {
        return data[i];
    }

}
